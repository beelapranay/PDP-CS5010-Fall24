package controller;

import view.ImageViewInterface;

/**
 * Command to generate and store a histogram for a specified image.
 */
public class HistogramCommand implements Command {
  private final ImageViewInterface view;

  /**
   * Constructs a HistogramCommand with the specified view.
   *
   * @param view the view interface to generate and store histograms
   */
  public HistogramCommand(ImageViewInterface view) {
    this.view = view;
  }

  /**
   * Executes the histogram command.
   *
   * @param tokens the input tokens containing the command, source image, and destination image
   */
  @Override
  public void execute(String[] tokens) {
    if (tokens.length < 3) {
      System.out.println("Error: 'histogram' requires a source and a destination image name.");
      return;
    }
    try {
      view.generateAndStoreHistogram(tokens[1], tokens[2]);
    } catch (Exception e) {
      System.out.println("Error generating histogram: " + e.getMessage());
    }
  }
}
