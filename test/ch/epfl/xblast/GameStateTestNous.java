package ch.epfl.xblast;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import ch.epfl.cs108.Sq;
import ch.epfl.xblast.Cell;
import ch.epfl.xblast.PlayerID;
import ch.epfl.xblast.server.Block;
import ch.epfl.xblast.server.Board;
import ch.epfl.xblast.server.Bomb;
import ch.epfl.xblast.server.GameState;
import ch.epfl.xblast.server.Player;
import ch.epfl.xblast.server.Ticks;

/**
 * This test tests the class GameState.
 * 
 * @author Maire Cedric (259314)
 * @author Délèze Benjamin (259992)
 *
 */

public class GameStateTestNous {
    
    @Test
    public void ticksGetterTest() {
        List<Player> player = new ArrayList<>();
        player.add(new Player(PlayerID.PLAYER_1, 3, new Cell(0,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_2, 3, new Cell(1,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_3, 3, new Cell(2,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_4, 3, new Cell(3,0), 3, 4));
        
        List<List<Block>> temporaryList = new ArrayList<List<Block>>();
        
        for (int i = 0; i < Cell.ROWS ; i++) {
            temporaryList.add(Collections.nCopies(Cell.COLUMNS, Block.FREE));
        }
        
        GameState temporaryGameState = new GameState(45, Board.ofRows(temporaryList), player, new ArrayList<Bomb>(), new ArrayList<Sq<Sq<Cell>>>(),  new ArrayList<Sq<Cell>>());
        
        assertTrue(temporaryGameState.ticks() == 45);
        
    }
    
    @Test
    public void isGameOverEveryBodyIsDeadTest() {
        List<Player> player = new ArrayList<>();
        player.add(new Player(PlayerID.PLAYER_1, 0, new Cell(0,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_2, 0, new Cell(1,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_3, 0, new Cell(2,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_4, 0, new Cell(3,0), 3, 4));

        List<List<Block>> temporaryList = new ArrayList<List<Block>>();
        
        for (int i = 0; i < Cell.ROWS ; i++) {
            temporaryList.add(Collections.nCopies(Cell.COLUMNS, Block.FREE));
        }
        
        GameState temporaryGameState = new GameState(45, Board.ofRows(temporaryList), player, new ArrayList<Bomb>(), new ArrayList<Sq<Sq<Cell>>>(),  new ArrayList<Sq<Cell>>());
        
        assertTrue(temporaryGameState.isGameOver());
        
    }
    
    @Test
    public void isGameOverOnlyOneRemainingTest() {
        List<Player> player = new ArrayList<>();
        player.add(new Player(PlayerID.PLAYER_1, 0, new Cell(0,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_2, 0, new Cell(1,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_3, 2, new Cell(2,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_4, 0, new Cell(3,0), 3, 4));
        
        List<List<Block>> temporaryList = new ArrayList<List<Block>>();
        
        for (int i = 0; i < Cell.ROWS ; i++) {
            temporaryList.add(Collections.nCopies(Cell.COLUMNS, Block.FREE));
        }
        
        GameState temporaryGameState = new GameState(45, Board.ofRows(temporaryList), player, new ArrayList<Bomb>(), new ArrayList<Sq<Sq<Cell>>>(),  new ArrayList<Sq<Cell>>());
        
        assertTrue(temporaryGameState.isGameOver());
        
    }
    
    @Test
    public void isGameOverFailTest() {
        List<Player> player = new ArrayList<>();
        player.add(new Player(PlayerID.PLAYER_1, 0, new Cell(0,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_2, 3, new Cell(1,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_3, 2, new Cell(2,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_4, 0, new Cell(3,0), 3, 4));
        
        List<List<Block>> temporaryList = new ArrayList<List<Block>>();
        
        for (int i = 0; i < Cell.ROWS ; i++) {
            temporaryList.add(Collections.nCopies(Cell.COLUMNS, Block.FREE));
        }
        
        GameState temporaryGameState = new GameState(45, Board.ofRows(temporaryList), player, new ArrayList<Bomb>(), new ArrayList<Sq<Sq<Cell>>>(),  new ArrayList<Sq<Cell>>());
        
        assertTrue(!temporaryGameState.isGameOver());
    }
    
    @Test
    public void isGameOverTicksTest() {
        List<Player> player = new ArrayList<>();
        player.add(new Player(PlayerID.PLAYER_1, 0, new Cell(0,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_2, 3, new Cell(1,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_3, 2, new Cell(2,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_4, 0, new Cell(3,0), 3, 4));
        
        List<List<Block>> temporaryList = new ArrayList<List<Block>>();
        
        for (int i = 0; i < Cell.ROWS ; i++) {
            temporaryList.add(Collections.nCopies(Cell.COLUMNS, Block.FREE));
        }
        
        GameState temporaryGameState = new GameState(Ticks.TOTAL_TICKS + 1, Board.ofRows(temporaryList), player, new ArrayList<Bomb>(), new ArrayList<Sq<Sq<Cell>>>(),  new ArrayList<Sq<Cell>>());
        
        assertTrue(temporaryGameState.isGameOver());
    }
    
    @Test
    public void remainingTimeTest() {
        List<Player> player = new ArrayList<>();
        player.add(new Player(PlayerID.PLAYER_1, 0, new Cell(0,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_2, 3, new Cell(1,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_3, 2, new Cell(2,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_4, 0, new Cell(3,0), 3, 4));
        
        List<List<Block>> temporaryList = new ArrayList<List<Block>>();
        
        for (int i = 0; i < Cell.ROWS ; i++) {
            temporaryList.add(Collections.nCopies(Cell.COLUMNS, Block.FREE));
        }
        
        GameState temporaryGameState = new GameState(780, Board.ofRows(temporaryList), player, new ArrayList<Bomb>(), new ArrayList<Sq<Sq<Cell>>>(),  new ArrayList<Sq<Cell>>());
        
        assertEquals(81.0, temporaryGameState.remainingTime(), 0.000001);
    }
    
    @Test
    public void winnerSucceedTest() {
        List<Player> player = new ArrayList<>();
        player.add(new Player(PlayerID.PLAYER_1, 0, new Cell(0,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_2, 0, new Cell(1,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_3, 2, new Cell(2,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_4, 0, new Cell(3,0), 3, 4));
        
        List<List<Block>> temporaryList = new ArrayList<List<Block>>();
        
        for (int i = 0; i < Cell.ROWS ; i++) {
            temporaryList.add(Collections.nCopies(Cell.COLUMNS, Block.FREE));
        }
        
        GameState temporaryGameState = new GameState(780, Board.ofRows(temporaryList), player, new ArrayList<Bomb>(), new ArrayList<Sq<Sq<Cell>>>(),  new ArrayList<Sq<Cell>>());
        
        assertTrue(temporaryGameState.winner().isPresent());
    }
    
    @Test
    public void winnerFailTest() {
        List<Player> player = new ArrayList<>();
        player.add(new Player(PlayerID.PLAYER_1, 6, new Cell(0,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_2, 0, new Cell(1,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_3, 2, new Cell(2,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_4, 9, new Cell(3,0), 3, 4));
        
        List<List<Block>> temporaryList = new ArrayList<List<Block>>();
        
        for (int i = 0; i < Cell.ROWS ; i++) {
            temporaryList.add(Collections.nCopies(Cell.COLUMNS, Block.FREE));
        }
        
        GameState temporaryGameState = new GameState(780, Board.ofRows(temporaryList), player, new ArrayList<Bomb>(), new ArrayList<Sq<Sq<Cell>>>(),  new ArrayList<Sq<Cell>>());
        
        assertTrue(!(temporaryGameState.winner().isPresent()));
    }
    
    @Test
    public void alivePlayersSucceedTest() {
        List<Player> player = new ArrayList<>();
        player.add(new Player(PlayerID.PLAYER_1, 3, new Cell(0,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_2, 3, new Cell(1,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_3, 3, new Cell(2,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_4, 0, new Cell(3,0), 3, 4));
        
        List<List<Block>> temporaryList = new ArrayList<List<Block>>();
        
        for (int i = 0; i < Cell.ROWS ; i++) {
            temporaryList.add(Collections.nCopies(Cell.COLUMNS, Block.FREE));
        }
        
        GameState temporaryGameState = new GameState(780, Board.ofRows(temporaryList), player, new ArrayList<Bomb>(), new ArrayList<Sq<Sq<Cell>>>(),  new ArrayList<Sq<Cell>>());
        
        assertTrue(temporaryGameState.alivePlayers().size() == 3);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void constructorTicksFailTest() {
        List<Player> player = new ArrayList<>();
        player.add(new Player(PlayerID.PLAYER_1, 3, new Cell(0,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_2, 3, new Cell(1,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_3, 3, new Cell(2,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_4, 0, new Cell(3,0), 3, 4));
        
        List<List<Block>> temporaryList = new ArrayList<List<Block>>();
        
        for (int i = 0; i < Cell.ROWS ; i++) {
            temporaryList.add(Collections.nCopies(Cell.COLUMNS, Block.FREE));
        }
        
        @SuppressWarnings("unused")
        GameState temporaryGameState = new GameState(-78, Board.ofRows(temporaryList), player, new ArrayList<Bomb>(), new ArrayList<Sq<Sq<Cell>>>(),  new ArrayList<Sq<Cell>>());
    }
    
    @Test (expected = NullPointerException.class)
    public void constructorBoardFailTest() {
        List<Player> player = new ArrayList<>();
        player.add(new Player(PlayerID.PLAYER_1, 3, new Cell(0,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_2, 3, new Cell(1,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_3, 3, new Cell(2,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_4, 0, new Cell(3,0), 3, 4));
        
        List<List<Block>> temporaryList = new ArrayList<List<Block>>();
        
        for (int i = 0; i < Cell.ROWS ; i++) {
            temporaryList.add(Collections.nCopies(Cell.COLUMNS, Block.FREE));
        }
        
        @SuppressWarnings("unused")
        GameState temporaryGameState = new GameState(78, null, player, new ArrayList<Bomb>(), new ArrayList<Sq<Sq<Cell>>>(),  new ArrayList<Sq<Cell>>());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void constructorPlayerNumberFailTest() {
        List<Player> player = new ArrayList<>();
        player.add(new Player(PlayerID.PLAYER_1, 3, new Cell(0,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_2, 3, new Cell(1,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_3, 3, new Cell(2,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_4, 0, new Cell(3,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_4, 0, new Cell(3,0), 3, 4));
        
        List<List<Block>> temporaryList = new ArrayList<List<Block>>();
        
        for (int i = 0; i < Cell.ROWS ; i++) {
            temporaryList.add(Collections.nCopies(Cell.COLUMNS, Block.FREE));
        }
        
        @SuppressWarnings("unused")
        GameState temporaryGameState = new GameState(78, Board.ofRows(temporaryList), player, new ArrayList<Bomb>(), new ArrayList<Sq<Sq<Cell>>>(),  new ArrayList<Sq<Cell>>());
    }
    
    @Test (expected = NullPointerException.class)
    public void constructorPlayerNullFailTest() {
        List<Player> player = new ArrayList<>();
        player.add(new Player(PlayerID.PLAYER_1, 3, new Cell(0,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_2, 3, new Cell(1,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_3, 3, new Cell(2,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_4, 0, new Cell(3,0), 3, 4));
        
        List<List<Block>> temporaryList = new ArrayList<List<Block>>();
        
        for (int i = 0; i < Cell.ROWS ; i++) {
            temporaryList.add(Collections.nCopies(Cell.COLUMNS, Block.FREE));
        }
        
        @SuppressWarnings("unused")
        GameState temporaryGameState = new GameState(78, Board.ofRows(temporaryList), null, new ArrayList<Bomb>(), new ArrayList<Sq<Sq<Cell>>>(),  new ArrayList<Sq<Cell>>());
    }
    
    @Test (expected = NullPointerException.class)
    public void constructorBombNullFailTest() {
        List<Player> player = new ArrayList<>();
        player.add(new Player(PlayerID.PLAYER_1, 3, new Cell(0,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_2, 3, new Cell(1,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_3, 3, new Cell(2,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_4, 0, new Cell(3,0), 3, 4));
        
        List<List<Block>> temporaryList = new ArrayList<List<Block>>();
        
        for (int i = 0; i < Cell.ROWS ; i++) {
            temporaryList.add(Collections.nCopies(Cell.COLUMNS, Block.FREE));
        }
        
        @SuppressWarnings("unused")
        GameState temporaryGameState = new GameState(78, Board.ofRows(temporaryList), player, null, new ArrayList<Sq<Sq<Cell>>>(),  new ArrayList<Sq<Cell>>());
    }
    
    @Test (expected = NullPointerException.class)
    public void constructorExplosionsNullFailTest() {
        List<Player> player = new ArrayList<>();
        player.add(new Player(PlayerID.PLAYER_1, 3, new Cell(0,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_2, 3, new Cell(1,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_3, 3, new Cell(2,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_4, 0, new Cell(3,0), 3, 4));
        
        List<List<Block>> temporaryList = new ArrayList<List<Block>>();
        
        for (int i = 0; i < Cell.ROWS ; i++) {
            temporaryList.add(Collections.nCopies(Cell.COLUMNS, Block.FREE));
        }
        
        @SuppressWarnings("unused")
        GameState temporaryGameState = new GameState(78, Board.ofRows(temporaryList), player, new ArrayList<Bomb>(), null,  new ArrayList<Sq<Cell>>());
    }
    
    @Test (expected = NullPointerException.class)
    public void constructorBlastsNullFailTest() {
        List<Player> player = new ArrayList<>();
        player.add(new Player(PlayerID.PLAYER_1, 3, new Cell(0,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_2, 3, new Cell(1,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_3, 3, new Cell(2,0), 3, 4));
        player.add(new Player(PlayerID.PLAYER_4, 0, new Cell(3,0), 3, 4));
        
        List<List<Block>> temporaryList = new ArrayList<List<Block>>();
        
        for (int i = 0; i < Cell.ROWS ; i++) {
            temporaryList.add(Collections.nCopies(Cell.COLUMNS, Block.FREE));
        }
        
        @SuppressWarnings("unused")
        GameState temporaryGameState = new GameState(78, Board.ofRows(temporaryList), player, new ArrayList<Bomb>(), new ArrayList<Sq<Sq<Cell>>>(),  null);
    }
}