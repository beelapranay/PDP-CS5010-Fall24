package spreadsheet;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class represents the controller of an interactive spreadsheet application.
 * It provides a simple text interface for users to type instructions and manipulate a spreadsheet.
 * This controller can work with any `Readable` to read inputs and any `Appendable` to transmit
 * output, allowing flexibility in input and output sources.
 * A cell in the spreadsheet is referred to using a row-letter and a column number format.
 * Row letters start from A-Z, then AA-ZZ, etc., while column numbers begin from 1.
 */
public class SpreadSheetController {
  private Readable readable;
  private Appendable appendable;
  private SpreadSheet sheet;

  /**
   * Creates a controller to work with the specified spreadsheet (model),
   * readable (for inputs), and appendable (for outputs).
   *
   * @param sheet      the spreadsheet model to control
   * @param readable   the source to read user inputs
   * @param appendable the destination to write outputs
   * @throws IllegalArgumentException if any parameter is null
   */
  public SpreadSheetController(SpreadSheet sheet, Readable readable, Appendable appendable) {
    if ((sheet == null) || (readable == null) || (appendable == null)) {
      throw new IllegalArgumentException("Sheet, readable, or appendable is null");
    }
    this.sheet = sheet;
    this.appendable = appendable;
    this.readable = readable;
  }

  /**
   * Starts the controller, relinquishing control of the application to handle user instructions.
   *
   * @throws IllegalStateException if unable to transmit output
   */
  public void control() throws IllegalStateException {
    Scanner sc = new Scanner(readable);
    boolean quit = false;

    // Print the welcome message
    this.welcomeMessage();

    // Process commands until the user decides to quit
    while (!quit && sc.hasNext()) {
      writeMessage("Type instruction: ");
      String userInstruction = sc.next();
      if (userInstruction.equals("quit") || userInstruction.equals("q")) {
        quit = true;
      } else {
        processCommand(userInstruction, sc, sheet);
      }
    }

    // Print farewell message after the user quits
    this.farewellMessage();
  }

  /**
   * Processes a user command and performs the appropriate action on the spreadsheet.
   *
   * @param userInstruction the command provided by the user
   * @param sc              the scanner to read additional command arguments
   * @param sheet           the spreadsheet being controlled
   */
  protected void processCommand(String userInstruction, Scanner sc, SpreadSheet sheet) {
    int row;
    int col;
    double value;

    switch (userInstruction) {
      case "assign-value":
        try {
          row = getRowNum(sc.next());
          col = sc.nextInt();
          value = sc.nextDouble();
          sheet.set(row, col - 1, value);
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;
      case "print-value":
        try {
          row = getRowNum(sc.next());
          col = sc.nextInt();
          writeMessage("Value: " + sheet.get(row, col - 1) + System.lineSeparator());
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;
      case "menu":
        printMenu();
        break;
      default:
        writeMessage("Undefined instruction: " + userInstruction + System.lineSeparator());
    }
  }

  /**
   * Converts a row string (e.g., "A", "AA") into a zero-based row number.
   *
   * @param rowLetters the row string to convert
   * @return the zero-based row number
   * @throws IllegalArgumentException if the row string is invalid
   */
  protected int getRowNum(String rowLetters) throws IllegalArgumentException {
    int rownumber = 0;

    for (int i = 0; i < rowLetters.length(); i++) {
      char c = rowLetters.charAt(i);
      if (!Character.isAlphabetic(c)) {
        throw new IllegalArgumentException("Invalid row");
      }
      rownumber = 26 * rownumber + (Character.toLowerCase(c) - 'a' + 1);
    }
    return rownumber - 1;
  }

  /**
   * Writes a message to the output destination.
   *
   * @param message the message to write
   * @throws IllegalStateException if unable to write the message
   */
  protected void writeMessage(String message) throws IllegalStateException {
    try {
      appendable.append(message);
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  /**
   * Prints the menu of supported instructions.
   *
   * @throws IllegalStateException if unable to write to the output
   */
  protected void printMenu() throws IllegalStateException {
    writeMessage("Supported user instructions are: " + System.lineSeparator());
    writeMessage("assign-value row-num col-num value (set a cell to a value)"
            + System.lineSeparator());
    writeMessage("print-value row-num col-num (print the value at a given cell)"
            + System.lineSeparator());
    writeMessage("menu (Print supported instruction list)" + System.lineSeparator());
    writeMessage("q or quit (quit the program) " + System.lineSeparator());
  }

  /**
   * Displays a welcome message and the menu of instructions.
   *
   * @throws IllegalStateException if unable to write to the output
   */
  protected void welcomeMessage() throws IllegalStateException {
    writeMessage("Welcome to the spreadsheet program!" + System.lineSeparator());
    printMenu();
  }

  /**
   * Displays a farewell message upon quitting the program.
   *
   * @throws IllegalStateException if unable to write to the output
   */
  protected void farewellMessage() throws IllegalStateException {
    writeMessage("Thank you for using this program!");
  }
}
