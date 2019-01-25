package ch.epfl.xblast;

import java.util.NoSuchElementException;

/**
 * 
 * @author Antonio Morais (260428)
 * @author Damian Dudzicz (257839)
 *
 */
public enum Direction {
    // the four cardinal directions
    N, E, S, W;

    /**
     * @return the opposite direction
     * 
     * @throws NoSuchElementException
     *             if the direction isn't included in the enumeration
     */
    public Direction opposite() {
        switch (this) {
        case N:
            return S;

        case E:
            return W;

        case S:
            return N;

        case W:
            return E;

        default:
            throw new NoSuchElementException();
        }
    }

    /**
     * Returns true if Direction W or E
     * 
     * @return true if Direction W or E
     */
    public boolean isHorizontal() {
        return (this == W || this == E);
    }

    /**
     * Returns true if direction is parallel
     * 
     * @return true if direction is parallel
     */
    public boolean isParallelTo(Direction that) {
        return (this == that || this.opposite() == that);
    }
}
