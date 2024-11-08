package spreadsheet;

/**
 * A macro to assign a specified value to all cells within a given range.
 */
public class BulkAssignMacro implements SpreadSheetMacro {
  private final int fromRow;
  private final int fromCol;
  private final int toRow;
  private final int toCol;
  private final double value;

  /**
   * Constructs a BulkAssignMacro to set a range of cells to a specified value.
   *
   * @param fromRow the starting row of the range
   * @param fromCol the starting column of the range
   * @param toRow   the ending row of the range
   * @param toCol   the ending column of the range
   * @param value   the value to assign to each cell in the specified range
   * @throws IllegalArgumentException if any row or column index is negative
   */
  public BulkAssignMacro(int fromRow, int fromCol, int toRow, int toCol, double value) {
    if (fromRow < 0 || fromCol < 0 || toRow < 0 || toCol < 0) {
      throw new IllegalArgumentException("Row and column numbers must be positive!");
    }
    this.fromRow = fromRow;
    this.fromCol = fromCol;
    this.toRow = toRow;
    this.toCol = toCol;
    this.value = value;
  }

  /**
   * Executes the macro by assigning the specified value to all cells within the given range.
   *
   * @param spreadsheet the spreadsheet on which the macro is executed
   */
  @Override
  public void execute(SpreadSheet spreadsheet) {
    for (int row = fromRow; row <= toRow; row++) {
      for (int col = fromCol; col <= toCol; col++) {
        spreadsheet.set(row, col, value);
      }
    }
  }
}
