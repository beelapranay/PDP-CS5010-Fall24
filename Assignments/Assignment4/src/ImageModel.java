import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageModel implements ImageModelInterface {
  final private double[][] blurKernel = {
          {1.0 / 16, 1.0 / 8, 1.0 / 16},
          {1.0 / 8, 1.0 / 4, 1.0 / 8},
          {1.0 / 16, 1.0 / 8, 1.0 / 16}
  };

  final private double[][] sharpenKernel = {
          {-1.0/8, -1.0/8, -1.0/8, -1.0/8, -1.0/8},
          {-1.0/8,  1.0/4,  1.0/4,  1.0/4, -1.0/8},
          {-1.0/8,  1.0/4,    1.0,  1.0/4, -1.0/8},
          {-1.0/8,  1.0/4,  1.0/4,  1.0/4, -1.0/8},
          {-1.0/8, -1.0/8, -1.0/8, -1.0/8, -1.0/8}
  };

  @Override
  public RGB[][] applySepiaTone(RGB[][] pixelArray) {
    for (int y = 0; y < pixelArray.length; y++) {
      for (int x = 0; x < pixelArray[y].length; x++) {
        RGB pixel = pixelArray[y][x];
        int r = pixel.red;
        int g = pixel.green;
        int b = pixel.blue;

        double v = 0.393 * r + 0.769 * g + 0.189 * b;
        int newRed = (int) Math.min(v, 255);
        int newGreen = (int) Math.min(v, 255);
        int newBlue = (int) Math.min(v, 255);

        pixelArray [y][x] = new RGB(newRed, newGreen, newBlue);
      }
    }
    return pixelArray;
  }

  @Override
  public RGB[][] convertToGreyscale(RGB[][] pixelArray) {
    for (int y = 0; y < pixelArray.length; y++) {
      for (int x = 0; x < pixelArray[y].length; x++) {
        RGB pixel = pixelArray[y][x];
        int r = pixel.red;
        int g = pixel.green;
        int b = pixel.blue;

        double v = 0.2126 * r + 0.7152 * g + 0.0722 * b;
        int newRed = (int) Math.min(v, 255);
        int newGreen = (int) Math.min(v, 255);
        int newBlue = (int) Math.min(v, 255);

        pixelArray [y][x] = new RGB(newRed, newGreen, newBlue);
      }
    }
    return pixelArray;
  }

  @Override
  public RGB[][] blurImage(RGB[][] pixelArray) {
    int width = pixelArray[0].length;
    int height = pixelArray.length;
    RGB[][] blurredArray = new RGB[height][width];

    for (int y = 1; y < height - 1; y++) {
      for (int x = 1; x < width - 1; x++) {

        double red = 0, green = 0, blue = 0;

        for (int ky = 0; ky < 3; ky++) {
          for (int kx = 0; kx < 3; kx++) {
            int pixelX = x + kx - 1;
            int pixelY = y + ky - 1;

            RGB pixel = pixelArray[pixelY][pixelX];

            red += pixel.red * blurKernel[ky][kx];
            green += pixel.green * blurKernel[ky][kx];
            blue += pixel.blue * blurKernel[ky][kx];
          }
        }

        int newRed = (int) Math.min(Math.max(red, 0), 255);
        int newGreen = (int) Math.min(Math.max(green, 0), 255);
        int newBlue = (int) Math.min(Math.max(blue, 0), 255);

        blurredArray[y][x] = new RGB(newRed, newGreen, newBlue);
      }
    }

    //populating the new array with the values we missed on in the beginning
    for (int y = 0; y < height; y++) {
      blurredArray[y][0] = pixelArray[y][0];
      blurredArray[y][width - 1] = pixelArray[y][width - 1];
    }
    for (int x = 0; x < width; x++) {
      blurredArray[0][x] = pixelArray[0][x];
      blurredArray[height - 1][x] = pixelArray[height - 1][x];
    }

    return blurredArray;
  }

  @Override
  public RGB[][] sharpenImage(RGB[][] pixelArray) {
    int width = pixelArray[0].length;
    int height = pixelArray.length;
    RGB[][] sharpenedArray = new RGB[height][width];

    for (int y = 1; y < height - 1; y++) {
      for (int x = 1; x < width - 1; x++) {

        double red = 0.0;
        double green = 0.0;
        double blue = 0.0;

        for (int ky = 0; ky < 3; ky++) {
          for (int kx = 0; kx < 3; kx++) {
            int pixelX = x + kx - 1;
            int pixelY = y + ky - 1;

            RGB pixel = pixelArray[pixelY][pixelX];

            red += pixel.red * sharpenKernel[ky][kx];
            green += pixel.green * sharpenKernel[ky][kx];
            blue += pixel.blue * sharpenKernel[ky][kx];
          }
        }

        int newRed = (int) Math.min(Math.max(red, 0), 255);
        int newGreen = (int) Math.min(Math.max(green, 0), 255);
        int newBlue = (int) Math.min(Math.max(blue, 0), 255);

        sharpenedArray[y][x] = new RGB(newRed, newGreen, newBlue);
      }
    }

    //populating the new array with the values we missed on in the beginning
    for (int y = 0; y < height; y++) {
      sharpenedArray[y][0] = pixelArray[y][0];
      sharpenedArray[y][width - 1] = pixelArray[y][width - 1];
    }
    for (int x = 0; x < width; x++) {
      sharpenedArray[0][x] = pixelArray[0][x];
      sharpenedArray[height - 1][x] = pixelArray[height - 1][x];
    }

    return sharpenedArray;
  }

  @Override
  public RGB[][] brightenImage(RGB[][] pixelArray, int value) {
    int width = pixelArray[0].length;
    int height = pixelArray.length;

    RGB[][] brightenedArray = new RGB[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int newRed = 0;
        int newGreen = 0;
        int newBlue = 0;

        newRed = Math.min(Math.max(value + pixelArray[y][x].red, 0), 255);
        newGreen = Math.min(Math.max(value + pixelArray[y][x].green, 0), 255);
        newBlue = Math.min(Math.max(value + pixelArray[y][x].blue, 0), 255);

        brightenedArray[y][x] = new RGB(newRed, newGreen, newBlue);
      }
    }
    return brightenedArray;
  }

  @Override
  public RGB[][] darkenImage(RGB[][] pixelArray, int value) {
    int width = pixelArray[0].length;
    int height = pixelArray.length;

    RGB[][] darkenedArray = new RGB[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int newRed = 0;
        int newGreen = 0;
        int newBlue = 0;

        newRed = Math.min(Math.max(pixelArray[y][x].red - value, 0), 255);
        newGreen = Math.min(Math.max(pixelArray[y][x].green - value, 0), 255);
        newBlue = Math.min(Math.max(pixelArray[y][x].blue - value, 0), 255);

        darkenedArray[y][x] = new RGB(newRed, newGreen, newBlue);
      }
    }
    return darkenedArray;
  }

  @Override
  public RGB[][] horizontalFlipImage(RGB[][] pixelArray) {
    int width = pixelArray[0].length;
    int height = pixelArray.length;

    RGB[][] flippedArray = new RGB[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        flippedArray[y][x] = pixelArray[y][width - x - 1];
      }
    }
    return flippedArray;
  }

  @Override
  public RGB[][] verticalFlipImage(RGB[][] pixelArray) {
    int width = pixelArray[0].length;
    int height = pixelArray.length;

    RGB[][] flippedArray = new RGB[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        flippedArray[y][x] = pixelArray[height - y - 1][x];
      }
    }
    return flippedArray;
  }

  @Override
  public RGB[][][] splitImageChannels(RGB[][] pixelArray) {
    int width = pixelArray[0].length;
    int height = pixelArray.length;

    RGB[][] redChannel = new RGB[height][width];
    RGB[][] greenChannel = new RGB[height][width];
    RGB[][] blueChannel = new RGB[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        RGB pixel = pixelArray[y][x];

        redChannel[y][x] = new RGB(pixel.red, 0, 0);
        greenChannel[y][x] = new RGB(0, pixel.green, 0);
        blueChannel[y][x] = new RGB(0, 0, pixel.blue);
      }
    }

    return new RGB[][][]{redChannel, greenChannel, blueChannel};
  }

  public static void main(String[] args) {
    ImageControllerInterface controller = new ImageController();
    ImageModelInterface model = new ImageModel();

    try {
      RGB[][] pixelArray = controller.loadImage("src/random.jpg");

      RGB[][][] channels = model.splitImageChannels(pixelArray);

      String[] colorNames = {"red", "green", "blue"};

      for (int i = 0; i < 3; i++) {
        controller.saveImage(controller.arrayToImage(channels[i]),
                "src/" + colorNames[i] + "-image.jpg", "jpg");
      }

    } catch (IOException e) {
      System.out.println("Error loading image: " + e.getMessage());
    }
  }
}