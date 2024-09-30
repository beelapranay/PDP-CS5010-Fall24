import solution.ChessPiece;
import solution.Color;
import solution.Knight;

/**
 * Test class for the {@link Knight} chess piece.
 * This class extends {@link ChessPiecesTest} to provide specific test cases
 * for the movement and behavior of the Knight.
 */
public class KnightTest extends ChessPiecesTest {

  /**
   * Creates a new {@link Knight} instance with the given row, column, and color.
   * This method is used to instantiate a Knight piece for testing.
   *
   * @param row   the starting row of the Knight (0-7)
   * @param col   the starting column of the Knight (0-7)
   * @param color the color of the Knight (either {@link Color#WHITE} or {@link Color#BLACK})
   * @return a new Knight object initialized with the specified row, column, and color
   */
  @Override
  protected ChessPiece chessPiece(int row, int col, Color color) {
    return new Knight(row, col, color);
  }

  /**
   * Sets up the valid move positions for a Knight from the given starting row and column.
   * The Knight moves in an "L" shape: two squares in one direction and
   * one in the perpendicular direction, or one square in one direction
   * and two in the perpendicular direction.
   * This method fills the `results` array with `true` for all valid Knight
   * moves and `false` elsewhere.
   *
   * @param row the starting row of the Knight (0-7)
   * @param col the starting column of the Knight (0-7)
   */
  @Override
  protected void setupResults(int row, int col) {
    int[][] knightMoves = {
            {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
            {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
    };

    for (int[] move : knightMoves) {
      int newRow = row + move[0];
      int newCol = col + move[1];

      if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
        results[newRow][newCol] = true;
      }
    }
  }
}
