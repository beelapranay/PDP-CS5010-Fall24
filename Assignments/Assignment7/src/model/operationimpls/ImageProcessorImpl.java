package model.operationimpls;


import model.image.ImageInterface;
import model.operationinterface.ImageProcessor;

import model.utilities.ImageValidator;

/**
 * Implementation of ImageProcessor interface that provides various image manipulation operations.
 * Supports operations like blurring, sharpening, brightening, sepia, and color transformations.
 * All operations validate input images before processing and return new ImageInterface instances.
 */
public class ImageProcessorImpl implements ImageProcessor {

  /**
   * Kernel matrix for Gaussian blur effect.
   * A 3x3 matrix where each value represents the weight for pixels in the blur calculation.
   * Values sum to 1.0 to maintain image brightness.
   */
  private static final double[][] BLUR_KERNEL = {
          {1.0 / 16, 1.0 / 8, 1.0 / 16},
          {1.0 / 8, 1.0 / 4, 1.0 / 8},
          {1.0 / 16, 1.0 / 8, 1.0 / 16}
  };

  /**
   * Kernel matrix for image sharpening effect.
   * A 5x5 matrix that enhances edges by increasing contrast between adjacent pixels.
   * Center values are positive while edge values are negative to create sharpening effect.
   */
  private static final double[][] SHARPEN_KERNEL = {
          {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8},
          {-1.0 / 8, 1.0 / 4, 1.0 / 2, 1.0 / 4, -1.0 / 8},
          {-1.0 / 8, 1.0 / 2, 1.0, 1.0 / 2, -1.0 / 8},
          {-1.0 / 8, 1.0 / 4, 1.0 / 2, 1.0 / 4, -1.0 / 8},
          {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8}
  };

  /**
   * Transformation matrix for sepia tone effect.
   * A 3x3 matrix used to convert RGB values to sepia-toned equivalents.
   * Each row represents weights for R, G, B channels respectively.
   */
  private static final double[][] SEPIA_MATRIX = {
          {0.393, 0.769, 0.189},
          {0.349, 0.686, 0.168},
          {0.272, 0.534, 0.131}
  };

  /**
   * Applies a Gaussian blur effect to the image.
   *
   * @param image The source image to blur
   * @return A new blurred version of the input image
   * @throws IllegalArgumentException if the input image is null or invalid
   */
  @Override
  public ImageInterface blur(ImageInterface image) {
    ImageValidator.validate(image);
    return new BlurOperation().execute(image);
  }

  /**
   * Adjusts the brightness of an image by adding an increment to each color channel.
   *
   * @param image     The source image to brighten
   * @param increment The amount to add to each pixel's RGB values (negative for darkening)
   * @return A new image with adjusted brightness
   * @throws IllegalArgumentException if the input image is null or invalid
   */
  @Override
  public ImageInterface brighten(ImageInterface image, int increment) {
    ImageValidator.validate(image);
    return new BrightenOperation(increment).execute(image);
  }

  /**
   * Applies a sepia tone effect to the image.
   *
   * @param image The source image to apply sepia effect to
   * @return A new sepia-toned version of the input image
   * @throws IllegalArgumentException if the input image is null or invalid
   */
  @Override
  public ImageInterface sepia(ImageInterface image) {
    ImageValidator.validate(image);
    return new SepiaOperation().execute(image);
  }

  /**
   * Sharpens the image by enhancing edges and details.
   *
   * @param image The source image to sharpen
   * @return A new sharpened version of the input image
   * @throws IllegalArgumentException if the input image is null or invalid
   */
  @Override
  public ImageInterface sharpen(ImageInterface image) {
    ImageValidator.validate(image);
    return new SharpenOperation().execute(image);
  }

  /**
   * Flips the image horizontally (left to right).
   *
   * @param image The source image to flip
   * @return A new horizontally flipped version of the input image
   * @throws IllegalArgumentException if the input image is null or invalid
   */
  @Override
  public ImageInterface flipHorizontal(ImageInterface image) {
    ImageValidator.validate(image);
    return new HorizontalFlipOperation().execute(image);
  }

  /**
   * Flips the image vertically (top to bottom).
   *
   * @param image The source image to flip
   * @return A new vertically flipped version of the input image
   * @throws IllegalArgumentException if the input image is null or invalid
   */
  @Override
  public ImageInterface flipVertical(ImageInterface image) {
    ImageValidator.validate(image);
    return new VerticalFlipOperation().execute(image);
  }

  /**
   * Extracts a single color component (red, green, or blue) from the image.
   *
   * @param image         The source image to extract component from
   * @param componentName The color component to extract (RED, GREEN, or BLUE)
   * @return A new grayscale image representing the extracted component
   * @throws IllegalArgumentException if the input image is null or invalid
   */
  @Override
  public ImageInterface extractComponent(ImageInterface image, String componentName) {
    ImageValidator.validate(image);
    return new ComponentOperation(componentName).execute(image);
  }

  /**
   * Combines three grayscale images into a single RGB image.
   *
   * @param redImage   The image to use for the red channel
   * @param greenImage The image to use for the green channel
   * @param blueImage  The image to use for the blue channel
   * @return A new RGB image combining the input channels
   * @throws IllegalArgumentException if any input image is null or invalid
   */
  @Override
  public ImageInterface combine(ImageInterface redImage, ImageInterface greenImage,
                                ImageInterface blueImage) {
    ImageValidator.validate(redImage);
    ImageValidator.validate(greenImage);
    ImageValidator.validate(blueImage);
    return new CombineOperation().execute(redImage, greenImage, blueImage);
  }

  /**
   * Splits an RGB image into its three component channels.
   *
   * @param image The source RGB image to split
   * @return An array of three images representing the red, green, and blue channels
   * @throws IllegalArgumentException if the input image is null or invalid
   */
  @Override
  public ImageInterface[] split(ImageInterface image) {
    ImageValidator.validate(image);
    return new SplitOperation().execute(image);
  }
}