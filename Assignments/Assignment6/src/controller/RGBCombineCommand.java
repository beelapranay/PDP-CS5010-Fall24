package controller;

import java.io.IOException;

import model.ImageModelInterface;

/**
 * Command to combine three greyscale images into a single RGB image.
 */
public class RGBCombineCommand implements Command {
  private final ImageModelInterface model;

  /**
   * Constructs an RGBCombineCommand with the specified image model.
   *
   * @param model the image model to perform the RGB combine operation
   */
  public RGBCombineCommand(ImageModelInterface model) {
    this.model = model;
  }

  /**
   * Executes the RGB combine command.
   *
   * @param tokens the input tokens containing the command, three source image names,
   *               and one destination image name
   * @throws IOException if an error occurs during the RGB combine operation
   */
  @Override
  public void execute(String[] tokens) throws IOException {
    if (tokens.length < 5) {
      System.out.println("Error: 'rgb-combine' requires three source "
              + "and one destination image names.");
    } else {
      try {
        model.combineGreyscale(tokens[1], tokens[2], tokens[3], tokens[4]);
      } catch (Exception e) {
        System.out.println("Error during RGB Combine: " + e.getMessage());
      }
    }
  }
}
