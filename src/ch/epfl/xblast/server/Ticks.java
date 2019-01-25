package ch.epfl.xblast.server;

import ch.epfl.xblast.Time;

/**
 * 
 * @author Antonio Morais (260428)
 * @author Damian Dudzicz (257839)
 *
 */

public interface Ticks {
    
    // The number of ticks of a dying player 
    public final static int PLAYER_DYING_TICKS = 8;
    // The number of ticks of an invulnerable player
    public final static int PLAYER_INVULNERABLE_TICKS = 64;
    // The number of ticks before the explosion of a bomb
    public final static int BOMB_FUSE_TICKS = 100;
    // the number of ticks during which the bomb is exploding
    public final static int EXPLOSION_TICKS = 30;
    // the number of ticks during which the wall is crumbling
    public final static int WALL_CRUMBLING_TICKS = 30;
    // the number of ticks after which the Bonus disappears after being hit by an explosion
    public final static int BONUS_DISAPPEARING_TICKS = 30;
    // the number of ticks per second 
    public final static int TICKS_PER_SECOND = 20;
    // the length of a tick in nanoseconds
    public final static int TICK_NANOSECOND_DURATION = Time.NS_PER_S/TICKS_PER_SECOND;
    // the number total of ticks of a game
    public final static int TOTAL_TICKS = Time.TOTAL_MINUTES*Time.S_PER_MIN*TICKS_PER_SECOND;
    
}
