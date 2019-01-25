package ch.epfl.xblast.client;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ch.epfl.xblast.Cell;
import ch.epfl.xblast.PlayerID;
import ch.epfl.xblast.RunLengthEncoder;
import ch.epfl.xblast.SubCell;
import ch.epfl.xblast.client.GameState.Player;

/**
 * 
 * @author Antonio Morais (260428)
 * @author Damian Dudzicz (257839)
 *
 */

public final class GameStateDeserializer {

    // the collection of the images representing the players
    private final static ImageCollection IMAGES_PLAYER = new ImageCollection(
            "player");
    // the collection of the images representing the elements of the board
    private final static ImageCollection IMAGES_BOARD = new ImageCollection(
            "block");
    // the collection of the images representing the bombs and explosions
    private final static ImageCollection IMAGES_BOMBS_AND_EXPLOSIONS = new ImageCollection(
            "explosion");
    // the collection of the images representing the score
    private final static ImageCollection IMAGES_SCORE = new ImageCollection(
            "score");
    // The number of images that can represent a player in the score
    private final static int NUMBER_SCORE_IMAGES_PER_PLAYER = 2;
    // the number of the image for the middle text of each player
    private final static int MIDDLE_TEXT_IMAGE = 10;
    // the number of the image for the right text of each player
    private final static int RIGHT_TEXT_IMAGE = 11;
    // The number of images that fill the empty space between player 2 and 3
    private final static int NUMBER_FILLING_IMAGES = 8;
    // The number of the empty image
    private final static int EMPTY_IMAGE = 12;
    // the number of bytes to represent a player
    private final static int SIZE_ONE_PLAYER = 4;
    // the size of the list that we need for deserializing the players
    private final static int SIZE_PLAYERS = PlayerID.values().length
            * SIZE_ONE_PLAYER;
    // the image of the led when it's on
    private final static int IMAGE_LED_ON = 21;
    // the image of the led when it's off
    private final static int IMAGE_LED_OFF = 20;
    // the total number of iamges representing 2 seconds in the time line
    private final static int TOTAL_IMAGES_TIME = 60;

    /**
     * GameStateDeserializer is not instanciable.
     */
    private GameStateDeserializer() {
    }

    /**
     * The method that deserialize the list of byte received from the server
     * which represents the compressed board. We receive a list of byte in the
     * spiral order but we return a list of images in the row-major order.
     * 
     * @param serializedBoard
     *            the list of byte representing the serializeBoard
     * @return the list of the images representing the board
     */

    private static List<Image> deserializeBoard(List<Byte> serializedBoard) {
        List<Byte> decodedBoard = RunLengthEncoder.decode(serializedBoard);
        Image[] arrayBoardImages = new Image[Cell.COUNT];

        for (int i = 0; i < decodedBoard.size(); i++) {
            arrayBoardImages[Cell.SPIRAL_ORDER.get(i)
                    .rowMajorIndex()] = IMAGES_BOARD.image(decodedBoard.get(i));
        }
        return Arrays.asList(arrayBoardImages);
    }

    /**
     * The method that deserialize the list of byte received from the server
     * which represents the compressed bombs and explosions.
     * 
     * @param serializedBombsAndExplosions
     *            the list of byte representing the bombs and explosions
     * @return the list of the images representing the bombs and explosions.
     */

    private static List<Image> deserializeBombsAndExplosions(
            List<Byte> serializedBombsAndExplosions) {
        List<Byte> decodedBombsAndExplosions = RunLengthEncoder
                .decode(serializedBombsAndExplosions);
        List<Image> bombsAndExplosionsImages = new ArrayList<>();
        for (byte b : decodedBombsAndExplosions) {
            bombsAndExplosionsImages
                    .add(IMAGES_BOMBS_AND_EXPLOSIONS.imageOrNull(b));
        }
        return bombsAndExplosionsImages;
    }

    /**
     * The method that deserialize the list of byte received from the server
     * which represents the players.
     * 
     * @param serializedPlayer
     *            the list of byte representing the players
     * @return the list of the images representing the players.
     */
    private static List<Player> deserializePlayers(
            List<Byte> serializedPlayer) {
        List<Player> listOfDeserializedPlayers = new ArrayList<>();
        for (int i = 0; i < serializedPlayer.size(); i = i + SIZE_ONE_PLAYER) {
            listOfDeserializedPlayers.add(new Player(
                    PlayerID.values()[i / SIZE_ONE_PLAYER],
                    serializedPlayer.get(i),
                    new SubCell(Byte.toUnsignedInt(serializedPlayer.get(i + 1)),
                            Byte.toUnsignedInt(serializedPlayer.get(i + 2))),
                    IMAGES_PLAYER.imageOrNull(serializedPlayer.get(i + 3))));
        }
        return listOfDeserializedPlayers;
    }

    /**
     * The method that creates a list of image representing the score thanks to
     * the list of the players.
     * 
     * @param listOfPlayers
     *            The list of the players
     * @return the list of image representing the score.
     */
    private static List<Image> deserializeImageScore(
            List<Player> listOfPlayers) {
        List<Image> listImageScore = new ArrayList<>();

        for (Player p : listOfPlayers) {
            if (p.isAlive()) {
                listImageScore.add(IMAGES_SCORE.image(
                        p.id().ordinal() * NUMBER_SCORE_IMAGES_PER_PLAYER));
            } else {
                listImageScore.add(IMAGES_SCORE.image(
                        p.id().ordinal() * NUMBER_SCORE_IMAGES_PER_PLAYER + 1));
            }
            listImageScore.add(IMAGES_SCORE.image(MIDDLE_TEXT_IMAGE));
            listImageScore.add(IMAGES_SCORE.image(RIGHT_TEXT_IMAGE));
            if (p.id() == PlayerID.PLAYER_2) {
                listImageScore.addAll(Collections.nCopies(NUMBER_FILLING_IMAGES,
                        IMAGES_SCORE.image(EMPTY_IMAGE)));
            }
        }
        return listImageScore;
    }

    /**
     * Constructs a gameState using the list of images obtained with the other
     * private methods deserialize. We use a unique sublist because it allows us
     * to select the consecutive ranges of bytes corresponding to the compressed
     * part of the Gamestate that we want to deserialize with the given
     * auxiliary method and also it gives a view on the list. This way if we
     * remove some elements from the sublist they will also be removed from the
     * list itself or if we clear the sublist then the list will be cleared too
     * which make easier the selection of the next list of needed bytes for the
     * parameter of the auxiliary methods.
     * 
     * @param serializedGameState
     *            The list of bytes representing a compressed GameState.
     * @return the gameState at the current GameState
     */

    public static GameState deserializeGameState(
            List<Byte> serializedGameState) {
        List<Byte> workingCopy = new ArrayList<>(serializedGameState);
        // Deserializing the Board
        List<Byte> sublist = workingCopy.subList(1, workingCopy.get(0) + 1);
        List<Image> imagesBoard = deserializeBoard(sublist);
        sublist.clear();
        workingCopy.remove(0);
        // Deserializing the bombs and explosions
        sublist = workingCopy.subList(1, workingCopy.get(0) + 1);
        List<Image> imagesBombsExplosions = deserializeBombsAndExplosions(
                sublist);
        sublist.clear();
        workingCopy.remove(0);
        // Deserializing the players
        sublist = workingCopy.subList(0, SIZE_PLAYERS);
        List<Player> players = deserializePlayers(sublist);
        sublist.clear();
        // Deserializing the time
        List<Image> imagesTime = new ArrayList<>();
        imagesTime.addAll(Collections.nCopies(workingCopy.get(0),
                IMAGES_SCORE.image(IMAGE_LED_ON)));
        imagesTime.addAll(
                Collections.nCopies(TOTAL_IMAGES_TIME - workingCopy.get(0),
                        IMAGES_SCORE.image(IMAGE_LED_OFF)));

        return new GameState(players, imagesBoard, imagesBombsExplosions,
                deserializeImageScore(players), imagesTime);
    }

}
