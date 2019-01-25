package ch.epfl.xblast;

import ch.epfl.xblast.server.Ticks;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This test tests the class Ticks.
 * 
 * @author Maire Cedric (259314)
 * @author Délèze Benjamin (259992)
 *
 */

public class TicksTestNous {
    
    @SuppressWarnings(value = {"all"})
    @Test
    public void testAllTickConsants() {
        if (Ticks.PLAYER_DYING_TICKS != 8 || Ticks.PLAYER_INVULNERABLE_TICKS != 64 || Ticks.BOMB_FUSE_TICKS != 100 ||
                Ticks.EXPLOSION_TICKS != 30 || Ticks.WALL_CRUMBLING_TICKS != 30 || Ticks.BONUS_DISAPPEARING_TICKS != 30) {
            assertTrue(false);
        }
        else {
            assertTrue(true);
        }
    }
}