package ch.epfl.xblast.server;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import ch.epfl.xblast.Cell;
import ch.epfl.xblast.Direction;

/**
 * 
 * @author Antonio Morais (260428)
 * @author Damian Dudzicz (257839)
 *
 */
public final class BoardPainter {

    private final Map<Block, BlockImage> palette;
    private final BlockImage shadowedFreeBlock;

    /**
     * Constructs a new BoardPainter using a Map assocating to every Block a
     * BlockImage
     * 
     * @param palette
     *            The Map that associates a Block to his BlockImage.
     * @param shadowedFreeBlock
     *            The BlockImage that has a shadow coming from the west
     */

    public BoardPainter(Map<Block, BlockImage> palette,
            BlockImage shadowedFreeBlock) {
        this.palette = Collections.unmodifiableMap(
                new HashMap<>(Objects.requireNonNull(palette)));
        this.shadowedFreeBlock = Objects.requireNonNull(shadowedFreeBlock);
    }

    /**
     * Returns the Byte corresponding to the number of the BlockImage in the
     * source files (images). We check if the block is free and if the block at
     * west is a wall, if so we choose the shadowedFreeBlock otherwise we choose
     * the BlockImage corresponding thanks to the Map.
     * 
     * @param board
     *            The current board from which we will paint the Blocks
     * @param cell
     *            The cell we want to paint.
     * @return the byte corresponding to the image of the Block.
     */

    public byte byteForCell(Board board, Cell cell) {
        Block block = board.blockAt(cell);
        return block.isFree()
                && board.blockAt(cell.neighbor(Direction.W)).castsShadow()
                        ? (byte) shadowedFreeBlock.ordinal()
                        : (byte) palette.get(block).ordinal();
    }
}
