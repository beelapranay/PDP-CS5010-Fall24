package model.operationinterface;

import model.image.ImageInterface;

/**
 * Extended interface for advanced image processing operations.
 * This interface includes all basic operations from ImageProcessor
 * and adds advanced functionalities like compression, histogram generation,
 * color correction, levels adjustment, and split view operations.
 */
public interface AdvancedImageProcessor extends ImageProcessor {

  /**
   * Compresses the image by reducing its quality to a certain percentage.
   *
   * @param image      The source image to compress
   * @param percentage The percentage to which the image should be compressed (0-100)
   * @return A new compressed version of the input image
   * @throws IllegalArgumentException if the input image is null or invalid
   */
  ImageInterface compress(ImageInterface image, double percentage);

  /**
   * Generates a histogram representation of the image.
   *
   * @param image The source image to generate histogram from
   * @return A new image representing the histogram
   * @throws IllegalArgumentException if the input image is null or invalid
   */
  ImageInterface generateHistogram(ImageInterface image);

  /**
   * Performs color correction on the image to adjust colors and improve visual quality.
   *
   * @param image The source image to color correct
   * @return A new color-corrected version of the input image
   * @throws IllegalArgumentException if the input image is null or invalid
   */
  ImageInterface colorCorrect(ImageInterface image);

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
  ImageInterface adjustLevels(ImageInterface image, int blackPoint, int midPoint, int whitePoint);

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
  ImageInterface splitView(ImageInterface image,
                           String operation, int splitPosition, Object... params);

  /**
   * Downscales the image to the specified width and height.
   *
   * @param image        The source image to downscale.
   * @param targetWidth  The target width.
   * @param targetHeight The target height.
   * @return A new downscaled version of the input image.
   * @throws IllegalArgumentException if the input image is null or invalid.
   */
  ImageInterface downscale(ImageInterface image, int targetWidth, int targetHeight);

  /**
   * Applies the specified operation to the image using the mask image.
   *
   * @param image     the source image
   * @param maskImage the mask image
   * @param operation the operation to apply
   * @return the resulting image after applying the mask operation
   */
  ImageInterface applyMask(ImageInterface image, ImageInterface maskImage, String operation);

  /**
   * Applies the dithering effect to the given image.
   * Converts the image to a black-and-white representation using dithering.
   *
   * @param image The source image to apply the dithering effect to.
   * @return A new image with the dithering effect applied.
   */
  ImageInterface applyDithering(ImageInterface image);
}
