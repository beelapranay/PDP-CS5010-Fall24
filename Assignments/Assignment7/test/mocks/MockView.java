package mocks;

import java.util.ArrayList;
import java.util.List;

import view.View;

/**
 * A mock view class that implements the View interface.
 * This class is used for testing purposes.
 */
public class MockView implements View {
  private final List<String> messages = new ArrayList<>();

  /**
   * Constructs a MockView object.
   * This constructor is empty because the view does not have any fields.
   * The messages are stored in a list.
   */
  @Override
  public void displayMessage(String message) {
    messages.add(message);
  }

  /**
   * Returns a list of messages that have been displayed.
   * This method returns a new list to prevent the original list from being modified.
   */
  public List<String> getMessages() {
    return new ArrayList<>(messages);
  }

  /**
   * Returns true if the view has displayed a message that contains the given message.
   */
  public boolean containsMessage(String message) {
    return messages.stream().anyMatch(msg -> msg.contains(message));
  }
}