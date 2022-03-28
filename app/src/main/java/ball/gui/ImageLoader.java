package ball.gui;

import ball.physics.Dimensions;
import stage.utils.MainImagesLoader;

import java.util.Map;
import java.util.HashMap;

import java.awt.image.BufferedImage;

/**
 * this class loads the image of given ball (maybe try to disassemble for multiuse),
 * and chaches the result of the loading;
 */
public class ImageLoader {

    private Map<Dimensions, BufferedImage> entries = new HashMap<>();
    private MainImagesLoader loader = new MainImagesLoader();

    public ImageLoader() {
        this.fillIamgesCache(Dimensions.FATHER);
        this.fillIamgesCache(Dimensions.SON);
        this.fillIamgesCache(Dimensions.GRANDSON);
    }

    private void fillIamgesCache(Dimensions dim) {
        String spriteName = this.getFileNameGivenDimension(dim);
        BufferedImage image = loader.load(spriteName);
        this.entries.put(dim, image);
    }
    
    public BufferedImage getBallImage(Dimensions ball) {
        return this.entries.get(ball);
    }


    private String getFileNameGivenDimension(Dimensions dim) {
        switch (dim) {
            case FATHER:
                return "ballFather.png";
            case SON:
                return "ballSon.png";
            case GRANDSON:
                return "ballGrandSon.png";
            default:
                throw new IllegalArgumentException();
        }
    }

}
