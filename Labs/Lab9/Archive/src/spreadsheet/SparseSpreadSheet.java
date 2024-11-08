package spreadsheet;

import java.util.HashMap;
import java.util.Map;

public class SparseSpreadSheet implements SpreadSheet {
  private final Map<String, Double> cells = new HashMap<>();
  private int maxRow = 0;
  private int maxCol = 0;

  // Helper to create a key for each cell using row and column
  private String getKey(int row, int col) {
    return row + "," + col;
  }

  private void checkBounds(int row, int col) {
    if (row < 0 || col < 0) {
      throw new IllegalArgumentException("Row and column indices must be non-negative.");
    }
  }

  @Override
  public double get(int row, int col) throws IllegalArgumentException {
    checkBounds(row, col);
    return cells.getOrDefault(getKey(row, col), 0.0);
  }

  @Override
  public void set(int row, int col, double value) throws IllegalArgumentException {
    checkBounds(row, col);
    cells.put(getKey(row, col), value);
    maxRow = Math.max(maxRow, row);
    maxCol = Math.max(maxCol, col);
  }

  @Override
  public boolean isEmpty(int row, int col) throws IllegalArgumentException {
    checkBounds(row, col);
    return !cells.containsKey(getKey(row, col));
  }

  @Override
  public int getWidth() {
    return maxCol + 1;
  }

  @Override
  public int getHeight() {
    return maxRow + 1;
  }
}
