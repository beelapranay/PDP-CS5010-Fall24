package model.operationimpls;

import model.image.ImageInterface;
import model.operationinterface.AdvancedImageProcessor;
import model.utilities.ImageValidator;

/**
 * An advanced image processor that provides additional image processing operations.
 * This class extends the basic image processor and adds operations like compressing an image,
 * generating a histogram, color correcting an image, adjusting levels, and split view.
 */
public class AdvancedImageProcessorImpl
        extends ImageProcessorImpl
        implements AdvancedImageProcessor {

  /**
   * Compresses the image by reducing its quality to a certain percentage.
   *
   * @param image      The source image to compress
   * @param percentage The percentage to which the image should be compressed (0-100)
   * @return A new compressed version of the input image
   * @throws IllegalArgumentException if the input image is null or invalid
   */
  @Override
  public ImageInterface compress(ImageInterface image, double percentage) {
    ImageValidator.validate(image);

    return new CompressOperation(percentage).execute(image);
  }

  /**
   * Generates a histogram representation of the image.
   *
   * @param image The source image to generate histogram from
   * @return A new image representing the histogram
   * @throws IllegalArgumentException if the input image is null or invalid
   */
  @Override
  public ImageInterface generateHistogram(ImageInterface image) {
    ImageValidator.validate(image);

    return new HistogramOperation().execute(image);
  }

  /**
   * Performs color correction on the image to adjust colors and improve visual quality.
   *
   * @param image The source image to color correct
   * @return A new color-corrected version of the input image
   * @throws IllegalArgumentException if the input image is null or invalid
   */
  @Override
  public ImageInterface colorCorrect(ImageInterface image) {
    ImageValidator.validate(image);

    return new ColorCorrectOperation().execute(image);
  }

  /**
   * Adjusts the levels of the image based on black, mid, and white points.
   *
   * @param image      The source image to adjust
   * @param blackPoint The black point value
   * @param midPoint   The mid-tone point value
   * @param whitePoint The white point value
   * @return A new image with adjusted levels
   * @throws IllegalArgumentException if the input image is null or invalid
   */
  @Override
  public ImageInterface adjustLevels(ImageInterface image,
                                     int blackPoint, int midPoint, int whitePoint) {

    ImageValidator.validate(image);

    return new AdjustLevelsOperation(blackPoint, midPoint, whitePoint).execute(image);
  }

  /**
   * Creates a split view of the image with an operation applied to one part.
   *
   * @param image         The source image to split
   * @param operation     The operation to apply (e.g., "blur", "sharpen")
   * @param splitPosition The position to split the image
   * @param params        Additional parameters for the operation
   * @return A new image with the split view effect
   * @throws IllegalArgumentException if the input image is null or invalid
   */
  @Override
  public ImageInterface splitView(ImageInterface image,
                                  String operation, int splitPosition, Object... params) {

    ImageValidator.validate(image);

    return new SplitViewOperation(operation, splitPosition, this, params).execute(image);
  }

  /**
   * Downscales the image to the specified width and height.
   *
   * @param image        The source image to downscale.
   * @param targetWidth  The target width.
   * @param targetHeight The target height.
   * @return A new downscaled version of the input image.
   * @throws IllegalArgumentException if the input image is null or invalid.
   */
  @Override
  public ImageInterface downscale(ImageInterface image, int targetWidth, int targetHeight) {
    ImageValidator.validate(image);
    return new DownscaleOperation(targetWidth, targetHeight).execute(image);
  }

  /**
   * Applies the specified operation to the image using the mask image.
   *
   * @param image     the source image
   * @param maskImage the mask image
   * @param operation the operation to apply
   * @return the resulting image after applying the mask operation
   */
  @Override
  public ImageInterface applyMask(ImageInterface image,
                                  ImageInterface maskImage, String operation) {
    ImageValidator.validate(image);
    ImageValidator.validate(maskImage);
    return new ApplyMaskOperation(operation, maskImage).execute(image);
  }

  /**
   * Applies the dithering effect to the given image.
   * Converts the image to a black-and-white representation using dithering.
   *
   * @param image The source image to apply the dithering effect to.
   * @return A new image with the dithering effect applied.
   * @throws IllegalArgumentException if the input image is null or invalid.
   */
  @Override
  public ImageInterface applyDithering(ImageInterface image) {
    ImageValidator.validate(image);
    return new DitheringOperation().execute(image);
  }
}