package ch.epfl.xblast.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import javax.swing.JComponent;

import ch.epfl.xblast.Cell;
import ch.epfl.xblast.PlayerID;
import ch.epfl.xblast.client.GameState.Player;

/**
 * 
 * @author Antonio Morais (260428)
 * @author Damian Dudzicz (257839)
 *
 */

@SuppressWarnings("serial")
public final class XBlastComponent extends JComponent {

    // the width of the prefered size for the windows
    private final static int WIDTH = 960;
    // the height of the preferred size for the windows
    private final static int HEIGHT = 688;
    // the preferred dimension for the windows
    private final static Dimension PREFERRED_DIMENSION = new Dimension(WIDTH,
            HEIGHT);
    // the y position of the lives
    private final static int Y_POSITION_LIVES = 659;
    // the normally space between the lives of 2 players
    private final static int NORMAL_SPACE_BETWEEN_LIVES = 144;
    // the space between the lives of player 2 and 3
    private final static int SPACE_BETWEEN_MIDDLLE_LIVES = 528;
    // the x position of the lives of the player 1
    private final static int FIRST_X_POSITION_LIVES = 96;
    // the size of the font
    private final static int FONT_SIZE = 25;
    // function for the x component of the player
    private final static Function<Integer, Integer> PLAYER_POSITION_X = x -> 4
            * x - 24;
    // function for the y component of the player
    private final static Function<Integer, Integer> PLAYER_POSITION_Y = y -> 3
            * y - 52;
    // the gameState
    private GameState gameState;
    // the PlayerID
    private PlayerID playerID;

    /**
     * Constructs a XBlastComponent initializing the GameState and the PlayerID
     * to null
     */
    public XBlastComponent() {

    }

    /**
     * Compute the list of playerIDs in the order of rotation of this player
     * (For example for the player 3 the list is : [Player 4, Player 1, Player
     * 2, Player 3])
     * 
     * @return the rotated list
     */

    private List<PlayerID> rotatedPlayer() {
        List<PlayerID> pidList = new ArrayList<>(
                Arrays.asList(PlayerID.values()));
        while (pidList.get(pidList.size() - 1) != playerID) {
            pidList.add(0, pidList.remove(pidList.size() - 1));
        }
        return pidList;
    }

    /**
     * Paint the GameState. First we paint the Board and the Explosions, next we
     * paint the images for the scores and at the end we paint the time
     * remaining.
     * 
     * @param graphics2d
     *            the Graphics2D used to paint the GameState.
     */

    private void paintGameState(Graphics2D graphics2d) {
        List<Image> board = gameState.getBoard();
        List<Image> explosions = gameState.getExplosions();
        // Paint of the board and the Bombs/Explosions
        int x = 0, y = 0;

        for (int i = 0; i < board.size(); i++) {
            Image boardImage = board.get(i);
            x = i % Cell.COLUMNS * boardImage.getWidth(null);
            y = i / Cell.COLUMNS * boardImage.getHeight(null);
            graphics2d.drawImage(boardImage, x, y, null);
            graphics2d.drawImage(explosions.get(i), x, y, null);
        }
        // Paint of the images for the score
        x = 0;
        y += board.get(x * y).getHeight(null);
        for (Image i : gameState.getScore()) {
            graphics2d.drawImage(i, x, y, null);
            x += i.getWidth(null);
        }
        // Paint of the time
        x = 0;
        y += gameState.getScore().get(0).getHeight(null);
        for (Image i : gameState.getTime()) {
            graphics2d.drawImage(i, x, y, null);
            x += i.getWidth(null);
        }
    }

    /**
     * Paint the lives remaining of the players
     * 
     * @param graphics2d
     *            the Graphics2D used to paint the lives.
     */
    private void paintLives(Graphics2D graphics2d) {
        Font font = new Font("Arial", Font.BOLD, FONT_SIZE);
        graphics2d.setColor(Color.WHITE);
        graphics2d.setFont(font);
        int x = FIRST_X_POSITION_LIVES;
        for (Player p : gameState.getPlayers()) {
            graphics2d.drawString(String.valueOf(p.lives()), x,
                    Y_POSITION_LIVES);
            if (p.id() == PlayerID.PLAYER_2)
                x += SPACE_BETWEEN_MIDDLLE_LIVES;
            else
                x += NORMAL_SPACE_BETWEEN_LIVES;
        }
    }

    /**
     * Paint the players in precise order depending on the playerID. First we
     * paint the ones that have a lower y position, if they have the same y
     * position we paint in the order given by the rotated player list and at
     * the end we paint the player with the bigger y position. Doing so they
     * will appear in front of the others
     * 
     * @param graphics2d
     *            the Graphics2D used to paint the players.
     */

    private void paintPlayers(Graphics2D graphics2d) {
        List<Player> listPlayer = new ArrayList<>(gameState.getPlayers());
        Comparator<Player> comparatorY = (p1, p2) -> Integer.compare(p1.getY(),
                p2.getY());
        List<PlayerID> rotatedPlayers = rotatedPlayer();
        Comparator<Player> comparatorRotated = (p1, p2) -> Integer.compare(
                rotatedPlayers.indexOf(p1.id()), rotatedPlayers.indexOf(p2.id()));
        Collections.sort(listPlayer,
                comparatorY.thenComparing(comparatorRotated));
        for (Player player : listPlayer) {
            int xs = PLAYER_POSITION_X.apply(player.getX());
            int ys = PLAYER_POSITION_Y.apply(player.getY());
            graphics2d.drawImage(player.getImage(), xs, ys, null);
        }
    }

    /**
     * Paint the game. First we paint the GameState (board, explosions, score
     * and time), next the lives of the player and at the end we paint the
     * players.
     */

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2d = (Graphics2D) g;
        if (gameState != null) {
            paintGameState(graphics2d);

            paintLives(graphics2d);

            paintPlayers(graphics2d);
        }
    }

    /**
     * Returns the preferred size of this component
     * 
     * @return the preferred size of this component
     */

    @Override
    public Dimension getPreferredSize() {
        return PREFERRED_DIMENSION;
    }

    /**
     * Set the GameState and the playerID at their new value given as
     * parameters. And updates the graphics by calling the repaint method
     * 
     * @param gameState
     *            The new gameState that we want to paint
     * @param playerID
     *            The playerID of the player that want to paint the GameState
     */

    public void setGameState(GameState gameState, PlayerID playerID) {
        this.gameState = gameState;
        this.playerID = playerID;
        repaint();
    }

}
