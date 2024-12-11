package mocks;

import java.util.HashMap;
import java.util.Map;

import model.ImageModelInterface;
import model.image.ImageInterface;

/**
 * A mock model class that implements the ImageModelInterface.
 * This class is used for testing purposes.
 */
public class MockModel implements ImageModelInterface {
  private final Map<String, ImageInterface> images = new HashMap<>();

  /**
   * Constructs a MockModel object.
   * This constructor is empty because the model does not have any fields.
   * The images are stored in a map.
   * The key is the name of the image and the value is the image itself.
   * The images are stored as a clone of the original image.
   * This is done to prevent the original image from being modified.
   */
  @Override
  public void addImage(String name, ImageInterface image) {
    images.put(name, image.clone());
  }

  /**
   * Returns a clone of the image with the given name.
   * If the image does not exist, this method returns null.
   */
  @Override
  public ImageInterface getImage(String name) {
    ImageInterface image = images.get(name);
    return (image != null) ? image.clone() : null;
  }

  /**
   * Removes the image with the given name.
   */
  @Override
  public void removeImage(String name) {
    images.remove(name);
  }

  /**
   * Returns true if the model contains an image with the given name.
   */
  public boolean hasImage(String name) {
    return images.containsKey(name);
  }
}