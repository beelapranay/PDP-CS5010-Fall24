package view;

import java.util.Map;
import java.util.NoSuchElementException;

import model.RGB;

/**
 * ImageView interface provides the graphical interface for displaying and processing
 * image histograms, grid overlays, and image transformations.
 */
public interface ImageViewInterface {
  /**
   * Generates and stores a histogram for the specified image, and saves the
   * histogram image to the controller under the specified destination name.
   *
   * @param imageName            the name of the source image.
   * @param destinationImageName the name under which to save the histogram image.
   * @throws NoSuchElementException if the source image is not found.
   */
  void generateAndStoreHistogram(String imageName, String destinationImageName);

  /**
   * Calculates the histogram data for an RGB pixel array, generating frequency counts
   * for each color channel.
   *
   * @param pixelArray the RGB pixel array.
   * @return a Map containing histogram data for red, green, and blue channels.
   */
  Map<String, int[]> calculateHistogramData(RGB[][] pixelArray);
}