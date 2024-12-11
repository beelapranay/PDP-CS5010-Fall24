package controller;

import java.io.IOException;

import model.ImageModelInterface;

/**
 * Command to split an RGB image into three separate greyscale images for
 * red, green, and blue components.
 */
public class RGBSplitCommand implements Command {
  private final ImageModelInterface model;

  /**
   * Constructs an RGBSplitCommand with the specified image model.
   *
   * @param model the image model to perform the RGB split operation
   */
  public RGBSplitCommand(ImageModelInterface model) {
    this.model = model;
  }

  /**
   * Executes the RGB split command.
   *
   * @param tokens the input tokens containing the command, source image name,
   *               and three destination image names for red, green, and blue components
   * @throws IOException if an error occurs during the RGB split operation
   */
  @Override
  public void execute(String[] tokens) throws IOException {
    if (tokens.length < 5) {
      System.out.println("Error: 'rgb-split' requires three destination image names.");
    } else {
      try {
        model.applyGreyscaleTransformation(tokens[1], tokens[2],
                "red-component", 100);
        model.applyGreyscaleTransformation(tokens[1], tokens[3],
                "green-component", 100);
        model.applyGreyscaleTransformation(tokens[1], tokens[4],
                "blue-component", 100);
      } catch (Exception e) {
        System.out.println("Error during RGB Split: " + e.getMessage());
      }
    }
  }
}
