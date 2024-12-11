package controller;

import java.io.IOException;

import model.ImageModelInterface;

/**
 * Command to apply a sepia tone effect to an image.
 */
public class SepiaCommand implements Command {
  private final ImageModelInterface model;

  /**
   * Constructs a SepiaCommand with the specified image model.
   *
   * @param model the image model to handle the sepia tone effect
   */
  public SepiaCommand(ImageModelInterface model) {
    this.model = model;
  }

  /**
   * Executes the sepia command.
   *
   * @param tokens the input tokens containing the command, source image name,
   *               destination image name, and optional split percentage
   * @throws IOException if an error occurs during the operation
   */
  @Override
  public void execute(String[] tokens) throws IOException {
    if (tokens.length == 3) {
      String sourceImageName = tokens[1];
      String destinationImageName = tokens[2];
      model.applySepiaTone(sourceImageName, destinationImageName, 100);
    } else if (tokens.length == 5 && tokens[3].equals("split")) {
      try {
        String sourceImageName = tokens[1];
        String destinationImageName = tokens[2];
        int splitPercentage = Integer.parseInt(tokens[4]);

        if (splitPercentage < 0 || splitPercentage > 100) {
          System.out.println("Error: Split percentage must be between 0 and 100.");
        } else {
          model.applySepiaTone(sourceImageName, destinationImageName, splitPercentage);
        }
      } catch (NumberFormatException e) {
        System.out.println("Error: Split percentage must be a valid integer.");
      }
    } else {
      System.out.println("Error: Invalid arguments for sepia.");
    }
  }
}
