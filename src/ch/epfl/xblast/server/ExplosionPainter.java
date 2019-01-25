package ch.epfl.xblast.server;

/**
 * 
 * @author Antonio Morais (260428)
 * @author Damian Dudzicz (257839)
 *
 */
public final class ExplosionPainter {

    // The Byte corresponding to a non-existant image.
    public static final byte BYTE_FOR_EMPTY = 16;
    // The Byte corresponding to the image of a white Bomb
    private static final byte BYTE_FOR_WHITE_BOMB = 21;
    // The Byte corresponding to the image of a black Bomb
    private static final byte BYTE_FOR_BLACK_BOMB = 20;

    private ExplosionPainter() {
    }

    /**
     * Returns the byte corresponding to the image of the Bomb. A white Bomb if
     * the fuseLength is a power of 2, a black Bomb otherwise.
     * 
     * @param bomb
     *            The Bomb that we want to paint.
     * @return the byte corresponding to the image of the bomb
     */

    public static byte byteForBomb(Bomb bomb) {
        return Integer.bitCount(bomb.fuseLength()) == 1
                ? (byte) BYTE_FOR_WHITE_BOMB : (byte) BYTE_FOR_BLACK_BOMB;
    }

    /**
     * Returns the byte corresponding to the image of the Blast depending on the
     * neighbour cells (if they are blasted or not).
     * 
     * @param blastedNorth
     *            True if the cell in the north is blasted.
     * @param blastedEast
     *            True if the cell in the east is blasted.
     * @param blastedSouth
     *            True if the cell in the south is blasted.
     * @param blastedWest
     *            True if the cell in the west is blasted.
     * @return the byte corresponding to the image of the blast
     */

    public static byte byteForBlast(boolean blastedNorth, boolean blastedEast,
            boolean blastedSouth, boolean blastedWest) {
        byte correspondingImage = 0b0;
        if (blastedNorth) {
            correspondingImage += 0b1000;
        }
        if (blastedEast) {
            correspondingImage += 0b0100;
        }
        if (blastedSouth) {
            correspondingImage += 0b0010;
        }
        if (blastedWest) {
            correspondingImage += 0b0001;
        }
        return correspondingImage;
    }
}
