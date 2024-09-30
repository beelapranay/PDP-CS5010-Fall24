import solution.ChessPiece;
import solution.Color;
import solution.Rook;

/**
 * Test class for the {@link Rook} chess piece.
 * This class extends {@link ChessPiecesTest} to provide specific
 * test cases for the movement and behavior of the Rook.
 */
public class RookTest extends ChessPiecesTest {

  /**
   * Creates a new {@link Rook} instance with the given row, column, and color.
   * This method is used to instantiate a Rook piece for testing.
   *
   * @param row   the starting row of the Rook (0-7)
   * @param col   the starting column of the Rook (0-7)
   * @param color the color of the Rook (either {@link Color#WHITE} or {@link Color#BLACK})
   * @return a new Rook object initialized with the specified row, column, and color
   */
  @Override
  protected ChessPiece chessPiece(int row, int col, Color color) {
    return new Rook(row, col, color);
  }

  /**
   * Sets up the valid move positions for a Rook from the given starting row and column.
   * The Rook can only move in straight lines, either horizontally or vertically.
   * This method fills the `results` array with `true`
   * for all valid Rook moves and `false` elsewhere.
   *
   * @param row the starting row of the Rook (0-7)
   * @param col the starting column of the Rook (0-7)
   */
  @Override
  protected void setupResults(int row, int col) {
    for (int i = 0; i < 8; i++) {
      results[i][col] = true;
      results[row][i] = true;
    }
  }
}
