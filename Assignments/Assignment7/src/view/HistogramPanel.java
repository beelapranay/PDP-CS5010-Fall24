package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;

/**
 * A panel for displaying the histogram of an image.
 */
public class HistogramPanel extends JPanel {
  private final JLabel histogramLabel;

  /**
   * Constructs a HistogramPanel.
   */
  public HistogramPanel() {
    setLayout(new BorderLayout());
    histogramLabel = new JLabel("No histogram available", JLabel.CENTER);
    add(histogramLabel, BorderLayout.CENTER);
  }

  /**
   * Sets the histogram image in the panel.
   *
   * @param histogramImage the histogram image to display
   */
  public void setHistogramImage(BufferedImage histogramImage) {
    if (histogramImage != null) {
      histogramLabel.setIcon(new ImageIcon(histogramImage));
      histogramLabel.setText(null);
    } else {
      clearHistogram();
    }
  }

  /**
   * Clears the histogram display.
   */
  public void clearHistogram() {
    histogramLabel.setIcon(null);
    histogramLabel.setText("No histogram available");
  }
}