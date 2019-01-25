package ch.epfl.xblast;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * 
 * @author Antonio Morais (260428)
 * @author Damian Dudzicz (257839)
 *
 */

public final class Lists {

    /**
     * the constructor has to be private and empty because it's not instantiable
     */
    private Lists() {
    }

    /**
     * First we check if the list is valid being not empty. Then we make a
     * temporary sublist of the list l excluding the last element ( because we
     * don't want it to be counted twice ) and we reverse. But we append it not
     * to l but to a copy of l ( because we suppose that l is immutable ).
     * 
     * @param l
     *            generic list l supposed to be immutable (that's why we make a
     *            copy of l to temp in order to append the tempList to it)
     * @return the immutable mirrored list given as parameter
     * @throws IllegalArgumentException
     *             if the given list is empty
     */
    public static <T> List<T> mirrored(List<T> l) {
        if (!(Objects.requireNonNull(l).isEmpty())) {
            List<T> temp = new ArrayList<>(l);
            List<T> tempList = new ArrayList<>(l.subList(0, l.size() - 1));
            Collections.reverse(tempList);
            temp.addAll(tempList);
            return Collections.unmodifiableList(new ArrayList<>(temp));
        } else
            throw new IllegalArgumentException();
    }

    /**
     * The method returns the permutations of all the elements of the given
     * list. At first, we do a copy of the given list in case it's immutable.
     * The algorithm is recursive this way we check if the list is of length 0
     * or 1, if it is the case we return the given list and we're done.
     * Otherwise we remove the first element of the list and we call the method
     * itself until it's of length 1. Then we add the previously removed element
     * at every possible position of the list. Because of the recursive
     * functioning we will have all the possible permutations.
     * 
     * @param l
     *            generic list of objects of type T
     * @return a list containing all the possible permutations of the elements
     *         of a given list.
     */
    public static <T> List<List<T>> permutations(List<T> l) {
        List<List<T>> listPermutation = new LinkedList<>();
        List<T> copyList = new LinkedList<>(Objects.requireNonNull(l));

        if (copyList.isEmpty()) {
            listPermutation.add(copyList);
        } else {

            T temp = copyList.remove(0);
            List<List<T>> listPermutationBefore = new LinkedList<>(permutations(copyList));
            for (List<T> lpb : listPermutationBefore) {
                for (int i = 0; i <= lpb.size(); i++) {
                    lpb.add(i, temp);
                    listPermutation.add(new ArrayList<>(lpb));
                    lpb.remove(i);
                }
            }
        }
        return Collections.unmodifiableList(new ArrayList<>(listPermutation));
    }
    
}
