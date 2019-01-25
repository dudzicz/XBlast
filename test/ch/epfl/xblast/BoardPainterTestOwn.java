package ch.epfl.xblast;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import ch.epfl.xblast.server.Block;
import ch.epfl.xblast.server.BlockImage;
import ch.epfl.xblast.server.Board;
import ch.epfl.xblast.server.BoardPainter;

/**
 * This test tests the class BoardPainter.
 * 
 * @author Maire Cedric (259314)
 * @author Délèze Benjamin (259992)
 *
 */

public class BoardPainterTestOwn {

    @Test(expected = NullPointerException.class)
    public void constructorblockToBlockImageFail() {
        new BoardPainter(null, BlockImage.IRON_FLOOR);
    }

    @Test(expected = NullPointerException.class)
    public void constructorshadowedBlockFail() {
        new BoardPainter(new HashMap<>(), null);
    }

    @Test
    public void byteForCellTest() {
        // Creation of a board
        final int COORD_X1 = 0; // You can change!
        final int COORD_X2 = 1; // You can change!
        final int COORD_X3 = 2; // You can change!
        final int COORD_X4 = 3; // You can change!
        final int COORD_X5 = 4; // You can change!
        final int COORD_X6 = 5; // You can change!
        final int COORD_X7 = 6; // You can change!
        final int COORD_Y = 0; // !!! DO NOT CHANGE !!!

        Cell temporaryCell1 = new Cell(COORD_X1, COORD_Y);
        Cell temporaryCell2 = new Cell(COORD_X2, COORD_Y);
        Cell temporaryCell3 = new Cell(COORD_X3, COORD_Y);
        Cell temporaryCell4 = new Cell(COORD_X4, COORD_Y);
        Cell temporaryCell5 = new Cell(COORD_X5, COORD_Y);
        Cell temporaryCell6 = new Cell(COORD_X6, COORD_Y);
        Cell temporaryCell7 = new Cell(COORD_X7, COORD_Y);

        List<List<Block>> temporaryList0 = new ArrayList<List<Block>>();

        temporaryList0.add(new ArrayList<Block>());

        for (int i = 0; i < Cell.COLUMNS; i++) {
            temporaryList0.get(COORD_Y).add(Block.INDESTRUCTIBLE_WALL);
        }

        temporaryList0.get(COORD_Y).set(COORD_X1, Block.INDESTRUCTIBLE_WALL);
        temporaryList0.get(COORD_Y).set(COORD_X2, Block.FREE);
        temporaryList0.get(COORD_Y).set(COORD_X3, Block.FREE);
        temporaryList0.get(COORD_Y).set(COORD_X4, Block.DESTRUCTIBLE_WALL);
        temporaryList0.get(COORD_Y).set(COORD_X5, Block.CRUMBLING_WALL);
        temporaryList0.get(COORD_Y).set(COORD_X6, Block.BONUS_BOMB);
        temporaryList0.get(COORD_Y).set(COORD_X7, Block.BONUS_RANGE);

        for (int i = 1; i < Cell.ROWS; i++) {
            temporaryList0.add(Collections.nCopies(Cell.COLUMNS,
                    Block.INDESTRUCTIBLE_WALL));
        }

        Board temporaryBoard = Board.ofRows(temporaryList0);

        // Creation of a BoardPainter
        Map<Block, BlockImage> m = new HashMap<Block, BlockImage>();
        m.put(Block.FREE, BlockImage.IRON_FLOOR);
        m.put(Block.INDESTRUCTIBLE_WALL, BlockImage.DARK_BLOCK);
        m.put(Block.DESTRUCTIBLE_WALL, BlockImage.EXTRA);
        m.put(Block.CRUMBLING_WALL, BlockImage.EXTRA_O);
        m.put(Block.BONUS_BOMB, BlockImage.BONUS_BOMB);
        m.put(Block.BONUS_RANGE, BlockImage.BONUS_RANGE);

        BoardPainter b = new BoardPainter(m, BlockImage.IRON_FLOOR_S);

        assertEquals(b.byteForCell(temporaryBoard, temporaryCell1), (byte) 2);
        assertEquals(b.byteForCell(temporaryBoard, temporaryCell2), (byte) 1);
        assertEquals(b.byteForCell(temporaryBoard, temporaryCell3), (byte) 0);
        assertEquals(b.byteForCell(temporaryBoard, temporaryCell4), (byte) 3);
        assertEquals(b.byteForCell(temporaryBoard, temporaryCell5), (byte) 4);
        assertEquals(b.byteForCell(temporaryBoard, temporaryCell6), (byte) 5);
        assertEquals(b.byteForCell(temporaryBoard, temporaryCell7), (byte) 6);
    }
}