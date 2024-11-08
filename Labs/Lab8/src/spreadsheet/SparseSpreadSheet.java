package spreadsheet;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a sparse spreadsheet using a hash map to efficiently store non-empty cells.
 */
public class SparseSpreadSheet implements SpreadSheet {
  private final Map<CellPosition, Double> sheet;
  private int width;
  private int height;

  /**
   * Initializes an empty sparse spreadsheet.
   */
  public SparseSpreadSheet() {
    this.sheet = new HashMap<>();
    this.width = 0;
    this.height = 0;
  }

  /**
   * Retrieves the value at the specified cell, returning 0 if the cell is empty.
   *
   * @param row the row index
   * @param col the column index
   * @return the value at the specified cell or 0 if the cell is empty
   * @throws IllegalArgumentException if row or column index is negative
   */
  @Override
  public double get(int row, int col) throws IllegalArgumentException {
    if (row < 0 || col < 0) {
      throw new IllegalArgumentException("Row or column cannot be negative");
    }
    return this.sheet.getOrDefault(new CellPosition(row, col), 0.0);
  }

  /**
   * Sets the specified cell to the given value, updating the width and height if necessary.
   *
   * @param row   the row index
   * @param col   the column index
   * @param value the value to set
   * @throws IllegalArgumentException if row or column index is negative
   */
  @Override
  public void set(int row, int col, double value) throws IllegalArgumentException {
    if (row < 0 || col < 0) {
      throw new IllegalArgumentException("Row or column cannot be negative");
    }
    this.sheet.put(new CellPosition(row, col), value);
    if (row + 1 > height) {
      height = row + 1;
    }
    if (col + 1 > width) {
      width = col + 1;
    }
  }

  /**
   * Checks if the specified cell is empty.
   *
   * @param row the row index
   * @param col the column index
   * @return {@code true} if the cell is empty, {@code false} otherwise
   * @throws IllegalArgumentException if row or column index is negative
   */
  @Override
  public boolean isEmpty(int row, int col) throws IllegalArgumentException {
    if (row < 0 || col < 0) {
      throw new IllegalArgumentException("Row or column cannot be negative");
    }
    return !this.sheet.containsKey(new CellPosition(row, col));
  }

  /**
   * Returns the width of the spreadsheet.
   *
   * @return the width of the spreadsheet
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * Returns the height of the spreadsheet.
   *
   * @return the height of the spreadsheet
   */
  @Override
  public int getHeight() {
    return this.height;
  }

  /**
   * Represents a cell position with row and column indices.
   */
  private static class CellPosition {
    private final int row;
    private final int column;

    /**
     * Constructs a {@code CellPosition} with the specified row and column.
     *
     * @param row    the row index
     * @param column the column index
     */
    private CellPosition(int row, int column) {
      this.row = row;
      this.column = column;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof CellPosition)) {
        return false;
      }
      CellPosition other = (CellPosition) o;
      return this.row == other.row && this.column == other.column;
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.row, this.column);
    }
  }
}