package controller;

import java.io.IOException;

import model.ImageModelInterface;

/**
 * Command to apply a greyscale transformation to an image.
 */
public class GreyscaleCommand implements Command {
  private final ImageModelInterface model;

  /**
   * Constructs a GreyscaleCommand with the specified image model.
   *
   * @param model the image model to interact with
   */
  public GreyscaleCommand(ImageModelInterface model) {
    this.model = model;
  }

  /**
   * Executes the greyscale transformation command.
   *
   * @param tokens the input tokens containing the command, source image, and destination image
   * @throws IOException if an error occurs during execution
   */
  @Override
  public void execute(String[] tokens) throws IOException {
    if (tokens.length < 3) {
      System.out.println("Error: Greyscale transformation requires source and "
              + "destination image names.");
      return;
    }

    String sourceImageName = tokens[1];
    String destinationImageName = tokens[2];
    String transformationType = tokens[0];

    if (tokens.length == 3) {
      model.applyGreyscaleTransformation(sourceImageName, destinationImageName,
              transformationType, 100);
    } else if (tokens.length == 5 && tokens[3].equals("split")) {
      try {
        int splitPercentage = Integer.parseInt(tokens[4]);
        if (splitPercentage < 0 || splitPercentage > 100) {
          System.out.println("Error: Split percentage must be between 0 and 100.");
          return;
        }

        model.applyGreyscaleTransformation(sourceImageName, destinationImageName,
                transformationType, splitPercentage);
      } catch (NumberFormatException e) {
        System.out.println("Error: Invalid split percentage. "
                + "Please enter a valid integer between 0 and 100.");
      }
    } else {
      System.out.println("Error: Invalid arguments for " + transformationType + " transformation.");
    }
  }
}
