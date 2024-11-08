import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import spreadsheet.SpreadSheet;
import spreadsheet.SparseSpreadSheet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * This class is the tester for a sparse spreadsheet.
 */
public class SparseSpreadSheetTest {
  private SpreadSheet sheet;

  @Before
  public void setup() {
    sheet = new SparseSpreadSheet();
  }

  @Test
  public void testGetSet() {
    Random r = new Random(100);
    double[][] expectedSet = new double[100][100];
    for (int i = 0; i < 100; i = i + 1) {
      for (int j = 0; j < 100; j = j + 1) {
        double num = r.nextDouble();
        expectedSet[i][j] = num;
        assertTrue(sheet.isEmpty(i, j));
        assertEquals(0.0, sheet.get(i, j), 0.001);
        sheet.set(i, j, num);
        assertFalse(sheet.isEmpty(i, j));
      }
    }

    for (int i = 0; i < 100; i = i + 1) {
      for (int j = 0; j < 100; j = j + 1) {
        assertEquals(expectedSet[i][j], sheet.get(i, j), 0.01);
      }
    }
  }

  @Test
  public void testGetWidthHeight() {
    for (int i = 0; i < 100; i = i + 1) {
      for (int j = 0; j < 100; j = j + 1) {
        sheet.set(i, j, 0);
        assertEquals((i + 1), sheet.getHeight());
        if (i == 0) {
          assertEquals((j + 1), sheet.getWidth());
        } else {
          assertEquals(100, sheet.getWidth());
        }
      }
    }

    sheet.set(1000, 1000, 0);
    assertEquals(1001, sheet.getWidth());
    assertEquals(1001, sheet.getHeight());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetWithNegativeRow() {
    sheet.set(0, 0, 1);
    sheet.set(0, 1, 9);
    sheet.get(-1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetWithNegativeColumn() {
    sheet.set(0, 0, 1);
    sheet.set(0, 1, 9);
    sheet.get(0, -1);
  }


}