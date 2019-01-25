package ch.epfl.xblast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import ch.epfl.xblast.server.Block;
import ch.epfl.xblast.server.BlockImage;
import ch.epfl.xblast.server.Board;
import ch.epfl.xblast.server.BoardPainter;
import ch.epfl.xblast.server.GameState;
import ch.epfl.xblast.server.Level;
import ch.epfl.xblast.server.Player;

/**
 * This test tests the class Level.
 * 
 * @author Maire Cedric (259314)
 * @author Délèze Benjamin (259992)
 *
 */

public class LevelTest {

    @Test(expected = NullPointerException.class)
    public void levelConstructorFailTest0() {
        Block __ = Block.FREE;
        Block XX = Block.INDESTRUCTIBLE_WALL;
        Block xx = Block.DESTRUCTIBLE_WALL;

        List<List<Block>> board = Arrays.asList(
                Arrays.asList(__, __, __, __, __, xx, __),
                Arrays.asList(__, XX, xx, XX, xx, XX, xx),
                Arrays.asList(__, xx, __, __, __, xx, __),
                Arrays.asList(xx, XX, __, XX, XX, XX, XX),
                Arrays.asList(__, xx, __, xx, __, __, __),
                Arrays.asList(xx, XX, xx, XX, xx, XX, __));

        List<Player> players = new ArrayList<>();

        players.add(new Player(PlayerID.PLAYER_1, 3, new Cell(1, 1), 3, 3));
        players.add(new Player(PlayerID.PLAYER_2, 3, new Cell(13, 1), 3, 3));
        players.add(new Player(PlayerID.PLAYER_3, 3, new Cell(13, 11), 3, 3));
        players.add(new Player(PlayerID.PLAYER_4, 3, new Cell(1, 11), 3, 3));

        @SuppressWarnings("unused")
        Level level = new Level(null,
                new GameState(Board.ofQuadrantNWBlocksWalled(board), players));
    }

    @Test(expected = NullPointerException.class)
    public void levelConstructorFailTest1() {
        Map<Block, BlockImage> blockToBlockImage = new HashMap<Block, BlockImage>();

        blockToBlockImage.put(Block.FREE, BlockImage.IRON_FLOOR);
        blockToBlockImage.put(Block.INDESTRUCTIBLE_WALL, BlockImage.DARK_BLOCK);
        blockToBlockImage.put(Block.DESTRUCTIBLE_WALL, BlockImage.EXTRA);
        blockToBlockImage.put(Block.CRUMBLING_WALL, BlockImage.EXTRA_O);
        blockToBlockImage.put(Block.BONUS_BOMB, BlockImage.BONUS_BOMB);
        blockToBlockImage.put(Block.BONUS_RANGE, BlockImage.BONUS_RANGE);

        BoardPainter boardPainter = new BoardPainter(blockToBlockImage,
                BlockImage.IRON_FLOOR_S);

        @SuppressWarnings("unused")
        Level level = new Level(boardPainter, null);
    }

    @Test
    public void defaultLevelTest() {
        @SuppressWarnings("unused")
        Level level = Level.DEFAULT_LEVEL;
    }
}