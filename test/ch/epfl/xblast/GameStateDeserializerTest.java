package ch.epfl.xblast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import ch.epfl.xblast.client.GameState;
import ch.epfl.xblast.client.GameStateDeserializer;

/**
 * This test tests the class GameStateDeserializer.
 * 
 * @author Maire Cedric (259314)
 * @author Délèze Benjamin (259992)
 *
 */

public class GameStateDeserializerTest {

    @Test
    public void deserializeTest() {
        List<Byte> serial0 = new ArrayList<>(Arrays.asList((byte) 121,
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

        List<Byte> serial1 = new ArrayList<>(Arrays.asList((byte) 4,
                (byte) -128, (byte) 16, (byte) -63, (byte) 16));

        List<Byte> serial2 = new ArrayList<>(Arrays.asList((byte) 3, (byte) 24,
                (byte) 24, (byte) 6, (byte) 3, (byte) -40, (byte) 24, (byte) 26,
                (byte) 3, (byte) -40, (byte) -72, (byte) 46, (byte) 3,
                (byte) 24, (byte) -72, (byte) 66));

        List<Byte> serial3 = new ArrayList<>(Arrays.asList((byte) 60));

        List<Byte> serial = new ArrayList<>(serial0);
        serial.addAll(serial1);
        serial.addAll(serial2);
        serial.addAll(serial3);

        @SuppressWarnings("unused")
        GameState gameState = GameStateDeserializer.deserializeGameState(serial);
    }
}