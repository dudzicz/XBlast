package ch.epfl.xblast.server;

/**
 * 
 * @author Antonio Morais (260428)
 * @author Damian Dudzicz (257839)
 *
 */

public enum Bonus {

    INC_BOMB {

        /**
         * Apply the Bonus INC_BOMB to the given player. Returns a new player
         * with all the attributes staying the same except for maxBombs being
         * increased by 1 if it's smaller than 9 otherwise returns the player
         * with maxBombs equals to 9. (By taking the min value of the 2).
         * 
         * @return a new player with a maxBombs increment of 1 if it was
         *         inferior to 9 otherwise return the player with maxBombs
         *         equals to 9
         */
        @Override
        public Player applyTo(Player player) {
            return player
                    .withMaxBombs(Math.min(player.maxBombs() + 1, MAX_VALUE));
        }
    },

    INC_RANGE {

        /**
         * Apply the Bonus INC_RANGE to the given player. Returns a new player
         * with all the attributes staying the same except for bombRange being
         * increased by 1 if it's smaller than 9 otherwise returns the player
         * with bombRange equals to 9. (By taking the min value of the 2).
         * 
         * @return a new player with an bombRange increment of 1 if it was
         *         inferior to 9 otherwise return the player with bombRange
         *         equals to 9.
         */
        @Override
        public Player applyTo(Player player) {
            return player
                    .withBombRange(Math.min(player.bombRange() + 1, MAX_VALUE));

        }
    };

    // the max value of bombs and range being a constant
    private final static int MAX_VALUE = 9;

    abstract public Player applyTo(Player player);
}
