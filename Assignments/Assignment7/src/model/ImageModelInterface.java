package model;

import model.image.ImageInterface;

/**
 * A class that represents a model for images.
 * This class contains a map of images and provides methods to add, get, and remove images.
 */
public interface ImageModelInterface {

  /**
   * Adds an image to the image map with the specified name.
   * If an image with the same name already exists, it is replaced.
   * The image is cloned before adding it to the map.
   * This is to prevent the image in the map from being modified.
   *
   * @param name  the name of the image
   * @param image the image to add
   */
  void addImage(String name, ImageInterface image);

  /**
   * Gets an image from the image map with the specified name.
   * The image is cloned before returning it.
   * This is to prevent the image in the map from being modified.
   *
   * @param name the name of the image
   * @return the image with the specified name, or null if no image is found
   */
  ImageInterface getImage(String name);

  /**
   * Removes an image from the image map with the specified name.
   *
   * @param name the name of the image to remove
   */
  void removeImage(String name);

}
