package model;

import java.util.NoSuchElementException;

/**
 * Interface for image processing operations such as applying filters,
 * changing brightness, and performing color manipulations.
 */
public interface ImageModelInterface {

  /**
   * Applies a sepia tone effect to the specified image.
   *
   * @param imageName            the name of the image to apply the effect to
   * @param destinationImageName the name of the destination image to save the result
   * @throws NoSuchElementException if the image is not found
   */
  void applySepiaTone(String imageName, String destinationImageName) throws NoSuchElementException;

  /**
   * Applies a blur effect to the specified image.
   *
   * @param imageName            the name of the image to blur
   * @param destinationImageName the name of the destination image to save the result
   * @throws NoSuchElementException if the image is not found
   */
  void blurImage(String imageName, String destinationImageName) throws NoSuchElementException;

  /**
   * Applies a sharpen effect to the specified image.
   *
   * @param imageName            the name of the image to sharpen
   * @param destinationImageName the name of the destination image to save the result
   * @throws NoSuchElementException if the image is not found
   */
  void sharpenImage(String imageName, String destinationImageName) throws NoSuchElementException;

  /**
   * Changes the brightness of the specified image by the given value.
   *
   * @param imageName            the name of the image to modify
   * @param destinationImageName the name of the destination image to save the result
   * @param value                the value to adjust the brightness
   * @throws NoSuchElementException if the image is not found
   */
  void changeBrightness(String imageName, String destinationImageName, int value)
          throws NoSuchElementException;

  /**
   * Flips the specified image horizontally.
   *
   * @param imageName            the name of the image to flip
   * @param destinationImageName the name of the destination image to save the result
   * @throws NoSuchElementException if the image is not found
   */
  void horizontalFlipImage(String imageName, String destinationImageName)
          throws NoSuchElementException;

  /**
   * Flips the specified image vertically.
   *
   * @param imageName            the name of the image to flip
   * @param destinationImageName the name of the destination image to save the result
   * @throws NoSuchElementException if the image is not found
   */
  void verticalFlipImage(String imageName, String destinationImageName)
          throws NoSuchElementException;

  /**
   * Extracts the red channel from the specified image.
   *
   * @param imageName            the name of the image to process
   * @param destinationImageName the name of the destination image to save the result
   * @throws NoSuchElementException if the image is not found
   */
  void splitRedChannel(String imageName, String destinationImageName) throws NoSuchElementException;

  /**
   * Extracts the green channel from the specified image.
   *
   * @param imageName            the name of the image to process
   * @param destinationImageName the name of the destination image to save the result
   * @throws NoSuchElementException if the image is not found
   */
  void splitGreenChannel(String imageName, String destinationImageName)
          throws NoSuchElementException;

  /**
   * Extracts the blue channel from the specified image.
   *
   * @param imageName            the name of the image to process
   * @param destinationImageName the name of the destination image to save the result
   * @throws NoSuchElementException if the image is not found
   */
  void splitBlueChannel(String imageName, String destinationImageName)
          throws NoSuchElementException;

  /**
   * Calculates the value (max of RGB components) for the specified image.
   *
   * @param imageName            the name of the image to process
   * @param destinationImageName the name of the destination image to save the result
   * @throws NoSuchElementException if the image is not found
   */
  void calculateValue(String imageName, String destinationImageName) throws NoSuchElementException;

  /**
   * Calculates the intensity (average of RGB components) for the specified image.
   *
   * @param imageName            the name of the image to process
   * @param destinationImageName the name of the destination image to save the result
   * @throws NoSuchElementException if the image is not found
   */
  void calculateIntensity(String imageName, String destinationImageName)
          throws NoSuchElementException;

  /**
   * Calculates the luma (weighted average of RGB components) for the specified image.
   *
   * @param imageName            the name of the image to process
   * @param destinationImageName the name of the destination image to save the result
   * @throws NoSuchElementException if the image is not found
   */
  void calculateLuma(String imageName, String destinationImageName) throws NoSuchElementException;

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
}
