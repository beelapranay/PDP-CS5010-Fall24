import java.io.IOException;
import java.util.NoSuchElementException;

public class ImageModel implements ImageModelInterface {
  private final ImageController imageController = new ImageController();

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

        for (int ky = 0; ky < blurredArray[0].length; ky++) {
          for (int kx = 0; kx < blurredArray.length; kx++) {
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
  public void changeBrightness(String imageName, String destinationImageName, int value) throws NoSuchElementException {
    if (!imageController.images.containsKey(imageName)) {
      throw new NoSuchElementException("Image with name " + imageName + " not found.");
    }

    RGB[][] pixelArray = imageController.images.get(imageName);

    int width = pixelArray[0].length;
    int height = pixelArray.length;

    RGB[][] changedArray = new RGB[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int newRed;
        int newGreen;
        int newBlue;

        if (value >= 0) {
          newRed = Math.min(Math.max(value + pixelArray[y][x].red, 0), 255);
          newGreen = Math.min(Math.max(value + pixelArray[y][x].green, 0), 255);
          newBlue = Math.min(Math.max(value + pixelArray[y][x].blue, 0), 255);
        } else {
          newRed = Math.min(Math.max(pixelArray[y][x].red - value, 0), 255);
          newGreen = Math.min(Math.max(pixelArray[y][x].green - value, 0), 255);
          newBlue = Math.min(Math.max(pixelArray[y][x].blue - value, 0), 255);
        }

        changedArray[y][x] = new RGB(newRed, newGreen, newBlue);
      }
    }

    imageController.images.put(destinationImageName, changedArray);
  }

  @Override
  public void horizontalFlipImage(String imageName, String destinationImageName) throws NoSuchElementException {
    if (!imageController.images.containsKey(imageName)) {
      throw new NoSuchElementException("Image with name " + imageName + " not found.");
    }

    RGB[][] pixelArray = imageController.images.get(imageName);

    int width = pixelArray[0].length;
    int height = pixelArray.length;

    RGB[][] flippedArray = new RGB[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        flippedArray[y][x] = pixelArray[y][width - x - 1];
      }
    }

    imageController.images.put(destinationImageName, flippedArray);
  }

  @Override
  public void verticalFlipImage(String imageName, String destinationImageName) throws NoSuchElementException {
    if (!imageController.images.containsKey(imageName)) {
      throw new NoSuchElementException("Image with name " + imageName + " not found.");
    }

    RGB[][] pixelArray = imageController.images.get(imageName);

    int width = pixelArray[0].length;
    int height = pixelArray.length;

    RGB[][] flippedArray = new RGB[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        flippedArray[y][x] = pixelArray[height - y - 1][x];
      }
    }

    imageController.images.put(destinationImageName, flippedArray);
  }

  @Override
  public void splitRedChannel(String imageName, String destinationImageName) throws NoSuchElementException {
    if (!imageController.images.containsKey(imageName)) {
      throw new NoSuchElementException("Image with name " + imageName + " not found.");
    }

    RGB[][] pixelArray = imageController.images.get(imageName);

    int width = pixelArray[0].length;
    int height = pixelArray.length;

    RGB[][] redChannel = new RGB[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        RGB pixel = pixelArray[y][x];

        redChannel[y][x] = new RGB(pixel.red, 0, 0);
      }
    }

    imageController.images.put(destinationImageName, redChannel);
  }

  @Override
  public void splitGreenChannel(String imageName, String destinationImageName) throws NoSuchElementException {
    if (!imageController.images.containsKey(imageName)) {
      throw new NoSuchElementException("Image with name " + imageName + " not found.");
    }

    RGB[][] pixelArray = imageController.images.get(imageName);

    int width = pixelArray[0].length;
    int height = pixelArray.length;

    RGB[][] greenChannel = new RGB[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        RGB pixel = pixelArray[y][x];

        greenChannel[y][x] = new RGB(0, pixel.green, 0);
      }
    }

    imageController.images.put(destinationImageName, greenChannel);
  }

  @Override
  public void splitBlueChannel(String imageName, String destinationImageName) throws NoSuchElementException {
    if (!imageController.images.containsKey(imageName)) {
      throw new NoSuchElementException("Image with name " + imageName + " not found.");
    }

    RGB[][] pixelArray = imageController.images.get(imageName);

    int width = pixelArray[0].length;
    int height = pixelArray.length;

    RGB[][] blueChannel = new RGB[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        RGB pixel = pixelArray[y][x];

        blueChannel[y][x] = new RGB(0, 0, pixel.blue);
      }
    }

    imageController.images.put(destinationImageName, blueChannel);
  }

  public void calculateValue(String imageName, String destinationImageName) throws NoSuchElementException {
    if (!imageController.images.containsKey(imageName)) {
      throw new NoSuchElementException("Image with name " + imageName + " not found.");
    }

    RGB[][] pixelArray = imageController.images.get(imageName);

    int width = pixelArray[0].length;
    int height = pixelArray.length;

    RGB[][] valueImageArray = new RGB[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        RGB pixel = pixelArray[y][x];

        int value = Math.max(pixel.red, Math.max(pixel.green, pixel.blue));

        valueImageArray[y][x] = new RGB(value, value, value);
      }
    }

    imageController.images.put(destinationImageName, valueImageArray);
  }

  public void calculateIntensity(String imageName, String destinationImageName) throws NoSuchElementException {
    if (!imageController.images.containsKey(imageName)) {
      throw new NoSuchElementException("Image with name " + imageName + " not found.");
    }

    RGB[][] pixelArray = imageController.images.get(imageName);

    int width = pixelArray[0].length;
    int height = pixelArray.length;

    RGB[][] intensityImageArray = new RGB[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        RGB pixel = pixelArray[y][x];

        int intensity = (pixel.red + pixel.green + pixel.blue) / 3;

        intensityImageArray[y][x] = new RGB(intensity, intensity, intensity);
      }
    }

    imageController.images.put(destinationImageName, intensityImageArray);
  }

  public void calculateLuma(String imageName, String destinationImageName) throws NoSuchElementException {
    if (!imageController.images.containsKey(imageName)) {
      throw new NoSuchElementException("Image with name " + imageName + " not found.");
    }

    RGB[][] pixelArray = imageController.images.get(imageName);

    int width = pixelArray[0].length;
    int height = pixelArray.length;

    RGB[][] lumaImageArray = new RGB[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        RGB pixel = pixelArray[y][x];

        int luma = (int) (0.2126 * pixel.red + 0.7152 * pixel.green + 0.0722 * pixel.blue);

        lumaImageArray[y][x] = new RGB(luma, luma, luma);
      }
    }

    imageController.images.put(destinationImageName, lumaImageArray);
  }

  @Override
  public RGB[][] combineGreyscale(RGB[][] redPixelArray,
                                  RGB[][] greenPixelArray,
                                  RGB[][] bluePixelArray) {
    int width = redPixelArray[0].length;
    int height = redPixelArray.length;

    RGB[][] combinedImage = new RGB[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int red = redPixelArray[y][x].red;
        int green = greenPixelArray[y][x].green;
        int blue = bluePixelArray[y][x].blue;

        combinedImage[y][x] = new RGB(red, green, blue);
      }
    }

    return combinedImage;
  }
}