package ch.epfl.xblast.server;

import ch.epfl.xblast.server.Player.LifeState.State;

/**
 * 
 * @author Antonio Morais (260428)
 * @author Damian Dudzicz (257839)
 *
 */
public final class PlayerPainter {

    // The byte corresponding to an invalid image (for the state Dead)
    private final static byte BYTE_FOR_DEAD = 14;
    // The number of image between two players
    private final static byte NUMBER_OF_IMAGE_PER_PLAYER = 20;
    // The number of image per Direction
    private final static byte NUMBER_OF_IMAGE_PER_DIRECTION = 3;
    // The number of the image when the player lose his last life
    private final static byte NUMBER_OF_IMAGE_FOR_DYING = 13;
    // The number of the image corresponding to an invulnerable player
    private final static byte NUMBER_OF_IMAGE_FOR_INVULNERABLE = 80;
    // The number of positions that the player can have 
    private final static int NUMBER_OF_PLAYER_POSITION = 4;
    // The number to add for the first foot to be ahead
    private final static byte FIRST_FOOT = 1;
    // The number to add for the second foot to be ahead
    private final static byte SECOND_FOOT = 2;

    private PlayerPainter() {

    }

    /**
     * Returns the byte corresponding to the image of the player depending on
     * the ticks and the player. If he's dying we return an image depending on
     * the fact that it's his last life or not. If the ticks are odd and the
     * player is invulnerable we return a image of a white player. The image
     * returned depends on the player.ID, his Direction and his Position.
     * 
     * @param ticks
     *            The current ticks of the current GameState
     * @param player
     *            The player we want to paint.
     * @return the byte corresponding to the image of the player.
     */

    public static byte byteForPlayer(int ticks, Player player) {
        byte correspondingImage = 0;
        if (!player.isAlive())
            correspondingImage += BYTE_FOR_DEAD;
        else {
            if (player.lifeState().state() == State.INVULNERABLE
                    && ticks % 2 == 1) {
                correspondingImage += NUMBER_OF_IMAGE_FOR_INVULNERABLE;
            } else {
                correspondingImage += player.id().ordinal()
                        * NUMBER_OF_IMAGE_PER_PLAYER;
            }
            if (player.lifeState().state() == State.DYING) {
                correspondingImage += player.lives() == 1
                        ? NUMBER_OF_IMAGE_FOR_DYING
                        : NUMBER_OF_IMAGE_FOR_DYING - 1;
            } else {
                correspondingImage += player.direction().ordinal()
                        * NUMBER_OF_IMAGE_PER_DIRECTION;
                int orientedPosition = player.direction().isHorizontal()
                        ? player.position().x() : player.position().y();
                if (orientedPosition % NUMBER_OF_PLAYER_POSITION == 1) {
                    correspondingImage += FIRST_FOOT;
                } else if (orientedPosition % NUMBER_OF_PLAYER_POSITION == 3) {
                    correspondingImage += SECOND_FOOT;
                }
            }
        }
        return correspondingImage;
    }
}
