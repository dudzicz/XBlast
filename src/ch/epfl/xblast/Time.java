package ch.epfl.xblast;


/**
 * 
 * @author Antonio Morais (260428)
 * @author Damian Dudzicz (257839)
 *
 */
public interface Time {

    // The number of seconds in a minute
    public final static int S_PER_MIN = 60;
    // The number of miliseconds in a second
    public final static int MS_PER_S = 1000;
    // The number of microseconds in a second
    public final static int US_PER_S = 1000 * MS_PER_S;
    // The number of nanoseconds in a second
    public final static int NS_PER_S = 1000 * US_PER_S;
    // The total number of minutes of an entire game
    public final static int TOTAL_MINUTES = 2;
}
