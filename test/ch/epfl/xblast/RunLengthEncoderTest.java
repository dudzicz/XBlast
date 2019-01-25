package ch.epfl.xblast;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * This test tests the class RunLengthEncoder.
 * 
 * @author Maire Cedric (259314)
 * @author Délèze Benjamin (259992)
 *
 */

public class RunLengthEncoderTest {

    @Test(expected = IllegalArgumentException.class)
    public void encodeTestFail() {
        List<Byte> bytesToEncode = new ArrayList<>(Arrays.asList((byte) 1,
                (byte) 2, (byte) 3, (byte) -4, (byte) 5));

        RunLengthEncoder.encode(bytesToEncode);
    }

    @Test(expected = IllegalArgumentException.class)
    public void decodeTestFail() {
        List<Byte> bytesToDecode = new ArrayList<>(Arrays.asList((byte) 1,
                (byte) 2, (byte) 3, (byte) 4, (byte) -5));

        RunLengthEncoder.decode(bytesToDecode);
    }

    @Test
    public void encodeAchieveTest() {
        List<Byte> bytesToEncode0 = new ArrayList<>(
                Arrays.asList((byte) 1, (byte) 2, (byte) 2, (byte) 3, (byte) 3,
                        (byte) 3, (byte) 4, (byte) 4, (byte) 4, (byte) 4,
                        (byte) 5, (byte) 5, (byte) 5, (byte) 5, (byte) 5));
        List<Byte> bytesToEncode1 = new ArrayList<>(Arrays.asList((byte) 1,
                (byte) 2, (byte) 2, (byte) 3, (byte) 3, (byte) 3, (byte) 4,
                (byte) 4, (byte) 4, (byte) 4, (byte) 5, (byte) 5, (byte) 5,
                (byte) 5, (byte) 5, (byte) 6));
        List<Byte> bytesToEncode2 = new ArrayList<>(Arrays.asList((byte) 1,
                (byte) 2, (byte) 2, (byte) 3, (byte) 3, (byte) 3, (byte) 4,
                (byte) 4, (byte) 4, (byte) 4, (byte) 5, (byte) 5, (byte) 5,
                (byte) 5, (byte) 5, (byte) 6, (byte) 6));
        List<Byte> bytesToEncode3 = new ArrayList<>(Arrays.asList((byte) 1));
        List<Byte> bytesToEncode4 = new ArrayList<>(
                Arrays.asList((byte) 1, (byte) 1));
        List<Byte> bytesToEncode5 = new ArrayList<>(Arrays.asList((byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1));

        List<Byte> expected0 = new ArrayList<>(
                Arrays.asList((byte) 1, (byte) 2, (byte) 2, (byte) -1, (byte) 3,
                        (byte) -2, (byte) 4, (byte) -3, (byte) 5));
        List<Byte> expected1 = new ArrayList<>(
                Arrays.asList((byte) 1, (byte) 2, (byte) 2, (byte) -1, (byte) 3,
                        (byte) -2, (byte) 4, (byte) -3, (byte) 5, (byte) 6));
        List<Byte> expected2 = new ArrayList<>(Arrays.asList((byte) 1, (byte) 2,
                (byte) 2, (byte) -1, (byte) 3, (byte) -2, (byte) 4, (byte) -3,
                (byte) 5, (byte) 6, (byte) 6));
        List<Byte> expected3 = new ArrayList<>(Arrays.asList((byte) 1));
        List<Byte> expected4 = new ArrayList<>(
                Arrays.asList((byte) 1, (byte) 1));
        List<Byte> expected5 = new ArrayList<>(Arrays.asList((byte) -128,
                (byte) 1, (byte) -128, (byte) 1, (byte) -128, (byte) 1,
                (byte) -128, (byte) 1, (byte) -128, (byte) 1, (byte) -128,
                (byte) 1, (byte) -128, (byte) 1, (byte) -128, (byte) 1,
                (byte) -128, (byte) 1, (byte) -89, (byte) 1));

        assertEquals(expected0, RunLengthEncoder.encode(bytesToEncode0));
        assertEquals(expected1, RunLengthEncoder.encode(bytesToEncode1));
        assertEquals(expected2, RunLengthEncoder.encode(bytesToEncode2));
        assertEquals(expected3, RunLengthEncoder.encode(bytesToEncode3));
        assertEquals(expected4, RunLengthEncoder.encode(bytesToEncode4));
        assertEquals(expected5, RunLengthEncoder.encode(bytesToEncode5));
    }

    @Test
    public void decodeAchieveTest() {
        List<Byte> bytesToDecode0 = new ArrayList<>(
                Arrays.asList((byte) 1, (byte) 2, (byte) 2, (byte) -1, (byte) 3,
                        (byte) -2, (byte) 4, (byte) -3, (byte) 5));
        List<Byte> bytesToDecode1 = new ArrayList<>(
                Arrays.asList((byte) 1, (byte) 2, (byte) 2, (byte) -1, (byte) 3,
                        (byte) -2, (byte) 4, (byte) -3, (byte) 5, (byte) 6));
        List<Byte> bytesToDecode2 = new ArrayList<>(Arrays.asList((byte) 1,
                (byte) 2, (byte) 2, (byte) -1, (byte) 3, (byte) -2, (byte) 4,
                (byte) -3, (byte) 5, (byte) 6, (byte) 6));
        List<Byte> bytesToDecode3 = new ArrayList<>(Arrays.asList((byte) 1));
        List<Byte> bytesToDecode4 = new ArrayList<>(
                Arrays.asList((byte) 1, (byte) 1));
        List<Byte> bytesToDecode5 = new ArrayList<>(Arrays.asList((byte) -128,
                (byte) 1, (byte) -128, (byte) 1, (byte) -128, (byte) 1,
                (byte) -128, (byte) 1, (byte) -128, (byte) 1, (byte) -128,
                (byte) 1, (byte) -128, (byte) 1, (byte) -128, (byte) 1,
                (byte) -128, (byte) 1, (byte) -89, (byte) 1));

        List<Byte> expected0 = new ArrayList<>(
                Arrays.asList((byte) 1, (byte) 2, (byte) 2, (byte) 3, (byte) 3,
                        (byte) 3, (byte) 4, (byte) 4, (byte) 4, (byte) 4,
                        (byte) 5, (byte) 5, (byte) 5, (byte) 5, (byte) 5));
        List<Byte> expected1 = new ArrayList<>(Arrays.asList((byte) 1, (byte) 2,
                (byte) 2, (byte) 3, (byte) 3, (byte) 3, (byte) 4, (byte) 4,
                (byte) 4, (byte) 4, (byte) 5, (byte) 5, (byte) 5, (byte) 5,
                (byte) 5, (byte) 6));
        List<Byte> expected2 = new ArrayList<>(Arrays.asList((byte) 1, (byte) 2,
                (byte) 2, (byte) 3, (byte) 3, (byte) 3, (byte) 4, (byte) 4,
                (byte) 4, (byte) 4, (byte) 5, (byte) 5, (byte) 5, (byte) 5,
                (byte) 5, (byte) 6, (byte) 6));
        List<Byte> expected3 = new ArrayList<>(Arrays.asList((byte) 1));
        List<Byte> expected4 = new ArrayList<>(
                Arrays.asList((byte) 1, (byte) 1));
        List<Byte> expected5 = new ArrayList<>(Arrays.asList((byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
                (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1));

        assertEquals(expected0, RunLengthEncoder.decode(bytesToDecode0));
        assertEquals(expected1, RunLengthEncoder.decode(bytesToDecode1));
        assertEquals(expected2, RunLengthEncoder.decode(bytesToDecode2));
        assertEquals(expected3, RunLengthEncoder.decode(bytesToDecode3));
        assertEquals(expected4, RunLengthEncoder.decode(bytesToDecode4));
        assertEquals(expected5, RunLengthEncoder.decode(bytesToDecode5));
    }
}