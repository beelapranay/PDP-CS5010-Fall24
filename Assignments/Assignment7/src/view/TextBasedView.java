package view;

/**
 * A class that represents a text-based view.
 * This class provides a text-based user interface for the application.
 * It takes input from the user and displays output on the console.
 */
public class TextBasedView implements View {

  /**
   * Constructs a TextBasedView object.
   */
  public TextBasedView() {
    return;
  }

  /**
   * Displays a message on the console.
   *
   * @param message The message to display.
   */
  @Override
  public void displayMessage(String message) {
    System.out.println(message);
  }
}