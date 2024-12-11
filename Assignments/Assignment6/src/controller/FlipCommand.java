package controller;

import java.io.IOException;

import model.ImageModelInterface;

/**
 * Command for flipping an image horizontally or vertically.
 */
public class FlipCommand implements Command {

  private final ImageModelInterface model;

  /**
   * Constructs a FlipCommand with the given model.
   *
   * @param model the image model to apply the command on
   */
  public FlipCommand(ImageModelInterface model) {
    this.model = model;
  }

  /**
   * Executes the flip command to flip an image horizontally or vertically.
   *
   * @param tokens an array of arguments for the flip command, including:
   *               - the flip type ("horizontal-flip" or "vertical-flip")
   *               - the source image name
   *               - the destination image name
   * @throws IOException if an error occurs during execution
   */
  @Override
  public void execute(String[] tokens) throws IOException {
    if (tokens.length < 3) {
      System.out.println("Error: '" + tokens[0]
              + "' requires source and destination image names.");
      return;
    }

    String sourceImageName = tokens[1];
    String destinationImageName = tokens[2];
    String flipType = tokens[0];

    if (!flipType.equals("horizontal-flip") && !flipType.equals("vertical-flip")) {
      System.out.println("Error: Invalid flip operation '" + flipType + "'.");
      return;
    }

    model.flipImage(sourceImageName, destinationImageName, flipType);
  }
}
