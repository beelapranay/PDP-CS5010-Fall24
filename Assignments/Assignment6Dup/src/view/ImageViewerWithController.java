package view;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import controller.ImageController;
import model.RGB;

/**
 * A graphical user interface (GUI) application for image editing.
 * This class provides functionalities for loading, manipulating,
 * and saving images using various image processing operations.
 */
public class ImageViewerWithController extends JFrame implements ActionListener {
  private final JLabel imageLabel;
  private final JLabel histogramLabel;

  private final JScrollPane imageScrollPane;

  private File selectedFile;

  private final ImageController controller;

  private boolean isImageLoaded = false;

  private String currentImageName;
  private final String levelsAdjustCommand = "levels-adjust";

  private int compressionPercentage;

  private int bValue;
  private int mValue;
  private int wValue;

  /**
   * Constructs an instance of ImageViewerWithController.
   * Initializes the GUI components and sets up the layout.
   *
   * @param controller the ImageController used for executing image operations.
   */
  public ImageViewerWithController(ImageController controller) {
    this.controller = controller;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    setTitle("Image Editor");
    setSize(screenSize.width, screenSize.height);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    JScrollPane buttonScrollPane = new JScrollPane(buttonPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    buttonPanel.setPreferredSize(new Dimension(200, 400));

    addButton(buttonPanel, "Load Image", "load");

    imageLabel = new JLabel();
    imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
    imageScrollPane = new JScrollPane(imageLabel);

    histogramLabel = new JLabel();
    histogramLabel.setHorizontalAlignment(SwingConstants.CENTER);
    JScrollPane histogramScrollPane = new JScrollPane(histogramLabel);

    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
            imageScrollPane, histogramScrollPane);
    splitPane.setResizeWeight(0.5);
    splitPane.setDividerSize(5);
    splitPane.setContinuousLayout(true);

    add(buttonScrollPane, BorderLayout.WEST);
    add(splitPane, BorderLayout.CENTER);
  }

  private void loadImage(File file) {
    String fileName = file.getName();
    String extension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();

    try {
      if (extension.equals("png") || extension.equals("jpg") || extension.equals("jpeg")
              || extension.equals("ppm")) {
        String imageName = fileName.contains(".")
                ? fileName.substring(0, fileName.lastIndexOf('.'))
                : fileName;
        this.controller.loadImage(file.getAbsolutePath(), imageName);
        RGB[][] rgbs = this.controller.getImages().get(imageName);

        currentImageName = imageName;

        BufferedImage bufferedImage = convertToBufferedImage(rgbs);
        ImageIcon imageIcon = new ImageIcon(bufferedImage);
        revalidateUI(imageIcon);

        showHistogram("");
      } else {
        JOptionPane.showMessageDialog(this,
                "Unsupported file format", "Error",
                JOptionPane.ERROR_MESSAGE);
      }
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(this,
              "Error loading image: " + ex.getMessage(), "Error",
              JOptionPane.ERROR_MESSAGE);
    }
  }

  private void loadImage() {
    JFileChooser fileChooser = new JFileChooser();

    fileChooser.setFileFilter(new FileNameExtensionFilter(
            "Image Files (*.jpg, *.jpeg, *.png, *.ppm)",
            "ppm", "jpg", "jpeg", "png"));

    int returnValue = fileChooser.showOpenDialog(this);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      selectedFile = fileChooser.getSelectedFile();

      loadImage(selectedFile);

      JScrollPane scrollPane = (JScrollPane) getContentPane().getComponent(0);
      JPanel buttonPanel = (JPanel) scrollPane.getViewport().getView();

      if (!isImageLoaded) {
        addButton(buttonPanel, "Convert to Sepia", "sepia");
        addButton(buttonPanel, "Horizontal Flip", "horizontal-flip");
        addButton(buttonPanel, "Vertical Flip", "vertical-flip");
        addButton(buttonPanel, "Blur", "blur");
        addButton(buttonPanel, "Sharpen", "sharpen");
        addButton(buttonPanel, "Grayscale", "luma-component");
        addButton(buttonPanel, "RGB Split", "rgb-split");
        addButton(buttonPanel, "Compress", "compress");
        addButton(buttonPanel, "Color Correct", "color-correct");
        addButton(buttonPanel, "Levels Adjust", levelsAdjustCommand);
        addButton(buttonPanel, "Save Image", "save");
        addButton(buttonPanel, "Downscale", "downscale");

        isImageLoaded = true;
      }

      buttonPanel.revalidate();
      buttonPanel.repaint();
    }
  }

  private void addButton(JPanel panel, String text, String command) {
    JButton button = new JButton(text);
    button.setAlignmentX(Component.CENTER_ALIGNMENT);
    button.addActionListener(this);
    button.setActionCommand(command);

    panel.add(button);
    panel.add(Box.createRigidArea(new Dimension(0, 10)));
  }

  private void updateImageView(String imageName) {
    RGB[][] pixels = this.controller.getImages().get(imageName);
    BufferedImage image = convertToBufferedImage(pixels);
    imageLabel.setIcon(new ImageIcon(image));
  }

  /**
   * Handles user actions performed in the GUI, such as button clicks.
   * Executes the appropriate image manipulation operations or displays errors.
   *
   * @param e the ActionEvent triggered by the user interaction.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    String error;

    try {
      switch (e.getActionCommand()) {
        case "load":
          loadImage();
          break;
        case "horizontal-flip":
        case "vertical-flip":
        case "red":
        case "green":
        case "blue":
        case "compress":
        case "downscale":
          applyOperation(e.getActionCommand());
          break;
        case "blur":
        case "sharpen":
        case "luma-component":
        case "sepia":
        case "color-correct":
        case "levels-adjust":
          applySplitOperation(e.getActionCommand());
          break;
        case "rgb-split":
          applyOperation("rgb-split");
          addRGBButtons();
          break;
        case "save":
          saveImage();
          break;
        default:
          JOptionPane.showMessageDialog(this,
                  "Unknown command: " + e.getActionCommand(),
                  "Error",
                  JOptionPane.ERROR_MESSAGE);
          break;
      }
    } catch (Exception ex) {
      if ((e.getActionCommand().equals("red")
              || e.getActionCommand().equals("green")
              || e.getActionCommand().equals("blue"))) {
        error = "Please click on the RGB Split Button before proceeding.";

        JOptionPane.showMessageDialog(this, error, "Error",
                JOptionPane.ERROR_MESSAGE);
      } else {
        JOptionPane.showMessageDialog(this,
                "An error occurred: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  private int getInputValue(String message, int defaultValue, int minValue, int maxValue) {
    String input = JOptionPane.showInputDialog(this,
            message, String.valueOf(defaultValue));
    try {
      int value = Integer.parseInt(input);
      if (value < minValue || value > maxValue) {
        throw new IllegalArgumentException("Value must be between "
                + minValue + " and " + maxValue + ".");
      }
      return value;
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(this,
              "Invalid input! Using default value: "
                      + defaultValue, "Error", JOptionPane.ERROR_MESSAGE);
      return defaultValue;
    }
  }

  private void applySplitOperation(String operation) {
    if (selectedFile != null) {
      if (operation.equals(levelsAdjustCommand)) {
        showInputDialogForBMW();
      }

      boolean applySplit = false;
      int splitPercentage = 100;

      int response = JOptionPane.showConfirmDialog(
              this,
              "Do you want to apply a split view for this operation?",
              "Split View Option",
              JOptionPane.YES_NO_OPTION
      );

      if (response == JOptionPane.YES_OPTION) {
        applySplit = true;
        splitPercentage = getInputValue(
                "Enter split percentage (0-100):", 50, 0, 100);
      }

      String destinationImageName = currentImageName + "_" + operation;
      String splitImageName = currentImageName + "_" + operation + "_split";
      String command;
      String splitCommand = "";

      if (applySplit) {
        if (operation.equals(levelsAdjustCommand)) {
          splitCommand = operation + " " + bValue + " " + mValue + " " + wValue
                  + " " + currentImageName + " " + splitImageName
                  + " split " + splitPercentage;
        } else {
          splitCommand = operation + " " + currentImageName + " " + splitImageName
                  + " split " + splitPercentage;
        }
      }

      if (operation.equals(levelsAdjustCommand)) {
        command = operation + " " + bValue + " " + mValue + " " + wValue
                + " " + currentImageName + " " + destinationImageName;
      } else {
        command = operation + " " + currentImageName + " " + destinationImageName;
      }

      try {
        if (applySplit) {
          this.controller.commandParser(splitCommand);
        }

        this.controller.commandParser(command);

        if (applySplit) {
          updateImageView(splitImageName);
          showHistogram(splitImageName);
        } else {
          currentImageName = destinationImageName;
          updateImageView(destinationImageName);
          showHistogram(currentImageName);
        }

        currentImageName = destinationImageName;
      } catch (Exception ex) {
        JOptionPane.showMessageDialog(
                this,
                "Error applying the operation: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
      }
    }
  }

  private void applyOperation(String operation) throws IOException {
    if (selectedFile != null) {
      String destinationImageName = currentImageName + "_" + operation
              .replace("-", "_");
      String command = "";
      boolean isRGBSplit = false;

      switch (operation) {
        case "horizontal-flip":
        case "vertical-flip":
          command = operation + " " + currentImageName + " " + destinationImageName;
          break;
        case "rgb-split":
          command = operation + " " + currentImageName + " "
                  + currentImageName + "_red "
                  + currentImageName + "_green "
                  + currentImageName + "_blue";
          this.controller.commandParser(command);
          return;
        case "red":
        case "green":
        case "blue":
          destinationImageName = currentImageName + "_" + operation;
          isRGBSplit = true;
          break;
        case "compress":
          showCompressionDialog();
          destinationImageName = currentImageName + "_compressed";
          command = operation + " " + compressionPercentage + " "
                  + currentImageName + " " + destinationImageName;
          break;

        case "downscale":
          int targetWidth = getInputValue("Enter target width:",
                  100, 1, 5000);
          int targetHeight = getInputValue("Enter target height:",
                  100, 1, 5000);
          destinationImageName = currentImageName + "_downscaled";
          command = "downscale " + targetWidth + " " + targetHeight + " "
                  + currentImageName + " " + destinationImageName;
          break;

        default:
          throw new IOException(operation + " not supported");
      }

      if (!isRGBSplit) {
        this.controller.commandParser(command);
      }

      if (isRGBSplit) {
        updateImageView(destinationImageName);
        showHistogram(destinationImageName);
      } else {
        currentImageName = destinationImageName;
        updateImageView(destinationImageName);
        showHistogram(currentImageName);
      }
    }
  }

  private void showInputDialogForBMW() {
    bValue = getInputValue("Enter b (0-255):", 50, 0, 255);
    mValue = getInputValue("Enter m (0-255):", 100, 0, 255);
    wValue = getInputValue("Enter w (0-255):", 150, 0, 255);

    if (bValue >= mValue || mValue >= wValue) {
      JOptionPane.showMessageDialog(this,
              "Values must be in ascending order: b < m < w.",
              "Invalid Input", JOptionPane.ERROR_MESSAGE);
      showInputDialogForBMW();
    }
  }

  private void showCompressionDialog() {
    compressionPercentage = getInputValue(
            "Enter compression percentage (1-99):",
            50, 1, 99
    );
  }

  private void showHistogram(String imageName) {
    RGB[][] transformedImagePixels;
    String destinationName;

    try {
      if (imageName.contains("red") || imageName.contains("green") || imageName.contains("blue")) {
        destinationName = "destinationImageName_" + imageName.split("-")[0];
        this.controller.commandParser("histogram " + imageName + " " + destinationName);
      } else {
        destinationName = "destinationImageName";
        this.controller.commandParser("histogram " + currentImageName + " " + destinationName);
      }

      transformedImagePixels = this.controller.getImages().get(destinationName);

      BufferedImage transformedImage = convertToBufferedImage(transformedImagePixels);

      histogramLabel.setIcon(new ImageIcon(transformedImage));
      histogramLabel.revalidate();
      histogramLabel.repaint();
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(this,
              "Error generating histogram: " + ex.getMessage(),
              "Error",
              JOptionPane.ERROR_MESSAGE);
    }
  }

  private void addRGBButtons() {
    JScrollPane scrollPane = (JScrollPane) getContentPane().getComponent(0); // Get the JScrollPane
    JPanel buttonPanel = (JPanel) scrollPane.getViewport().getView(); // Get the JPanel inside it

    for (Component component : buttonPanel.getComponents()) {
      if (component instanceof JButton) {
        JButton button = (JButton) component;
        if (button.getActionCommand().equals("red")
                || button.getActionCommand().equals("green")
                || button.getActionCommand().equals("blue")) {
          return;
        }
      }
    }

    addButton(buttonPanel, "Red Image", "red");
    addButton(buttonPanel, "Green Image", "green");
    addButton(buttonPanel, "Blue Image", "blue");

    buttonPanel.revalidate();
    buttonPanel.repaint();
  }

  private void saveImage() throws IOException {
    if (selectedFile != null) {
      JFileChooser fileChooser = new JFileChooser();

      fileChooser.setFileFilter(new FileNameExtensionFilter(
              "Image Files (*.jpg, *.jpeg, *.png, *.ppm)",
              "jpg", "jpeg", "png", "ppm"));

      JPanel savePanel = new JPanel(new BorderLayout());
      JLabel formatLabel = new JLabel("Select format: ");
      JComboBox<String> formatBox = new JComboBox<>(new String[]{"jpg", "jpeg", "png", "ppm"});
      savePanel.add(formatLabel, BorderLayout.WEST);
      savePanel.add(formatBox, BorderLayout.CENTER);

      fileChooser.setAccessory(savePanel);

      int returnValue = fileChooser.showSaveDialog(this);
      if (returnValue == JFileChooser.APPROVE_OPTION) {
        File saveFile = fileChooser.getSelectedFile();

        String selectedFormat = (String) formatBox.getSelectedItem();

        String filePath = saveFile.getAbsolutePath();
        if (!filePath.endsWith("." + selectedFormat)) {
          filePath += "." + selectedFormat;
        }

        String command = "save" + " " + filePath + " " + currentImageName;
        this.controller.commandParser(command);

        JOptionPane.showMessageDialog(this,
                "Image saved successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
      }
    }
  }

  private BufferedImage convertToBufferedImage(RGB[][] pixelArray) {
    int height = pixelArray.length;
    int width = pixelArray[0].length;

    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        RGB pixel = pixelArray[y][x];
        int rgb = (pixel.red << 16) | (pixel.green << 8) | pixel.blue;
        image.setRGB(x, y, rgb);
      }
    }
    return image;
  }

  private void revalidateUI(ImageIcon imageIcon) {
    imageLabel.setIcon(imageIcon);
    imageLabel.revalidate();
    imageScrollPane.revalidate();
  }
}