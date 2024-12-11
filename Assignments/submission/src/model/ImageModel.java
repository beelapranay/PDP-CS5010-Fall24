package model;

import java.util.Arrays;
import java.util.Map;
import java.util.NoSuchElementException;

import controller.ImageControllerInterface;
import view.ImageViewInterface;

/**
 * ImageModel class implements image processing operations like sepia tone,
 * blur, sharpen, and brightness adjustment.
 */
public class ImageModel implements ImageModelInterface {
  private final ImageControllerInterface imageController;
  private final ImageViewInterface imageView;

  /**
   * Constructor that initializes the image controller and view.
   *
   * @param imageController the controller to interact with images
   * @param imageView       the view interface for image display
   */
  public ImageModel(ImageControllerInterface imageController, ImageViewInterface imageView) {
    this.imageController = imageController;
    this.imageView = imageView;
  }

  /**
   * Data class representing image data with original and transformed arrays.
   */
  private static class ImageData {
    RGB[][] originalArray;
    RGB[][] transformedArray;
    int width;
    int height;
    int splitPoint;

    ImageData(RGB[][] originalArray, RGB[][] transformedArray, int width, int height, int splitPoint) {
      this.originalArray = originalArray;
      this.transformedArray = transformedArray;
      this.width = width;
      this.height = height;
      this.splitPoint = splitPoint;
    }
  }

  /**
   * Retrieves image data with specified split percentage for transformation.
   *
   * @param imageName       the name of the image to retrieve
   * @param splitPercentage the percentage of the width to apply transformations
   * @return ImageData containing original and transformed image arrays, dimensions,
   * and split point
   * @throws NoSuchElementException if the image is not found
   */
  private ImageData getImageData(String imageName, int splitPercentage) throws NoSuchElementException {
    if (!imageController.getImages().containsKey(imageName)) {
      throw new NoSuchElementException("Image not found: " + imageName);
    }

    RGB[][] pixelArray = imageController.getImages().get(imageName);
    int height = pixelArray.length;
    int width = pixelArray[0].length;
    RGB[][] newArray = new RGB[height][width];
    int splitPoint = (int) (width * splitPercentage / 100.0);

    return new ImageData(pixelArray, newArray, width, height, splitPoint);
  }

  private final double[][] blurKernel = {
          {1.0 / 16, 1.0 / 8, 1.0 / 16},
          {1.0 / 8, 1.0 / 4, 1.0 / 8},
          {1.0 / 16, 1.0 / 8, 1.0 / 16}
  };

  private final double[][] sharpenKernel = {
          {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8},
          {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
          {-1.0 / 8, 1.0 / 4, 1.0, 1.0 / 4, -1.0 / 8},
          {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
          {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8}
  };

  /**
   * Applies sepia tone effect to an image within a specified split percentage.
   *
   * @param imageName            the name of the image to process
   * @param destinationImageName the name to save the processed image
   * @param splitPercentage      the percentage of the image width to apply sepia tone
   * @throws NoSuchElementException if the image is not found
   */
  @Override
  public void applySepiaTone(String imageName, String destinationImageName, int splitPercentage)
          throws NoSuchElementException {
    ImageData imageData = getImageData(imageName, splitPercentage);

    for (int y = 0; y < imageData.height; y++) {
      for (int x = 0; x < imageData.width; x++) {
        RGB pixel = imageData.originalArray[y][x];
        int r = pixel.red;
        int g = pixel.green;
        int b = pixel.blue;

        int newRed = (int) Math.min(0.393 * r + 0.769 * g + 0.189 * b, 255);
        int newGreen = (int) Math.min(0.349 * r + 0.686 * g + 0.168 * b, 255);
        int newBlue = (int) Math.min(0.272 * r + 0.534 * g + 0.131 * b, 255);

        if (x < imageData.splitPoint) {
          imageData.transformedArray[y][x] = new RGB(newRed, newGreen, newBlue);
        } else {
          imageData.transformedArray[y][x] = imageData.originalArray[y][x];
        }
      }
    }

    imageController.getImages().put(destinationImageName, imageData.transformedArray);
  }

  /**
   * Applies blur or sharpen effect to an image within a specified split percentage.
   *
   * @param imageName            the name of the image to process
   * @param destinationImageName the name to save the processed image
   * @param splitPercentage      the percentage of the image width to apply transformation
   * @param operationName        the operation name, either "blur" or "sharpen"
   * @throws NoSuchElementException if the image is not found or operation is invalid
   */
  @Override
  public void blurOrSharpen(String imageName, String destinationImageName,
                            int splitPercentage, String operationName)
          throws NoSuchElementException {
    ImageData imageData = getImageData(imageName, splitPercentage);

    switch (operationName) {
      case "blur":
        for (int y = 1; y < imageData.height - 1; y++) {
          for (int x = 1; x < imageData.width - 1; x++) {

            double red = 0;
            double green = 0;
            double blue = 0;

            for (int ky = 0; ky < 3; ky++) {
              for (int kx = 0; kx < 3; kx++) {
                int pixelX = x + kx - 1;
                int pixelY = y + ky - 1;

                RGB pixel = imageData.originalArray[pixelY][pixelX];

                red += pixel.red * blurKernel[ky][kx];
                green += pixel.green * blurKernel[ky][kx];
                blue += pixel.blue * blurKernel[ky][kx];
              }
            }

            int newRed = (int) Math.min(Math.max(red, 0), 255);
            int newGreen = (int) Math.min(Math.max(green, 0), 255);
            int newBlue = (int) Math.min(Math.max(blue, 0), 255);

            imageData.transformedArray[y][x] = new RGB(newRed, newGreen, newBlue);
          }
        }
        break;
      case "sharpen":
        int kernelSize = 5;
        int offset = kernelSize / 2;

        for (int y = offset; y < imageData.height - offset; y++) {
          for (int x = offset; x < imageData.width - offset; x++) {

            double red = 0;
            double green = 0;
            double blue = 0;

            for (int ky = 0; ky < kernelSize; ky++) {
              for (int kx = 0; kx < kernelSize; kx++) {
                int pixelX = x + kx - offset;
                int pixelY = y + ky - offset;

                RGB pixel = imageData.originalArray[pixelY][pixelX];

                red += pixel.red * sharpenKernel[ky][kx];
                green += pixel.green * sharpenKernel[ky][kx];
                blue += pixel.blue * sharpenKernel[ky][kx];
              }
            }

            int newRed = (int) Math.min(Math.max(red, 0), 255);
            int newGreen = (int) Math.min(Math.max(green, 0), 255);
            int newBlue = (int) Math.min(Math.max(blue, 0), 255);

            imageData.transformedArray[y][x] = new RGB(newRed, newGreen, newBlue);
          }
        }
        break;
      default:
        throw new NoSuchElementException();
    }

    for (int y = 0; y < imageData.height; y++) {
      imageData.transformedArray[y][0] = imageData.originalArray[y][0];
      imageData.transformedArray[y][imageData.width - 1]
              = imageData.originalArray[y][imageData.width - 1];
    }
    for (int x = 0; x < imageData.width; x++) {
      imageData.transformedArray[0][x] = imageData.originalArray[0][x];
      imageData.transformedArray[imageData.height - 1][x]
              = imageData.originalArray[imageData.height - 1][x];
    }

    imageController.getImages().put(destinationImageName, imageData.transformedArray);
  }

  /**
   * Changes the brightness of an image by a given value.
   *
   * @param imageName            the name of the image to process
   * @param destinationImageName the name to save the processed image
   * @param value                the brightness value to adjust
   * @throws NoSuchElementException if the image is not found
   */
  @Override
  public void changeBrightness(String imageName, String destinationImageName,
                               int value)
          throws NoSuchElementException {
    ImageData imageData = getImageData(imageName, 100);

    for (int y = 0; y < imageData.height; y++) {
      for (int x = 0; x < imageData.width; x++) {
        int newRed = Math.min(Math.max(value + imageData.originalArray[y][x].red, 0), 255);
        int newGreen = Math.min(Math.max(value + imageData.originalArray[y][x].green, 0), 255);
        int newBlue = Math.min(Math.max(value + imageData.originalArray[y][x].blue, 0), 255);

        imageData.transformedArray[y][x] = new RGB(newRed, newGreen, newBlue);
      }
    }

    imageController.getImages().put(destinationImageName, imageData.transformedArray);
  }

  /**
   * Flips the image horizontally or vertically.
   *
   * @param imageName            the name of the image to flip
   * @param destinationImageName the name to save the flipped image
   * @param operationName        the type of flip operation, either
   *                             "horizontal-flip" or "vertical-flip"
   * @throws NoSuchElementException if the image is not found or operation is invalid
   */
  @Override
  public void flipImage(String imageName, String destinationImageName, String operationName)
          throws NoSuchElementException {
    ImageData imageData = getImageData(imageName, 100);

    for (int y = 0; y < imageData.height; y++) {
      for (int x = 0; x < imageData.width; x++) {
        switch (operationName) {
          case "horizontal-flip":
            imageData.transformedArray[y][x] = imageData.originalArray[y][imageData.width - x - 1];
            break;
          case "vertical-flip":
            imageData.transformedArray[y][x] = imageData.originalArray[imageData.height - y - 1][x];
            break;
          default:
            throw new NoSuchElementException();
        }
      }
    }

    imageController.getImages().put(destinationImageName, imageData.transformedArray);
  }

  /**
   * Applies grayscale transformation based on specified component to a portion of the image.
   *
   * @param imageName            the name of the image to process
   * @param destinationImageName the name to save the processed image
   * @param transformationType   the type of grayscale transformation
   *                             (e.g., "red-component", "luma-component")
   * @param splitPercentage      the percentage of the image width to apply transformation
   * @throws NoSuchElementException if the image is not found or transformation type is invalid
   */
  public void applyGreyscaleTransformation(String imageName, String destinationImageName,
                                  String transformationType, int splitPercentage)
          throws NoSuchElementException {
    ImageData imageData = getImageData(imageName, splitPercentage);

    for (int y = 0; y < imageData.height; y++) {
      for (int x = 0; x < imageData.width; x++) {
        RGB pixel = imageData.originalArray[y][x];
        if (x < imageData.splitPoint) {
          imageData.transformedArray[y][x] = transformPixel(pixel, transformationType);
        } else {
          imageData.transformedArray[y][x] = imageData.originalArray[y][x];
        }
      }
    }

    imageController.getImages().put(destinationImageName, imageData.transformedArray);
  }

  /**
   * Transforms an individual pixel based on the specified grayscale transformation type.
   *
   * @param pixel               the pixel to transform
   * @param transformationType  the type of transformation
   * @return                    the transformed RGB pixel
   * @throws IllegalArgumentException if an unknown transformation type is provided
   */
  private RGB transformPixel(RGB pixel, String transformationType) {
    int value;
    switch (transformationType.toLowerCase()) {
      case "red-component":
        return new RGB(pixel.red, pixel.red, pixel.red);

      case "green-component":
        return new RGB(pixel.green, pixel.green, pixel.green);

      case "blue-component":
        return new RGB(pixel.blue, pixel.blue, pixel.blue);

      case "value-component":
        value = Math.max(pixel.red, Math.max(pixel.green, pixel.blue));
        return new RGB(value, value, value);

      case "intensity-component":
        value = (pixel.red + pixel.green + pixel.blue) / 3;
        return new RGB(value, value, value);

      case "luma-component":
        value = (int) Math.round(0.2126 * pixel.red + 0.7152 * pixel.green + 0.0722 * pixel.blue);
        return new RGB(value, value, value);

      default:
        throw new IllegalArgumentException("Unknown transformation type: " + transformationType);
    }
  }

  /**
   * Combines the red, green, and blue channels from
   * three greyscale images to create a single RGB image.
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

  /**
   * Compresses an image using the specified compression percentage.
   *
   * @param compressionPercentage the percentage of compression to apply
   * @param imageName             the name of the image to compress
   * @param destinationImageName  the name to save the compressed image
   */
  public void compressImage(String compressionPercentage, String imageName,
                            String destinationImageName) {
    RGB[][] image = imageController.getImages().get(imageName);
    RGB[][] paddedImage;

    try {
      int height = image.length;
      int width = image[0].length;
      int compressionPercentageValue = Integer.parseInt(compressionPercentage);

      // Apply padding to create a square matrix with power-of-2 dimensions if needed
      if (!isPowerOf2(width) || !isPowerOf2(height) || width != height) {
        paddedImage = padArrayToSquare(image, width, height);
        height = paddedImage.length;
        width = paddedImage[0].length;
      } else {
        paddedImage = image;
      }

      // Separate color channels
      double[][] redChannel = new double[height][width];
      double[][] greenChannel = new double[height][width];
      double[][] blueChannel = new double[height][width];

      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          redChannel[y][x] = paddedImage[y][x].red;
          greenChannel[y][x] = paddedImage[y][x].green;
          blueChannel[y][x] = paddedImage[y][x].blue;
        }
      }

      // Apply Haar transform to each channel
      haar2D(redChannel, paddedImage.length);
      haar2D(greenChannel, paddedImage.length);
      haar2D(blueChannel, paddedImage.length);

      // Apply threshold to compress (for lossy compression)
      applyThreshold(redChannel, compressionPercentageValue);
      applyThreshold(greenChannel, compressionPercentageValue);
      applyThreshold(blueChannel, compressionPercentageValue);

      // Apply inverse Haar transform to reconstruct the channels
      invHaar2D(redChannel, paddedImage.length);
      invHaar2D(greenChannel, paddedImage.length);
      invHaar2D(blueChannel, paddedImage.length);

      // Combine channels back into an RGB image, cropping to original size if needed
      RGB[][] compressedImage = new RGB[image.length][image[0].length];
      for (int y = 0; y < image.length; y++) {
        for (int x = 0; x < image[0].length; x++) {
          int red = (int) Math.min(255, Math.max(0, redChannel[y][x]));
          int green = (int) Math.min(255, Math.max(0, greenChannel[y][x]));
          int blue = (int) Math.min(255, Math.max(0, blueChannel[y][x]));
          compressedImage[y][x] = new RGB(red, green, blue);
        }
      }

      // Save the compressed image
      imageController.getImages().put(destinationImageName, compressedImage);

    } catch (NoSuchElementException e) {
      throw new NoSuchElementException("Image not found.");
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid compression percentage.");
    }
  }

  /**
   * Color corrects an image to match target peaks based on histogram data.
   *
   * @param imageName            the name of the image to correct
   * @param destinationImageName the name to save the corrected image
   * @param splitPercentage      the percentage of the image width to apply color correction
   */
  @Override
  public void colorCorrect(String imageName, String destinationImageName, int splitPercentage) {
    if (!imageController.getImages().containsKey(imageName)) {
      throw new NoSuchElementException("Image not found: " + imageName);
    }

    RGB[][] pixelArray = imageController.getImages().get(imageName);

    Map<String, int[]> histogramData = imageView.calculateHistogramData(pixelArray);

    int redPeak = findPeak(histogramData.get("Red"));
    int greenPeak = findPeak(histogramData.get("Green"));
    int bluePeak = findPeak(histogramData.get("Blue"));

    int targetPeak = calculateTargetPeak(redPeak, greenPeak, bluePeak);
    int redOffset = calculateOffset(redPeak, targetPeak);
    int greenOffset = calculateOffset(greenPeak, targetPeak);
    int blueOffset = calculateOffset(bluePeak, targetPeak);

    RGB[][] correctedImage = applyColorCorrection(pixelArray, redOffset, greenOffset, blueOffset);

    imageController.getImages().put(destinationImageName, correctedImage);
  }

  /**
   * Adjusts levels of the image based on specified points b, m, and w using a quadratic function.
   *
   * @param imageName       the name of the image to adjust
   * @param destImageName   the name to save the adjusted image
   * @param b               the black point for levels adjustment
   * @param m               the mid-tone point for levels adjustment
   * @param w               the white point for levels adjustment
   * @param splitPercentage the percentage of the image width to apply adjustment
   */
  @Override
  public void levelsAdjust(String imageName, String destImageName, int b, int m, int w,
                           int splitPercentage) {
    RGB[][] image = imageController.getImages().get(imageName);

    if (image == null) {
      throw new IllegalArgumentException("Image not found: " + imageName);
    }

    int height = image.length;
    int width = image[0].length;

    // Ensure the points are in the correct order
    if (b < 0 || b >= m || m >= w || w > 255) {
      throw new IllegalArgumentException("Points must satisfy 0 <= b < m < w <= 255");
    }

    // Step 1: Calculate the intermediate values for A, Aa, Ab, and Ac based on the provided formulas
    double A = b * b * (m - w) - b * (m * m - w * w) + w * m * m - m * w * w;
    double Aa = -b * (128 - 255) + 128 * w - 255 * m;
    double Ab = b * b * (128 - 255) + 255 * m * m - 128 * w * w;
    double Ac = b * b * (255 * m - 128 * w) - b * (255 * m * m - 128 * w * w);

    // Step 2: Calculate coefficients a, b, and c for the quadratic function
    double a = Aa / A;
    double bCoef = Ab / A;
    double c = Ac / A;

    // Create an adjusted image array
    RGB[][] adjustedImage = new RGB[height][width];

    // Step 3: Apply the levels adjustment using the quadratic function for each pixel
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int adjustedRed = applyQuadraticLevelsAdjustment(image[y][x].red, a, bCoef, c);
        int adjustedGreen = applyQuadraticLevelsAdjustment(image[y][x].green, a, bCoef, c);
        int adjustedBlue = applyQuadraticLevelsAdjustment(image[y][x].blue, a, bCoef, c);
        adjustedImage[y][x] = new RGB(adjustedRed, adjustedGreen, adjustedBlue);
      }
    }

    // Store the adjusted image in the controller's image map
    imageController.getImages().put(destImageName, adjustedImage);
  }

  /**
   * Checks if a given integer is a power of 2.
   *
   * @param n the integer to check.
   * @return true if n is a power of 2, false otherwise.
   */
  private boolean isPowerOf2(int n) {
    // A power of 2 in binary has only one '1' bit (e.g., 1, 2, 4, 8, etc.)
    // This bitwise check verifies if n is a power of 2
    return n > 0 && (n & (n - 1)) == 0;
  }

  /**
   * Pads a 2D RGB array to make it a square matrix with dimensions that
   * are the next power of 2. The padding area is filled with black pixels.
   *
   * @param original the original RGB array.
   * @param width    the width of the original array.
   * @param height   the height of the original array.
   * @return a padded square RGB array.
   */
  private RGB[][] padArrayToSquare(RGB[][] original, int width, int height) {
    // Determine the size to pad to (next power of 2 for the larger dimension)
    int paddedSize = nextPowerOf2(Math.max(width, height));

    // Create a new square RGB array with dimensions paddedSize x paddedSize
    RGB[][] paddedArray = new RGB[paddedSize][paddedSize];

    // Initialize the padded array with black (0, 0, 0) pixels for the padding area
    for (int y = 0; y < paddedSize; y++) {
      for (int x = 0; x < paddedSize; x++) {
        paddedArray[y][x] = new RGB(0, 0, 0);  // Padding color set to black
      }
    }

    // Copy the original data into the top-left corner of the padded array
    for (int y = 0; y < height; y++) {
      System.arraycopy(original[y], 0, paddedArray[y], 0, width);
    }

    return paddedArray;
  }

  /**
   * Computes the next power of 2 for a given integer.
   *
   * @param n the integer for which to compute the next power of 2.
   * @return the smallest power of 2 that is greater than or equal to n.
   */
  private int nextPowerOf2(int n) {
    int power = 1;
    while (power < n) {
      power *= 2;
    }
    return power;
  }

  /**
   * Applies a threshold to a 2D array of double values to zero out
   * values below a specified compression percentage.
   *
   * @param channel             the 2D array representing a color channel.
   * @param compressionPercentage the percentage of values to retain.
   */
  private void applyThreshold(double[][] channel, int compressionPercentage) {
    int totalElements = channel.length * channel[0].length;
    int thresholdCount = (int) (totalElements * compressionPercentage / 100.0);

    // Flatten the 2D array to find the threshold value
    double[] flattened = new double[totalElements];
    int index = 0;
    for (double[] row : channel) {
      for (double value : row) {
        flattened[index++] = Math.abs(value);
      }
    }

    // Sort and find the threshold value at the compression percentage
    Arrays.sort(flattened);
    double thresholdValue = flattened[thresholdCount];

    // Set values below threshold to zero
    for (int y = 0; y < channel.length; y++) {
      for (int x = 0; x < channel[0].length; x++) {
        if (Math.abs(channel[y][x]) < thresholdValue) {
          channel[y][x] = 0;
        }
      }
    }
  }

  /**
   * Applies the Haar wavelet transform to a 2D array, modifying it in place.
   * The transformation progressively halves the size to be transformed.
   *
   * @param channel the 2D array representing a color channel.
   * @param size    the size of the array.
   */
  private void haar2D(double[][] channel, int size) {
    int c = size;
    while (c > 1) {
      // Apply Haar transform to each row up to current size `c`
      for (int i = 0; i < c; i++) {
        transformation(channel[i], c); // Transform each row from 1 to c
      }

      // Apply Haar transform to each column up to current size `c`
      for (int j = 0; j < c; j++) {
        double[] column = new double[c];
        for (int i = 0; i < c; i++) {
          column[i] = channel[i][j];
        }
        transformation(column, c); // Transform each column from 1 to c
        for (int i = 0; i < c; i++) {
          channel[i][j] = column[i];
        }
      }

      c /= 2; // Reduce size for next iteration
    }
  }

  /**
   * Applies the inverse Haar wavelet transform to a 2D array, modifying it in place.
   * The transformation progressively doubles the size to be transformed.
   *
   * @param channel the 2D array representing a color channel.
   * @param size    the size of the array.
   */
  private void invHaar2D(double[][] channel, int size) {
    int c = 2;
    while (c <= size) {
      // Apply inverse Haar transform to each column up to current size `c`
      for (int j = 0; j < c; j++) {
        double[] column = new double[c];
        for (int i = 0; i < c; i++) {
          column[i] = channel[i][j];
        }
        inverseTransformation(column, c); // Inverse transform each column from 1 to c
        for (int i = 0; i < c; i++) {
          channel[i][j] = column[i];
        }
      }

      // Apply inverse Haar transform to each row up to current size `c`
      for (int i = 0; i < c; i++) {
        inverseTransformation(channel[i], c); // Inverse transform each row from 1 to c
      }

      c *= 2; // Increase size for next iteration
    }
  }

  /**
   * Applies the Haar wavelet transform to a 2D array, modifying it in place.
   * The transformation progressively halves the size to be transformed.
   *
   * @param s the 2D array representing a color channel.
   * @param length    the size of the array.
   */
  private void transformation(double[] s, int length) {
    int newLength = length / 2;  // Ensure we only process pairs
    double[] avg = new double[newLength];
    double[] diff = new double[newLength];

    for (int i = 0; i < newLength * 2; i += 2) {
      double a = s[i];
      double b = s[i + 1];
      avg[i / 2] = (a + b) / Math.sqrt(2);
      diff[i / 2] = (a - b) / Math.sqrt(2);
    }

    System.arraycopy(avg, 0, s, 0, avg.length);
    System.arraycopy(diff, 0, s, avg.length, diff.length);
  }

  /**
   * Applies the inverse Haar wavelet transform to a 2D array, modifying it in place.
   * The transformation progressively doubles the size to be transformed.
   *
   * @param s the 2D array representing a color channel.
   * @param length    the size of the array.
   */
  private void inverseTransformation(double[] s, int length) {
    int newLength = length / 2;
    double[] original = new double[length];

    for (int i = 0; i < newLength; i++) {
      double avg = s[i];
      double diff = s[i + newLength];

      // Check for zeroed differences due to thresholding
      if (diff == 0) {
        // When the difference is zero, set both reconstructed values to the average
        original[2 * i] = avg / Math.sqrt(2);
        original[2 * i + 1] = avg / Math.sqrt(2);
      } else {
        original[2 * i] = (avg + diff) / Math.sqrt(2);
        original[2 * i + 1] = (avg - diff) / Math.sqrt(2);
      }
    }

    System.arraycopy(original, 0, s, 0, length);
  }

  /**
   * Finds the peak intensity in a given histogram, ignoring extreme values.
   * This peak intensity corresponds to the value with the highest frequency
   * in the histogram data within the range of interest (10 to 245).
   *
   * @param histogram an array representing the frequency of intensities.
   * @return the intensity value with the highest frequency within the range.
   */
  private int findPeak(int[] histogram) {
    int peakIntensity = 0;
    int peakFrequency = 0;

    for (int i = 10; i < 245; i++) { // Ignore extreme values
      if (histogram[i] > peakFrequency) {
        peakFrequency = histogram[i];
        peakIntensity = i;
      }
    }

    return peakIntensity;
  }

  /**
   * Calculates the target peak intensity for color correction by averaging
   * the peak intensities of the red, green, and blue channels.
   *
   * @param redPeak   the peak intensity of the red channel.
   * @param greenPeak the peak intensity of the green channel.
   * @param bluePeak  the peak intensity of the blue channel.
   * @return the average peak intensity of the three color channels.
   */
  private int calculateTargetPeak(int redPeak, int greenPeak, int bluePeak) {
    return (redPeak + greenPeak + bluePeak) / 3;
  }

  /**
   * Calculates the offset needed to adjust a color channel's peak intensity
   * to a target peak value.
   *
   * @param channelPeak the current peak intensity of the color channel.
   * @param targetPeak  the target peak intensity.
   * @return the offset to be applied to align the channel peak with the target peak.
   */
  private int calculateOffset(int channelPeak, int targetPeak) {
    return targetPeak - channelPeak;
  }

  /**
   * Applies color correction to an image by adjusting the RGB channels based
   * on the provided offsets for each color. The offsets are applied to shift
   * each channel towards a target peak value.
   *
   * @param pixelArray 2D array of RGB pixels representing the original image.
   * @param redOffset  offset for the red channel.
   * @param greenOffset offset for the green channel.
   * @param blueOffset  offset for the blue channel.
   * @return a new 2D array of RGB pixels representing the color-corrected image.
   */
  private RGB[][] applyColorCorrection(RGB[][] pixelArray, int redOffset, int greenOffset,
                                       int blueOffset) {
    int height = pixelArray.length;
    int width = pixelArray[0].length;

    RGB[][] correctedImage = new RGB[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        RGB pixel = pixelArray[y][x];

        int newRed = Math.min(255, Math.max(0, pixel.red + redOffset));
        int newGreen = Math.min(255, Math.max(0, pixel.green + greenOffset));
        int newBlue = Math.min(255, Math.max(0, pixel.blue + blueOffset));

        correctedImage[y][x] = new RGB(newRed, newGreen, newBlue);
      }
    }

    return correctedImage;
  }

  /**
   * Applies a quadratic function to adjust intensity levels for a single channel.
   * The quadratic adjustment is determined by the coefficients a, b, and c.
   *
   * @param intensity the original intensity value.
   * @param a         the quadratic coefficient.
   * @param b         the linear coefficient.
   * @param c         the constant coefficient.
   * @return the adjusted intensity value, constrained between 0 and 255.
   */
  private int applyQuadraticLevelsAdjustment(int intensity, double a, double b, double c) {
    double output = a * intensity * intensity + b * intensity + c;

    return (int) Math.min(255, Math.max(0, Math.round(output)));
  }

}