package ch.epfl.xblast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author Antonio Morais (260428)
 * @author Damian Dudzicz (257839)
 *
 */
public class RunLengthEncoder {
    // maximum number of elements that can be coded on a byte
    private final static int MAX_ELEMENTS_IN_A_ROW = 130;
    // the minimum number of repetitions to apply the format
    private final static int MIN_SIZE_COMPRESSED = 2;

    private RunLengthEncoder() {
    }

    /**
     * Given a sequence of byte the method compressed it to a shorter list of
     * byte by grouping elements that are identical in a row. If there is more
     * than 2 elements which are the same we take the number of iterations we
     * substract 2 and we negate it afterwards in order not to confound the
     * number of repetition with the values repeated. If the number of identical
     * consecutive elements is over MAX_ELEMENTS_IN_A_ROW, we create a new
     * counter for the remaining elements. If the number of identical elements
     * are less or equal to 2 we add them in their original state.
     * 
     * @throws IllegalArgumentException
     *             if we receive a negative byte from the list as parameter
     * @param listOfByte
     *            the list of byte wanted to be compressed
     * @return the compressed list of byte by the algorithm described before
     */
    public static List<Byte> encode(List<Byte> listOfByte) {
        // case if the size of the list is 1 ( because no next element)
        if (listOfByte.size() == 1) {
            return Collections.unmodifiableList(new ArrayList<>(listOfByte));
        }
        
        List<Byte> encodedListOfByte = new ArrayList<>();
        Iterator<Byte> itByte = listOfByte.iterator();

        byte currentByte = 0;
        // initialization of the first byte if there is one in the given list
        if (itByte.hasNext()) {
            currentByte = itByte.next();
        }

        while (itByte.hasNext()) {
            int nbCounter = 1;
            byte nextByte = itByte.next();
            if (currentByte < 0 || nextByte < 0) {
                throw new IllegalArgumentException();
            }
            // counting of the repetitions of the same byte in a row
            while (itByte.hasNext() && nextByte == currentByte) {
                ++nbCounter;
                // case overpassing of the maximal stocking capacity
                if (nbCounter == MAX_ELEMENTS_IN_A_ROW) {
                    encodedListOfByte.add(Byte.MIN_VALUE);
                    encodedListOfByte.add(currentByte);
                    nbCounter = 0;
                }
                nextByte = itByte.next();
            }

            // the handle of the case of the last byte
            if (nextByte == currentByte) {
                ++nbCounter;
            }
            if (nbCounter <= MIN_SIZE_COMPRESSED) {
                encodedListOfByte
                        .addAll(Collections.nCopies(nbCounter, currentByte));
            } else {
                encodedListOfByte
                        .add((byte) (-(nbCounter - MIN_SIZE_COMPRESSED)));
                encodedListOfByte.add(currentByte);
            }

            // the handle of the last byte if different from the previous one
            if (!itByte.hasNext() && currentByte != nextByte) {
                encodedListOfByte.add(nextByte);
            }

            currentByte = nextByte;
        }

        return Collections.unmodifiableList(new ArrayList<>(encodedListOfByte));
    }

    /**
     * Given a List of Byte that is compressed, we decompressed it using the
     * reverse method from the encode. We first check if the last element is
     * negative because it would be not decodable. Afterwards we read through
     * the list checking if the byte is positive or negative. If it's positive
     * we just add it. if it's negative we add the next byte n times (n is equal
     * to the absolute value of the negative byte - 2).
     * 
     * @param listOfByte
     *            The list that we want to decode
     * @return the decoded list.
     */
    public static List<Byte> decode(List<Byte> listOfByte) {
        if (listOfByte.get(listOfByte.size() - 1) < 0) {
            throw new IllegalArgumentException();
        }

        List<Byte> listOfDecodedByte = new ArrayList<>();
        Iterator<Byte> itByte = listOfByte.iterator();

        while (itByte.hasNext()) {
            byte actualByte = itByte.next();
            if (actualByte >= 0) {
                listOfDecodedByte.add(actualByte);
            } else {
                listOfDecodedByte.addAll(Collections
                        .nCopies(Math.abs(actualByte - 2), itByte.next()));
            }
        }
        return Collections.unmodifiableList(new ArrayList<>(listOfDecodedByte));
    }
}
