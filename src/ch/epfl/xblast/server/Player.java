package ch.epfl.xblast.server;

import java.util.Objects;

import ch.epfl.cs108.Sq;
import ch.epfl.xblast.ArgumentChecker;
import ch.epfl.xblast.Cell;
import ch.epfl.xblast.Direction;
import ch.epfl.xblast.PlayerID;
import ch.epfl.xblast.SubCell;
import ch.epfl.xblast.server.Player.LifeState.State;

/**
 * 
 * @author Antonio Morais (260428)
 * @author Damian Dudzicz (257839)
 *
 */
public final class Player {
    private final PlayerID id;
    private final Sq<LifeState> lifeStates;
    private final Sq<DirectedPosition> directedPos;
    private final int maxBombs;
    private final int bombRange;

    /**
     * Constructs a Player. We control the case if a player is constructed with
     * 0 lives which means that he is already dead. Therefore we associate a
     * particular sequence to this player of constant death state.
     * 
     * @param id
     *            The ID of the player (ex. PLAYER_1)
     * @param lifeStates
     *            The evolution of the LifeStates (the number of lives and the
     *            state).
     * @param directedPos
     *            The evolution of the Directed Positions (the position and the
     *            direction of the player)
     * @param maxBombs
     *            The maximum number of Bombs which can be simultaneously placed
     *            on the board by the player.
     * @param bombRange
     *            The range of the bombs
     */

    public Player(PlayerID id, Sq<LifeState> lifeStates,
            Sq<DirectedPosition> directedPos, int maxBombs, int bombRange) {
        this.id = Objects.requireNonNull(id);
        if (Objects.requireNonNull(lifeStates).head().lives() == 0) {
            this.lifeStates = Sq.constant(new LifeState(0, State.DEAD));
        } else {
            this.lifeStates = lifeStates;
        }
        this.directedPos = Objects.requireNonNull(directedPos);
        this.maxBombs = ArgumentChecker.requireNonNegative(maxBombs);
        this.bombRange = ArgumentChecker.requireNonNegative(bombRange);
    }

    /**
     * Constructs a Player using the other constructor.
     * 
     * @param id
     *            The ID of the player (ex. PLAYER_1)
     * @param lives
     *            The number of the Player
     * @param position
     *            The position of the Player
     * @param maxBombs
     *            The maximum number of Bombs which can be simultaneously placed
     *            on the board by the player.
     * @param bombRange
     *            The range of the bombs
     */

    public Player(PlayerID id, int lives, Cell position, int maxBombs,
            int bombRange) {
        this(id, Sq
                .repeat(Ticks.PLAYER_INVULNERABLE_TICKS,
                        new LifeState(lives, State.INVULNERABLE))
                .concat(Sq.constant(new LifeState(lives, State.VULNERABLE))),
                DirectedPosition.stopped(new DirectedPosition(
                        SubCell.centralSubCellOf(
                                Objects.requireNonNull(position)),
                        Direction.S)),
                maxBombs, bombRange);
    }

    /**
     * Returns the id of the player.
     * 
     * @return the id of the player.
     */

    public PlayerID id() {
        return id;
    }

    /**
     * Returns the evolution of the LifeStates.
     * 
     * @return the evolution of the LifeStates.
     */

    public Sq<LifeState> lifeStates() {
        return lifeStates;
    }

    /**
     * Returns the current LifeState.
     * 
     * @return the current LifeState.
     */

    public LifeState lifeState() {
        return lifeStates().head();
    }

    /**
     * Returns the current lives of the Player
     * 
     * @return the current lives of the Player
     */
    public int lives() {
        return lifeStates().head().lives();
    }

    /**
     * Returns true if the Player is alive.
     * 
     * @return true if the Player is alive.
     */

    public boolean isAlive() {
        return lives() > 0;
    }

    /**
     * Returns the evolution of the directedPositions.
     * 
     * @return the evolution of the directedPositions.
     */

    public Sq<DirectedPosition> directedPositions() {
        return directedPos;
    }

    /**
     * Returns the current position of the Player.
     * 
     * @return the current position of the Player.
     */

    public SubCell position() {
        return directedPositions().head().position();
    }

    /**
     * Returns the current direction of the Player.
     * 
     * @return the current direction of the Player.
     */

    public Direction direction() {
        return directedPositions().head().direction();
    }

    /**
     * Returns the maximum number of Bombs which can be simultaneously placed on
     * the board by the player.
     * 
     * @return the maximum number of Bombs which can be simultaneously placed on
     *         the board by the player.
     */

    public int maxBombs() {
        return maxBombs;
    }

    /**
     * Returns a Player with a new number of maximum Bombs.
     * 
     * @param newMaxBombs
     *            The new number of Bombs.
     * @return a Player with a new number of maximum Bombs.
     */

    public Player withMaxBombs(int newMaxBombs) {
        return new Player(id(), lifeStates(), directedPositions(), newMaxBombs,
                bombRange());
    }

    /**
     * Returns the range of the bombs of the player.
     * 
     * @return the range of the bombs of the player.
     */

    public int bombRange() {
        return bombRange;
    }

    /**
     * Returns a new Player with a different range of his bombs
     * 
     * @param newBombRange
     *            The new range of the player
     * @return a new Player with a different range of his bombs
     */

    public Player withBombRange(int newBombRange) {
        return new Player(id(), lifeStates(), directedPositions(), maxBombs(),
                newBombRange);
    }

    /**
     * Returns a new Bomb with the position of the player when he puts the bomb
     * on the board.
     * 
     * @return a new Bomb with the position of the player when he puts the bomb
     *         on the board.
     */
    public Bomb newBomb() {
        return new Bomb(id(), position().containingCell(),
                Ticks.BOMB_FUSE_TICKS, bombRange());
    }

    /**
     * Returns the sequence of state for the next life of the player. It begins
     * with a sequence of dying state of length Ticks.PLAYER_DYING_TICKS. The
     * number of lives is then reduced by 1. If the number of lives is 1 or 0
     * the following elements of the sequence are constant and permanent death
     * states. If the number of lives is superior to 1 the following elements
     * are invulnerable states of length Ticks.PLAYER_INVULNERABLE_TICKS
     * followed by constant and permanent vulnerable states. The lost life is
     * subtracted after the finite sequence of dying states.
     * 
     * @return the sequence of state for the next life of the player.
     */

    public Sq<LifeState> statesForNextLife() {
        return Sq
                .repeat(Ticks.PLAYER_DYING_TICKS,
                        new LifeState(lives(), LifeState.State.DYING))
                .concat(statesForNextLifeAuxiliary(lives()));
    }

    /**
     * The goal of this auxiliary method is to make the code of
     * statesForNextLife() more digest to read. For the if we check that the
     * number of lives is equal to 1 or 0. 1 means that the player is going to
     * die in the next life and 0 is for security purpose avoiding a
     * "resurrection" of an already dead player. For more information about the
     * functioning of the method see the javadoc @statesForNextLife
     * 
     * @see statesForNextLife
     * @param lives
     *            the current number of lives of the player
     * @return an auxiliary sequence of states used in the method
     *         statesForNextLife()
     */

    private static Sq<LifeState> statesForNextLifeAuxiliary(int lives) {
        if (lives <= 1)
            return Sq.constant(new LifeState(0, State.DEAD));
        else
            return Sq
                    .repeat(Ticks.PLAYER_INVULNERABLE_TICKS,
                            new LifeState(lives - 1, State.INVULNERABLE))
                    .concat(Sq.constant(
                            new LifeState(lives - 1, State.VULNERABLE)));
    }

    public final static class LifeState {
        private final int lives;
        private final State state;

        public enum State {
            // The four possible states of the player
            INVULNERABLE, VULNERABLE, DYING, DEAD;
        }

        /**
         * Constructs a new instance of LifeState with the given number of lives
         * and the state
         * 
         * @param lives
         *            the number of lives
         * @param state
         *            the state of the player
         */

        public LifeState(int lives, State state) {
            this.lives = ArgumentChecker.requireNonNegative(lives);
            this.state = Objects.requireNonNull(state);
        }

        /**
         * Returns the number of lives of the LifeState
         * 
         * @return the number of lives of the LifeState
         */

        public int lives() {
            return lives;
        }

        /**
         * Returns the state of the LifeState
         * 
         * @return the state of the LifeState
         */

        public State state() {
            return state;
        }

        /**
         * Returns true if the player is able to move (i.e. all the states that
         * aren't dead nor dying)
         * 
         * @return true if the player is able to move (i.e. all the states that
         *         aren't dead nor dying)
         */

        public boolean canMove() {
            return (state == State.INVULNERABLE || state == State.VULNERABLE);
        }
    }

    public final static class DirectedPosition {
        private final SubCell position;
        private final Direction direction;

        /**
         * Constructs a new instance of DirectedPosition with the given position
         * and direction
         * 
         * @param position
         *            The position of the DirectedPosition
         * @param direction
         *            The direction of the DirectedPosition
         */

        public DirectedPosition(SubCell position, Direction direction) {
            this.position = Objects.requireNonNull(position);
            this.direction = Objects.requireNonNull(direction);
        }

        /**
         * Returns a new constant sequence of DirectedPosition, based on a
         * DirectedPosition given as parameter, having the same attributs
         * 
         * @param p
         *            The directedPosition of the player
         * @return a new constant sequence of DirectedPosition
         */

        public static Sq<DirectedPosition> stopped(DirectedPosition p) {
            return Sq.constant(p);
        }

        /**
         * Returns a new changing sequence of DirectedPosition built by
         * iterating the successive neighbors of the position of
         * DirectedPosition given as parameter and keeping also its direction
         * 
         * @param p
         *            The directedPosition of the player
         * @return a new sequence of DirectedPosition representing a movement
         */

        public static Sq<DirectedPosition> moving(DirectedPosition p) {
            return Sq.iterate(p,
                    f -> f.withPosition(f.position().neighbor(f.direction())));

        }

        /**
         * Returns the Subcell corresponding to the position associated to the
         * DirectedPostion
         * 
         * @return the Subcell corresponding to the position associated to the
         *         DirectedPostion
         */

        public SubCell position() {
            return position;
        }

        /**
         * Builder of a new DirectedPosition with a different position
         * 
         * @param newPosition
         *            The wanted new position
         * @return a new DirectedPosition with a different position
         */

        public DirectedPosition withPosition(SubCell newPosition) {
            return new DirectedPosition(newPosition, direction());
        }

        /**
         * Returns the Direction associated to the DirectedPostion
         * 
         * @return the Direction associated to the DirectedPostion
         */

        public Direction direction() {
            return direction;
        }

        /**
         * Builder of a new DirectedPosition with a different direction
         * 
         * @param newDirection
         *            The wanted new direction
         * @return a new DirectedPosition with a different direction
         */

        public DirectedPosition withDirection(Direction newDirection) {
            return new DirectedPosition(position(), newDirection);
        }

    }
}
