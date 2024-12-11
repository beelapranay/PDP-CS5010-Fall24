package view;

/**
 * The View interface defines the methods that any view class must implement.
 * This allows the Controller to interact with different types of views
 * without depending on their concrete implementations.
 */
public interface View {

  /**
   * Displays a message to the user.
   *
   * @param message The message to display.
   */
  void displayMessage(String message);
}
