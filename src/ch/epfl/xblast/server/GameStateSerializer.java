package ch.epfl.xblast.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.epfl.xblast.Cell;
import ch.epfl.xblast.Direction;
import ch.epfl.xblast.RunLengthEncoder;
import ch.epfl.xblast.Time;

/**
 * 
 * @author Antonio Morais (260428)
 * @author Damian Dudzicz (257839)
 *
 */

public final class GameStateSerializer {
    
    /**
     * GameStateDeserializer is not instanciable.
     */
    private GameStateSerializer() {
    }

    /**
     * We first add all the bytes representing the images of the Board in the
     * spiral order of the cells (in order to have more repetitions). Then we
     * encode it with the method encode and we add the size of this encoded List
     * at the beginning of the list.
     * 
     * @see RunLengthEncoder.encode
     * @param boardPainter
     *            The painter of the Board in order to know which byte use for
     *            every cell.
     * @param board
     *            The board that we want to serialize
     * @return The encoded list of the Board
     */

    private static List<Byte> serializeBoard(BoardPainter boardPainter,
            Board board) {
        List<Byte> boardSerialized = new ArrayList<>();

        for (Cell c : Cell.SPIRAL_ORDER) {
            boardSerialized.add(boardPainter.byteForCell(board, c));
        }
        List<Byte> boardFinished = new ArrayList<>(
                RunLengthEncoder.encode(boardSerialized));
        boardFinished.add(0, (byte) boardFinished.size());
        return boardFinished;
    }

    /**
     * We first check if the Cell is bombed, if this is the case we add the
     * corresponding byte for the Bomb. Next we check if the cell is Free and if
     * she is Blasted, if this is the case we add the corresponding byte for the
     * blastedCell. Otherwise we add a byte that corresponds to an empty image.
     * Then we encode it with the method encode and we add the size of this
     * encoded List at the beginning of the list.
     * 
     * @param gameState
     *            The gameState that we want to serialize (that contains the
     *            bombedCells and the BlastedCells that we want to encode)
     * @return the encoded List of the Bombs and Explosions.
     */

    private static List<Byte> serializeBombsAndExplosions(GameState gameState) {
        List<Byte> bombsAndExplosionsSerialized = new ArrayList<>();
        for (Cell c : Cell.ROW_MAJOR_ORDER) {
            if (gameState.bombedCells().containsKey(c))
                bombsAndExplosionsSerialized.add(ExplosionPainter
                        .byteForBomb(gameState.bombedCells().get(c)));
            else if (gameState.board().blockAt(c).isFree()
                    && gameState.blastedCells().contains(c)) {
                boolean blastedNorth = gameState.blastedCells()
                        .contains(c.neighbor(Direction.N));
                boolean blastedEast = gameState.blastedCells()
                        .contains(c.neighbor(Direction.E));
                boolean blastedSouth = gameState.blastedCells()
                        .contains(c.neighbor(Direction.S));
                boolean blastedWest = gameState.blastedCells()
                        .contains(c.neighbor(Direction.W));
                bombsAndExplosionsSerialized.add(ExplosionPainter.byteForBlast(
                        blastedNorth, blastedEast, blastedSouth, blastedWest));
            } else {
                bombsAndExplosionsSerialized
                        .add(ExplosionPainter.BYTE_FOR_EMPTY);
            }
        }
        List<Byte> bombsAndExplosionsFinished = new ArrayList<>(
                RunLengthEncoder.encode(bombsAndExplosionsSerialized));
        bombsAndExplosionsFinished.add(0,
                (byte) bombsAndExplosionsFinished.size());
        return bombsAndExplosionsFinished;
    }

    /**
     * For every player from the GameState we serialized his lifes, his position
     * (x and y) and the byte corresponding to the image.
     * 
     * @param gameState
     *            The gamestate that contains the players we want to serialize
     * @return the serialized List of the Players.
     */

    private static List<Byte> serializePlayers(GameState gameState) {
        List<Byte> playersSerialized = new ArrayList<>();

        for (Player p : gameState.players()) {
            playersSerialized.add((byte) p.lives());
            playersSerialized.add((byte) p.position().x());
            playersSerialized.add((byte) p.position().y());
            playersSerialized
                    .add(PlayerPainter.byteForPlayer(gameState.ticks(), p));
        }
        return playersSerialized;
    }

    /**
     * This method serialize the gameState using auxiliary methods that
     * serialize each part of the Board.
     * 
     * @param boardPainter
     *            The BoardPainter used to paint the Blocks of the Board.
     * @param gameState
     *            The GameState that we want to serialize.
     * @return The serialized List of Byte representing the GameState
     */
    
    public static List<Byte> serialize(BoardPainter boardPainter,
            GameState gameState) {
        List<Byte> serializeGamestate = new ArrayList<>();
    
        serializeGamestate
                .addAll(serializeBoard(boardPainter, gameState.board()));
    
        serializeGamestate.addAll(serializeBombsAndExplosions(gameState));
    
        serializeGamestate.addAll(serializePlayers(gameState));
    
        serializeGamestate
                .add((byte) Math.ceil(gameState.remainingTime() / Time.TOTAL_MINUTES));
    
        return Collections
                .unmodifiableList(new ArrayList<>(serializeGamestate));
    }
}
