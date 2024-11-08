package spreadsheet;

/**
 * An extension of the SpreadSheet interface that supports macro execution.
 * This interface allows a spreadsheet to accept and execute macro commands,
 * enabling extended functionality without modifying the core spreadsheet operations.
 */
public interface SpreadSheetWithMacro extends SpreadSheet {

  /**
   * Executes the given macro command on the spreadsheet.
   *
   * @param macro the macro to be executed
   */
  void executeMacro(SpreadSheetMacro macro);
}