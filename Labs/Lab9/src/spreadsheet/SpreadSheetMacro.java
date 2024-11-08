package spreadsheet;

/**
 * Represents a macro command that can be executed on a spreadsheet.
 * This interface allows for defining various operations that can be
 * applied to a spreadsheet without altering the core spreadsheet class.
 */
public interface SpreadSheetMacro {

  /**
   * Executes the macro command on the specified spreadsheet.
   *
   * @param spreadsheet the spreadsheet on which the macro is executed
   */
  void execute(SpreadSheet spreadsheet);
}