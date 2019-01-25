package ch.epfl.xblast.server;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import ch.epfl.xblast.Cell;
import ch.epfl.xblast.PlayerID;

/**
 * 
 * @author Antonio Morais (260428)
 * @author Damian Dudzicz (257839)
 *
 */
public final class Level {
    private final BoardPainter boardPainter;
    private final GameState initialGameState;

    // the default level of the game
    public final static Level DEFAULT_LEVEL = new Level(defaultBoardPainter(),
            defaultGameState());
    // the number of lives of each player
    public final static int DEFAULT_NUMBER_OF_LIVES = 3;
    // the initial range of each player
    public final static int DEFAULT_NUMBER_OF_BLASTS = 3;
    // the initial number of bombs that a player can drop at the same time
    public final static int DEFAULT_NUMBER_OF_BOMBS = 2;

    /**
     * The constructor of the Level given a boardPainter and a GameStatec if the
     * parameters are not null.
     * 
     * @param boardPainter
     *            the boardPainter that gives the graphical aspect of the level
     * @param gameState
     *            the GameState at a given tick
     */
    public Level(BoardPainter boardPainter, GameState gameState) {
        this.boardPainter = Objects.requireNonNull(boardPainter);
        initialGameState = Objects.requireNonNull(gameState);
    }

    /**
     * Returns the boardPainter of the level.
     * 
     * @return boardPainter of the level.
     */
    public BoardPainter boardPainter() {
        return boardPainter;
    }

    /**
     * Returns the gameState of the level.
     * 
     * @return gameState of the level.
     */
    public GameState gameState() {
        return initialGameState;
    }

    /**
     * Returns a default BoardPainter.
     * 
     * @return default BoardPainte.
     */
    private static BoardPainter defaultBoardPainter() {
        Map<Block, BlockImage> mapOfBlocks = new HashMap<>();
        mapOfBlocks.put(Block.FREE, BlockImage.IRON_FLOOR);
        mapOfBlocks.put(Block.INDESTRUCTIBLE_WALL, BlockImage.DARK_BLOCK);
        mapOfBlocks.put(Block.DESTRUCTIBLE_WALL, BlockImage.EXTRA);
        mapOfBlocks.put(Block.CRUMBLING_WALL, BlockImage.EXTRA_O);
        mapOfBlocks.put(Block.BONUS_BOMB, BlockImage.BONUS_BOMB);
        mapOfBlocks.put(Block.BONUS_RANGE, BlockImage.BONUS_RANGE);

        return new BoardPainter(mapOfBlocks, BlockImage.IRON_FLOOR_S);
    }

    /**
     * Returns a defaultGameState corresponding to the initial GameState
     * presented in the examples of the project.
     * 
     * @return defaultGameState
     */
    private static GameState defaultGameState() {
        Block __ = Block.FREE;
        Block XX = Block.INDESTRUCTIBLE_WALL;
        Block xx = Block.DESTRUCTIBLE_WALL;
        Board board = Board.ofQuadrantNWBlocksWalled(
                Arrays.asList(Arrays.asList(__, __, __, __, __, xx, __),
                        Arrays.asList(__, XX, xx, XX, xx, XX, xx),
                        Arrays.asList(__, xx, __, __, __, xx, __),
                        Arrays.asList(xx, XX, __, XX, XX, XX, XX),
                        Arrays.asList(__, xx, __, xx, __, __, __),
                        Arrays.asList(xx, XX, xx, XX, xx, XX, __)));

        List<Player> players = Arrays.asList(
                new Player(PlayerID.PLAYER_1, DEFAULT_NUMBER_OF_LIVES,
                        new Cell(1, 1), DEFAULT_NUMBER_OF_BOMBS,
                        DEFAULT_NUMBER_OF_BLASTS),
                new Player(PlayerID.PLAYER_2, DEFAULT_NUMBER_OF_LIVES,
                        new Cell(13, 1), DEFAULT_NUMBER_OF_BOMBS,
                        DEFAULT_NUMBER_OF_BLASTS),
                new Player(PlayerID.PLAYER_3, DEFAULT_NUMBER_OF_LIVES,
                        new Cell(13, 11), DEFAULT_NUMBER_OF_BOMBS,
                        DEFAULT_NUMBER_OF_BLASTS),
                new Player(PlayerID.PLAYER_4, DEFAULT_NUMBER_OF_LIVES,
                        new Cell(1, 11), DEFAULT_NUMBER_OF_BOMBS,
                        DEFAULT_NUMBER_OF_BLASTS));

        return new GameState(board, players);
    }
}