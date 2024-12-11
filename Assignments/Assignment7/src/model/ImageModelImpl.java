package model;

import model.image.ImageInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * A class that represents a model for images.
 * This class contains a map of images and provides methods to add, get, and remove images.
 */
public class ImageModelImpl implements ImageModelInterface {

  private Map<String, ImageInterface> imageMap;

  /**
   * Constructs an image model with an empty image map.
   * The image map is used to store images by name.
   */
  public ImageModelImpl() {
    this.imageMap = new HashMap<>();
  }

  /**
   * Adds an image to the image map with the specified name.
   * If an image with the same name already exists, it is replaced.
   * The image is cloned before adding it to the map.
   * This is to prevent the image in the map from being modified.
   *
   * @param name  the name of the image
   * @param image the image to add
   */
  @Override
  public void addImage(String name, ImageInterface image) {
    imageMap.put(name, image.clone());
  }

  /**
   * Gets an image from the image map with the specified name.
   * The image is cloned before returning it.
   * This is to prevent the image in the map from being modified.
   *
   * @param name the name of the image
   * @return the image with the specified name, or null if no image is found
   */
  @Override
  public ImageInterface getImage(String name) {
    ImageInterface image = imageMap.get(name);
    return (image != null) ? image.clone() : null;
  }

  /**
   * Removes an image from the image map with the specified name.
   *
   * @param name the name of the image to remove
   */
  @Override
  public void removeImage(String name) {
    imageMap.remove(name);
  }
}
