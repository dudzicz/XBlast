package ch.epfl.xblast;

/**
 * 
 * @author Antonio Morais (260428)
 * @author Damian Dudzicz (257839)
 *
 */

public final class ArgumentChecker {

    private ArgumentChecker() {
    }

    /**
     * Returns the value if non-negative, otherwise throw an
     * IllegalArgumentException
     * 
     * @param value
     *            an integer value that the user want to check
     * @return the value if non-negative
     * @throws IllegalArgumentException
     *             if negative
     */
    public static int requireNonNegative(int value) {
        if (value >= 0)
            return value;
        else
            throw new IllegalArgumentException();
    }
}
