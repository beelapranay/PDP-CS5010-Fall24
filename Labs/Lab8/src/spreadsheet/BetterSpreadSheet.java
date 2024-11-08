package spreadsheet;

/**
 * An extension of the {@code SpreadSheet} interface that adds functionality
 * for setting values over a specified rectangular region.
 */
public interface BetterSpreadSheet extends SpreadSheet {

  /**
   * Sets all cells within a specified rectangular region to the given value.
   *
   * @param startRow the starting row of the region (inclusive)
   * @param startCol the starting column of the region (inclusive)
   * @param endRow   the ending row of the region (inclusive)
   * @param endCol   the ending column of the region (inclusive)
   * @param value    the value to set in each cell within the specified region
   * @throws IllegalArgumentException if the specified region is invalid
   */
  void setRegion(int startRow, int startCol, int endRow, int endCol, double value)
          throws IllegalArgumentException;
}

