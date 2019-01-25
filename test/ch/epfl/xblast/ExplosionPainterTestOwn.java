package ch.epfl.xblast;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ch.epfl.xblast.server.Bomb;
import ch.epfl.xblast.server.ExplosionPainter;

/**
 * This test tests the class ExplosionPainter.
 * 
 * @author Maire Cedric (259314)
 * @author Délèze Benjamin (259992)
 *
 */

public class ExplosionPainterTestOwn {

    @Test
    public void byteForBombTest() {
        Bomb b1 = new Bomb(PlayerID.PLAYER_1, new Cell(0, 0), 6, 4);
        Bomb b2 = new Bomb(PlayerID.PLAYER_2, new Cell(0, 0), 8, 5);

        assertEquals(ExplosionPainter.byteForBomb(b1), (byte) 20);
        assertEquals(ExplosionPainter.byteForBomb(b2), (byte) 21);
    }

    @Test
    public void byteForBlastTest() {
        assertEquals(ExplosionPainter.byteForBlast(false, false, false, false),
                (byte) 0);
        assertEquals(ExplosionPainter.byteForBlast(false, false, false, true),
                (byte) 1);
        assertEquals(ExplosionPainter.byteForBlast(false, false, true, false),
                (byte) 2);
        assertEquals(ExplosionPainter.byteForBlast(false, false, true, true),
                (byte) 3);
        assertEquals(ExplosionPainter.byteForBlast(false, true, false, false),
                (byte) 4);
        assertEquals(ExplosionPainter.byteForBlast(false, true, false, true),
                (byte) 5);
        assertEquals(ExplosionPainter.byteForBlast(false, true, true, false),
                (byte) 6);
        assertEquals(ExplosionPainter.byteForBlast(false, true, true, true),
                (byte) 7);
        assertEquals(ExplosionPainter.byteForBlast(true, false, false, false),
                (byte) 8);
        assertEquals(ExplosionPainter.byteForBlast(true, false, false, true),
                (byte) 9);
        assertEquals(ExplosionPainter.byteForBlast(true, false, true, false),
                (byte) 10);
        assertEquals(ExplosionPainter.byteForBlast(true, false, true, true),
                (byte) 11);
        assertEquals(ExplosionPainter.byteForBlast(true, true, false, false),
                (byte) 12);
        assertEquals(ExplosionPainter.byteForBlast(true, true, false, true),
                (byte) 13);
        assertEquals(ExplosionPainter.byteForBlast(true, true, true, false),
                (byte) 14);
        assertEquals(ExplosionPainter.byteForBlast(true, true, true, true),
                (byte) 15);
    }
}