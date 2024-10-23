package model;

import java.util.NoSuchElementException;

import controller.ImageController;

/**
 * ImageModel class implements image processing operations like sepia tone, blur, sharpen, and brightness adjustment.
 */
public class ImageModel implements ImageModelInterface {
  private final ImageController imageController;

  /**
   * Constructor that initializes the image controller.
   *
   * @param imageController the controller to interact with images
   */
  public ImageModel(ImageController imageController) {
    this.imageController = imageController;
  }

  final private double[][] blurKernel = {
          {1.0 / 16, 1.0 / 8, 1.0 / 16},
          {1.0 / 8, 1.0 / 4, 1.0 / 8},
          {1.0 / 16, 1.0 / 8, 1.0 / 16}
  };

  final private double[][] sharpenKernel = {
          {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8},
          {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
          {-1.0 / 8, 1.0 / 4, 1.0, 1.0 / 4, -1.0 / 8},
          {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
          {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8}
  };

  /**
   * Applies sepia tone effect to an image.
   *
   * @param imageName            the name of the image to process
   * @param destinationImageName the name to save the processed image
   * @throws NoSuchElementException if the image is not found
   */
  @Override
  public void applySepiaTone(String imageName, String destinationImageName)
          throws NoSuchElementException {
    if (!imageController.getImages().containsKey(imageName)) {
      throw new NoSuchElementException();
    }

    RGB[][] pixelArray = imageController.getImages().get(imageName);

    int width = pixelArray[0].length;
    int height = pixelArray.length;
    RGB[][] sepiaArray = new RGB[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        RGB pixel = pixelArray[y][x];
        int r = pixel.red;
        int g = pixel.green;
        int b = pixel.blue;

        int newRed = (int) Math.min(0.393 * r + 0.769 * g + 0.189 * b, 255);
        int newGreen = (int) Math.min(0.349 * r + 0.686 * g + 0.168 * b, 255);
        int newBlue = (int) Math.min(0.272 * r + 0.534 * g + 0.131 * b, 255);

        sepiaArray[y][x] = new RGB(newRed, newGreen, newBlue);
      }
    }

    imageController.getImages().put(destinationImageName, sepiaArray);
  }

  /**
   * Applies blur effect to an image.
   *
   * @param imageName            the name of the image to process
   * @param destinationImageName the name to save the processed image
   * @throws NoSuchElementException if the image is not found
   */
  @Override
  public void blurImage(String imageName, String destinationImageName)
          throws NoSuchElementException {
    if (!imageController.getImages().containsKey(imageName)) {
      throw new NoSuchElementException();
    }

    RGB[][] pixelArray = imageController.getImages().get(imageName);

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

    for (int y = 0; y < height; y++) {
      blurredArray[y][0] = pixelArray[y][0];
      blurredArray[y][width - 1] = pixelArray[y][width - 1];
    }
    for (int x = 0; x < width; x++) {
      blurredArray[0][x] = pixelArray[0][x];
      blurredArray[height - 1][x] = pixelArray[height - 1][x];
    }

    imageController.getImages().put(destinationImageName, blurredArray);
  }

  /**
   * Applies to sharpen effect to an image.
   *
   * @param imageName            the name of the image to process
   * @param destinationImageName the name to save the processed image
   * @throws NoSuchElementException if the image is not found
   */
  @Override
  public void sharpenImage(String imageName, String destinationImageName)
          throws NoSuchElementException {
    if (!imageController.getImages().containsKey(imageName)) {
      throw new NoSuchElementException();
    }

    RGB[][] pixelArray = imageController.getImages().get(imageName);

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

    for (int y = 0; y < height; y++) {
      sharpenedArray[y][0] = pixelArray[y][0];
      sharpenedArray[y][width - 1] = pixelArray[y][width - 1];
    }
    for (int x = 0; x < width; x++) {
      sharpenedArray[0][x] = pixelArray[0][x];
      sharpenedArray[height - 1][x] = pixelArray[height - 1][x];
    }

    imageController.getImages().put(destinationImageName, sharpenedArray);
  }

  /**
   * Changes the brightness of an image.
   *
   * @param imageName            the name of the image to process
   * @param destinationImageName the name to save the processed image
   * @param value                the brightness value to apply
   * @throws NoSuchElementException if the image is not found
   */
  @Override
  public void changeBrightness(String imageName, String destinationImageName, int value)
          throws NoSuchElementException {
    if (!imageController.getImages().containsKey(imageName)) {
      throw new NoSuchElementException();
    }

    RGB[][] pixelArray = imageController.getImages().get(imageName);

    int width = pixelArray[0].length;
    int height = pixelArray.length;

    RGB[][] changedArray = new RGB[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int newRed = Math.min(Math.max(value + pixelArray[y][x].red, 0), 255);
        int newGreen = Math.min(Math.max(value + pixelArray[y][x].green, 0), 255);
        int newBlue = Math.min(Math.max(value + pixelArray[y][x].blue, 0), 255);

        changedArray[y][x] = new RGB(newRed, newGreen, newBlue);
      }
    }

    imageController.getImages().put(destinationImageName, changedArray);
  }

  /**
   * Flips the image horizontally.
   *
   * @param imageName            the name of the image to flip
   * @param destinationImageName the name to save the flipped image
   * @throws NoSuchElementException if the image is not found
   */
  @Override
  public void horizontalFlipImage(String imageName, String destinationImageName)
          throws NoSuchElementException {
    if (!imageController.getImages().containsKey(imageName)) {
      throw new NoSuchElementException();
    }

    RGB[][] pixelArray = imageController.getImages().get(imageName);
    int width = pixelArray[0].length;
    int height = pixelArray.length;

    RGB[][] flippedArray = new RGB[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        flippedArray[y][x] = pixelArray[y][width - x - 1];
      }
    }

    imageController.getImages().put(destinationImageName, flippedArray);
  }

  /**
   * Flips the image vertically.
   *
   * @param imageName            the name of the image to flip
   * @param destinationImageName the name to save the flipped image
   * @throws NoSuchElementException if the image is not found
   */
  @Override
  public void verticalFlipImage(String imageName, String destinationImageName)
          throws NoSuchElementException {
    if (!imageController.getImages().containsKey(imageName)) {
      throw new NoSuchElementException();
    }

    RGB[][] pixelArray = imageController.getImages().get(imageName);
    int width = pixelArray[0].length;
    int height = pixelArray.length;

    RGB[][] flippedArray = new RGB[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        flippedArray[y][x] = pixelArray[height - y - 1][x];
      }
    }

    imageController.getImages().put(destinationImageName, flippedArray);
  }

  /**
   * Splits the red channel from the image and creates a greyscale image with red channel values.
   *
   * @param imageName            the name of the image to process
   * @param destinationImageName the name to save the processed image
   * @throws NoSuchElementException if the image is not found
   */
  @Override
  public void splitRedChannel(String imageName, String destinationImageName)
          throws NoSuchElementException {
    if (!imageController.getImages().containsKey(imageName)) {
      throw new NoSuchElementException();
    }

    RGB[][] pixelArray = imageController.getImages().get(imageName);
    int width = pixelArray[0].length;
    int height = pixelArray.length;

    RGB[][] redChannel = new RGB[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        RGB pixel = pixelArray[y][x];
        redChannel[y][x] = new RGB(pixel.red, pixel.red, pixel.red);
      }
    }

    imageController.getImages().put(destinationImageName, redChannel);
  }

  /**
   * Splits the green channel from the image and creates a greyscale image with green channel values.
   *
   * @param imageName            the name of the image to process
   * @param destinationImageName the name to save the processed image
   * @throws NoSuchElementException if the image is not found
   */
  @Override
  public void splitGreenChannel(String imageName, String destinationImageName)
          throws NoSuchElementException {
    if (!imageController.getImages().containsKey(imageName)) {
      throw new NoSuchElementException();
    }

    RGB[][] pixelArray = imageController.getImages().get(imageName);
    int width = pixelArray[0].length;
    int height = pixelArray.length;

    RGB[][] greenChannel = new RGB[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        RGB pixel = pixelArray[y][x];
        greenChannel[y][x] = new RGB(pixel.green, pixel.green, pixel.green);
      }
    }

    imageController.getImages().put(destinationImageName, greenChannel);
  }

  /**
   * Splits the blue channel from the image and creates a greyscale image with blue channel values.
   *
   * @param imageName            the name of the image to process
   * @param destinationImageName the name to save the processed image
   * @throws NoSuchElementException if the image is not found
   */
  @Override
  public void splitBlueChannel(String imageName, String destinationImageName)
          throws NoSuchElementException {
    if (!imageController.getImages().containsKey(imageName)) {
      throw new NoSuchElementException();
    }

    RGB[][] pixelArray = imageController.getImages().get(imageName);
    int width = pixelArray[0].length;
    int height = pixelArray.length;

    RGB[][] blueChannel = new RGB[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        RGB pixel = pixelArray[y][x];
        blueChannel[y][x] = new RGB(pixel.blue, pixel.blue, pixel.blue);
      }
    }

    imageController.getImages().put(destinationImageName, blueChannel);
  }

  /**
   * Calculates the value of each pixel by taking the maximum of the red, green, and blue components and creates a greyscale image.
   *
   * @param imageName            the name of the image to process
   * @param destinationImageName the name to save the processed image
   * @throws NoSuchElementException if the image is not found
   */
  @Override
  public void calculateValue(String imageName, String destinationImageName)
          throws NoSuchElementException {
    if (!imageController.getImages().containsKey(imageName)) {
      throw new NoSuchElementException();
    }

    RGB[][] pixelArray = imageController.getImages().get(imageName);
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

    imageController.getImages().put(destinationImageName, valueImageArray);
  }

  /**
   * Calculates the intensity of each pixel by averaging the red, green, and blue components and creates a greyscale image.
   *
   * @param imageName            the name of the image to process
   * @param destinationImageName the name to save the processed image
   * @throws NoSuchElementException if the image is not found
   */
  @Override
  public void calculateIntensity(String imageName, String destinationImageName)
          throws NoSuchElementException {
    if (!imageController.getImages().containsKey(imageName)) {
      throw new NoSuchElementException();
    }

    RGB[][] pixelArray = imageController.getImages().get(imageName);
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

    imageController.getImages().put(destinationImageName, intensityImageArray);
  }

  /**
   * Calculates the luma of each pixel using the weighted sum of the red, green, and blue components and creates a greyscale image.
   *
   * @param imageName            the name of the image to process
   * @param destinationImageName the name to save the processed image
   * @throws NoSuchElementException if the image is not found
   */
  @Override
  public void calculateLuma(String imageName, String destinationImageName)
          throws NoSuchElementException {
    if (!imageController.getImages().containsKey(imageName)) {
      throw new NoSuchElementException();
    }

    RGB[][] pixelArray = imageController.getImages().get(imageName);
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

    imageController.getImages().put(destinationImageName, lumaImageArray);
  }

  /**
   * Combines the red, green, and blue channels from three greyscale images to create a single RGB image.
   *
   * @param imageName  the name of the combined image
   * @param redImage   the greyscale image for the red channel
   * @param greenImage the greyscale image for the green channel
   * @param blueImage  the greyscale image for the blue channel
   * @throws NoSuchElementException if any of the images are not found
   */
  @Override
  public void combineGreyscale(
          String imageName, String redImage, String greenImage, String blueImage)
          throws NoSuchElementException {
    try {
      RGB[][] redPixelArray = imageController.getImages().get(redImage);
      RGB[][] greenPixelArray = imageController.getImages().get(greenImage);
      RGB[][] bluePixelArray = imageController.getImages().get(blueImage);

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

      imageController.getImages().put(imageName, combinedImage);
    } catch (NoSuchElementException e) {
      throw new NoSuchElementException();
    }
  }
}