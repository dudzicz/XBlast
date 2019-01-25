package ch.epfl.xblast.client;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.function.Consumer;

import ch.epfl.xblast.PlayerAction;

/**
 * 
 * @author Antonio Morais (260428)
 * @author Damian Dudzicz (257839)
 *
 */

public class KeyboardEventHandler extends KeyAdapter {

    private Map<Integer, PlayerAction> playerActionKeys;
    private Consumer<PlayerAction> consumerAction;

    /**
     * Constructs a KeyboardEventHandler who will manage the Keyboard inputs and
     * what to do depending on what input it is.
     * 
     * @param playerActionKeys
     *            The map associating the keyboard inputs (represented by an
     *            integer) to a PlayerAction
     * @param consumerAction
     *            The consumer in order to know what to do when a key is pressed
     */
    public KeyboardEventHandler(Map<Integer, PlayerAction> playerActionKeys,
            Consumer<PlayerAction> consumerAction) {
        this.playerActionKeys = playerActionKeys;
        this.consumerAction = consumerAction;
    }

    /**
     * When a Keyboard input occurs we control if he's in the Map and if so we
     * use the consumer with the right parameter
     * 
     * @param e
     *            The event of type KeyEvent that occurs at a given time
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (playerActionKeys.containsKey(e.getKeyCode())) {
            consumerAction.accept(playerActionKeys.get(e.getKeyCode()));
        }
    }
}
