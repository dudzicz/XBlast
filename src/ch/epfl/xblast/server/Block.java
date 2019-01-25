package ch.epfl.xblast.server;

import java.util.NoSuchElementException;

/**
 * 
 * @author Antonio Morais (260428)
 * @author Damian Dudzicz (257839)
 *
 */

public enum Block {
    // The different Blocks
    FREE, INDESTRUCTIBLE_WALL, DESTRUCTIBLE_WALL, CRUMBLING_WALL, BONUS_BOMB(
            Bonus.INC_BOMB), BONUS_RANGE(Bonus.INC_RANGE);

    private final Bonus maybeAssociatedBonus;

    /**
     * Constructs a Block with an associated Bonus
     * 
     * @param maybeAssociatedBonus
     *            The Bonus that is associated to the Block
     */

    private Block(Bonus maybeAssociatedBonus) {
        this.maybeAssociatedBonus = maybeAssociatedBonus;
    }

    /**
     * Constructs a Block without any bonus.
     */
    
    private Block() {
        this.maybeAssociatedBonus = null;
    }

    /**
     * Returns true if the cell is free, false otherwise
     * 
     * @return true if the cell is free, false otherwise
     */
    
    public boolean isFree() {
        return this == FREE;
    }

    /**
     * Returns true if the cell can host a player, false otherwise
     * 
     * @return true if the cell can host a player, false otherwise
     */
    
    public boolean canHostPlayer() {
        return (this.isFree() || this.isBonus());
    }

    /**
     * Returns true if the cell is a wall being the only one with shadowing,
     * false otherwise
     * 
     * @return true if the cell is a wall being the only one with shadowing,
     *         false otherwise
     */
    
    public boolean castsShadow() {
        return (this == INDESTRUCTIBLE_WALL || this == DESTRUCTIBLE_WALL
                || this == CRUMBLING_WALL);
    }

    /**
     * Returns true if the cell is a bonus, false otherwise.
     * 
     * @return true if the cell is a bonus, false otherwise.
     */
    
    public boolean isBonus() {
        return (this == BONUS_RANGE || this == BONUS_BOMB);
    }

    /**
     * Returns the Bonus that is associated to the cell or throw an Exception if
     * there is no such Element.
     * 
     * @return the bonus that is associated to the cell.
     * @throws NoSuchElementException
     *             if there is no bonus.
     */
    public Bonus associatedBonus() {
        if (isBonus()) {
            return maybeAssociatedBonus;
        } else {
            throw new NoSuchElementException();
        }
    }

}
