package ch.epfl.xblast.server;

import ch.epfl.cs108.Sq;
import ch.epfl.xblast.Cell;
import ch.epfl.xblast.Lists;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * @author Antonio Morais (260428)
 * @author Damian Dudzicz (257839)
 *
 */

public final class Board {

    private final List<Sq<Block>> blocksOfBoard;

    /**
     * The constructor of the board. We do an immutable copy of the List given
     * as parameter
     * 
     * @param blocks
     *            The List of Sequence of Blocks which represents the Board in a
     *            1-D List
     * @throws IllegalArgumentException
     *             if the number of sequences of Block in the list blocks is not
     *             equal to the total number of cells (COUNT)
     */

    public Board(List<Sq<Block>> blocks) {
        Objects.requireNonNull(blocks);
        if (blocks.size() == Cell.COUNT) {
            blocksOfBoard = Collections
                    .unmodifiableList(new ArrayList<>(blocks));
        } else
            throw new IllegalArgumentException();
    }

    /**
     * Construct a Board with constant sequences given as parameter
     * 
     * @param rows
     *            The List (two-dimensional) of the Blocks which will constitute
     *            the Board
     * @return the Board created from the given List
     */

    public static Board ofRows(List<List<Block>> rows) {
        checkBlockMatrix(rows, Cell.ROWS, Cell.COLUMNS);
        List<Sq<Block>> listOfBlocks = new ArrayList<>();
        for (List<Block> lB : rows) {
            for (Block b : lB) {
                listOfBlocks.add(Sq.constant(b));
            }
        }
        return new Board(listOfBlocks);
    }

    /**
     * Constructs a Board with indestructible Walls all around
     * 
     * @param innerBlocks
     *            The Blocks that will be inside the walls
     * @return a Board with indestructible walls around the innerBlocks
     */

    public static Board ofInnerBlocksWalled(List<List<Block>> innerBlocks) {
        checkBlockMatrix(innerBlocks, Cell.ROWS - 2, Cell.COLUMNS - 2);
        List<List<Block>> listOfIBW = new ArrayList<>();
        createCopyOfImmutableListOfList(listOfIBW, innerBlocks);
        for (List<Block> subList : listOfIBW) {
            subList.add(0, Block.INDESTRUCTIBLE_WALL);
            subList.add(Block.INDESTRUCTIBLE_WALL);
        }
        listOfIBW.add(0,
                Collections.nCopies(Cell.COLUMNS, Block.INDESTRUCTIBLE_WALL));
        listOfIBW.add(
                Collections.nCopies(Cell.COLUMNS, Block.INDESTRUCTIBLE_WALL));
        return ofRows(listOfIBW);
    }

    /**
     * Constructs a Board using only one quadrant (by copying it symetrically in
     * the others quadrants)
     * 
     * @param quadrantNWBlocks
     *            The North west's quadrant
     * @return the symetric Board
     */

    public static Board ofQuadrantNWBlocksWalled(
            List<List<Block>> quadrantNWBlocks) {
        checkBlockMatrix(quadrantNWBlocks, Cell.ROWS / 2, Cell.COLUMNS / 2);
        List<List<Block>> listToReverse = new ArrayList<>();
        List<List<Block>> listToMirror = new ArrayList<>();
        createCopyOfImmutableListOfList(listToReverse, quadrantNWBlocks);
        createCopyOfImmutableListOfList(listToMirror, quadrantNWBlocks);
        Collections.reverse(listToReverse);
        listToReverse.remove(0);
        listToMirror.addAll(listToReverse);
        listToMirror.replaceAll(l -> Lists.mirrored(l));
        return ofInnerBlocksWalled(listToMirror);
    }

    /**
     * Returns the sequence of Blocks at the position of the cell c
     * 
     * @param c
     *            the Cell where we want the sequence
     * @return the sequence of Blocks at the position of the cell c
     */

    public Sq<Block> blocksAt(Cell c) {
        return blocksOfBoard.get(c.rowMajorIndex());
    }

    /**
     * Returns the first Block of the sequence at the position of the cell c
     * 
     * @param c
     *            the Cell where we want the Block
     * @return the first Block of the sequence at the position of the cell c
     */

    public Block blockAt(Cell c) {
        return blocksAt(c).head();
    }

    /**
     * Check if the matrix has the right number of rows and columns
     * 
     * @param matrix
     *            the matrix that we want to control
     * @param rows
     *            the number of rows we want
     * @param columns
     *            the number of columns we want
     * @throws IllegalArgumentException
     *             if the given matrix has not the right number of rows and
     *             columns
     */

    private static void checkBlockMatrix(List<List<Block>> matrix, int rows,
            int columns) {
        if (matrix.size() == rows) {
            for (List<Block> subList : matrix) {
                if (subList.size() != columns) {
                    throw new IllegalArgumentException();
                }
            }
        } else
            throw new IllegalArgumentException();
    }

    /**
     * Copy in a mutable list an immutable List of Lists.
     * 
     * @param newList
     *            the copy List
     * @param oldList
     *            the immutable List
     */

    private static void createCopyOfImmutableListOfList(
            List<List<Block>> newList, List<List<Block>> oldList) {
        for (List<Block> subList : oldList) {
            newList.add(new ArrayList<>(subList));
        }
    }
}
