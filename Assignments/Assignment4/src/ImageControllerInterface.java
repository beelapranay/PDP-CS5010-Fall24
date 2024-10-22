import java.awt.image.BufferedImage;
import java.io.IOException;

public interface ImageControllerInterface {
  void loadImage(String filePath, String imageName) throws IOException;

  BufferedImage arrayToImage(RGB[][] image);

  void saveImage(String filePath, String imageName) throws IOException;

  void commandParser(String command) throws IOException;

  void processCommands(String[] tokens) throws IOException;
}
