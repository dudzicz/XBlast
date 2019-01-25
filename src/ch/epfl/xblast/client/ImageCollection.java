package ch.epfl.xblast.client;

import java.awt.Image;
import java.io.File;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.imageio.ImageIO;

/**
 * 
 * @author Antonio Morais (260428)
 * @author Damian Dudzicz (257839)
 *
 */
public final class ImageCollection {

    private final Map<Integer, Image> loadedFiles;

    /**
     * We create an ImageCollection. The HashMap that we create in this
     * constructor gives the image corresponding to the index that we gave.
     * 
     * @param directoryName
     *            The name from the file in which there is the images that we
     *            want to load.
     */

    public ImageCollection(String directoryName) {

        Map<Integer, Image> loadedFilesInConstruct = new HashMap<>();
        try {
            File dir = new File(ImageCollection.class.getClassLoader()
                    .getResource(directoryName).toURI());
            for (File f : dir.listFiles()) {
                try{
                Image image = ImageIO.read(f);
                int index = Integer.parseInt(f.getName().substring(0, 3));
                loadedFilesInConstruct.put(index, image);
                }
                catch(Exception e){                    
                }
            }
        } catch (URISyntaxException e) {
        }
        loadedFiles = Collections.unmodifiableMap(loadedFilesInConstruct);
    }

    /**
     * If the Map contains the index of the image returns it, otherwise throw a
     * NoSuchElementException.
     * 
     * @param index
     *            The number of the image that we want to receive.
     * @return the image corresponding to the given number.
     */
    public Image image(int index) {
        if (loadedFiles.containsKey(index)) {
            return loadedFiles.get(index);
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * If the Map contains the index of the image returns it, otherwise returns
     * null. (We use here the fact that if there is no element associated to a
     * key the value returned is null).
     * 
     * @param index
     *            The number of the image that we want to receive.
     * @return the image corresponding to the given number or null if there is
     *         no correspondence..
     */
    public Image imageOrNull(int index) {
        return loadedFiles.get(index);
    }

}
