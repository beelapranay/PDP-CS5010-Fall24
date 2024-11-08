package spreadsheet;

import java.util.Scanner;

/**
 * A controller for a spreadsheet that supports macros, allowing execution of advanced commands
 * such as bulk assignment, range assignment, and averaging.
 */
public class MacroSpreadSheetController extends SpreadSheetController {
  private final SpreadSheetWithMacro spreadsheet;

  /**
   * Constructs a MacroSpreadSheetController with a specified spreadsheet, readable input source,
   * and appendable output destination.
   *
   * @param spreadsheet the spreadsheet that supports macro operations
   * @param readable    the input source for user commands
   * @param appendable  the output destination for responses
   */
  public MacroSpreadSheetController(SpreadSheetWithMacro spreadsheet,
                                    Readable readable, Appendable appendable) {
    super(spreadsheet, readable, appendable);
    this.spreadsheet = spreadsheet;
  }

  /**
   * Processes a user instruction and executes the appropriate command on the spreadsheet.
   *
   * @param userInstruction the command provided by the user
   * @param sc              the scanner to read additional command arguments
   * @param sheet           the spreadsheet being controlled
   */
  @Override
  protected void processCommand(String userInstruction, Scanner sc, SpreadSheet sheet) {
    int fromRow;
    int fromCol;
    int toRow;
    int toCol;
    int destRow;
    int destCol;

    double value;
    double startValue;
    double increment;

    switch (userInstruction) {
      case "bulk-assign-value":
        try {
          fromRow = getRowNum(sc.next());
          fromCol = sc.nextInt() - 1;
          toRow = getRowNum(sc.next());
          toCol = sc.nextInt() - 1;
          value = sc.nextDouble();
          BulkAssignMacro bulkAssignMacro = new BulkAssignMacro(fromRow, fromCol,
                  toRow, toCol, value);
          spreadsheet.executeMacro(bulkAssignMacro);
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;

      case "average":
        try {
          fromRow = getRowNum(sc.next());
          fromCol = sc.nextInt() - 1;
          toRow = getRowNum(sc.next());
          toCol = sc.nextInt() - 1;
          destRow = getRowNum(sc.next());
          destCol = sc.nextInt() - 1;
          AverageMacro averageMacro = new AverageMacro(fromRow, fromCol, toRow,
                  toCol, destRow, destCol);
          spreadsheet.executeMacro(averageMacro);
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;

      case "range-assign":
        try {
          fromRow = getRowNum(sc.next());
          fromCol = sc.nextInt() - 1;
          toRow = getRowNum(sc.next());
          toCol = sc.nextInt() - 1;
          startValue = sc.nextDouble();
          increment = sc.nextDouble();
          RangeAssignMacro rangeAssignMacro = new RangeAssignMacro(fromRow, fromCol, toRow,
                  toCol, startValue, increment);
          spreadsheet.executeMacro(rangeAssignMacro);
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;

      default:
        super.processCommand(userInstruction, sc, sheet);
    }
  }

  /**
   * Prints the menu of supported user instructions, describing each command's purpose and syntax.
   *
   * @throws IllegalStateException if unable to write to the output destination
   */
  @Override
  protected void printMenu() throws IllegalStateException {
    writeMessage("Supported user instructions are: " + System.lineSeparator());
    writeMessage("assign-value row-num col-num value (set a cell to a value)"
            + System.lineSeparator());
    writeMessage("print-value row-num col-num (print the value at a given cell)"
            + System.lineSeparator());
    writeMessage("bulk-assign-value from-row-num from-col-num to-row-num to-col-num value "
            + "(set a range of cells to a value)" + System.lineSeparator());
    writeMessage("range-assign from-row-num from-col-num to-row-num to-col-num start-value "
            + "increment (set a row or column of cells to a range of values starting at the given "
            + "value and advancing by the increment)" + System.lineSeparator());
    writeMessage("average from-row-num from-col-num to-row-num to-col-num dest-row-num "
            + "dest-col-num (compute the average of a range of cells and "
            + "put it at the given location)" + System.lineSeparator());
    writeMessage("menu (Print supported instruction list)" + System.lineSeparator());
    writeMessage("q or quit (quit the program) " + System.lineSeparator());
  }
}
