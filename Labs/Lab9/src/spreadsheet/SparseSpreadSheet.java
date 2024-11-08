package spreadsheet;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * This class represents a sparse spreadsheet. A sparse spreadsheet is a spreadsheet
 * with a large number of empty cells. It represents this efficiently using a hash map.
 */
public class SparseSpreadSheet implements SpreadSheet {
  private final Map<CellPosition, Double> sheet;
  private int width;
  private int height;

  /**
   * Creates an empty spreadsheet with no cells populated.
   */
  public SparseSpreadSheet() {
    this.sheet = new HashMap<CellPosition, Double>();
    this.width = 0;
    this.height = 0;
  }

  /**
   * Retrieves the value at the specified cell position.
   *
   * @param row the row index of the cell
   * @param col the column index of the cell
   * @return the value at the specified cell, or 0.0 if the cell is empty
   * @throws IllegalArgumentException if the row or column index is negative
   */
  @Override
  public double get(int row, int col) throws IllegalArgumentException {
    if ((row < 0) || (col < 0)) {
      throw new IllegalArgumentException("Row or column cannot be negative");
    }
    return this.sheet.getOrDefault(new CellPosition(row, col), 0.0);
  }

  /**
   * Sets the value at the specified cell position.
   *
   * @param row   the row index of the cell
   * @param col   the column index of the cell
   * @param value the value to set in the cell
   * @throws IllegalArgumentException if the row or column index is negative
   */
  @Override
  public void set(int row, int col, double value) throws IllegalArgumentException {
    if ((row < 0) || (col < 0)) {
      throw new IllegalArgumentException("Row or column cannot be negative");
    }
    this.sheet.put(new CellPosition(row, col), value);
    if ((row + 1) > height) {
      height = row + 1;
    }

    if ((col + 1) > width) {
      width = col + 1;
    }
  }

  /**
   * Checks if the specified cell is empty.
   *
   * @param row the row index of the cell
   * @param col the column index of the cell
   * @return true if the cell is empty; false otherwise
   * @throws IllegalArgumentException if the row or column index is negative
   */
  @Override
  public boolean isEmpty(int row, int col) throws IllegalArgumentException {
    if ((row < 0) || (col < 0)) {
      throw new IllegalArgumentException("Row or column cannot be negative");
    }
    return !this.sheet.containsKey(new CellPosition(row, col));
  }

  /**
   * Gets the width of the spreadsheet, which is the highest populated column index + 1.
   *
   * @return the width of the spreadsheet
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * Gets the height of the spreadsheet, which is the highest populated row index + 1.
   *
   * @return the height of the spreadsheet
   */
  @Override
  public int getHeight() {
    return this.height;
  }

  /**
   * Represents the position of a cell in the spreadsheet, identified by row and column indices.
   */
  private static class CellPosition {
    private final int row;
    private final int column;

    /**
     * Constructs a CellPosition with specified row and column indices.
     *
     * @param row    the row index of the cell
     * @param column the column index of the cell
     */
    private CellPosition(int row, int column) {
      this.row = row;
      this.column = column;
    }

    /**
     * Checks equality between this CellPosition and another object.
     *
     * @param o the object to compare with
     * @return true if the objects are equal, false otherwise
     */
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

    /**
     * Computes the hash code for the CellPosition.
     *
     * @return the hash code based on row and column indices
     */
    @Override
    public int hashCode() {
      return Objects.hash(this.row, this.column);
    }
  }
}
