package ch.epfl.xblast.client;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.StandardProtocolFamily;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

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
    // the port used by the client and the server to communicate
    private static final int PORT = 2016;
    // the map associating the corresponding PlayerAction to the key pressed
    private static final Map<Integer, PlayerAction> KEY_MAP = createKeyMap();
    // the maximum size of a serialized GameState
    private static final int MAXIMUM_SIZE = 409;
    // the number of byte required to represent a PlayerAction
    private static final int ACTION_PLAYER_BYTE = 1;
    // the number of byte required to represent a PlayerID
    private static final int PLAYERID_BYTE = 1;
    // the XBlastComponent which paints the GameState
    private static XBlastComponent xBlastComponent;

    /**
     * The main method of the client which at first send periodically requests
     * to the server to join the game. Displays the GameState, handles the input
     * of the physical player and sends it to the server.
     * 
     * @param args
     *            the address of the server
     * @throws IOException
     *             if some I/O error occurs
     * @throws InterruptedException
     *             if any thread has interrupted the current thread
     * @throws InvocationTargetException
     *             if an exception is thrown while running doRun (from the
     *             invokeAndWait method )
     * 
     */
    public static void main(String[] args) throws IOException,
            InterruptedException, InvocationTargetException {
        String hostName = args.length == 0 ? "localhost" : args[0];
        SocketAddress address = new InetSocketAddress(hostName, PORT);
        List<Byte> serializedGameState = new ArrayList<>();

        try (DatagramChannel channel = DatagramChannel
                .open(StandardProtocolFamily.INET);) {

            // Swing thread handling the view and the controller
            SwingUtilities.invokeAndWait(() -> createUI(channel, address));

            ByteBuffer bufferGameState = ByteBuffer
                    .allocate(MAXIMUM_SIZE + PLAYERID_BYTE);

            // Requesting to connect to the server
            channel.configureBlocking(false);
            requestToConnect(channel, address, bufferGameState);

            channel.configureBlocking(true);

            // handle the progress of the game by deserializing the gameState
            gameHandler(bufferGameState, serializedGameState, channel);

        }
    }

    /**
     * Create the map associating to every key pressed number the corresponding
     * player action
     * 
     * @return the map associating to every key pressed number the corresponding
     *         player action
     */
    private static Map<Integer, PlayerAction> createKeyMap() {
        Map<Integer, PlayerAction> kb = new HashMap<>();
        kb.put(KeyEvent.VK_UP, PlayerAction.MOVE_N);
        kb.put(KeyEvent.VK_RIGHT, PlayerAction.MOVE_E);
        kb.put(KeyEvent.VK_DOWN, PlayerAction.MOVE_S);
        kb.put(KeyEvent.VK_LEFT, PlayerAction.MOVE_W);
        kb.put(KeyEvent.VK_SPACE, PlayerAction.DROP_BOMB);
        kb.put(KeyEvent.VK_SHIFT, PlayerAction.STOP);
        return Collections.unmodifiableMap(new HashMap<>(kb));
    }

    /**
     * Create the graphical interface ( the view ) and also the controller which
     * handles the inputs of the physical player and sends them to the server.
     * 
     * @param channel
     *            the channel with which the client communicate with server
     * @param address
     *            the address of the server
     */
    private static void createUI(DatagramChannel channel,
            SocketAddress address) {
        xBlastComponent = new XBlastComponent();

        JFrame j = new JFrame("XBlast 2k16");
        j.add(xBlastComponent, BorderLayout.CENTER);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.pack();
        j.setResizable(false);
        j.setVisible(true);

        Consumer<PlayerAction> cons = p -> {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1);
            byteBuffer.put(0, (byte) p.ordinal());
            try {
                channel.send(byteBuffer, address);
            } catch (Exception e) {
            }
        };

        xBlastComponent.addKeyListener(new KeyboardEventHandler(KEY_MAP, cons));
        xBlastComponent.requestFocusInWindow();
    }

    /**
     * Manage the request of the client to connect to server by sending
     * periodically a request.
     * 
     * @param channel
     *            the channel with which the client and the server communicate
     * @param adress
     *            the address of the server
     * @param bufferGameState
     *            the buffer to send the request to connect to the server
     * @throws IOException
     *             if some other I/O error occurs
     * @throws InterruptedException
     *             if any thread has interrupted the current thread
     */
    private static void requestToConnect(DatagramChannel channel,
            SocketAddress adress, ByteBuffer bufferGameState)
                    throws IOException, InterruptedException {
        ByteBuffer bufferToEnter = ByteBuffer.allocate(ACTION_PLAYER_BYTE);
        do {
            bufferToEnter.put(0, (byte) PlayerAction.JOIN_GAME.ordinal());
            channel.send(bufferToEnter, adress);
            Thread.sleep(Time.MS_PER_S);
        } while (channel.receive(bufferGameState) == null);
    }

    /**
     * The method managing the progress of the game on the side of the client.
     * Receives the bytes corresponding to the serialized GameState from the
     * server and then deserialized it. Handle also the repainting of the
     * GameState on the screen of the client.
     * 
     * @param bufferGameState
     *            the ByteBuffer received from the server corresponding to the
     *            serialized GameState
     * @param serializedGameState
     *            the list in which the bytes of the serialized GameState
     *            received are stocked
     * @param channel
     *            the channel with which the client and the server communicate
     * @throws IOException
     *             if some other I/O error occurs
     */
    private static void gameHandler(ByteBuffer bufferGameState,
            List<Byte> serializedGameState, DatagramChannel channel)
                    throws IOException {
        while (true) {
            GameState gameState;

            // reception of the buffer of the serialized GameState
            bufferGameState.flip();
            PlayerID playerID = PlayerID.values()[bufferGameState.get()];
            while (bufferGameState.hasRemaining()) {
                serializedGameState.add(bufferGameState.get());
            }

            // deserialization of the GameState
            gameState = GameStateDeserializer
                    .deserializeGameState(serializedGameState);

            // painting of the GameState
            xBlastComponent.setGameState(gameState, playerID);

            bufferGameState.clear();
            serializedGameState.clear();
            channel.receive(bufferGameState);
        }
    }

}