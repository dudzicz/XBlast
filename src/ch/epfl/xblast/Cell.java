package ch.epfl.xblast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author Antonio Morais (260428)
 * @author Damian Dudzicz (257839)
 *
 */
public final class Cell {

    // the number of columns of the board
    public static final int COLUMNS = 15;
    // the number of rows of the board
    public static final int ROWS = 13;
    // the number of cells in the board
    public static final int COUNT = ROWS * COLUMNS;

    // A list of the cells in the row major order
    public static final List<Cell> ROW_MAJOR_ORDER = Collections
            .unmodifiableList(new ArrayList<>(rowMajorOrder()));
    // A list of the cells in the spiral order
    public static final List<Cell> SPIRAL_ORDER = Collections
            .unmodifiableList(new ArrayList<>(spiralOrder()));

    private final int x;
    private final int y;

    /**
     * Construct an object Cell with x and y as coordinates after being
     * normalized.
     * 
     * @param x
     *            horizontal coordinate
     * @param y
     *            vertical coordinate
     */
    public Cell(int x, int y) {
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
     * We take the width of the the board which is given by the number of
     * COLUMNS and we multiply it by the vertical coordinate y of the cell and
     * we add the horizontal coordinate x to obtain the index of the cell in the
     * row major order
     * 
     * @return the index of the cell in the row major order
     */
    public int rowMajorIndex() {
        return y * COLUMNS + x;
    }

    /**
     * We don't take care explicitly here of the toric nature of the board due
     * the use of the floorMod in the constructor which solves the problem
     * 
     * @param dir
     *            one of the 4 possible direction
     * @return the neighbor cell
     * 
     * @throws IllegalArgumentException
     *             if the given parameter in the method is not allowed
     */
    public Cell neighbor(Direction dir) {
        switch (dir) {
        case N:
            return new Cell(x, y - 1);

        case E:
            return new Cell(x + 1, y);

        case S:
            return new Cell(x, y + 1);

        case W:
            return new Cell(x - 1, y);

        default:
            throw new IllegalArgumentException();
        }
    }

    /**
     * we define the equals by first comparing the class of the parameter that
     * then we compare the position by taking the row major index
     * 
     * @return true if the two cells are considered equal, false otherwise
     */
    @Override
    public boolean equals(Object that) {
        if (this.getClass() == that.getClass()) {
            return (rowMajorIndex() == ((Cell) that).rowMajorIndex());
        } else
            return false;
    }
    
    /**
     * overriding of the hashCode method
     * 
     * @return an associated integer value of the cell corresponding to the rowMajorIndex of the cell
     */
    
    @Override
    public int hashCode() {
        return this.rowMajorIndex();
    };

    /**
     * for debugging purpose, give the coordinate of the cell in the form (x,y)
     * 
     * @return a textual representation of the coordinates of the cell
     */
    @Override
    public String toString() {
        return "( " + x() + " , " + y() + " )";
    }

    /**
     * We add to an initially empty list each cell using 2 for-loops in the row
     * major order
     * 
     * @return a list of cells ordered in a row major order
     */
    private static List<Cell> rowMajorOrder() {
        List<Cell> rowMajorList = new ArrayList<Cell>();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                rowMajorList.add(new Cell(j, i));
            }
        }
        return rowMajorList;
    }

    /**
     * We apply the pseudo code given in the part 2.2.2 given on the website of
     * the course http://cs108.epfl.ch/p/01_cells.html
     * 
     * @return a list of cells ordered in a spiral order
     */
    private static List<Cell> spiralOrder() {
        List<Integer> listX = new ArrayList<>();
        for (int i = 0; i < COLUMNS; i++) {
            listX.add(i);
        }
        List<Integer> listY = new ArrayList<>();
        for (int i = 0; i < ROWS; i++) {
            listY.add(i);
        }
        boolean horizontal = true;
        List<Cell> spiralList = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        while (!(listX.isEmpty() || listY.isEmpty())) {
            if (horizontal) {
                list1 = listX;
                list2 = listY;
            } else {
                list1 = listY;
                list2 = listX;
            }
            int c2 = list2.remove(0);
            for (Integer c1 : list1) {
                if (horizontal) {
                    spiralList.add(new Cell(c1, c2));
                } else {
                    spiralList.add(new Cell(c2, c1));
                }
            }
            Collections.reverse(list1);
            horizontal = !horizontal;
        }

        return spiralList;
    }
}
