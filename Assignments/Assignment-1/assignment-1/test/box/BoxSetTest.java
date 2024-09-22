package box;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * This class contains all the unit tests for the methods in the SimpleBoxSet class,
 * which handles operations involving a collection of rectangles.
 */
public class BoxSetTest {

  /**
   * Test adding a rectangle with a negative width.
   * An IllegalArgumentException is expected.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddNegativeWidth() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 2, -39, 34);
  }

  /**
   * Test adding a rectangle with a negative height.
   * An IllegalArgumentException is expected.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddNegativeHeight() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, -3, 3);
  }

  /**
   * Test adding a rectangle with both negative width and height.
   * An IllegalArgumentException is expected.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddNegativeWidthAndHeight() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, -9, -3, -2);
  }

  /**
   * Test adding a rectangle with zero width.
   * An IllegalArgumentException is expected.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddRectangleWithZeroWidth() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 0, 13);
  }

  /**
   * Test adding a rectangle with zero height.
   * An IllegalArgumentException is expected.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddRectangleWithZeroHeight() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 42, 0);
  }

  /**
   * Test retrieving the set of boxes after adding two non-overlapping rectangles.
   * The returned set should match the expected array of rectangles.
   */
  @Test
  public void testGetBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(1, 2, 8, 8);
    simpleBoxSet.add(12, 13, 6, 6);

    int[][] expected = {
            {1, 2, 8, 8},
            {12, 13, 6, 6}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  /**
   * Test adding two non-overlapping rectangles.
   * The expected array should contain both rectangles without any modifications.
   */
  @Test
  public void testNonOverlappingBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(2, 3, 9, 9);
    simpleBoxSet.add(14, 16, 7, 7);

    int[][] expected = {
            {2, 3, 9, 9},
            {14, 16, 7, 7}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  /**
   * Test adding two overlapping rectangles where one overlaps from the center.
   * The expected array contains the fragmented result of the overlap.
   */
  @Test
  public void testOverlappingFCBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 8, 8);
    simpleBoxSet.add(3, 4, 4, 4);

    int[][] expected = {
            {0, 0, 3, 8},
            {3, 0, 4, 4},
            {7, 0, 1, 8},
            {3, 4, 4, 4}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  /**
   * Test adding two rectangles where one fully overlaps the other.
   * The expected result is only the larger rectangle remaining.
   */
  @Test
  public void testOverlappingCOBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 12, 12);
    simpleBoxSet.add(-2, -2, 18, 18);

    int[][] expected = {
            {-2, -2, 18, 18}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  /**
   * Test adding two rectangles where the second overlaps from the top-left.
   * The expected result contains fragmented portions of the original
   * rectangle and the overlapping rectangle.
   */
  @Test
  public void testOverlappingTLBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 12, 12);
    simpleBoxSet.add(-3, 4, 10, 18);

    int[][] expected = {
            {0, 0, 7, 4},
            {7, 0, 5, 12},
            {-3, 4, 10, 18}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  /**
   * Test adding two rectangles where one overlaps from the left-middle side.
   * The expected result includes fragmented portions of the
   * first rectangle and the overlapping one.
   */
  @Test
  public void testOverlappingLMBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 12, 12);
    simpleBoxSet.add(-3, 4, 6, 6);

    int[][] expected = {
            {0, 0, 3, 4},
            {3, 0, 9, 12},
            {0, 10, 3, 2},
            {-3, 4, 6, 6}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  /**
   * Test adding two rectangles where one overlaps from the bottom-left side.
   * The expected result contains fragmented portions of the first
   * rectangle and the overlapping rectangle.
   */
  @Test
  public void testOverlappingBLBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 12, 12);
    simpleBoxSet.add(-3, -4, 9, 12);

    int[][] expected = {
            {6, 0, 6, 12},
            {0, 8, 6, 4},
            {-3, -4, 9, 12}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  /**
   * Test adding two rectangles where one overlaps from the left half
   * of the first rectangle.
   * The expected result includes the non-overlapping portion of the
   * first rectangle and the full overlapping rectangle.
   */
  @Test
  public void testOverlappingLHBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 12, 12);
    simpleBoxSet.add(-3, -5, 6, 17);

    int[][] expected = {
            {3, 0, 9, 12},
            {-3, -5, 6, 17}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  /**
   * Test adding two rectangles where one overlaps from the middle
   * on the vertical side.
   * The expected result is fragmented portions of the original
   * rectangle and the overlapping one.
   */
  @Test
  public void testOverlappingOMHBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 12, 12);
    simpleBoxSet.add(-3, 4, 16, 6);

    int[][] expected = {
            {0, 0, 12, 4},
            {0, 10, 12, 2},
            {-3, 4, 16, 6}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  /**
   * Test adding two rectangles where one overlaps from the
   * bottom-middle side.
   * The expected result is fragmented portions of the first
   * rectangle and the overlapping one.
   */
  @Test
  public void testOverlappingBMBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 12, 12);
    simpleBoxSet.add(3, -4, 6, 6);

    int[][] expected = {
            {0, 0, 3, 12},
            {9, 0, 3, 12},
            {3, 2, 6, 10},
            {3, -4, 6, 6}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  /**
   * Test adding two rectangles where one overlaps from the
   * middle on the horizontal side.
   * The expected result includes fragmented portions of the
   * original rectangle and the overlapping rectangle.
   */
  @Test
  public void testOverlappingMHBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 12, 12);
    simpleBoxSet.add(3, -4, 6, 16);

    int[][] expected = {
            {0, 0, 3, 12},
            {9, 0, 3, 12},
            {3, -4, 6, 16}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  /**
   * Test adding two rectangles where one overlaps from the bottom-right side.
   * The expected result is the non-overlapping part of the first rectangle and
   * the overlapping rectangle.
   */
  @Test
  public void testOverlappingBRBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 12, 12);
    simpleBoxSet.add(9, -3, 6, 8);

    int[][] expected = {
            {0, 0, 9, 12},
            {9, 5, 3, 7},
            {9, -3, 6, 8}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  /**
   * Test adding two rectangles where one overlaps from the right half.
   * The expected result includes the non-overlapping part of the
   * first rectangle and the full overlapping rectangle.
   */
  @Test
  public void testOverlappingRHBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 12, 12);
    simpleBoxSet.add(10, -3, 6, 15);

    int[][] expected = {
            {0, 0, 10, 12},
            {10, -3, 6, 15}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  /**
   * Test adding two rectangles where one overlaps from the
   * right-middle side.
   * The expected result is the fragmented portions of the
   * original rectangle and the overlapping one.
   */
  @Test
  public void testOverlappingRMBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 12, 12);
    simpleBoxSet.add(9, 3, 13, 5);

    int[][] expected = {
            {0, 0, 9, 12},
            {9, 0, 3, 3},
            {9, 8, 3, 4},
            {9, 3, 13, 5}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  /**
   * Test adding two rectangles where one overlaps from the top-right side.
   * The expected result contains the non-overlapping portion of
   * the first rectangle and the overlapping rectangle.
   */
  @Test
  public void testOverlappingTRBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 12, 12);
    simpleBoxSet.add(9, 3, 16, 16);

    int[][] expected = {
            {0, 0, 9, 12},
            {9, 0, 3, 3},
            {9, 3, 16, 16}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  /**
   * Test adding two rectangles where one overlaps
   * from the top-middle side.
   * The expected result is the fragmented parts of the
   * original rectangle and the overlapping one.
   */
  @Test
  public void testOverlappingTMBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 12, 12);
    simpleBoxSet.add(5, 3, 6, 17);

    int[][] expected = {
            {0, 0, 5, 12},
            {5, 0, 6, 3},
            {11, 0, 1, 12},
            {5, 3, 6, 17}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  /**
   * Test adding multiple overlapping rectangles.
   * The expected result contains the fragmented
   * portions of all the rectangles after each addition.
   */
  @Test
  public void testOverlappingAddMultipleBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 12, 12);
    simpleBoxSet.add(0, 0, 4, 4);
    simpleBoxSet.add(7, 0, 4, 4);
    simpleBoxSet.add(10, 0, 2, 12);
    simpleBoxSet.add(0, 6, 12, 4);

    int[][] expected = {
            {0, 0, 4, 4},
            {7, 0, 3, 4},
            {0, 4, 4, 2},
            {0, 10, 4, 2},
            {4, 0, 3, 6},
            {4, 10, 3, 2},
            {7, 4, 3, 2},
            {7, 10, 3, 2},
            {10, 0, 2, 6},
            {10, 10, 2, 2},
            {0, 6, 12, 4}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  /**
   * Test subtracting multiple overlapping rectangles.
   * The expected result is the fragmented
   * parts of the original rectangle after each subtraction.
   */
  @Test
  public void testOverlappingSubMultipleBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 12, 12);
    simpleBoxSet.subtract(0, 0, 4, 4);
    simpleBoxSet.subtract(7, 0, 4, 4);
    simpleBoxSet.add(10, 0, 2, 12);
    simpleBoxSet.subtract(0, 6, 12, 4);

    int[][] expected = {
            {0, 4, 4, 2},
            {0, 10, 4, 2},
            {4, 0, 3, 6},
            {4, 10, 3, 2},
            {7, 4, 3, 2},
            {7, 10, 3, 2},
            {10, 0, 2, 6},
            {10, 10, 2, 2}
    };

    Arrays.sort(expected, Comparator.comparingInt(arr -> arr[0]));
    Arrays.sort(simpleBoxSet.getBoxes(), Comparator.comparingInt(arr -> arr[0]));

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  /**
   * Test subtracting a non-overlapping rectangle.
   * The expected result is that the original rectangle remains unchanged.
   */
  @Test
  public void testSubNonOverlappingBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(5, 5, 10, 10);
    simpleBoxSet.subtract(20, 20, 5, 5);

    int[][] expected = {
            {5, 5, 10, 10}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  /**
   * Test subtracting overlapping rectangles from the first one.
   * The expected result contains the
   * fragmented parts after subtracting the overlapping areas.
   */
  @Test
  public void testSubOverlappingFCBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 12, 12);
    simpleBoxSet.subtract(3, 3, 4, 4);
    simpleBoxSet.subtract(3, 3, 4, 4);

    int[][] expected = {
            {0, 0, 3, 12},
            {3, 0, 4, 3},
            {7, 0, 5, 12},
            {3, 7, 4, 5}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  /**
   * Test subtracting a rectangle that fully
   * overlaps the original rectangle.
   * The expected result is an empty set since
   * the original rectangle is fully removed.
   */
  @Test
  public void testSubOverlappingCOBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(1, 1, 8, 8);
    simpleBoxSet.subtract(0, 0, 10, 10);

    int[][] expected = {
            // No rectangles should remain
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  /**
   * Test subtracting a rectangle that overlaps from the top-left corner.
   * The expected result includes the fragmented parts of the original rectangle.
   */
  @Test
  public void testSubOverlappingTLBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(2, 2, 12, 12);
    simpleBoxSet.subtract(-1, 4, 10, 14);

    int[][] expected = {
            {2, 2, 7, 2},
            {9, 2, 5, 12}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  /**
   * Test subtracting a rectangle that overlaps from the left-middle side.
   * The expected result includes the fragmented parts of the original rectangle.
   */
  @Test
  public void testSubOverlappingLMBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(2, 2, 12, 12);
    simpleBoxSet.subtract(-1, 4, 7, 7);

    int[][] expected = {
            {2, 2, 4, 2},
            {6, 2, 8, 12},
            {2, 11, 4, 3}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  /**
   * Test subtracting a rectangle that overlaps from the bottom-left corner.
   * The expected result contains the fragmented parts of the original rectangle.
   */
  @Test
  public void testSubOverlappingBLBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(1, 1, 12, 12);
    simpleBoxSet.subtract(-3, -2, 9, 12);

    int[][] expected = {
            {6, 1, 7, 12},
            {1, 10, 5, 3}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  /**
   * Test subtracting a rectangle that overlaps from
   * the left half of the original rectangle.
   * The expected result contains the non-overlapping
   * portion of the first rectangle.
   */
  @Test
  public void testSubOverlappingLHBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(5, 5, 15, 15);
    simpleBoxSet.subtract(-5, 2, 10, 20);

    int[][] expected = {
            {5, 5, 15, 15}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  /**
   * Test subtracting a rectangle that
   * overlaps from the middle (on height).
   * The expected result includes the
   * fragmented parts of the original rectangle.
   */
  @Test
  public void testSubOverlappingOMHBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(1, 1, 12, 12);
    simpleBoxSet.subtract(-2, 3, 15, 5);

    int[][] expected = {
            {1, 1, 12, 2},
            {1, 8, 12, 5}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  /**
   * Test subtracting a rectangle that overlaps from the bottom-middle side.
   * The expected result contains the non-overlapping
   * part and the fragmented parts of the original rectangle.
   */
  @Test
  public void testSubOverlappingBMBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(5, 5, 20, 20);
    simpleBoxSet.subtract(10, 0, 8, 10);

    int[][] expected = {
            {5, 5, 5, 20},
            {18, 5, 7, 20},
            {10, 10, 8, 15}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  /**
   * Test subtracting a rectangle that overlaps from the middle (on width).
   * The expected result includes the fragmented
   * parts of the original rectangle on the left and right sides.
   */
  @Test
  public void testSubOverlappingMHBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 20, 20);
    simpleBoxSet.subtract(5, -5, 10, 30);

    int[][] expected = {
            {0, 0, 5, 20},
            {15, 0, 5, 20}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  /**
   * Test subtracting a rectangle that overlaps from the bottom-right corner.
   * The expected result includes the
   * non-overlapping portions of the first rectangle and the fragmented parts.
   */
  @Test
  public void testSubOverlappingBRBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(10, 10, 20, 20);
    simpleBoxSet.subtract(18, 8, 5, 7);

    int[][] expected = {
            {10, 10, 8, 20},
            {23, 10, 7, 20},
            {18, 15, 5, 15}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  /**
   * Test subtracting a rectangle that overlaps from the right half.
   * The expected result contains the non-overlapping part of the original rectangle.
   */
  @Test
  public void testSubOverlappingRHBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 30, 20);
    simpleBoxSet.subtract(25, -5, 10, 30);

    int[][] expected = {
            {0, 0, 25, 20}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  /**
   * Test subtracting a rectangle that overlaps from the right-middle side.
   * The expected result includes the fragmented parts of the original rectangle.
   */
  @Test
  public void testSubOverlappingRMBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 20, 15);
    simpleBoxSet.subtract(12, 4, 15, 5);

    int[][] expected = {
            {0, 0, 12, 15},
            {12, 0, 8, 4},
            {12, 9, 8, 6}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  /**
   * Test subtracting a rectangle that overlaps from the top-right side.
   * The expected result contains the fragmented parts of the original rectangle.
   */
  @Test
  public void testSubOverlappingTRBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 20, 20);
    simpleBoxSet.subtract(18, 5, 10, 15);

    int[][] expected = {
            {0, 0, 18, 20},
            {18, 0, 2, 5}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  /**
   * Test subtracting a rectangle that overlaps from the top-middle side.
   * The expected result contains the fragmented
   * parts of the original rectangle after subtraction.
   */
  @Test
  public void testSubOverlappingTMBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 15, 15);
    simpleBoxSet.subtract(6, 3, 6, 15);

    int[][] expected = {
            {0, 0, 6, 15},
            {6, 0, 6, 3},
            {12, 0, 3, 15}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  /**
   * Test adding the same rectangle multiple times.
   * The expected result is that only one instance of the rectangle remains.
   */
  @Test
  public void testAddSameBox() {
    SimpleBoxSet addSameRectangle = new SimpleBoxSet();
    addSameRectangle.add(10, 10, 8, 8);
    addSameRectangle.add(10, 10, 8, 8);

    assertEquals(1, addSameRectangle.size());
  }

  /**
   * Test adding a rectangle and subtracting the same rectangle.
   * The expected result is an empty set of rectangles.
   */
  @Test
  public void testZeroSet() {
    SimpleBoxSet testZeroSet = new SimpleBoxSet();
    testZeroSet.add(5, 5, 4, 4);
    testZeroSet.subtract(5, 5, 4, 4);

    assertEquals(0, testZeroSet.size());
  }

  /**
   * Test adding multiple rectangles and checking
   * the total number of rectangles.
   * The expected result is the correct
   * number of fragmented rectangles after all additions.
   */
  @Test
  public void testBoxSizeAddMultipleBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 15, 15);
    simpleBoxSet.add(1, 1, 5, 5);
    simpleBoxSet.add(5, 5, 7, 7);
    simpleBoxSet.add(12, 12, 3, 3);
    simpleBoxSet.add(8, 8, 10, 4);

    int expected = 13;

    assertEquals(expected, simpleBoxSet.size());
  }

  /**
   * Test that an empty SimpleBoxSet returns a size of 0.
   * The expected result is 0, as no rectangles have been added.
   */
  @Test
  public void testBoxSizeEmpty() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    int expected = 0;

    assertEquals(expected, simpleBoxSet.size());
  }

  /**
   * Test adding and subtracting multiple
   * overlapping rectangles, then checking the size of the set.
   * The expected result is the correct
   * number of rectangles after all the operations.
   */
  @Test
  public void testOverlappingBoxSizeSubtract() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(2, 2, 12, 12);
    simpleBoxSet.subtract(4, 4, 6, 6);
    simpleBoxSet.subtract(7, 2, 3, 3);
    simpleBoxSet.add(9, 9, 4, 4);
    simpleBoxSet.subtract(0, 0, 8, 4);

    int expected = 7;

    assertEquals(expected, simpleBoxSet.size());
  }

  /**
   * Test adding rectangles in the negative plane (bottom-left quadrant).
   * The expected result is the correct number of fragmented rectangles.
   */
  @Test
  public void testAddInLeftCoordinate() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(-5, -5, 6, 6);
    simpleBoxSet.add(-4, -4, 2, 2);

    assertEquals(5, simpleBoxSet.size());
  }
}