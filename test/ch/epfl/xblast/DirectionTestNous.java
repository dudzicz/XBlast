package ch.epfl.xblast;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DirectionTestNous {
    @Test
    public void oppositeOfOppositeIsIdentity() {
        for (Direction d: Direction.values())
            assertEquals(d, d.opposite().opposite());
    }

    @Test
    public void oppositeIsTwoStepsAway() {
        for (Direction d: Direction.values())
            assertEquals(2, Math.abs(d.ordinal() - d.opposite().ordinal()));
    }

    @Test
    public void isHorizontalIsCorrect() {
        assertFalse(Direction.N.isHorizontal());
        assertTrue(Direction.E.isHorizontal());
        assertFalse(Direction.S.isHorizontal());
        assertTrue(Direction.W.isHorizontal());
    }

    @Test
    public void isParallelIsTrueOnlyForOppositeAndSelf() {
        for (Direction d1: Direction.values()) {
            for (Direction d2: Direction.values()) {
                if (d1 == d2 || d1 == d2.opposite())
                    assertTrue(d1.isParallelTo(d2));
                else
                    assertFalse(d1.isParallelTo(d2));
            }
        }
    }
}
