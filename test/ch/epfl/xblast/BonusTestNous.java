package ch.epfl.xblast;

import static org.junit.Assert.*;

import org.junit.Test;

import ch.epfl.xblast.Cell;
import ch.epfl.xblast.PlayerID;
import ch.epfl.xblast.server.Bonus;
import ch.epfl.xblast.server.Player;

/**
 * This test tests the class Bonus.
 * 
 * @author Maire Cedric (259314)
 * @author Délèze Benjamin (259992)
 *
 */

public class BonusTestNous {

    @Test
    public void applyToRangeAndMaxBombTestNormal() {
        Player temporaryPlayer = new Player(PlayerID.PLAYER_1, 3, new Cell(0,0), 7, 6);
        
        assertTrue(Bonus.INC_BOMB.applyTo(temporaryPlayer).maxBombs() == 8);
        assertTrue(Bonus.INC_RANGE.applyTo(temporaryPlayer).bombRange() == 7);
    }
    
    @Test
    public void applyToRangeAndMaxBombTestOver() {
        Player temporaryPlayer = new Player(PlayerID.PLAYER_1, 3, new Cell(0,0), 54, 8);
        
        assertTrue(Bonus.INC_BOMB.applyTo(temporaryPlayer).maxBombs() == 9);
        assertTrue(Bonus.INC_RANGE.applyTo(temporaryPlayer).bombRange() == 9);
    }

}
