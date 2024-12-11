package controller;

import model.image.ImageInterface;

/**
 * Interface representing the features that can be performed by the controller in response
 * to user actions.
 */
public interface Features {


  /**
   * Loads an image from the given file path and assigns it the given name.
   *
   * @param filePath  the file path of the image
   * @param imageName the name to assign to the loaded image
   */
  void loadImage(String filePath, String imageName);

  /**
   * Saves the image with the given name to the specified file path.
   *
   * @param filePath  the file path to save the image to
   * @param imageName the name of the image to save
   */
  void saveImage(String filePath, String imageName);

  // Image retrieval methods

  /**
   * Gets the image with the given name.
   *
   * @param imageName the name of the image to retrieve
   * @return the image with the given name
   */
  ImageInterface getImage(String imageName);

  /**
   * Generates a histogram image for the given image.
   *
   * @param imageName the name of the image to generate the histogram for
   * @return the histogram image
   */
  ImageInterface generateHistogramImage(String imageName);


  /**
   * Applies the red component operation to the image with the given name.
   *
   * @param imageName     the name of the image to process
   * @param destImageName the name to assign to the resulting image
   */
  void applyRedComponent(String imageName, String destImageName);

  /**
   * Applies the green component operation to the image with the given name.
   *
   * @param imageName     the name of the image to process
   * @param destImageName the name to assign to the resulting image
   */
  void applyGreenComponent(String imageName, String destImageName);

  /**
   * Applies the blue component operation to the image with the given name.
   *
   * @param imageName     the name of the image to process
   * @param destImageName the name to assign to the resulting image
   */
  void applyBlueComponent(String imageName, String destImageName);

  /**
   * Applies the luma component operation to the image with the given name.
   *
   * @param imageName     the name of the image to process
   * @param destImageName the name to assign to the resulting image
   */
  void applyLumaComponent(String imageName, String destImageName);

  /**
   * Applies the intensity component operation to the image with the given name.
   *
   * @param imageName     the name of the image to process
   * @param destImageName the name to assign to the resulting image
   */
  void applyIntensityComponent(String imageName, String destImageName);

  /**
   * Performs a horizontal flip on the image with the given name.
   *
   * @param imageName     the name of the image to flip
   * @param destImageName the name to assign to the resulting image
   */
  void applyHorizontalFlip(String imageName, String destImageName);

  /**
   * Performs a vertical flip on the image with the given name.
   *
   * @param imageName     the name of the image to flip
   * @param destImageName the name to assign to the resulting image
   */
  void applyVerticalFlip(String imageName, String destImageName);

  /**
   * Applies a sepia tone to the image with the given name.
   *
   * @param imageName     the name of the image to process
   * @param destImageName the name to assign to the resulting image
   */
  void applySepia(String imageName, String destImageName);

  /**
   * Applies a sharpen filter to the image with the given name.
   *
   * @param imageName     the name of the image to process
   * @param destImageName the name to assign to the resulting image
   */
  void applySharpen(String imageName, String destImageName);

  /**
   * Applies a blur filter to the image with the given name.
   *
   * @param imageName     the name of the image to process
   * @param destImageName the name to assign to the resulting image
   */
  void applyBlur(String imageName, String destImageName);

  /**
   * Applies brightness adjustment to the image with the given name.
   *
   * @param imageName     the name of the image to adjust
   * @param increment     the amount to adjust brightness by
   * @param destImageName the name to assign to the resulting image
   */
  void applyBrighten(String imageName, int increment, String destImageName);

  /**
   * Compresses the image with the given name using the specified compression percentage.
   *
   * @param imageName            the name of the image to compress
   * @param compressionPercentage the compression percentage to apply
   * @param destImageName        the name to assign to the resulting image
   */
  void applyCompress(String imageName, double compressionPercentage, String destImageName);

  /**
   * Adjusts levels of the image with the given name using the specified parameters.
   *
   * @param imageName     the name of the image to adjust
   * @param blackPoint    the black point value
   * @param midPoint      the mid point value
   * @param whitePoint    the white point value
   * @param destImageName the name to assign to the resulting image
   */
  void applyLevelsAdjust(String imageName, int blackPoint, int midPoint, int whitePoint,
                         String destImageName);

  /**
   * Applies color correction to the image with the given name.
   *
   * @param imageName     the name of the image to correct
   * @param destImageName the name to assign to the resulting image
   */
  void applyColorCorrect(String imageName, String destImageName);

  /**
   * Downscales the image with the given name to the specified width and height.
   *
   * @param imageName     the name of the image to downscale
   * @param targetWidth   the target width
   * @param targetHeight  the target height
   * @param destImageName the name to assign to the resulting image
   */
  void applyDownscale(String imageName, int targetWidth, int targetHeight, String destImageName);

  /**
   * Displays details of the image with the given name.
   *
   * @param imageName the name of the image
   */
  void showImageDetails(String imageName);

  /**
   * Combines two images into a split view based on the split percentage and operation parameters.
   *
   * @param originalImage   The original image.
   * @param splitPercentage The percentage to split the images.
   * @param operationName   The name of the operation.
   * @param params          Additional parameters for the operation.
   * @return The combined split view image.
   */
  ImageInterface getSplitViewImage(ImageInterface originalImage, int splitPercentage,
                                   String operationName, Object... params);

  /**
   * Removes an image from the model by name.
   *
   * @param imageName The name of the image to remove.
   */
  void removeImage(String imageName);

  void applyDithering(String imageName, String destImageName);
}