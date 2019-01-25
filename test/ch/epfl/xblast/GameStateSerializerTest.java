package ch.epfl.xblast;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import ch.epfl.xblast.server.GameStateSerializer;
import ch.epfl.xblast.server.Level;

/**
 * This test tests the class GameStateSerializer.
 * 
 * @author Maire Cedric (259314)
 * @author Délèze Benjamin (259992)
 *
 */

public class GameStateSerializerTest {

    @Test
    public void serializeTest() {
        Level defaultLevel = Level.DEFAULT_LEVEL;

        List<Byte> expectedSerial0 = new ArrayList<>(Arrays.asList((byte) 121,
                (byte) -50, (byte) 2, (byte) 1, (byte) -2, (byte) 0, (byte) 3,
                (byte) 1, (byte) 3, (byte) 1, (byte) -2, (byte) 0, (byte) 1,
                (byte) 1, (byte) 3, (byte) 1, (byte) 3, (byte) 1, (byte) 3,
                (byte) 1, (byte) 1, (byte) -2, (byte) 0, (byte) 1, (byte) 3,
                (byte) 1, (byte) 3, (byte) -2, (byte) 0, (byte) -1, (byte) 1,
                (byte) 3, (byte) 1, (byte) 3, (byte) 1, (byte) 3, (byte) 1,
                (byte) 1, (byte) 2, (byte) 3, (byte) 2, (byte) 3, (byte) 2,
                (byte) 3, (byte) 2, (byte) 3, (byte) 2, (byte) 3, (byte) 2,
                (byte) 3, (byte) 2, (byte) 3, (byte) 2, (byte) 3, (byte) 2,
                (byte) 3, (byte) 2, (byte) 3, (byte) 2, (byte) 3, (byte) 2,
                (byte) 3, (byte) 2, (byte) 3, (byte) 2, (byte) 3, (byte) 2,
                (byte) 3, (byte) 2, (byte) 3, (byte) 2, (byte) 3, (byte) 2,
                (byte) 3, (byte) 1, (byte) 0, (byte) 0, (byte) 3, (byte) 1,
                (byte) 3, (byte) 1, (byte) 0, (byte) 0, (byte) 1, (byte) 1,
                (byte) 3, (byte) 1, (byte) 1, (byte) 0, (byte) 0, (byte) 1,
                (byte) 3, (byte) 1, (byte) 3, (byte) 0, (byte) 0, (byte) -1,
                (byte) 1, (byte) 3, (byte) 1, (byte) 1, (byte) -5, (byte) 2,
                (byte) 3, (byte) 2, (byte) 3, (byte) -5, (byte) 2, (byte) 3,
                (byte) 2, (byte) 3, (byte) 1, (byte) -2, (byte) 0, (byte) 3,
                (byte) -2, (byte) 0, (byte) 1, (byte) 3, (byte) 2, (byte) 1,
                (byte) 2));

        List<Byte> expectedSerial1 = new ArrayList<>(Arrays.asList((byte) 4,
                (byte) -128, (byte) 16, (byte) -63, (byte) 16));

        List<Byte> expectedSerial2 = new ArrayList<>(Arrays.asList((byte) 3,
                (byte) 24, (byte) 24, (byte) 6, (byte) 3, (byte) -40, (byte) 24,
                (byte) 26, (byte) 3, (byte) -40, (byte) -72, (byte) 46,
                (byte) 3, (byte) 24, (byte) -72, (byte) 66));

        List<Byte> expectedSerial3 = new ArrayList<>(Arrays.asList((byte) 60));

        List<Byte> expectedSerial = new ArrayList<>(expectedSerial0);
        expectedSerial.addAll(expectedSerial1);
        expectedSerial.addAll(expectedSerial2);
        expectedSerial.addAll(expectedSerial3);

        List<Byte> actualSerial = GameStateSerializer.serialize(
                defaultLevel.boardPainter(), defaultLevel.gameState());

        assertEquals(expectedSerial, actualSerial);
    }
}