package spreadsheet;

/**
 * Implementation of the SpreadSheetWithMacro interface, which supports macro execution.
 * This class wraps an existing SpreadSheet instance and allows macros to be executed on it,
 * enabling additional operations without modifying the core spreadsheet functionality.
 */
public class SpreadSheetWithMacroImpl implements SpreadSheetWithMacro {
  private final SpreadSheet spreadsheet;

  /**
   * Constructs a SpreadSheetWithMacroImpl with a new SparseSpreadSheet instance.
   */
  public SpreadSheetWithMacroImpl() {
    this.spreadsheet = new SparseSpreadSheet();
  }

  /**
   * Constructs a SpreadSheetWithMacroImpl with a specified spreadsheet instance.
   *
   * @param spreadsheet the spreadsheet instance to wrap
   * @throws IllegalArgumentException if the provided spreadsheet is null
   */
  public SpreadSheetWithMacroImpl(SpreadSheet spreadsheet) {
    if (spreadsheet == null) {
      throw new IllegalArgumentException("SpreadSheet instance cannot be null.");
    }
    this.spreadsheet = spreadsheet;
  }

  /**
   * Executes the given macro on the underlying spreadsheet.
   *
   * @param macro the macro to execute
   */
  @Override
  public void executeMacro(SpreadSheetMacro macro) {
    macro.execute(spreadsheet);
  }

  /**
   * Retrieves the value from a specific cell in the spreadsheet.
   *
   * @param row the row index of the cell
   * @param col the column index of the cell
   * @return the value at the specified cell
   */
  @Override
  public double get(int row, int col) {
    return spreadsheet.get(row, col);
  }

  /**
   * Sets a specified value in a specific cell of the spreadsheet.
   *
   * @param row   the row index of the cell
   * @param col   the column index of the cell
   * @param value the value to set in the cell
   */
  @Override
  public void set(int row, int col, double value) {
    spreadsheet.set(row, col, value);
  }

  /**
   * Checks if a specific cell in the spreadsheet is empty.
   *
   * @param row the row index of the cell
   * @param col the column index of the cell
   * @return true if the cell is empty, false otherwise
   */
  @Override
  public boolean isEmpty(int row, int col) {
    return spreadsheet.isEmpty(row, col);
  }

  /**
   * Returns the width of the spreadsheet.
   *
   * @return the width of the spreadsheet
   */
  @Override
  public int getWidth() {
    return spreadsheet.getWidth();
  }

  /**
   * Returns the height of the spreadsheet.
   *
   * @return the height of the spreadsheet
   */
  @Override
  public int getHeight() {
    return spreadsheet.getHeight();
  }
}