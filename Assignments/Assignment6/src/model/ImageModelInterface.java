package model;

import java.util.NoSuchElementException;

/**
 * Interface for image processing operations such as applying filters,
 * changing brightness, and performing color manipulations.
 */
public interface ImageModelInterface {

  /**
   * Applies sepia tone effect to an image within a specified split percentage.
   *
   * @param imageName            the name of the image to process
   * @param destinationImageName the name to save the processed image
   * @param splitPercentage      the percentage of the image width to apply sepia tone
   * @throws NoSuchElementException if the image is not found
   */
  void applySepiaTone(String imageName, String destinationImageName, int splitPercentage)
          throws NoSuchElementException;

  /**
   * Applies blur or sharpen effect to an image within a specified split percentage.
   *
   * @param imageName            the name of the image to process
   * @param destinationImageName the name to save the processed image
   * @param splitPercentage      the percentage of the image width to apply transformation
   * @param operationName        the operation name, either "blur" or "sharpen"
   * @throws NoSuchElementException if the image is not found or operation is invalid
   */
  void blurOrSharpen(String imageName, String destinationImageName,
                     int splitPercentage, String operationName) throws NoSuchElementException;

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
  void applyGreyscaleTransformation(String imageName, String destinationImageName,
                           String transformationType, int splitPercentage)
          throws NoSuchElementException;

  /**
   * Changes the brightness of the specified image by the given value.
   *
   * @param imageName            the name of the image to modify
   * @param destinationImageName the name of the destination image to save the result
   * @param value                the value to adjust the brightness
   * @throws NoSuchElementException if the image is not found
   */
  void changeBrightness(String imageName, String destinationImageName,
                        int value)
          throws NoSuchElementException;

  /**
   * Color corrects an image to match target peaks based on histogram data.
   *
   * @param imageName            the name of the image to correct
   * @param destinationImageName the name to save the corrected image
   * @param splitPercentage      the percentage of the image width to apply color correction
   */
  void colorCorrect(String imageName, String destinationImageName, int splitPercentage);

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
  void levelsAdjust(String imageName, String destImageName, int b, int m, int w,
                           int splitPercentage);

  /**
   * Flips the image horizontally or vertically.
   *
   * @param imageName            the name of the image to flip
   * @param destinationImageName the name to save the flipped image
   * @param operationName        the type of flip operation, either
   *                             "horizontal-flip" or "vertical-flip"
   * @throws NoSuchElementException if the image is not found or operation is invalid
   */
  void flipImage(String imageName, String destinationImageName, String operationName)
          throws NoSuchElementException;

  /**
   * Combines greyscale images into a single image using the red, green, and blue components.
   *
   * @param imageName  the name of the combined image
   * @param redImage   the image containing the red component
   * @param greenImage the image containing the green component
   * @param blueImage  the image containing the blue component
   * @throws NoSuchElementException if any of the images are not found
   */
  void combineGreyscale(String imageName, String redImage, String greenImage, String blueImage)
          throws NoSuchElementException;

  /**
   * Compresses an image using the specified compression percentage.
   *
   * @param compressionPercentage the percentage of compression to apply
   * @param imageName             the name of the image to compress
   * @param destinationImageName  the name to save the compressed image
   */
  void compressImage(String compressionPercentage, String imageName, String destinationImageName)
          throws NoSuchElementException;

  /**
   * Downscales an image from its original dimensions to the specified target dimensions
   * using bi linear interpolation.
   *
   * @param sourceImageName     the name of the source image to be downscaled
   * @param destinationImageName the name of the resulting downscaled image
   * @param targetWidth         the desired width of the downscaled image
   * @param targetHeight        the desired height of the downscaled image
   * @throws NoSuchElementException if the source image is not found
   */
  void downscaleImage(String sourceImageName, String destinationImageName,
                      int targetWidth, int targetHeight);
}
