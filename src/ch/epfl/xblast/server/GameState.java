package ch.epfl.xblast.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import ch.epfl.cs108.Sq;
import ch.epfl.xblast.ArgumentChecker;
import ch.epfl.xblast.Cell;
import ch.epfl.xblast.Direction;
import ch.epfl.xblast.Lists;
import ch.epfl.xblast.PlayerID;
import ch.epfl.xblast.SubCell;
import ch.epfl.xblast.server.Player.DirectedPosition;
import ch.epfl.xblast.server.Player.LifeState;
import ch.epfl.xblast.server.Player.LifeState.State;

/**
 * 
 * @author Antonio Morais (260428)
 * @author Damian Dudzicz (257839)
 *
 */

public final class GameState {

    private final int ticks;
    private final Board board;
    private final List<Player> players;
    private final List<Bomb> bombs;
    private final List<Sq<Sq<Cell>>> explosions;
    private final List<Sq<Cell>> blasts;
    private static final Random RANDOM = new Random(2016);
    private static final List<List<PlayerID>> PLAYERS_PERMUTATION = Lists
            .permutations(Arrays.asList(PlayerID.values()));
    private static final int DISTANCE_BLOCKED_BY_BOMB = 6;

    /**
     * Construct a GameState.
     * 
     * @param ticks
     *            The number of ticks at the current moment of the creation.
     * @param board
     *            The board in which the game is currently playing.
     * @param players
     *            The players of the game
     * @param bombs
     *            The Bombs that are in the game at this moment.
     * @param explosions
     *            The explosions that happen at this moment.
     * @param blasts
     *            The blasts at this moment.
     */

    public GameState(int ticks, Board board, List<Player> players,
            List<Bomb> bombs, List<Sq<Sq<Cell>>> explosions,
            List<Sq<Cell>> blasts) {
        this.ticks = ArgumentChecker.requireNonNegative(ticks);
        this.board = Objects.requireNonNull(board);
        this.bombs = Collections.unmodifiableList(
                new ArrayList<>(Objects.requireNonNull(bombs)));
        this.explosions = Collections.unmodifiableList(
                new ArrayList<>(Objects.requireNonNull(explosions)));
        this.blasts = Collections.unmodifiableList(
                new ArrayList<>(Objects.requireNonNull(blasts)));
        if (Objects.requireNonNull(players)
                .size() == PlayerID.values().length) {
            this.players = Collections.unmodifiableList(
                    new ArrayList<>(Objects.requireNonNull(players)));
        } else
            throw new IllegalArgumentException();
    }

    /**
     * Constructs the first GameState.
     * 
     * @param board
     *            The first Board.
     * @param players
     *            The players of the game.
     */

    public GameState(Board board, List<Player> players) {
        this(0, board, players, new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>());
    }

    /**
     * Returns the number of ticks of the current GameState.
     * 
     * @return the number of ticks of the current GameState.
     */

    public int ticks() {
        return ticks;
    }

    /**
     * Returns true if the Game is over (if the number of ticks is over the
     * maximum number of ticks or if there is at most 1 player), false
     * otherwise.
     * 
     * @return true if the Game is over, false otherwise.
     */

    public boolean isGameOver() {
        return ticks() > Ticks.TOTAL_TICKS || alivePlayers().size() <= 1;
    }

    /**
     * Returns the remaining time in seconds of the current game.
     * 
     * @return the remaining time in seconds of the current game.
     */

    public double remainingTime() {
        return (double) (Ticks.TOTAL_TICKS - ticks()) / Ticks.TICKS_PER_SECOND;
    }

    /**
     * Returns the Winner if there is one.
     * 
     * @return the Winner if there is one.
     */

    public Optional<PlayerID> winner() {
        return alivePlayers().size() == 1
                ? Optional.of(alivePlayers().get(0).id()) : Optional.empty();
    }

    /**
     * Returns the current Board.
     * 
     * @return the current Board.
     */

    public Board board() {
        return board;
    }

    /**
     * Returns the list of Players.
     * 
     * @return the list of Players.
     */

    public List<Player> players() {
        return players;
    }

    /**
     * Returns the List of the players that are currently alive.
     * 
     * @return the List of the players that are currently alive.
     */

    public List<Player> alivePlayers() {
        List<Player> listOfAlivePlayers = new ArrayList<>();
        for (Player p : players) {
            if (p.isAlive()) {
                listOfAlivePlayers.add(p);
            }
        }
        return Collections
                .unmodifiableList(new ArrayList<>(listOfAlivePlayers));
    }

    /**
     * Returns the hashMap which associates to every cell the corresponding
     * bomb.
     * 
     * @return the hashMap which associates to every cell the corresponding
     *         bomb.
     */

    public Map<Cell, Bomb> bombedCells() {
        Map<Cell, Bomb> bombedCellsMap = new HashMap<>();
        for (Bomb b : bombs) {
            bombedCellsMap.put(b.position(), b);
        }

        return Collections.unmodifiableMap(new HashMap<>(bombedCellsMap));
    }

    /**
     * Returns the set containing all the Cells in which there is at least one
     * blast.
     * 
     * @return the set containing all the Cells in which there is at least one
     *         blast
     */

    public Set<Cell> blastedCells() {
        Set<Cell> blastedCellsSet = new HashSet<>();
        for (Sq<Cell> bl : blasts) {
            blastedCellsSet.add(bl.head());
        }

        return Collections.unmodifiableSet(new HashSet<>(blastedCellsSet));

    }

    /**
     * Returns the List of sequences of the next blasts given the current list
     * of sequences of blasts, the board and explosions. First we add the next
     * state of the sequence of the current blast0, if its tail is not empty and
     * the cell where it is is free. We add then the first sequence of the new
     * blasts of the explosions0 taking place on the board. Eventually, we
     * return the list of sequences (as it is private we don't need to make it
     * immutable).
     * 
     * @param blasts0
     *            The list of the current Blasts.
     * @param board0
     *            The current Board.
     * @param explosions0
     *            The explosions that happened at this moment.
     * @return the List of sequences of the next blasts.
     */

    private static List<Sq<Cell>> nextBlasts(List<Sq<Cell>> blasts0,
            Board board0, List<Sq<Sq<Cell>>> explosions0) {
        List<Sq<Cell>> listOfBlasts = new ArrayList<>();
        for (Sq<Cell> b : blasts0) {
            if (!b.tail().isEmpty() && board0.blockAt(b.head()).isFree()) {
                listOfBlasts.add(b.tail());
            }
        }

        for (Sq<Sq<Cell>> e : explosions0) {
            listOfBlasts.add(e.head());
        }

        return listOfBlasts;
    }

    /**
     * It is an auxiliary method used in the method @nextBoard to simplify the
     * code of this method. We use a random generator and we specify the
     * corresponding sequence of block to the integer value which has to be 0,
     * 1, 2.
     * 
     * @return a constant sequence of Block based on a random value
     */

    private static Sq<Block> randomBlock() {
        int randomValue = RANDOM.nextInt(3);
        switch (randomValue) {
        case 0:
            return Sq.constant(Block.BONUS_BOMB);
        case 1:
            return Sq.constant(Block.BONUS_RANGE);
        default:
            return Sq.constant(Block.FREE);
        }
    }

    /**
     * We create the Board for the next tick using a list of sequence of Block
     * (used in the constructor of the board). We complete this list by
     * iterating on every cell using the list ROW_MAJOR_ORDER that we created in
     * the class Cell. And we add the corresponding evolved sequence of Block .
     * (@see randomBlock())
     * 
     * @param board0
     *            the current Board
     * @param consumedBonuses
     *            the list of the bonuses by the players on the current tick
     * @param blastedCells1
     *            the list of cells on which there is at least 1 blast
     * @return the Board for the next tick
     */

    private static Board nextBoard(Board board0, Set<Cell> consumedBonuses,
            Set<Cell> blastedCells1) {
        List<Sq<Block>> constructedBoard = new ArrayList<>();
        for (Cell c : Cell.ROW_MAJOR_ORDER) {
            if (consumedBonuses.contains(c)) {
                constructedBoard.add(Sq.constant(Block.FREE));
            }
            // Evolution of the blocks that are touched by a blast
            else if (blastedCells1.contains(c)) {
                if (board0.blockAt(c) == Block.DESTRUCTIBLE_WALL) {
                    constructedBoard.add(Sq
                            .repeat(Ticks.WALL_CRUMBLING_TICKS,
                                    Block.CRUMBLING_WALL)
                            .concat(randomBlock()));
                } else if (board0.blockAt(c).isBonus()) {
                    constructedBoard.add(board0.blocksAt(c)
                            .limit(Ticks.BONUS_DISAPPEARING_TICKS + 1)
                            .concat(Sq.constant(Block.FREE)).tail());
                } else {
                    constructedBoard.add(board0.blocksAt(c).tail());
                }
            } else {
                constructedBoard.add(board0.blocksAt(c).tail());
            }
        }
        return new Board(constructedBoard);
    }

    /**
     * The method returns the list of the evolved players for next GameState
     * taking in account all the factors of the game i.e: the list of the
     * previous players, the bonuses taken, the bombs on the board, the blasts
     * particles and the inputs of direction change of the physical players. We
     * first make evolve the directedpositions, after the lifestates and
     * eventually we apply the bonuses.
     * 
     * @param players0
     *            The list of the players of the previous GameState
     * @param playerBonuses
     *            The map which associates to each player his taken bonuses
     * @param bombedCells1
     *            The cells with a bomb on it, taking in account the newly
     *            dropped bombs
     * @param board1
     *            The new board taking in consideration the modifications
     *            occured (bonus apparition, wall crumbled, ...)
     * @param blastedCells1
     *            The set of all the cells with a blast on them taking in
     *            account the newly appeared ones from the explosions
     * @param speedChangeEvents
     *            The map associating the eventual direction change with the
     *            concerned player
     * @return the list of the evolved players
     */
    private static List<Player> nextPlayers(List<Player> players0,
            Map<PlayerID, Bonus> playerBonuses, Set<Cell> bombedCells1,
            Board board1, Set<Cell> blastedCells1,
            Map<PlayerID, Optional<Direction>> speedChangeEvents) {

        List<Player> players1 = new ArrayList<>();

        for (Player p : players0) {
            // Evolution DirectedPosition
            Sq<DirectedPosition> tempDirPos;
            // Case : input
            if (speedChangeEvents.containsKey(p.id())) {
                // Compute of a sequence and a subcell that will be useful
                Sq<DirectedPosition> SqUntilFirstCentral = p.directedPositions()
                        .takeWhile(u -> !u.position().isCentral());
                SubCell firstCentralSubCell = p.directedPositions()
                        .findFirst(u -> u.position().isCentral()).position();
                // Case : the player want to move
                if (speedChangeEvents.get(p.id()).isPresent()) {
                    // Direction change : Parallel case
                    if (p.direction().isParallelTo(
                            speedChangeEvents.get(p.id()).get())) {
                        tempDirPos = DirectedPosition.moving(
                                p.directedPositions().head().withDirection(
                                        speedChangeEvents.get(p.id()).get()));
                    }
                    // Direction change : orthogonal case
                    else {
                        tempDirPos = SqUntilFirstCentral.concat(
                                DirectedPosition.moving(new DirectedPosition(
                                        firstCentralSubCell, speedChangeEvents
                                                .get(p.id()).get())));
                    }
                }
                // Case : the player want to stop
                else {

                    tempDirPos = SqUntilFirstCentral.concat(
                            DirectedPosition.stopped(p.directedPositions()
                                    .head().withPosition(firstCentralSubCell)));
                }
            }
            // Case : no input
            else {
                tempDirPos = p.directedPositions();
            }
            
            Sq<DirectedPosition> sequenceDirPos = tempDirPos;

            // Evolution of the DirectedPosition if permitted
            if (p.lifeState().canMove()) {
                // we find the next cell the player is facing
                Cell nextCell = p.position().containingCell()
                        .neighbor(tempDirPos.head().direction());
                // Blocked by a wall
                if (p.position().isCentral()
                        && !board1.blockAt(nextCell).canHostPlayer()) {
                    sequenceDirPos = tempDirPos;
                }
                // Blocked by a bomb
                else if (bombedCells1.contains(p.position().containingCell())
                        && p.position()
                                .distanceToCentral() == DISTANCE_BLOCKED_BY_BOMB
                        && tempDirPos.tail().head().position()
                                .distanceToCentral() == (DISTANCE_BLOCKED_BY_BOMB
                                        - 1)) {
                    sequenceDirPos = tempDirPos;
                } else {
                    sequenceDirPos = tempDirPos.tail();
                }
            }
            Sq<LifeState> tempLifeState;
            // Evolution of the LifeState
            if (blastedCells1
                    .contains(sequenceDirPos.head().position().containingCell())
                    && p.lifeState().state() == State.VULNERABLE) {
                tempLifeState = p.statesForNextLife();
            } else {
                tempLifeState = p.lifeStates().tail();
            }
            Player tempNextPlayer = new Player(p.id(), tempLifeState,
                    sequenceDirPos, p.maxBombs(), p.bombRange());
            // Application of an eventual Bonus
            if (playerBonuses.containsKey(p.id())) {
                tempNextPlayer = playerBonuses.get(p.id())
                        .applyTo(tempNextPlayer);
            }
            players1.add(tempNextPlayer);
        }
        return players1;

    }

    /**
     * For every sequence of explosion particles we check if the tail
     * corresponding to the next explosion particle is not empty and if so we
     * add this sequence of particles.
     * 
     * @param explosion0
     *            the explosion of the current tick
     * @return a list of sequence of sequence of cells containing all the
     *         explosions
     */

    private static List<Sq<Sq<Cell>>> nextExplosions(
            List<Sq<Sq<Cell>>> explosion0) {
        List<Sq<Sq<Cell>>> nextExplosionList = new ArrayList<>();
        for (Sq<Sq<Cell>> b : explosion0) {
            if (!b.tail().isEmpty()) {
                nextExplosionList.add(b.tail());
            }
        }
        return nextExplosionList;
    }

    /**
     * This method returns the list of the bombs newly dropped by the players by
     * verifying if the drop is permitted.
     * 
     * @param players0
     *            The list of the players in the order of permutation
     * @param bombDropEvents
     *            A set of the player that want to put down a bomb
     * @param bombs0
     *            The bombs that are already on the board.
     * @return the list of the bombs newly dropped by the players on the current
     *         tick
     */

    private static List<Bomb> newlyDroppedBombs(List<Player> players0,
            Set<PlayerID> bombDropEvents, List<Bomb> bombs0) {
        List<Bomb> newBombs = new ArrayList<>();
        Map<PlayerID, Integer> bombsOnBoard = new HashMap<>();
        Set<Cell> positionOfBombs = new HashSet<>();

        // Hashmap associating a player to his number of Bombs on the board
        for (PlayerID pid : PlayerID.values()) {
            bombsOnBoard.put(pid, 0);
        }
        for (Bomb b : bombs0) {
            positionOfBombs.add(b.position());
            bombsOnBoard.replace(b.ownerId(),
                    bombsOnBoard.get(b.ownerId()) + 1);
        }

        // Adding of the bombs if possible
        for (Player p : players0) {
            Cell cellPlayer = p.position().containingCell();
            if (bombDropEvents.contains(p.id()) && p.isAlive()
                    && bombsOnBoard.get(p.id()) < p.maxBombs()
                    && !(positionOfBombs.contains(cellPlayer))) {

                newBombs.add(p.newBomb());
                positionOfBombs.add(cellPlayer);
            }
        }

        return newBombs;

    }

    /**
     * The method next use the other next methods to compute the next GameState.
     * First we evolve the blasts, next the board, next the explosions and the
     * bombs, eventually the players. At the end creation of the new gamestate.
     * 
     * @param speedChangeEvents
     *            A map that associates the player that want to change direction
     *            and the direction where they want to go
     * @param bombDropEvents
     *            the set of the players which have dropped a bomb
     * @return the next GameState
     */
    public GameState next(Map<PlayerID, Optional<Direction>> speedChangeEvents,
            Set<PlayerID> bombDropEvents) {

        List<Sq<Cell>> blasts1 = nextBlasts(blasts, board(), explosions);
        Set<Cell> blastedCells = new HashSet<>();
        for (Sq<Cell> sqc : blasts1) {
            blastedCells.add(sqc.head());
        }
        Map<PlayerID, Player> playerMap = new HashMap<>();
        // Map associating an id to the correspondingPlayer
        for (Player p : players()) {
            playerMap.put(p.id(), p);
        }
        List<Player> players0 = new ArrayList<>();
        // We create a list of the players in the priority order
        for (PlayerID playerid : PLAYERS_PERMUTATION
                .get(ticks() % PLAYERS_PERMUTATION.size())) {
            players0.add(playerMap.get(playerid));
        }
        Set<Cell> consumedBonuses = new HashSet<>();
        Map<PlayerID, Bonus> playerBonuses = new HashMap<>();

        // Creating the map playerBonuses taking on account the priority order
        for (Player p : players0) {
            Cell cellPlayer = p.position().containingCell();
            if (board().blockAt(cellPlayer).isBonus()
                    && p.position().isCentral()
                    && !consumedBonuses.contains(cellPlayer)) {
                playerBonuses.put(p.id(),
                        board().blockAt(cellPlayer).associatedBonus());
                consumedBonuses.add(cellPlayer);
            }
        }

        Board board1 = nextBoard(board(), consumedBonuses, blastedCells);
        List<Sq<Sq<Cell>>> explosions1 = nextExplosions(explosions);
        List<Bomb> bombs0 = new ArrayList<>(bombs);
        bombs0.addAll(newlyDroppedBombs(players0, bombDropEvents, bombs));
        List<Bomb> bombs1 = new ArrayList<>();
        Set<Cell> bombedCells1 = new HashSet<>();

        // Evolution of bombs and explosions
        for (Bomb b : bombs0) {
            if (blastedCells.contains(b.position()) || b.fuseLength() <= 1 || b.fuseLengths().isEmpty()) {
                explosions1.addAll(b.explosion());
            } else {
                bombedCells1.add(b.position());
                bombs1.add(new Bomb(b.ownerId(), b.position(),
                        b.fuseLengths().tail(), b.range()));
            }
        }
        List<Player> players1 = nextPlayers(players(), playerBonuses,
                bombedCells1, board1, blastedCells, speedChangeEvents);
        return new GameState(ticks() + 1, board1, players1, bombs1, explosions1,
                blasts1);
    }
}
