package ch.epfl.xblast.server.debug;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import javax.swing.JFrame;

import ch.epfl.xblast.PlayerAction;
import ch.epfl.xblast.PlayerID;
import ch.epfl.xblast.Time;
import ch.epfl.xblast.client.GameStateDeserializer;
import ch.epfl.xblast.client.KeyboardEventHandler;
import ch.epfl.xblast.client.XBlastComponent;
import ch.epfl.xblast.server.BoardPainter;
import ch.epfl.xblast.server.GameState;
import ch.epfl.xblast.server.GameStateSerializer;
import ch.epfl.xblast.server.Level;
import ch.epfl.xblast.server.Ticks;

public final class GameStatePrinter {
    private static final RandomEventGenerator randomEventGenerator = new RandomEventGenerator(2016, 30, 100);

    public static void main(String[] args) throws InterruptedException {
        JFrame j = new JFrame("XBlast 2k16");

        Container c = j.getContentPane();
        c.setPreferredSize(new Dimension(960, 688));
        j.pack();
        j.setResizable(false);

        j.setVisible(true);

        XBlastComponent xbc = new XBlastComponent();
        j.add(xbc, BorderLayout.CENTER);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Map<Integer, PlayerAction> kb = new HashMap<>();
        Consumer<PlayerAction> cons = System.out::println;
        kb.put(KeyEvent.VK_UP, PlayerAction.MOVE_N);
        kb.put(KeyEvent.VK_RIGHT, PlayerAction.MOVE_E);
        kb.put(KeyEvent.VK_DOWN, PlayerAction.MOVE_S);
        kb.put(KeyEvent.VK_LEFT, PlayerAction.MOVE_W);
        kb.put(KeyEvent.VK_SPACE, PlayerAction.DROP_BOMB);
        kb.put(KeyEvent.VK_SHIFT, PlayerAction.STOP);

        xbc.addKeyListener(new KeyboardEventHandler(kb, cons));
        xbc.requestFocusInWindow();

        GameState s = Level.DEFAULT_LEVEL.gameState();
        BoardPainter b = Level.DEFAULT_LEVEL.boardPainter();

        while (!s.isGameOver()) {
            Thread.sleep(Time.MS_PER_S / Ticks.TICKS_PER_SECOND);

            xbc.setGameState(
                    GameStateDeserializer
                            .deserializeGameState(GameStateSerializer.serialize(b, s)),
                    PlayerID.PLAYER_2);
            s = s.next(randomEventGenerator.randomSpeedChangeEvents(),
                    randomEventGenerator.randomBombDropEvents());
        }
    }
}
