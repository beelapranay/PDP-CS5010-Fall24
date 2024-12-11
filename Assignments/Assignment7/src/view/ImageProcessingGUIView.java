package view;

import controller.Features;
import model.image.ImageInterface;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.function.BiConsumer;

/**
 * A GUI view for the image processing application.
 * This class provides a graphical user interface using Java Swing.
 */
public class ImageProcessingGUIView extends JFrame implements GUIView {
  private Features features;
  private JLabel imageLabel;
  private HistogramPanel histogramPanel;

  private String currentImageName;


  /**
   * Constructs an ImageProcessingGUIView.
   * Sets up the GUI and displays the welcome instructions.
   * The window is set to be visible and the window is centered on the screen.
   * The window is set to exit on close and the window size is set to 1400x800.
   */
  public ImageProcessingGUIView() {
    super("Image Processing Application");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(1400, 800);
    setLocationRelativeTo(null);

    // Set up the main layout
    setLayout(new BorderLayout());

    setUpGUI(); // Call the GUI setup method
    setVisible(true);
  }

  /**
   * Sets up the GUI components.
   */
  private void setUpGUI() {
    // Create menu bar and content panels
    createMenuBar();
    createContentPanels();
  }

  /**
   * Sets the controller (Features) for the view.
   *
   * @param features the Features implementation
   */
  public void setController(Features features) {
    this.features = features;
    // Update the action listeners to use features
    updateActionListeners();
  }

  /**
   * Displays a message to the user.
   * The message is displayed in a dialog box.
   *
   * @param message The message to display.
   */
  @Override
  public void displayMessage(String message) {
    JOptionPane.showMessageDialog(this, message);
  }

  /**
   * Displays the image with the given name.
   *
   * @param imageName the name of the image to display
   */
  public void displayImage(String imageName) {
    if (imageName == null || imageName.isEmpty()) {
      displayMessage("Error: Image name is invalid.");
      return;
    }
    try {
      ImageInterface image = features.getImage(imageName);
      if (image != null) {
        setImage(image);
        ImageInterface histogramImage = features.generateHistogramImage(imageName);
        setHistogram(histogramImage);
        currentImageName = imageName;
        setTitle("Image Processing Application");
      }
    } catch (IllegalArgumentException e) {
      displayMessage("Error: " + e.getMessage());
    }
  }

  /**
   * Sets the histogram image in the histogram panel.
   *
   * @param histogramImage the histogram image to display
   */
  private void setHistogram(ImageInterface histogramImage) {
    if (histogramImage != null) {
      BufferedImage bufferedHistogram = ImageConverter.toBufferedImage(histogramImage);
      histogramPanel.setHistogramImage(bufferedHistogram);
    } else {
      histogramPanel.clearHistogram();
    }
  }

  /**
   * Sets the image in the image label.
   *
   * @param image the image to display
   */
  private void setImage(ImageInterface image) {
    if (image != null) {
      BufferedImage bufferedImage = ImageConverter.toBufferedImage(image);
      imageLabel.setIcon(new ImageIcon(bufferedImage));
      imageLabel.setText(null);
    } else {
      imageLabel.setIcon(null);
      imageLabel.setText("Load an image to get started!");
    }
  }

  /**
   * Creates the content panels for the GUI.
   * The content panels include the image display and histogram panel.
   */
  private void createContentPanels() {
    imageLabel = new JLabel("Load an image to get started!", SwingConstants.CENTER);
    imageLabel.setFont(new Font("Arial", Font.ITALIC, 16));
    imageLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
    imageScrollPane.setPreferredSize(new Dimension(600, 600));

    histogramPanel = new HistogramPanel();
    histogramPanel.setBorder(BorderFactory.createTitledBorder("Image Histogram"));

    JSplitPane imageAndHistogramPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, imageScrollPane,
        histogramPanel);
    imageAndHistogramPane.setDividerLocation(500);

    JPanel operationsPanel = createOperationsPanel();
    JScrollPane operationsScrollPane = new JScrollPane(operationsPanel);
    operationsScrollPane.setPreferredSize(new Dimension(250, 800));
    operationsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
        operationsScrollPane, imageAndHistogramPane);
    mainSplitPane.setDividerLocation(250);

    add(mainSplitPane, BorderLayout.CENTER);
  }


  // Buttons for Load and Save
  private JButton loadButton;
  private JButton saveButton;

  /**
   * Creates the operations panel with buttons for all operations.
   *
   * @return the operations panel
   */
  private JPanel createOperationsPanel() {
    JPanel operationsPanel = new JPanel();
    operationsPanel.setLayout(new GridLayout(0, 1, 5, 5));
    operationsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

    // Create Load and Save buttons
    loadButton = createOperationButton("Load Image", "Load an image from file");
    saveButton = createOperationButton("Save Image", "Save the current image");

    // Create buttons for each operation
    blurButton = createOperationButton("Blur", "Apply blur effect");
    brightenButton = createOperationButton("Brighten", "Adjust brightness");
    sharpenButton = createOperationButton("Sharpen", "Apply sharpen effect");
    sepiaButton = createOperationButton("Sepia", "Apply sepia tone");
    flipHorizontalButton = createOperationButton("Flip Horizontal",
        "Flip image horizontally");
    flipVerticalButton = createOperationButton("Flip Vertical",
        "Flip image vertically");
    adjustLevelsButton = createOperationButton("Adjust Levels", "Adjust levels");
    compressButton = createOperationButton("Compress", "Compress image");
    colorCorrectButton = createOperationButton("Color Correct",
        "Apply color correction");
    downscaleButton = createOperationButton("Downscale", "Downscale image");

    redComponentButton = createOperationButton("Red Component",
        "Visualize red component");
    greenComponentButton = createOperationButton("Green Component",
        "Visualize green component");
    blueComponentButton = createOperationButton("Blue Component",
        "Visualize blue component");
    lumaComponentButton = createOperationButton("Luma Component",
        "Visualize luma component");
    intensityComponentButton = createOperationButton("Intensity Component",
        "Visualize intensity component");
    valueComponentButton = createOperationButton("Value Component",
        "Visualize value component");
    ditheringButton = createOperationButton("Dither Image",
            "Dither the image");
    detailsButton = createOperationButton("Show Details", "Show image details");

    // Add Load and Save buttons at the top
    operationsPanel.add(loadButton);
    operationsPanel.add(saveButton);

    // Add a separator label
    JLabel operationsLabel = new JLabel("Operations:");
    operationsLabel.setFont(new Font("Arial", Font.BOLD, 16));
    operationsPanel.add(operationsLabel);

    // Add buttons to panel
    operationsPanel.add(blurButton);
    operationsPanel.add(brightenButton);
    operationsPanel.add(sharpenButton);
    operationsPanel.add(sepiaButton);
    operationsPanel.add(flipHorizontalButton);
    operationsPanel.add(flipVerticalButton);
    operationsPanel.add(adjustLevelsButton);
    operationsPanel.add(compressButton);
    operationsPanel.add(colorCorrectButton);
    operationsPanel.add(downscaleButton);

    // Add visualize component buttons
    operationsPanel.add(redComponentButton);
    operationsPanel.add(greenComponentButton);
    operationsPanel.add(blueComponentButton);
    operationsPanel.add(lumaComponentButton);
    operationsPanel.add(intensityComponentButton);
    operationsPanel.add(valueComponentButton);
    operationsPanel.add(detailsButton);
    operationsPanel.add(ditheringButton);

    return operationsPanel;
  }


  // Buttons for operations
  private JButton blurButton;
  private JButton brightenButton;
  private JButton sharpenButton;
  private JButton sepiaButton;
  private JButton flipHorizontalButton;
  private JButton flipVerticalButton;
  private JButton adjustLevelsButton;
  private JButton compressButton;
  private JButton colorCorrectButton;
  private JButton downscaleButton;
  private JButton redComponentButton;
  private JButton greenComponentButton;
  private JButton blueComponentButton;
  private JButton lumaComponentButton;
  private JButton intensityComponentButton;
  private JButton valueComponentButton;
  private JButton detailsButton;
  private JButton ditheringButton;

  /**
   * Creates a styled button for an operation.
   *
   * @param text    the button text
   * @param toolTip the tooltip text
   * @return the styled JButton
   */
  private JButton createOperationButton(String text, String toolTip) {
    JButton button = new JButton(text);
    button.setToolTipText(toolTip);
    button.setPreferredSize(new Dimension(220, 40));
    button.setFont(new Font("Arial", Font.BOLD, 14));
    button.setBackground(new Color(245, 245, 245)); // Light gray background
    button.setForeground(new Color(50, 50, 50)); // Dark gray text
    button.setFocusPainted(false);

    // Set a minimalistic border
    button.setBorder(BorderFactory.createCompoundBorder(
        new LineBorder(new Color(200, 200, 200), 1, true),
        BorderFactory.createEmptyBorder(5, 10, 5, 10)
    ));

    // Add rollover effect
    button.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseEntered(java.awt.event.MouseEvent evt) {
        button.setBackground(new Color(230, 230, 230));
        button.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(150, 150, 150), 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
      }

      public void mouseExited(java.awt.event.MouseEvent evt) {
        button.setBackground(new Color(245, 245, 245)); // Revert to original background
        button.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
      }
    });

    return button;
  }

  /**
   * Creates the menu bar for the GUI.
   */
  private void createMenuBar() {
    JMenuBar menuBar = new JMenuBar();
    setJMenuBar(menuBar);
  }

  /**
   * Updates the action listeners for the menu items and buttons.
   */
  private void updateActionListeners() {

    loadButton.addActionListener(e -> openLoadDialog());
    saveButton.addActionListener(e -> openSaveDialog());

    // Operations without parameters
    blurButton.addActionListener(e -> performOperationWithSplitView(
        "Blur", features::applyBlur, true));
    sharpenButton.addActionListener(e -> performOperationWithSplitView(
        "Sharpen", features::applySharpen, true));
    sepiaButton.addActionListener(e -> performOperationWithSplitView(
        "Sepia", features::applySepia, true));
    flipHorizontalButton.addActionListener(e -> performOperationWithSplitView(
        "Flip Horizontal", features::applyHorizontalFlip, false));
    flipVerticalButton.addActionListener(e -> performOperationWithSplitView(
        "Flip Vertical", features::applyVerticalFlip, false));
    colorCorrectButton.addActionListener(e -> performOperationWithSplitView(
        "Color Correct", features::applyColorCorrect, true));
    ditheringButton.addActionListener(e -> performOperationWithSplitView(
            "Dither", features::applyDithering, true
    ));

    // Operations with parameters
    brightenButton.addActionListener(e -> {
      if (currentImageName == null) {
        displayMessage("Please load an image before applying operations.");
        return;
      }
      String inputValue = JOptionPane.showInputDialog(this,
          "Enter the amount to adjust brightness:");
      if (inputValue != null) {
        try {
          int increment = Integer.parseInt(inputValue);
          performOperationWithSplitView(
              "brighten",
              (currentName, destName) -> features.applyBrighten(currentName, increment, destName),
              true,
              increment
          );
        } catch (NumberFormatException ex) {
          displayMessage("Invalid brightness value. Please enter a valid integer.");
        }
      }
    });

    adjustLevelsButton.addActionListener(e -> {
      if (currentImageName == null) {
        displayMessage("Please load an image before applying operations.");
        return;
      }
      // Collect parameters
      JTextField blackField = new JTextField();
      JTextField midField = new JTextField();
      JTextField whiteField = new JTextField();
      Object[] message = {
          "Enter the black point value (0 to 255):", blackField,
          "Enter the mid point value (0 to 255):", midField,
          "Enter the white point value (0 to 255):", whiteField
      };
      int option = JOptionPane.showConfirmDialog(
          this, message, "Adjust Levels", JOptionPane.OK_CANCEL_OPTION
      );
      if (option == JOptionPane.OK_OPTION) {
        try {
          int blackPoint = Integer.parseInt(blackField.getText());
          int midPoint = Integer.parseInt(midField.getText());
          int whitePoint = Integer.parseInt(whiteField.getText());
          performOperationWithSplitView(
              "levels-adjust",
              (currentName, destName) -> features.applyLevelsAdjust(
                  currentName, blackPoint, midPoint, whitePoint, destName
              ),
              true,
              blackPoint, midPoint, whitePoint
          );
        } catch (NumberFormatException ex) {
          displayMessage("Invalid input. Please enter numeric values.");
        } catch (IllegalArgumentException ex) {
          displayMessage("Invalid levels: " + ex.getMessage());
        }
      }
    });

    compressButton.addActionListener(e -> {
      if (currentImageName == null) {
        displayMessage("Please load an image before applying operations.");
        return;
      }
      String inputValue = JOptionPane.showInputDialog(this,
          "Enter the compression percentage (0 to 100):");
      if (inputValue != null) {
        double amount = Double.parseDouble(inputValue);
        String destImageName = currentImageName + "_compressed";
        features.applyCompress(currentImageName, amount, destImageName);
        displayImage(destImageName);
      }
    });

    downscaleButton.addActionListener(e -> {
      if (currentImageName == null) {
        displayMessage("Please load an image before applying operations.");
        return;
      }
      JTextField widthField = new JTextField();
      JTextField heightField = new JTextField();
      Object[] message = {
          "Enter target width:", widthField,
          "Enter target height:", heightField,
      };
      int option = JOptionPane.showConfirmDialog(this, message, "Downscale",
          JOptionPane.OK_CANCEL_OPTION);
      if (option == JOptionPane.OK_OPTION) {
        int targetWidth = Integer.parseInt(widthField.getText());
        int targetHeight = Integer.parseInt(heightField.getText());
        String destImageName = currentImageName + "_downscaled";
        features.applyDownscale(currentImageName, targetWidth, targetHeight, destImageName);
        displayImage(destImageName);
      }
    });

    // Visualize component operations
    redComponentButton.addActionListener(e -> performOperationWithSplitView(
        "red-component", features::applyRedComponent, true));
    greenComponentButton.addActionListener(e -> performOperationWithSplitView(
        "green-component", features::applyGreenComponent, true));
    blueComponentButton.addActionListener(e -> performOperationWithSplitView(
        "blue-component", features::applyBlueComponent, true));
    lumaComponentButton.addActionListener(e -> performOperationWithSplitView(
        "luma-component", features::applyLumaComponent, true));
    intensityComponentButton.addActionListener(e -> performOperationWithSplitView(
        "intensity-component", features::applyIntensityComponent, true));
    valueComponentButton.addActionListener(e -> performOperationWithSplitView(
        "luma-component", features::applyLumaComponent, true));

    detailsButton.addActionListener(e -> {
      if (currentImageName == null) {
        displayMessage("Please load an image to view details.");
        return;
      }
      features.showImageDetails(currentImageName);
    });

  }

  /**
   * Opens the load image dialog and handles loading the image.
   */
  public void openLoadDialog() {
    JFileChooser fileChooser = new JFileChooser();
    int returnValue = fileChooser.showOpenDialog(this);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      String imagePath = selectedFile.getAbsolutePath();
      String imageName = "currentImage";
      features.loadImage(imagePath, imageName);
      displayImage(imageName);
      currentImageName = imageName;
    }
  }

  /**
   * Opens the save image dialog and handles saving the image.
   */
  public void openSaveDialog() {
    if (currentImageName == null) {
      displayMessage("No image is currently displayed.");
      return;
    }

    JFileChooser fileChooser = new JFileChooser();
    int returnValue = fileChooser.showSaveDialog(this);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      String imagePath = selectedFile.getAbsolutePath();

      try {
        features.saveImage(imagePath, currentImageName);
        displayMessage("Image saved successfully.");
      } catch (IllegalArgumentException ex) {
        displayMessage("Error saving image: " + ex.getMessage());
      } catch (Exception ex) {
        displayMessage("Unexpected error while saving image: " + ex.getMessage());
      }
    }
  }


  /**
   * Performs an operation with optional live split view preview.
   *
   * @param operationName     The name of the operation.
   * @param operationFunc     The function to apply the operation.
   * @param supportsSplitView Whether the operation supports split view.
   */
  private void performOperationWithSplitView(String operationName,
                                             BiConsumer<String, String> operationFunc,
                                             boolean supportsSplitView,
                                             Object... additionalParams) {
    if (currentImageName == null) {
      displayMessage("Please load an image before applying operations.");
      return;
    }

    // Normalize operation name for image name
    String normalizedOperationName = operationName.toLowerCase().replace(" ", "-");
    System.out.println(normalizedOperationName);

    if (!supportsSplitView) {
      // Directly apply the operation without split preview
      String destImageName = currentImageName + "_" + normalizedOperationName.replace(" ", "");
      operationFunc.accept(currentImageName, destImageName);
      displayImage(destImageName);
      return;
    }

    // If split view is supported, show the options dialog
    String[] options = {"Apply Directly", "Preview with Split", "Cancel"};
    int choice = JOptionPane.showOptionDialog(
        this,
        "Choose how to proceed with the operation:",
        "Operation Options",
        JOptionPane.DEFAULT_OPTION,
        JOptionPane.INFORMATION_MESSAGE,
        null,
        options,
        options[0]
    );

    if (choice == 0) { // Apply directly
      String destImageName = currentImageName + "_" + normalizedOperationName.replace(" ", "");
      operationFunc.accept(currentImageName, destImageName);
      displayImage(destImageName);
    } else if (choice == 1) { // Preview with split view
      openSplitPreviewPopup(normalizedOperationName, operationFunc, additionalParams);
    }
  }

  /**
   * Opens a popup dialog to preview the split view of an operation.
   *
   * @param operationName The name of the operation.
   * @param operationFunc The function to apply the operation.
   */
  private void openSplitPreviewPopup(String operationName,
                                     BiConsumer<String, String> operationFunc,
                                     Object... additionalParams) {
    // Create components for the popup
    JLabel imageLabel = new JLabel();
    JScrollPane scrollPane = new JScrollPane(imageLabel);
    scrollPane.setPreferredSize(new Dimension(800, 600));
    JTextField splitPercentageField = new JTextField("50", 5);

    JButton previewButton = new JButton("Preview with this split %");
    JButton applyButton = new JButton("Apply on entire image");
    JButton cancelButton = new JButton("Cancel");

    // Control panel
    JPanel controlPanel = new JPanel();
    controlPanel.add(new JLabel("Split Percentage:"));
    controlPanel.add(splitPercentageField);
    controlPanel.add(previewButton);
    controlPanel.add(applyButton);
    controlPanel.add(cancelButton);

    // Main panel
    JPanel mainPanel = new JPanel(new BorderLayout());
    mainPanel.add(scrollPane, BorderLayout.CENTER);
    mainPanel.add(controlPanel, BorderLayout.SOUTH);

    // Popup dialog
    JDialog dialog = new JDialog(this, "Preview - " + operationName, true);
    dialog.setContentPane(mainPanel);
    dialog.pack();
    dialog.setLocationRelativeTo(this);

    // Fetch original image
    ImageInterface originalImage = features.getImage(currentImageName);

    // Set the default image (original) in the popup
    if (originalImage != null) {
      BufferedImage bufferedImage = ImageConverter.toBufferedImage(originalImage);
      imageLabel.setIcon(new ImageIcon(bufferedImage));
    }

    // Action listeners
    previewButton.addActionListener(e -> {
      try {
        int splitPercentage = Integer.parseInt(splitPercentageField.getText());
        if (splitPercentage < 0 || splitPercentage > 100) {
          throw new NumberFormatException("Percentage must be between 0 and 100.");
        }

        String tempImageName = "tempImage";

        // Use operationFunc to apply the operation
        operationFunc.accept(currentImageName, tempImageName);

        // Fetch processed image
        ImageInterface processedImage = features.getImage(tempImageName);

        // Use getSplitViewImage for generating split view, passing additionalParams
        ImageInterface splitViewImage = features.getSplitViewImage(
            originalImage, splitPercentage, operationName, additionalParams
        );

        // Displays the split view image
        BufferedImage bufferedSplitView = ImageConverter.toBufferedImage(splitViewImage);
        imageLabel.setIcon(new ImageIcon(bufferedSplitView));

        // Removes temporary image
        features.removeImage(tempImageName);
      } catch (NumberFormatException ex) {
        displayMessage("Invalid input: " + ex.getMessage());
      } catch (Exception ex) {
        displayMessage("Error generating split preview: " + ex.getMessage());
      }
    });

    applyButton.addActionListener(e -> {
      dialog.dispose();
      String destImageName = currentImageName + "_" + operationName.replace(" ", "");
      operationFunc.accept(currentImageName, destImageName);
      displayImage(destImageName);
    });

    cancelButton.addActionListener(e -> dialog.dispose());

    dialog.setVisible(true);
  }
}