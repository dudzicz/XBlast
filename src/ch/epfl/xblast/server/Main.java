package ch.epfl.xblast.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.StandardProtocolFamily;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import ch.epfl.xblast.ArgumentChecker;
import ch.epfl.xblast.Direction;
import ch.epfl.xblast.PlayerAction;
import ch.epfl.xblast.PlayerID;
import ch.epfl.xblast.Time;

/**
 * 
 * @author Antonio Morais (260428)
 * @author Damian Dudzicz (257839)
 *
 */
public final class Main {
    // the number of byte to attribute a PLAYER_ID to a client
    private static final int BYTE_PLAYER_ID = 1;
    // the port used by the client and the server to communicate
    private static final int PORT = 2016;
    // the map associating to every SocketAdress ( of a client ) a PlayerID
    private static Map<SocketAddress, PlayerID> connectingClients;
    // placeholder used to keep the 1st position in the buffer for the playerID
    private static final int PLACEHOLDER = 0;
    // number of byte used to represent a playeraction
    private static final int BYTE_PLAYER_ACTION = 1;

    /**
     * The main method of the server managing all the GameState. It receives
     * requests to connect from the client until the number of connected clients
     * is equal to the number of wanted players ( @see args ). It collects all
     * the input informations from the clients (bomb dropping, change
     * direction). It compute the next GameState and send it to all the clients.
     * Eventually, if there's a winner print it on the console.
     * 
     * @param args
     *            number of players for the game
     * @throws IOException
     *             if an I/O error occurs
     * @throws InterruptedException
     *             if any thread has interrupted the current thread
     */
    public static void main(String[] args)
            throws IOException, InterruptedException {
        // handle the number of players / clients
        int numberOfClients = args.length == 0 ? PlayerID.values().length
                : Integer.parseInt(args[0]);
        if (ArgumentChecker
                .requireNonNegative(numberOfClients) > PlayerID.values().length)
            numberOfClients = PlayerID.values().length;
        connectingClients = new HashMap<>();
        ByteBuffer bufferNextMove;
        Map<PlayerID, Optional<Direction>> speedChangeEvents = new HashMap<>();
        Set<PlayerID> bombDropEvents = new HashSet<>();

        try (DatagramChannel channel = DatagramChannel
                .open(StandardProtocolFamily.INET);) {
            channel.bind(new InetSocketAddress(PORT));
            channel.configureBlocking(true);

            // managing of the connexions of the clients to the server
            playerIdAttribution(numberOfClients, channel);

            channel.configureBlocking(false);

            GameState gameState = Level.DEFAULT_LEVEL.gameState();
            BoardPainter boardPainter = Level.DEFAULT_LEVEL.boardPainter();

            final long initialTime = System.nanoTime();

            // global managing of the game
            bufferNextMove = ByteBuffer.allocate(BYTE_PLAYER_ACTION);
            while (!gameState.isGameOver()) {

                sendGamestate(gameState, boardPainter, channel);

                sleepTime(initialTime, gameState);

                // creation of the next Gamestate
                bufferNextMove.clear();
                speedChangeEvents.clear();
                bombDropEvents.clear();
                eventHandler(bufferNextMove, speedChangeEvents, bombDropEvents,
                        channel);
                gameState = gameState.next(speedChangeEvents, bombDropEvents);
            }

            // printing of the possible winner
            if (gameState.winner().isPresent()) {
                System.out
                        .println("The winner is : " + gameState.winner().get());
            } else {
                System.out.println("There is no winner");
            }
        }
    }

    /**
     * The method handles the connection of the clients with the server by
     * waiting for the required number of players and attributing to every new
     * client a different PlayerID.
     * 
     * @param numberOfClients
     *            number of wanted players to play
     * @param channel
     *            channel from which we receive the request of the players to
     *            connect to the
     * @throws IOException
     */
    private static void playerIdAttribution(int numberOfClients,
            DatagramChannel channel) throws IOException {
        ByteBuffer bufferPlayer = ByteBuffer.allocate(BYTE_PLAYER_ID);
        while (connectingClients.size() != numberOfClients) {
            bufferPlayer.clear();
            SocketAddress clientAdress = channel.receive(bufferPlayer);
            bufferPlayer.rewind();
            if (bufferPlayer.hasRemaining()
                    && bufferPlayer.get() == PlayerAction.JOIN_GAME.ordinal()) {
                connectingClients.putIfAbsent(clientAdress,
                        PlayerID.values()[connectingClients.size()]);
            }
        }
    }

    /**
     * The method serialize the GameState and sends it to every client.
     * 
     * @param gameState
     *            the GameState to serialize
     * @param boardPainter
     *            the required boardPainter to use with the method serialize of
     *            GameStateSerializer
     * @param channel
     *            the channel on which the bytes of the serialized GameState are
     *            sent
     * @throws IOException
     *             If some other I/O error occurs
     * 
     */
    private static void sendGamestate(GameState gameState,
            BoardPainter boardPainter, DatagramChannel channel)
                    throws IOException {

        // serialization
        List<Byte> serializedGameState = GameStateSerializer
                .serialize(boardPainter, gameState);

        ByteBuffer bufferGameState = ByteBuffer
                .allocate(serializedGameState.size() + BYTE_PLAYER_ID);
        // placeholder for the PlayerID in the ByteBuffer to send
        bufferGameState.put((byte) PLACEHOLDER);
        for (Byte b : serializedGameState) {
            bufferGameState.put(b);
        }

        // sending of the GameState to every client
        for (Map.Entry<SocketAddress, PlayerID> cltAdress : connectingClients
                .entrySet()) {
            bufferGameState.put(0, (byte) cltAdress.getValue().ordinal());
            bufferGameState.rewind();
            channel.send(bufferGameState, cltAdress.getKey());
        }
    }

    /**
     * The method managing the sleeping time required in the functioning process
     * of the server and sleeps this duration if non negative.
     * 
     * @param initialTime
     *            the initial time of the server in nanoseconds
     * @param gameState
     *            the GameState in order to access the ticks
     * @throws InterruptedException
     *             if any thread has interrupted the current thread
     */
    private static void sleepTime(long initialTime, GameState gameState)
            throws InterruptedException {
        long actualTime = System.nanoTime();
        long timeNextTick = initialTime + (long) (gameState.ticks() + 1)
                * Ticks.TICK_NANOSECOND_DURATION;
        long timeSleep = timeNextTick - actualTime;

        if (timeSleep > 0) {
            Thread.sleep(timeSleep / Time.US_PER_S,
                    (int) timeSleep % Time.US_PER_S);
        }
    }

    /**
     * The methods handle the events received from the clients.
     * 
     * @param bufferNextMove
     *            the buffer ( byte ) received from the client corresponding to
     *            an event
     * @param speedChangeEvents
     *            the map of speedChangeEvents used in the main method
     * @param bombDropEvents
     *            the set of bombDropEvents used in the main method
     * @param channel
     *            the channel used to receive the events from the clients
     * @throws IOException
     *             If some other I/O error occurs
     */
    private static void eventHandler(ByteBuffer bufferNextMove,
            Map<PlayerID, Optional<Direction>> speedChangeEvents,
            Set<PlayerID> bombDropEvents, DatagramChannel channel)
                    throws IOException {
        SocketAddress clientAdress = channel.receive(bufferNextMove);
        while (clientAdress != null) {
            bufferNextMove.rewind();
            PlayerID playerID = connectingClients.get(clientAdress);
            switch (PlayerAction.values()[bufferNextMove.get()]) {
            case MOVE_N:
                speedChangeEvents.put(playerID, Optional.of(Direction.N));
                break;
            case MOVE_E:
                speedChangeEvents.put(playerID, Optional.of(Direction.E));
                break;
            case MOVE_S:
                speedChangeEvents.put(playerID, Optional.of(Direction.S));
                break;
            case MOVE_W:
                speedChangeEvents.put(playerID, Optional.of(Direction.W));
                break;
            case STOP:
                speedChangeEvents.put(playerID, Optional.empty());
                break;
            case DROP_BOMB:
                bombDropEvents.add(playerID);
                break;
            default:
                break;
            }
            bufferNextMove.clear();

            clientAdress = channel.receive(bufferNextMove);
        }

    }
}
