import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageController implements ImageControllerInterface {

  @Override
  public RGB[][] loadImage(String filePath) throws IOException {
    BufferedImage image = ImageIO.read(new File(filePath));

    int width = image.getWidth();
    int height = image.getHeight();

    RGB[][] pixelArray = new RGB[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int rgb = image.getRGB(x, y);

        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = rgb & 0xFF;

        pixelArray[y][x] = new RGB(red, green, blue);
      }
    }

    return pixelArray;
  }

  @Override
  public BufferedImage arrayToImage(RGB[][] image) {
    int width = image[0].length;
    int height = image.length;

    BufferedImage processedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        RGB pixel = image[y][x];

        int rgb = (pixel.red << 16) | (pixel.green << 8) | pixel.blue;

        processedImage.setRGB(x, y, rgb);
      }
    }

    return processedImage;
  }

  @Override
  public void saveImage(BufferedImage image, String filePath, String format) {
    try {
      File outputfile = new File(filePath);

      ImageIO.write(image, format, outputfile);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
