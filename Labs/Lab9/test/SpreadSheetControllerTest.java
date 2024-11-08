import org.junit.Test;

import spreadsheet.SpreadSheet;
import spreadsheet.SpreadSheetController;
import spreadsheet.SparseSpreadSheet;

import java.io.StringReader;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the SpreadSheetController to verify its functionality
 * in processing user commands for assigning values to cells and printing cell values.
 */
public class SpreadSheetControllerTest {

  /**
   * Tests the "assign-value" and "print-value" commands in the controller.
   * This test verifies that cell values are set and retrieved correctly,
   * and that the expected output matches the actual output.
   */
  @Test
  public void testAssignValue() {
    StringBuilder sb = new StringBuilder();
    StringBuilder expected = new StringBuilder();

    expected.append("Welcome to the spreadsheet program!")
            .append(System.lineSeparator())
            .append("Supported user instructions are: ")
            .append(System.lineSeparator())
            .append("assign-value row-num col-num value (set a cell to a value)")
            .append(System.lineSeparator())
            .append("print-value row-num col-num (print the value at a given cell)")
            .append(System.lineSeparator()).append("menu (Print supported instruction list)")
            .append(System.lineSeparator()).append("q or quit (quit the program) ")
            .append(System.lineSeparator());

    for (int i = 1; i <= 3; i++) {
      for (int j = 0; j < 2; j++) {
        expected.append("Type instruction: ");
        sb.append("assign-value ")
                .append((char) ('A' + j))
                .append(" ").append(i)
                .append(" -10")
                .append(System.lineSeparator());
      }
    }

    for (int i = 1; i < 6; i++) {
      for (int j = 0; j < 3; j++) {
        expected.append("Type instruction: ");
        sb.append("print-value ")
                .append((char) ('A' + j))
                .append(" ")
                .append(i)
                .append(System.lineSeparator());
        if (j < 2 && i <= 3) {
          expected.append("Value: -10.0")
                  .append(System.lineSeparator());
        } else {
          expected.append("Value: 0.0")
                  .append(System.lineSeparator());
        }
      }
    }

    expected.append("Thank you for using this program!");

    Readable input = new StringReader(sb.toString());
    Appendable output = new StringBuilder();
    SpreadSheet model = new SparseSpreadSheet();
    SpreadSheetController controller = new SpreadSheetController(model, input, output);
    controller.control();

    assertEquals(expected.toString(), output.toString());
  }
}
