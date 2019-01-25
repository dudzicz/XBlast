package ch.epfl.xblast;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ch.epfl.cs108.Sq;
import ch.epfl.xblast.server.Player;
import ch.epfl.xblast.server.Player.DirectedPosition;
import ch.epfl.xblast.server.Player.LifeState;
import ch.epfl.xblast.server.Player.LifeState.State;
import ch.epfl.xblast.server.PlayerPainter;

/**
 * This test tests the class PlayerPainter.
 * 
 * @author Maire Cedric (259314)
 * @author Délèze Benjamin (259992)
 *
 */

public class PlayerPainterTestOwn {

    @Test
    public void byteForPlayer() {
        // DEAD AF
        assertEquals(
                PlayerPainter.byteForPlayer(1,
                        new Player(PlayerID.PLAYER_1, 0, new Cell(0, 0), 5, 5)),
                14);

        // White
        LifeState l = new LifeState(2, State.INVULNERABLE);
        Sq<LifeState> lsq = Sq.constant(l);
        DirectedPosition d = new DirectedPosition(new SubCell(4, 8),
                Direction.W);
        Sq<DirectedPosition> dsq = Sq.constant(d);
        assertTrue(PlayerPainter.byteForPlayer(1,
                new Player(PlayerID.PLAYER_1, lsq, dsq, 5, 2)) > 73
                && PlayerPainter.byteForPlayer(1,
                        new Player(PlayerID.PLAYER_1, lsq, dsq, 5, 2)) < 92);
        // Not white (even ticks)
        assertTrue(!(PlayerPainter.byteForPlayer(2,
                new Player(PlayerID.PLAYER_1, lsq, dsq, 5, 2)) > 73
                && PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_1, lsq, dsq, 5, 2)) < 92));

        // Basic player move
        LifeState l1 = new LifeState(2, State.VULNERABLE);
        // Dying
        LifeState l2 = new LifeState(2, State.DYING);
        // DIE
        LifeState l3 = new LifeState(1, State.DYING);

        Sq<LifeState> l1sq = Sq.constant(l1);
        Sq<LifeState> l2sq = Sq.constant(l2);
        Sq<LifeState> l3sq = Sq.constant(l3);
        // Directed position north premiere image
        DirectedPosition north1 = new DirectedPosition(new SubCell(10, 10),
                Direction.N);
        Sq<DirectedPosition> north1Sq = Sq.constant(north1);
        // Directed position north deuxieme image
        DirectedPosition north2 = new DirectedPosition(new SubCell(10, 5),
                Direction.N);
        Sq<DirectedPosition> north2Sq = Sq.constant(north2);
        // Directed position north troisieme image
        DirectedPosition north3 = new DirectedPosition(new SubCell(10, 7),
                Direction.N);
        Sq<DirectedPosition> north3Sq = Sq.constant(north3);

        // Directed position south premiere image
        DirectedPosition south1 = new DirectedPosition(new SubCell(10, 10),
                Direction.S);
        Sq<DirectedPosition> south1Sq = Sq.constant(south1);
        // Directed position south deuxieme image
        DirectedPosition south2 = new DirectedPosition(new SubCell(10, 5),
                Direction.S);
        Sq<DirectedPosition> south2Sq = Sq.constant(south2);
        // Directed position south troisieme image
        DirectedPosition south3 = new DirectedPosition(new SubCell(10, 7),
                Direction.S);
        Sq<DirectedPosition> south3Sq = Sq.constant(south3);

        // Directed position east premiere image
        DirectedPosition east1 = new DirectedPosition(new SubCell(10, 10),
                Direction.E);
        Sq<DirectedPosition> east1Sq = Sq.constant(east1);
        // Directed position east deuxieme image
        DirectedPosition east2 = new DirectedPosition(new SubCell(5, 10),
                Direction.E);
        Sq<DirectedPosition> east2Sq = Sq.constant(east2);
        // Directed position east troisieme image
        DirectedPosition east3 = new DirectedPosition(new SubCell(7, 10),
                Direction.E);
        Sq<DirectedPosition> east3Sq = Sq.constant(east3);

        // Directed position west premiere image
        DirectedPosition west1 = new DirectedPosition(new SubCell(10, 10),
                Direction.W);
        Sq<DirectedPosition> west1Sq = Sq.constant(west1);
        // Directed position east deuxieme image
        DirectedPosition west2 = new DirectedPosition(new SubCell(5, 10),
                Direction.W);
        Sq<DirectedPosition> west2Sq = Sq.constant(west2);
        // Directed position east troisieme image
        DirectedPosition west3 = new DirectedPosition(new SubCell(7, 10),
                Direction.W);
        Sq<DirectedPosition> west3Sq = Sq.constant(west3);

        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_1, l1sq, north1Sq, 2, 2)),
                0);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_1, l1sq, north2Sq, 2, 2)),
                1);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_1, l1sq, north3Sq, 2, 2)),
                2);
        assertEquals(PlayerPainter.byteForPlayer(2,
                new Player(PlayerID.PLAYER_1, l1sq, east1Sq, 2, 2)), 3);
        assertEquals(PlayerPainter.byteForPlayer(2,
                new Player(PlayerID.PLAYER_1, l1sq, east2Sq, 2, 2)), 4);
        assertEquals(PlayerPainter.byteForPlayer(2,
                new Player(PlayerID.PLAYER_1, l1sq, east3Sq, 2, 2)), 5);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_1, l1sq, south1Sq, 2, 2)),
                6);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_1, l1sq, south2Sq, 2, 2)),
                7);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_1, l1sq, south3Sq, 2, 2)),
                8);
        assertEquals(PlayerPainter.byteForPlayer(2,
                new Player(PlayerID.PLAYER_1, l1sq, west1Sq, 2, 2)), 9);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_1, l1sq, west2Sq, 2, 2)),
                10);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_1, l1sq, west3Sq, 2, 2)),
                11);

        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_1, l2sq, north1Sq, 2, 2)),
                12);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_1, l3sq, north1Sq, 2, 2)),
                13);

        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_2, l1sq, north1Sq, 2, 2)),
                20);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_2, l1sq, north2Sq, 2, 2)),
                21);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_2, l1sq, north3Sq, 2, 2)),
                22);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_2, l1sq, east1Sq, 2, 2)),
                23);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_2, l1sq, east2Sq, 2, 2)),
                24);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_2, l1sq, east3Sq, 2, 2)),
                25);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_2, l1sq, south1Sq, 2, 2)),
                26);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_2, l1sq, south2Sq, 2, 2)),
                27);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_2, l1sq, south3Sq, 2, 2)),
                28);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_2, l1sq, west1Sq, 2, 2)),
                29);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_2, l1sq, west2Sq, 2, 2)),
                30);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_2, l1sq, west3Sq, 2, 2)),
                31);

        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_2, l2sq, north1Sq, 2, 2)),
                32);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_2, l3sq, north1Sq, 2, 2)),
                33);

        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_3, l1sq, north1Sq, 2, 2)),
                40);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_3, l1sq, north2Sq, 2, 2)),
                41);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_3, l1sq, north3Sq, 2, 2)),
                42);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_3, l1sq, east1Sq, 2, 2)),
                43);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_3, l1sq, east2Sq, 2, 2)),
                44);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_3, l1sq, east3Sq, 2, 2)),
                45);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_3, l1sq, south1Sq, 2, 2)),
                46);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_3, l1sq, south2Sq, 2, 2)),
                47);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_3, l1sq, south3Sq, 2, 2)),
                48);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_3, l1sq, west1Sq, 2, 2)),
                49);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_3, l1sq, west2Sq, 2, 2)),
                50);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_3, l1sq, west3Sq, 2, 2)),
                51);

        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_3, l2sq, north1Sq, 2, 2)),
                52);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_3, l3sq, north1Sq, 2, 2)),
                53);

        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_4, l1sq, north1Sq, 2, 2)),
                60);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_4, l1sq, north2Sq, 2, 2)),
                61);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_4, l1sq, north3Sq, 2, 2)),
                62);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_4, l1sq, east1Sq, 2, 2)),
                63);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_4, l1sq, east2Sq, 2, 2)),
                64);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_4, l1sq, east3Sq, 2, 2)),
                65);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_4, l1sq, south1Sq, 2, 2)),
                66);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_4, l1sq, south2Sq, 2, 2)),
                67);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_4, l1sq, south3Sq, 2, 2)),
                68);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_4, l1sq, west1Sq, 2, 2)),
                69);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_4, l1sq, west2Sq, 2, 2)),
                70);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_4, l1sq, west3Sq, 2, 2)),
                71);

        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_4, l2sq, north1Sq, 2, 2)),
                72);
        assertEquals(
                PlayerPainter.byteForPlayer(2,
                        new Player(PlayerID.PLAYER_4, l3sq, north1Sq, 2, 2)),
                73);
    }
}