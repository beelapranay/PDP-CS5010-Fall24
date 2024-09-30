import solution.Bishop;
import solution.ChessPiece;
import solution.Color;

/**
 * Unit test class for the {@link Bishop} chess piece.
 * This class extends the base {@link ChessPiecesTest} and provides the implementation for
 * testing the specific movement rules of a Bishop. The Bishop moves diagonally on the chessboard.
 */
public class BishopTest extends ChessPiecesTest {

  /**
   * Creates a new instance of a {@link Bishop} with the specified row, column, and color.
   * This method is used to create the Bishop piece for testing.
   *
   * @param row   the starting row for the Bishop (0-7)
   * @param col   the starting column for the Bishop (0-7)
   * @param color the color of the Bishop, either {@link Color#WHITE} or {@link Color#BLACK}
   * @return a new Bishop object with the given position and color
   */
  @Override
  protected ChessPiece chessPiece(int row, int col, Color color) {
    return new Bishop(row, col, color);
  }

  /**
   * Sets up the expected valid moves for the Bishop at the specified starting position (row, col).
   * The Bishop can only move diagonally, so this method fills the `results` array
   * with `true` for all diagonal positions the Bishop can move to, and `false` elsewhere.
   *
   * @param row the starting row of the Bishop (0-7)
   * @param col the starting column of the Bishop (0-7)
   */
  @Override
  protected void setupResults(int row, int col) {
    for (int i = 0; i < 8; i++) {
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
