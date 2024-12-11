package controller.commands;

import model.ImageModelInterface;
import model.image.ImageInterface;
import view.View;

/**
 * Command that displays details of a specified image.
 */
public class DetailsCommand implements Command {
  private final String imageName;
  private final ImageModelInterface model;
  private final View view;

  /**
   * Constructs a DetailsCommand with the given parameters.
   *
   * @param tokens The command tokens (expects "details image-name").
   * @param model  The image model to retrieve the image from.
   * @param view   The view to display the details.
   */
  public DetailsCommand(String[] tokens, ImageModelInterface model, View view) {
    if (tokens.length != 2) {
      throw new IllegalArgumentException("Usage: details image-name");
    }
    this.imageName = tokens[1];
    this.model = model;
    this.view = view;
  }

  /**
   * Executes the command, retrieving details of the specified image.
   */
  @Override
  public void execute() {
    ImageInterface image = model.getImage(imageName);
    if (image == null) {
      throw new IllegalArgumentException("Image not found: " + imageName);
    }

    StringBuilder details = new StringBuilder();
    details.append("Width: ").append(image.getWidth()).append(" pixels\n");
    details.append("Height: ").append(image.getHeight()).append(" pixels\n");
    details.append("Max Color Value: ").append(image.getMaxValue()).append("\n");

    // Use the view to display the details
    view.displayMessage(details.toString());
  }
}