package ch.epfl.xblast;

import ch.epfl.xblast.server.Block;
import ch.epfl.xblast.server.Bonus;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * This test tests the class Block.
 * 
 * @author Maire Cedric (259314)
 * @author Délèze Benjamin (259992)
 *
 */

public class BlockTestNous {
    
    @Test
    public void isFreeTest() {
        if (Block.FREE.isFree() != true || Block.INDESTRUCTIBLE_WALL.isFree() == true ||
                Block.DESTRUCTIBLE_WALL.isFree() == true || Block.CRUMBLING_WALL.isFree() == true) {
            assertTrue(false);
        }
        else {
            assertTrue(true);
        }
    }
    
    @Test
    public void canHostPlayerTest() {
        if (Block.FREE.canHostPlayer() != true || Block.INDESTRUCTIBLE_WALL.canHostPlayer() == true ||
                Block.DESTRUCTIBLE_WALL.canHostPlayer() == true || Block.CRUMBLING_WALL.canHostPlayer() == true 
                || Block.BONUS_BOMB.canHostPlayer() != true || Block.BONUS_RANGE.canHostPlayer() != true) {
            assertTrue(false);
        }
        else {
            assertTrue(true);
        }
    }
    
    @Test
    public void castsShadowTest() {
        if (Block.FREE.castsShadow() == true || Block.INDESTRUCTIBLE_WALL.castsShadow() != true ||
                Block.DESTRUCTIBLE_WALL.castsShadow() != true || Block.CRUMBLING_WALL.castsShadow() != true) {
            assertTrue(false);
        }
        else {
            assertTrue(true);
        }
    }
    
    @Test
    public void isBonusTest() {
        assertTrue(Block.BONUS_BOMB.isBonus());
        assertTrue(Block.BONUS_RANGE.isBonus());
        assertTrue(!Block.CRUMBLING_WALL.isBonus());
    }
    
    @Test(expected = NoSuchElementException.class)
    public void associatedBonusTestError() {
        Block.FREE.associatedBonus();
    }
    
    @Test
    public void associatedBonusTestWork() {
        assertTrue(Block.BONUS_BOMB.associatedBonus() == Bonus.INC_BOMB);
        assertTrue(Block.BONUS_RANGE.associatedBonus() == Bonus.INC_RANGE);
    }
}