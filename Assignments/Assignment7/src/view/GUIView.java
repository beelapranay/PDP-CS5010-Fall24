package view;

/**
 * An interface for GUI-specific views that extends the base View interface.
 */
public interface GUIView extends View {

  /**
   * Displays an image with the given name.
   *
   * @param imageName The name of the image to display.
   */
  void displayImage(String imageName);

  /**
   * Prompts the user to load an image via a file dialog.
   */
  void openLoadDialog();

  /**
   * Prompts the user to save an image via a file dialog.
   */
  void openSaveDialog();


}