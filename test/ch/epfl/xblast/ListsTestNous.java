package ch.epfl.xblast;

import ch.epfl.xblast.Lists;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * This test tests the class Lists.
 * 
 * @author Maire Cedric (259314)
 * @author Délèze Benjamin (259992)
 *
 */

public class ListsTestNous {
    
    @Test (expected = IllegalArgumentException.class)
    public void mirroredThrowExceptionOnEmptyList() {
        Lists.mirrored(new ArrayList<Integer>());
    }
    
    @Test
    public void mirroredNormalTest() {
        List<Integer> testList = new ArrayList<Integer>(
                Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> resultList = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 4, 3, 2, 1));
        
        testList = Lists.mirrored(testList);
        
        assertEquals(resultList, testList);
    }
    
    @Test
    public void mirroredOneElementTest() {
        List<Integer> testList = new ArrayList<Integer>(
                Arrays.asList(1));
        List<Integer> resultList = new ArrayList<Integer>(Arrays.asList(1));
        
        testList = Lists.mirrored(testList);
        
        assertEquals(resultList, testList);
    }
    
    @Test
    public void mirroredStringTest() {
        List<String> testList = new ArrayList<String>(
                Arrays.asList("a", "b", "c", "d"));
        List<String> resultList = new ArrayList<String>(Arrays.asList("a", "b", "c", "d", "c", "b", "a"));
        
        testList = Lists.mirrored(testList);
        
        assertEquals(resultList, testList);
    }
    
    @Test
    public void permutationEmpty() {
        List<String> empty = new ArrayList<String>();
        assertTrue(Lists.permutations(empty).get(0).isEmpty());
    }
    
    @Test
    public void permutationSimple() {
        List<String> a = new ArrayList<>();
        a.add("a");
        assertTrue(Lists.permutations(a).size() == 1);
        assertTrue(Lists.permutations(a).get(0).get(0) == "a");
    }
    
    @Test
    public void permutationComplex() {
        List<String> a = new ArrayList<>();
        a.add("a");
        a.add("b");
        a.add("c");
        assertTrue(Lists.permutations(a).size() == 6);
        
        assertTrue(Lists.permutations(a).get(0).get(0) == "a");
        assertTrue(Lists.permutations(a).get(0).get(1) == "b");
        assertTrue(Lists.permutations(a).get(0).get(2) == "c");
        
        assertTrue(Lists.permutations(a).get(1).get(0) == "b");
        assertTrue(Lists.permutations(a).get(1).get(1) == "a");
        assertTrue(Lists.permutations(a).get(1).get(2) == "c");
        
        assertTrue(Lists.permutations(a).get(2).get(0) == "b");
        assertTrue(Lists.permutations(a).get(2).get(1) == "c");
        assertTrue(Lists.permutations(a).get(2).get(2) == "a");
        
        assertTrue(Lists.permutations(a).get(3).get(0) == "a");
        assertTrue(Lists.permutations(a).get(3).get(1) == "c");
        assertTrue(Lists.permutations(a).get(3).get(2) == "b");
        
        assertTrue(Lists.permutations(a).get(4).get(0) == "c");
        assertTrue(Lists.permutations(a).get(4).get(1) == "a");
        assertTrue(Lists.permutations(a).get(4).get(2) == "b");
        
        assertTrue(Lists.permutations(a).get(5).get(0) == "c");
        assertTrue(Lists.permutations(a).get(5).get(1) == "b");
        assertTrue(Lists.permutations(a).get(5).get(2) == "a");
    }
}