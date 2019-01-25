package ch.epfl.xblast.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import ch.epfl.cs108.Sq;
import ch.epfl.xblast.ArgumentChecker;
import ch.epfl.xblast.Cell;
import ch.epfl.xblast.Direction;
import ch.epfl.xblast.PlayerID;

/**
 * 
 * @author Antonio Morais (260428)
 * @author Damian Dudzicz (257839)
 *
 */
public final class Bomb {

    private final PlayerID ownerID;
    private final Cell position;
    private final Sq<Integer> fuseLengths;
    private final int range;

    /**
     * Constructs a Bomb with the owner, the position, the fuselength (at
     * different times) and the range of the Bomb.
     * 
     * @param ownerID
     *            The Player who owns the Bomb.
     * @param position
     *            The position of the Bomb
     * @param fuseLengths
     *            The Sequence of the states of the fuseLength.
     * @param range
     *            The range of the Bomb.
     * @throws IllegalArgumentException
     *             if the sequence fuseLenghts is empty (has no elements in it)
     */

    public Bomb(PlayerID ownerID, Cell position, Sq<Integer> fuseLengths,
            int range) {
        this.ownerID = Objects.requireNonNull(ownerID);
        this.position = Objects.requireNonNull(position);
        this.fuseLengths = Objects.requireNonNull(fuseLengths);
        if (fuseLengths.isEmpty())
            throw new IllegalArgumentException();
        this.range = ArgumentChecker.requireNonNegative(range);
    }

    /**
     * Constructs a Bomb using the other constructor but instead of a given
     * Sequence of fuseLengths it is created by this constructor based on an
     * integer value.
     * 
     * @param ownerID
     *            The Player who owns the Bomb.
     * @param position
     *            The position of the Bomb.
     * @param fuseLengths
     *            The length of the fuse.
     * @param range
     *            The range of the Bomb.
     */

    public Bomb(PlayerID ownerID, Cell position, int fuseLengths, int range) {
        this(ownerID, position,
                Sq.iterate(fuseLengths, s -> s - 1)
                        .limit(ArgumentChecker.requireNonNegative(fuseLengths)),
                range);
    }

    /**
     * Returns the Player who owns the Bomb.
     * 
     * @return the Player who owns the Bomb.
     */

    public PlayerID ownerId() {
        return ownerID;
    }

    /**
     * Returns the Position of the Bomb.
     * 
     * @return the Position of the Bomb.
     */

    public Cell position() {
        return position;
    }

    /**
     * Returns the fuselength's Sequence of the Bomb.
     * 
     * @return the fuselength's Sequence of the Bomb.
     */

    public Sq<Integer> fuseLengths() {
        return fuseLengths;
    }

    /**
     * Returns the fuselength at this moment.
     * 
     * @return the fuselength at this moment.
     */

    public int fuseLength() {
        return fuseLengths.head();
    }

    /**
     * Returns the range of the Bomb.
     * 
     * @return the range of the Bomb.
     */

    public int range() {
        return range;
    }

    /**
     * Returns the List of all the arms.
     * 
     * @return the List of all the arms.
     */

    public List<Sq<Sq<Cell>>> explosion() {
        List<Sq<Sq<Cell>>> temp = new ArrayList<>();
        for (Direction dir : Direction.values()) {
            temp.add(explosionArmTowards(dir));
        }
        return Collections.unmodifiableList(new ArrayList<>(temp));
    }

    /**
     * Returns an Arm of the explosion in the given direction. It consists of a
     * Sequence of Sequence of explosion particles modelising the different
     * states of all the explosion particles in movement.
     * 
     * @param dir
     *            The direction of the Arm.
     * @return an Arm of the explosion.
     */

    private Sq<Sq<Cell>> explosionArmTowards(Direction dir) {
        return Sq.repeat(Ticks.EXPLOSION_TICKS,
                Sq.iterate(position(), c -> c.neighbor(dir)).limit(range()));
    }
}
