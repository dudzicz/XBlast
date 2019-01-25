package ch.epfl.xblast;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Random;

import org.junit.Test;

import ch.epfl.cs108.Sq;
import ch.epfl.xblast.Cell;
import ch.epfl.xblast.PlayerID;
import ch.epfl.xblast.server.Bomb;
import ch.epfl.xblast.server.Ticks;

/**
 * This test tests the class Bomb.
 * 
 * @author Maire Cedric (259314)
 * @author Délèze Benjamin (259992)
 *
 */

public class BombTestNous {

    @SuppressWarnings("unused")
    @Test(expected = NullPointerException.class)
    public void bombConstructorFailOwnerID() {
        Bomb bomb = new Bomb(null, new Cell(0, 0), Ticks.BOMB_FUSE_TICKS, 5);
    }

    @SuppressWarnings("unused")
    @Test(expected = NullPointerException.class)
    public void bombConstructorFailPosition() {
        Bomb bomb = new Bomb(PlayerID.PLAYER_1, null, Ticks.BOMB_FUSE_TICKS, 5);
    }

    @SuppressWarnings("unused")
    @Test(expected = NullPointerException.class)
    public void bombConstructorFailFuseLengths() {
        Bomb bomb = new Bomb(PlayerID.PLAYER_1, new Cell(0, 0), null, 5);
    }

    @SuppressWarnings("unused")
    @Test(expected = IllegalArgumentException.class)
    public void bombConstructorFailFuseLength() {
        Bomb bomb = new Bomb(PlayerID.PLAYER_1, new Cell(0, 0), 0, 5);
    }

    @SuppressWarnings("unused")
    @Test(expected = IllegalArgumentException.class)
    public void bombConstructorFailRange() {
        Bomb bomb = new Bomb(PlayerID.PLAYER_1, new Cell(0, 0),
                Ticks.BOMB_FUSE_TICKS, -1);
    }

    @SuppressWarnings("unused")
    @Test
    public void bombConstructorAchieve() {
        Bomb bomb = new Bomb(PlayerID.PLAYER_1, new Cell(0, 0),
                Ticks.BOMB_FUSE_TICKS, 5);
    }

    @Test
    public void ownerIdGetterTest() {
        Random random = new Random();
        int randomNumber = random.nextInt(4);
        PlayerID[] tabOfPlayerID = { PlayerID.PLAYER_1, PlayerID.PLAYER_2,
                PlayerID.PLAYER_3, PlayerID.PLAYER_4 };

        assertEquals(tabOfPlayerID[randomNumber],
                (new Bomb(tabOfPlayerID[randomNumber], new Cell(0, 0),
                        Ticks.BOMB_FUSE_TICKS, 5)).ownerId());
    }

    @Test
    public void positionGetterTest() {
        Random random = new Random();
        int randomNumber0 = random.nextInt(15);
        int randomNumber1 = random.nextInt(13);

        assertEquals(new Cell(randomNumber0, randomNumber1),
                (new Bomb(PlayerID.PLAYER_1,
                        new Cell(randomNumber0, randomNumber1),
                        Ticks.BOMB_FUSE_TICKS, 5)).position());
    }

    @Test
    public void fuseLength_s_GetterTest() {
        if (Sq.iterate(Ticks.BOMB_FUSE_TICKS, u -> u - 1)
                .limit(Ticks.BOMB_FUSE_TICKS)
                .head() == (new Bomb(PlayerID.PLAYER_1, new Cell(0, 0),
                        Ticks.BOMB_FUSE_TICKS, 5).fuseLength())) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }

    @Test
    public void rangeGetterTest() {
        Random random = new Random();
        int randomNumber = random.nextInt(10);

        if (randomNumber == (new Bomb(PlayerID.PLAYER_1, new Cell(0, 0),
                Ticks.BOMB_FUSE_TICKS, randomNumber).range())) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }

    @Test
    public void explosionTest() {
        Bomb bomb = new Bomb(PlayerID.PLAYER_1, new Cell(2, 2),
                Ticks.BOMB_FUSE_TICKS, 2);
        List<Sq<Sq<Cell>>> explosionTest = bomb.explosion();
        assertEquals(4, explosionTest.size());
    }

    // rendre public la méthode
//    @Test
//    public void explosionArmTowardsTest() {
//        Bomb bomb = new Bomb(PlayerID.PLAYER_1, new Cell(2, 2),
//                Ticks.BOMB_FUSE_TICKS, 2);
//            assertEquals(new Cell(2, 2), bomb.explosionArmTowards(Direction.S).head().head());
//            assertEquals(new Cell(2, 3), bomb.explosionArmTowards(Direction.S).head().tail().head());
//            assertEquals(new Cell(2, 2), bomb.explosionArmTowards(Direction.N).head().head());
//            assertEquals(new Cell(2, 1), bomb.explosionArmTowards(Direction.N).head().tail().head());
//            assertEquals(new Cell(2, 2), bomb.explosionArmTowards(Direction.E).head().head());
//            assertEquals(new Cell(3, 2), bomb.explosionArmTowards(Direction.E).head().tail().head());
//            assertEquals(new Cell(2, 2), bomb.explosionArmTowards(Direction.W).head().head());
//            assertEquals(new Cell(1, 2), bomb.explosionArmTowards(Direction.W).head().tail().head());
//            
//    }
//    
//    @Test(expected = NoSuchElementException.class)
//    public void explosionArmTowardsTestFail() {
//        Bomb bomb = new Bomb(PlayerID.PLAYER_1, new Cell(2, 2),
//                Ticks.BOMB_FUSE_TICKS, 2);
//        bomb.explosionArmTowards(Direction.S).head().tail().tail().head();
//    }
}