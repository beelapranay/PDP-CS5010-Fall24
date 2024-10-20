package solution;

/**
 * Custom runtime exception thrown when an operation is performed on an empty tree.
 */
public class NothingThereException extends RuntimeException {

  /**
   * Constructs a new NothingThereException with the specified detail message.
   *
   * @param message the detail message
   */
  public NothingThereException(String message) {
    super(message);
  }
}
