package mocks;

import controller.Features;
import view.GUIView;

import java.util.ArrayList;
import java.util.List;

/**
 * A mock GUI view class that overrides methods
 * to prevent GUI interactions and records interactions for testing.
 */
public class MockGUIView implements GUIView {
  private final List<String> messages = new ArrayList<>();
  private final List<String> actions = new ArrayList<>();
  private String currentImageName;
  private Features features;

  /**
   * Sets the controller for the GUI.
   *
   * @param features the controller
   */
  public void setController(Features features) {
    this.features = features;
  }

  /**
   * Displays a message to the user.
   *
   * @param message The message to display.
   */
  @Override
  public void displayMessage(String message) {
    messages.add(message);
  }

  /**
   * Gets the messages displayed to the user.
   *
   * @return The messages displayed to the user.
   */
  public List<String> getMessages() {
    return new ArrayList<>(messages);
  }

  /**
   * Gets the actions performed by the user.
   *
   * @return The actions performed by the user.
   */
  public List<String> getActions() {
    return new ArrayList<>(actions);
  }

  /**
   * Simulates loading an image.
   */
  @Override
  public void openLoadDialog() {
    actions.add("openLoadDialog");
    String imagePath = "resources/sampleImages/manhattan.jpg";
    String imageName = "manhattan";
    features.loadImage(imagePath, imageName);
    displayImage(imageName);
    this.currentImageName = imageName;
  }

  /**
   * Simulates saving the image.
   */
  @Override
  public void openSaveDialog() {
    actions.add("openSaveDialog");
    if (currentImageName != null) {
      String imagePath = "resources/" + currentImageName + ".jpg";
      features.saveImage(imagePath, currentImageName);
      displayMessage("Image saved successfully.");
    } else {
      displayMessage("No image is currently displayed.");
    }
  }

  /**
   * Displays an image.
   *
   * @param imageName The name of the image to display.
   */
  @Override
  public void displayImage(String imageName) {
    actions.add("displayImage: " + imageName);
    this.currentImageName = imageName;
  }


  /**
   * Sets the current image name.
   *
   * @param imageName The image name to set.
   */
  public void setCurrentImageName(String imageName) {
    this.currentImageName = imageName;
  }

  /**
   * Gets the current image name.
   *
   * @return The current image name.
   */
  public String getCurrentImageName() {
    return this.currentImageName;
  }

}