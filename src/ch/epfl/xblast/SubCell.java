package ch.epfl.xblast;

import java.lang.Math;

/**
 * 
 * @author Antonio Morais (260428)
 * @author Damian Dudzicz (257839)
 *
 */

public final class SubCell {

    // gives the number of columns of SubCells in a Cell
    public static final int WIDTH = 16;
    // The number of rows of Subcells in the board
    public static final int ROWS = WIDTH*Cell.ROWS;
    // The number of columns of SubCells in the board
    public static final int COLUMNS = WIDTH*Cell.COLUMNS;


    private final int x;
    private final int y;

    /**
     * Construct an object SubCell with x and y as coordinates being normalized
     * before
     * 
     * @param x
     *            horizontal coordinate
     * @param y
     *            vertical coordinate
     */
    public SubCell(int x, int y) {
        this.x = Math.floorMod(x, COLUMNS);
        this.y = Math.floorMod(y, ROWS);
    }

    /**
     * Returns the horizontal coordinate x
     * 
     * @return the horizontal coordinate x
     */
    public int x() {
        return x;
    }

    /**
     * Returns the vertical coordinate y
     * 
     * @return the vertical coordinate y
     */
    public int y() {
        return y;
    }

    /**
     * Returns the central SubCell of a given Cell
     * 
     * @param cell
     *            The cell from which we want the central SubCell
     * @return the central SubCell of a given Cell
     */
    public static SubCell centralSubCellOf(Cell cell) {
        return new SubCell(cell.x() * WIDTH + WIDTH / 2,
                cell.y() * WIDTH + WIDTH / 2);
    }

    /**
     * Returns the Cell which contains the SubCell
     * 
     * @return the Cell which contains the SubCell
     */
    public Cell containingCell() {
        return new Cell(x() / WIDTH, y() / WIDTH);
    }

    /**
     * Returns true if the SubCell is a central SubCell, false otherwise
     * 
     * @return true if the SubCell is a central SubCell, false otherwise
     */
    public boolean isCentral() {
        return this.equals(centralSubCellOf(containingCell()));
    }

    /**
     * Returns the distance between the SubCell and the central SubCell
     * 
     * @return the distance between the SubCell and the central SubCell
     */
    public int distanceToCentral() {
        return Math.abs(Math.floorMod(x(), WIDTH) - WIDTH / 2)
                + Math.abs(Math.floorMod(y(), WIDTH) - WIDTH / 2);
    }

    /**
     * We don't take care explicitly here of the toric nature of the board due
     * the use of the floorMod in the constructor which solves the problem
     * 
     * @param dir
     *            one of the 4 possible direction
     * @return the neighbor subcell
     * 
     * @throws IllegalArgumentException
     *             if the given parameter in the method is not allowed
     */
    public SubCell neighbor(Direction dir) throws IllegalArgumentException {
        switch (dir) {
        case N:
            return new SubCell(x(), y() - 1);

        case E:
            return new SubCell(x() + 1, y());

        case S:
            return new SubCell(x(), y() + 1);

        case W:
            return new SubCell(x() - 1, y());

        default:
            throw new IllegalArgumentException();
        }
    }

    /**
     * we define the equals by first comparing the class of the parameter that
     * then we compare the position by taking the 2 coordinates
     * 
     * @return true if the two subcells are considered equal, false otherwise
     */
    @Override
    public boolean equals(Object that) {
        if (getClass() == that.getClass()) {
            return ((x() == ((SubCell) that).x())
                    && (y() == ((SubCell) that).y()));
        } else
            return false;
    }

    /**
     * overriding of the hashCode method
     * 
     * @return an associated integer value of the subCell corresponding to the
     *         rowMajorIndex of the subCell
     */

    @Override
    public int hashCode() {
        return y() * COLUMNS + x();
    };

    /**
     * for debugging purpose give the coordinate of the subcell in the form
     * (x,y)
     * 
     * @return a textual representation of the coordinates of the subcell
     */
    @Override
    public String toString() {
        return "( " + x() + " , " + y() + " )";
    }

}
