package ch.epfl.xblast.client;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import ch.epfl.xblast.ArgumentChecker;
import ch.epfl.xblast.PlayerID;
import ch.epfl.xblast.SubCell;

/**
 * 
 * @author Antonio Morais (260428)
 * @author Damian Dudzicz (257839)
 *
 */

public final class GameState {

    private final List<Player> players;
    private final List<Image> imagesBoard;
    private final List<Image> imagesExplosion;
    private final List<Image> imagesScore;
    private final List<Image> imagesTime;

    /**
     * The constructor of the (client) GameState. This version of the GameState
     * is used to create a graphical modelizazion of the gameState.
     * 
     * @param players
     *            the list of the players at a given tick
     * @param imagesBoard
     *            the images corresponding to the elements of the board in the
     *            row-major order
     * @param imagesExplosion
     *            the images corresponding to the explosions and blasts in the
     *            row-major order
     * @param imagesScore
     *            the images which modelize the table of score
     * @param imagesTime
     *            the images corresponding to the blocks modelizing the
     *            remaining time ( 1 block corresponds to 2s )
     * 
     */
    public GameState(List<Player> players, List<Image> imagesBoard,
            List<Image> imagesExplosion, List<Image> imagesScore,
            List<Image> imagesTime) {

        this.imagesBoard = Collections.unmodifiableList(
                new ArrayList<>(Objects.requireNonNull(imagesBoard)));
        this.imagesExplosion = Collections.unmodifiableList(
                new ArrayList<>(Objects.requireNonNull(imagesExplosion)));
        this.imagesScore = Collections.unmodifiableList(
                new ArrayList<>(Objects.requireNonNull(imagesScore)));
        this.imagesTime = Collections.unmodifiableList(
                new ArrayList<>(Objects.requireNonNull(imagesTime)));
        this.players = Collections.unmodifiableList(
                new ArrayList<>(Objects.requireNonNull(players)));
    }

    /**
     * Returns the list of image of the Board for this GameState
     * 
     * @return the list of image of the Board for this GameState
     */

    public List<Image> getBoard() {
        return imagesBoard;
    }

    /**
     * Returns the list of image of the Explosions for this GameState
     * 
     * @return the list of image of the Explosions for this GameState
     */

    public List<Image> getExplosions() {
        return imagesExplosion;
    }

    /**
     * Returns the list of the players for this GameState.
     * 
     * @return the list of the players for this GameState.
     */

    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Returns the list of image of the scores for this GameState
     * 
     * @return the list of image of the scores for this GameState
     */

    public List<Image> getScore() {
        return imagesScore;
    }

    /**
     * Returns the list of images for the time for this GameState
     * 
     * @return the list of images for the time for this GameState
     */

    public List<Image> getTime() {
        return imagesTime;
    }

    public final static class Player {
        private final PlayerID playerID;
        private final int lives;
        private final SubCell position;
        private final Image image;

        /**
         * The constructor of the player. This player is simplified for
         * modelizing purpose.
         * 
         * @param playerID
         *            the id of the player
         * @param lives
         *            the number of lives of the player
         * @param position
         *            the position of the player
         * @param image
         *            the image representing at the current tick
         */
        public Player(PlayerID playerID, int lives, SubCell position,
                Image image) {
            this.playerID = Objects.requireNonNull(playerID);
            this.lives = ArgumentChecker.requireNonNegative(lives);
            this.position = Objects.requireNonNull(position);
            this.image = image;
        }

        /**
         * Returns true if the player is alive.
         * 
         * @return true if the player is alive otherwise false.
         */
        public boolean isAlive() {
            return lives > 0;
        }

        /**
         * Returns the id of the given player.
         * 
         * @return the id of the given player.
         */
        public PlayerID id() {
            return playerID;
        }

        /**
         * Returns the number of remaining lives of this player
         * 
         * @return the number of remaining lives of this player
         */

        public int lives() {
            return lives;
        }

        /**
         * Returns the x position of this player
         * 
         * @return the x position of this player
         */

        public int getX() {
            return position.x();
        }

        /**
         * Returns the y position of this player
         * 
         * @return the y position of this player
         */

        public int getY() {
            return position.y();
        }

        /**
         * Returns the corresponding image of this player
         * 
         * @return the corresponding image of this player
         */
        
        public Image getImage() {
            return image;
        }
    }
}
