import solution.ChessPiece;
import solution.Color;
import solution.Queen;

/**
 * Test class for the {@link Queen} chess piece.
 * This class extends {@link ChessPiecesTest} to provide specific
 * test cases for the movement and behavior of the Queen.
 */
public class QueenTest extends ChessPiecesTest {

  /**
   * Creates a new {@link Queen} instance with the given row, column, and color.
   * This method is used to instantiate a Queen piece for testing.
   *
   * @param row   the starting row of the Queen (0-7)
   * @param col   the starting column of the Queen (0-7)
   * @param color the color of the Queen (either {@link Color#WHITE} or {@link Color#BLACK})
   * @return a new Queen object initialized with the specified row, column, and color
   */
  @Override
  protected ChessPiece chessPiece(int row, int col, Color color) {
    return new Queen(row, col, color);
  }

  /**
   * Sets up the valid move positions for a Queen from the given starting row and column.
   * The Queen can move like a Rook (horizontally or vertically) and like a Bishop (diagonally).
   * This method fills the `results` array with `true` for all
   * valid Queen moves and `false` elsewhere.
   *
   * @param row the starting row of the Queen (0-7)
   * @param col the starting column of the Queen (0-7)
   */
  @Override
  protected void setupResults(int row, int col) {

    for (int i = 0; i < 8; i++) {
      results[i][col] = true;
      results[row][i] = true;
      if ((row + i) < 8) {
        if ((col + i) < 8) {
          results[row + i][col + i] = true;
        }
        if (col >= i) {
          results[row + i][col - i] = true;
        }

      }

      if (row >= i) {
        if ((col + i) < 8) {
          results[row - i][col + i] = true;
        }
        if (col >= i) {
          results[row - i][col - i] = true;
        }
      }
    }

  }
}
