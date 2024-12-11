package model.operationinterface;

import model.image.ImageInterface;

/**
 * Interface defining operations for processing and manipulating images.
 * Provides methods for various image transformations including filters, color adjustments,
 * geometric operations, and color channel manipulations.
 */
public interface ImageProcessor {

  /**
   * Applies a Gaussian blur effect to the image.
   * Uses a 3x3 kernel to create a smoothing effect by averaging neighboring pixels.
   *
   * @param image The source image to blur
   * @return A new blurred version of the input image
   * @throws IllegalArgumentException if the input image is null or invalid
   */
  ImageInterface blur(ImageInterface image);

  /**
   * Adjusts the brightness of an image by adding a constant value to all color channels.
   * Positive values brighten the image, negative values darken it.
   *
   * @param image     The source image to adjust
   * @param increment The amount to add to each pixel's RGB values (negative for darkening)
   * @return A new image with adjusted brightness
   * @throws IllegalArgumentException if the input image is null or invalid
   */
  ImageInterface brighten(ImageInterface image, int increment);

  /**
   * Applies a sepia tone effect to create an antique, brownish appearance.
   * Uses a color transformation matrix to convert RGB values to sepia tones.
   *
   * @param image The source image to apply sepia effect to
   * @return A new sepia-toned version of the input image
   * @throws IllegalArgumentException if the input image is null or invalid
   */
  ImageInterface sepia(ImageInterface image);

  /**
   * Sharpens the image by enhancing edges and details.
   * Uses a 5x5 kernel that increases contrast between adjacent pixels.
   *
   * @param image The source image to sharpen
   * @return A new sharpened version of the input image
   * @throws IllegalArgumentException if the input image is null or invalid
   */
  ImageInterface sharpen(ImageInterface image);

  /**
   * Flips the image horizontally (left to right).
   *
   * @param image The source image to flip
   * @return A new horizontally flipped version of the input image
   * @throws IllegalArgumentException if the input image is null or invalid
   */
  ImageInterface flipHorizontal(ImageInterface image);

  /**
   * Flips the image vertically (top to bottom).
   *
   * @param image The source image to flip
   * @return A new vertically flipped version of the input image
   * @throws IllegalArgumentException if the input image is null or invalid
   */
  ImageInterface flipVertical(ImageInterface image);

  /**
   * Extracts a specific component (color channel or intensity measure) from the image.
   *
   * @param image         The source image to extract component from
   * @param componentType The type of component to extract (RED, GREEN, BLUE, etc.)
   * @return A new grayscale image representing the extracted component
   * @throws IllegalArgumentException if the input image is null or invalid
   */
  ImageInterface extractComponent(ImageInterface image, String componentType);

  /**
   * Combines three grayscale images into a single RGB color image.
   * Each input image becomes one color channel in the output.
   *
   * @param redImage   The image to use for the red channel
   * @param greenImage The image to use for the green channel
   * @param blueImage  The image to use for the blue channel
   * @return A new RGB image combining the input channels
   * @throws IllegalArgumentException if any input image is null or invalid
   */
  ImageInterface combine(ImageInterface redImage,
                         ImageInterface greenImage, ImageInterface blueImage);

  /**
   * Splits an RGB image into its three component color channels.
   *
   * @param image The source RGB image to split
   * @return An array of three images representing the [red, green, blue] channels
   * @throws IllegalArgumentException if the input image is null or invalid
   */
  ImageInterface[] split(ImageInterface image);
}