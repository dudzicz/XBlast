package ch.epfl.xblast;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

/**
 * This test tests the class ArgumentChecker.
 * 
 * @author Maire Cedric (259314)
 * @author Délèze Benjamin (259992)
 *
 */

public class ArgumentCheckerTestNous {
    
    @Test (expected = IllegalArgumentException.class)
    public void requireNonNegativeFailTest() {
        ArgumentChecker.requireNonNegative(-1);
    }
    
    @Test
    public void requireNonNegativeAchieveTest() {
        Random random = new Random();
        int randomNumber = Math.abs(random.nextInt());
        
        assertEquals(randomNumber, ArgumentChecker.requireNonNegative(randomNumber));
    }
}