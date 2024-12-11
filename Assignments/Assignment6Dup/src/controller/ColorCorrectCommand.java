package controller;

import model.ImageModelInterface;

/**
 * Represents a command to perform color correction on an image.
 */
public class ColorCorrectCommand implements Command {

  private final ImageModelInterface model;

  /**
   * Constructs a ColorCorrectCommand with the specified image model.
   *
   * @param model the image model to execute the color correction operation
   */
  public ColorCorrectCommand(ImageModelInterface model) {
    this.model = model;
  }

  /**
   * Executes the color correction operation based on the given tokens.
   * The tokens should specify the source image name, destination image name,
   * and optionally a split percentage for partial application.
   *
   * @param tokens an array of strings representing the command arguments
   */
  @Override
  public void execute(String[] tokens) {
    if (tokens.length == 3) {
      model.colorCorrect(tokens[1], tokens[2], 100);
    } else if (tokens.length == 5 && tokens[3].equals("split")) {
      try {
        int splitPercentage = Integer.parseInt(tokens[4]);
        model.colorCorrect(tokens[1], tokens[2], splitPercentage);
      } catch (NumberFormatException e) {
        System.out.println("Error: Split percentage must be a valid integer.");
      }
    } else {
      System.out.println("Error: Invalid arguments for 'color-correct'.");
    }
  }
}
