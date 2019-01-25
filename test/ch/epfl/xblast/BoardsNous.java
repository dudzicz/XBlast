package ch.epfl.xblast;

import java.util.Arrays;

import ch.epfl.xblast.server.Block;
import ch.epfl.xblast.server.Board;

public final class BoardsNous {
    private static Block __ = Block.FREE;
    private static Block XX = Block.INDESTRUCTIBLE_WALL;
    private static Block xx = Block.DESTRUCTIBLE_WALL;
    public static Board board = Board.ofQuadrantNWBlocksWalled(
      Arrays.asList(
        Arrays.asList(__, __, __, __, __, xx, __),
        Arrays.asList(__, XX, xx, XX, xx, XX, xx),
        Arrays.asList(__, xx, __, __, __, xx, __),
        Arrays.asList(xx, XX, __, XX, XX, XX, XX),
        Arrays.asList(__, xx, __, xx, __, __, __),
        Arrays.asList(xx, XX, xx, XX, xx, XX, __)));
}
