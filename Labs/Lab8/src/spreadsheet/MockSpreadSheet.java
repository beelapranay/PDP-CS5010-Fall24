package spreadsheet;

import java.util.List;

/**
 * A mock implementation of {@code SpreadSheet} for testing, logging method calls.
 */
public class MockSpreadSheet implements SpreadSheet {
  private final List<String> log;

  /**
   * Constructs a {@code MockSpreadSheet} with a log to record method calls.
   *
   * @param log the log to record method calls
   */
  public MockSpreadSheet(List<String> log) {
    this.log = log;
  }

  /**
   * Logs the {@code get} method call and returns a dummy value.
   *
   * @param row the row index
   * @param col the column index
   * @return a dummy value for testing
   * @throws IllegalArgumentException if the row or column index is invalid
   */
  @Override
  public double get(int row, int col) throws IllegalArgumentException {
    log.add("get(" + row + ", " + col + ")");
    return 0;
  }

  /**
   * Logs the {@code set} method call with the specified parameters.
   *
   * @param row   the row index
   * @param col   the column index
   * @param value the value to set
   * @throws IllegalArgumentException if the row or column index is invalid
   */
  @Override
  public void set(int row, int col, double value) throws IllegalArgumentException {
    log.add("set(" + row + ", " + col + ", " + value + ")");
  }

  /**
   * Logs the {@code isEmpty} method call and returns {@code true}.
   *
   * @param row the row index
   * @param col the column index
   * @return {@code true} to indicate the cell is empty
   * @throws IllegalArgumentException if the row or column index is invalid
   */
  @Override
  public boolean isEmpty(int row, int col) throws IllegalArgumentException {
    log.add("isEmpty(" + row + ", " + col + ")");
    return true;
  }

  /**
   * Logs the {@code getWidth} method call and returns a default width.
   *
   * @return the default width for testing
   */
  @Override
  public int getWidth() {
    log.add("getWidth()");
    return 0;
  }

  /**
   * Logs the {@code getHeight} method call and returns a default height.
   *
   * @return the default height for testing
   */
  @Override
  public int getHeight() {
    log.add("getHeight()");
    return 0;
  }
}