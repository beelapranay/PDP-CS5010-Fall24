package spreadsheet;

/**
 * Extends {@code SparseSpreadSheet} to support setting values over a specified region.
 */
public class ExtendedSparseSpreadSheet extends SparseSpreadSheet implements BetterSpreadSheet {

  /**
   * Sets all cells in the region defined by the start and end indices to the specified value.
   *
   * @param startRow the starting row index (inclusive)
   * @param startCol the starting column index (inclusive)
   * @param endRow   the ending row index (inclusive)
   * @param endCol   the ending column index (inclusive)
   * @param value    the value to set for each cell in the region
   * @throws IllegalArgumentException if indices are negative or start indices exceed end indices
   */
  @Override
  public void setRegion(int startRow, int startCol, int endRow, int endCol, double value)
          throws IllegalArgumentException {
    if (startRow < 0 || startCol < 0 || endRow < 0 || endCol < 0) {
      throw new IllegalArgumentException("Row and column indices cannot be negative.");
    }
    if (startRow > endRow || startCol > endCol) {
      throw new IllegalArgumentException("Invalid region: start indices must be ≤ end indices.");
    }

    for (int row = startRow; row <= endRow; row++) {
      for (int col = startCol; col <= endCol; col++) {
        set(row, col, value);
      }
    }
  }
}