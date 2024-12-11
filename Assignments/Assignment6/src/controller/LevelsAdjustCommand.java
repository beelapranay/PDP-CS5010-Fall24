package controller;

import model.ImageModelInterface;

/**
 * Command to adjust levels in an image using black, mid-tone, and white points.
 */
public class LevelsAdjustCommand implements Command {
  private final ImageModelInterface model;

  /**
   * Constructs a LevelsAdjustCommand with the specified model.
   *
   * @param model the image model interface to perform levels adjustment
   */
  public LevelsAdjustCommand(ImageModelInterface model) {
    this.model = model;
  }

  /**
   * Executes the levels-adjust command.
   *
   * @param tokens the input tokens containing the command, parameters, and image names
   */
  @Override
  public void execute(String[] tokens) {
    try {
      if (tokens.length == 6) {
        int b = Integer.parseInt(tokens[1]);
        int m = Integer.parseInt(tokens[2]);
        int w = Integer.parseInt(tokens[3]);
        model.levelsAdjust(tokens[4], tokens[5], b, m, w, 100);
      } else if (tokens.length == 8 && tokens[6].equals("split")) {
        int b = Integer.parseInt(tokens[1]);
        int m = Integer.parseInt(tokens[2]);
        int w = Integer.parseInt(tokens[3]);
        int splitPercentage = Integer.parseInt(tokens[7]);
        model.levelsAdjust(tokens[4], tokens[5], b, m, w, splitPercentage);
      } else {
        System.out.println("Error: Invalid arguments for 'levels-adjust'.");
      }
    } catch (NumberFormatException e) {
      System.out.println("Error: Invalid input for b, m, w, or split percentage. "
              + "Please enter valid integers.");
    }
  }
}
