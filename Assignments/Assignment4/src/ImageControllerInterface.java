import java.awt.image.BufferedImage;
import java.io.IOException;

public interface ImageControllerInterface {
  RGB[][] loadImage(String filePath) throws IOException;

  BufferedImage arrayToImage(RGB[][] image);

  void saveImage(BufferedImage image, String filePath, String format) throws IOException;
}
