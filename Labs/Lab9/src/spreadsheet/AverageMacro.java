package spreadsheet;

/**
 * A macro to compute the average of values in a specified range of cells and store the result
 * in a designated destination cell.
 */
public class AverageMacro implements SpreadSheetMacro {
  private final int fromRow;
  private final int fromCol;
  private final int toRow;
  private final int toCol;
  private final int destRow;
  private final int destCol;

  /**
   * Constructs an AverageMacro with a specified range of cells and a destination cell.
   *
   * @param fromRow the starting row of the range
   * @param fromCol the starting column of the range
   * @param toRow   the ending row of the range
   * @param toCol   the ending column of the range
   * @param destRow the row of the destination cell where the average will be stored
   * @param destCol the column of the destination cell where the average will be stored
   */
  public AverageMacro(int fromRow, int fromCol, int toRow, int toCol, int destRow, int destCol) {
    this.fromRow = fromRow;
    this.fromCol = fromCol;
    this.toRow = toRow;
    this.toCol = toCol;
    this.destRow = destRow;
    this.destCol = destCol;
  }

  /**
   * Executes the macro by calculating the average of values within the specified range
   * and setting the result in the destination cell.
   *
   * @param spreadsheet the spreadsheet on which the macro is executed
   */
  @Override
  public void execute(SpreadSheet spreadsheet) {
    double sum = 0.0;
    int count = 0;
    for (int row = fromRow; row <= toRow; row++) {
      for (int col = fromCol; col <= toCol; col++) {
        sum += spreadsheet.get(row, col);
        count++;
      }
    }
    double average = count == 0 ? 0.0 : sum / count;
    spreadsheet.set(destRow, destCol, average);
  }
}
