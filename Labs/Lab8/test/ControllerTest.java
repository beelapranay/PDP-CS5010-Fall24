import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import spreadsheet.BetterSpreadSheet;
import spreadsheet.ExtendedSparseSpreadSheet;
import spreadsheet.MockBetterSpreadSheet;
import spreadsheet.MockSpreadSheet;
import spreadsheet.SparseSpreadSheet;
import spreadsheet.SpreadSheet;
import spreadsheet.SpreadSheetController;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the {@code SpreadSheetController} to verify the interactive spreadsheet functionality.
 */
public class ControllerTest {
  private String[] outputLines;

  private BetterSpreadSheet betterSpreadSheet;

  /**
   * Sets up the controller with a basic {@code SparseSpreadSheet} and
   * initializes the output for testing.
   */
  @Before
  public void setUp() {
    SpreadSheet sheet = new SparseSpreadSheet();
    StringBuilder output = new StringBuilder();
    betterSpreadSheet = new ExtendedSparseSpreadSheet();
    SpreadSheetController controller = new SpreadSheetController(sheet,
            new StringReader("q"), output);
    controller.startController();

    outputLines = output.toString().split(System.lineSeparator());
  }

  /**
   * Tests that the welcome message is correctly displayed upon starting the controller.
   */
  @Test
  public void testWelcomeMessage() {
    String actualWelcome = String.join(System.lineSeparator(), outputLines[0], outputLines[1]);
    String expectedWelcome = "Welcome to the spreadsheet program!" + System.lineSeparator()
            + "Supported user instructions are: ";
    assertEquals(expectedWelcome, actualWelcome);
  }

  /**
   * Tests that the farewell message is correctly displayed upon quitting the controller.
   */
  @Test
  public void testFarewellMessage() {
    String actualFarewell = outputLines[outputLines.length - 1];
    String expectedFarewell = "Thank you for using this program!";
    assertTrue(actualFarewell.contains(expectedFarewell));
  }

  /**
   * Tests that the controller sends the correct inputs to the {@code MockSpreadSheet}.
   */
  @Test
  public void testControllerSendsCorrectInputs() {
    List<String> log = new ArrayList<>();
    MockSpreadSheet mockSheet = new MockSpreadSheet(log);
    StringBuilder output = new StringBuilder();

    SpreadSheetController controller = new SpreadSheetController(
            mockSheet,
            new StringReader("assign-value B 2 42.0\nquit"),
            output
    );

    controller.startController();

    String expectedLogEntry = "set(1, 1, 42.0)";
    assertEquals(expectedLogEntry, log.get(0));
  }

  /**
   * Tests the bulk assignment feature by verifying that a region is assigned the correct value.
   */

  @Test
  public void testBulkAssign() {
    List<String> log = new ArrayList<>();
    MockBetterSpreadSheet mockSheet = new MockBetterSpreadSheet(log);
    StringBuilder output = new StringBuilder();

    SpreadSheetController controller = new SpreadSheetController(
            mockSheet,
            new StringReader("bulk-assign A 1 B 4 100\nquit"),
            output
    );

    controller.startController();

    String expectedLogEntry = "setRegion(0, 0, 1, 3, 100.0)";
    assertEquals(expectedLogEntry, log.get(0));
  }

  /**
   * Tests setting a single cell within a region.
   */
  @Test
  public void testSetRegionSingleCell() {
    betterSpreadSheet.setRegion(1, 1, 1, 1, 10.0);
    assertEquals(10.0, betterSpreadSheet.get(1, 1), 0.0);
  }

  /**
   * Tests setting multiple cells within a defined region.
   */
  @Test
  public void testSetRegionMultipleCells() {
    betterSpreadSheet.setRegion(0, 0, 2, 2, 5.0);
    for (int row = 0; row <= 2; row++) {
      for (int col = 0; col <= 2; col++) {
        assertEquals(5.0, betterSpreadSheet.get(1, 1), 0.0);
      }
    }
  }

  /**
   * Tests that setting a region updates the spreadsheet dimensions.
   */
  @Test
  public void testSetRegionUpdatesDimensions() {
    betterSpreadSheet.setRegion(0, 0, 3, 3, 20.0);
    assertEquals(4, betterSpreadSheet.getWidth());
    assertEquals(4, betterSpreadSheet.getHeight());
  }

  /**
   * Tests that an exception is thrown when setting a region with negative indices.
   */
  @Test
  public void testSetRegionWithInvalidNegativeIndices() {
    assertThrows(IllegalArgumentException.class, () -> {
      betterSpreadSheet.setRegion(-1, 0, 1, 1, 10.0);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      betterSpreadSheet.setRegion(0, -1, 1, 1, 10.0);
    });
  }

  /**
   * Tests that an exception is thrown when setting a region with invalid bounds.
   */
  @Test
  public void testSetRegionWithInvalidRegionBounds() {
    assertThrows(IllegalArgumentException.class, () -> {
      betterSpreadSheet.setRegion(2, 2, 1, 1, 10.0);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      betterSpreadSheet.setRegion(1, 1, 1, 0, 10.0);
    });
  }
}
