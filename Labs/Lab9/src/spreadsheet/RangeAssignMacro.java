package spreadsheet;

/**
 * A macro to assign a range of values to cells within a specified range, starting at a given
 * value and incrementing by a specified amount for each subsequent cell.
 */
public class RangeAssignMacro implements SpreadSheetMacro {
  private final int fromRow;
  private final int fromCol;
  private final int toRow;
  private final int toCol;
  private final double startValue;
  private final double increment;

  /**
   * Constructs a RangeAssignMacro to set a range of cells to sequential values.
   *
   * @param fromRow    the starting row of the range
   * @param fromCol    the starting column of the range
   * @param toRow      the ending row of the range
   * @param toCol      the ending column of the range
   * @param startValue the initial value to assign to the first cell in the range
   * @param increment  the increment to apply to each subsequent cell in the range
   */
  public RangeAssignMacro(int fromRow, int fromCol, int toRow, int toCol, double startValue,
                          double increment) {
    this.fromRow = fromRow;
    this.fromCol = fromCol;
    this.toRow = toRow;
    this.toCol = toCol;
    this.startValue = startValue;
    this.increment = increment;
  }

  /**
   * Executes the macro by assigning values to cells within the specified range. The first cell
   * in the range is assigned the start value, and each subsequent cell is assigned a value
   * incremented by the specified amount.
   *
   * @param spreadsheet the spreadsheet on which the macro is executed
   */
  @Override
  public void execute(SpreadSheet spreadsheet) {
    double value = startValue;
    for (int row = fromRow; row <= toRow; row++) {
      for (int col = fromCol; col <= toCol; col++) {
        spreadsheet.set(row, col, value);
        value += increment;
      }
    }
  }
}
