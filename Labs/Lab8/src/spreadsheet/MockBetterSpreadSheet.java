package spreadsheet;

import java.util.List;

/**
 * A mock implementation of {@code BetterSpreadSheet} for testing, logging method calls.
 */
public class MockBetterSpreadSheet implements BetterSpreadSheet {
  private final List<String> log;

  /**
   * Constructs a {@code MockBetterSpreadSheet} with a log to record method calls.
   *
   * @param log the log to record method calls
   */
  public MockBetterSpreadSheet(List<String> log) {
    this.log = log;
  }

  /**
   * Logs the {@code setRegion} method call with the provided parameters.
   */
  @Override
  public void setRegion(int startRow, int startCol, int endRow, int endCol, double value) {
    log.add("setRegion(" + startRow + ", " + startCol + ", " + endRow + ", "
            + endCol + ", " + value + ")");
  }

  /**
   * Logs the {@code get} method call and returns 0.
   */
  @Override
  public double get(int row, int col) {
    log.add("get(" + row + ", " + col + ")");
    return 0;
  }

  /**
   * Logs the {@code set} method call with the provided parameters.
   */
  @Override
  public void set(int row, int col, double value) {
    log.add("set(" + row + ", " + col + ", " + value + ")");
  }

  /**
   * Logs the {@code isEmpty} method call and returns true.
   */
  @Override
  public boolean isEmpty(int row, int col) {
    log.add("isEmpty(" + row + ", " + col + ")");
    return true;
  }

  /**
   * Logs the {@code getWidth} method call and returns 0.
   */
  @Override
  public int getWidth() {
    log.add("getWidth()");
    return 0;
  }

  /**
   * Logs the {@code getHeight} method call and returns 0.
   */
  @Override
  public int getHeight() {
    log.add("getHeight()");
    return 0;
  }
}