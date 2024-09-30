package solution;

/**
 * Represents a Queen chess piece. The Queen is one of the most versatile pieces on the board,
 * as it can move in straight lines horizontally, vertically, or diagonally.
 * This class extends the {@link MovesAbstractClass} to provide shared functionality
 * for all chess pieces.
 */
public class Queen extends MovesAbstractClass {

  /**
   * Creates a Queen piece at the given position and color.
   * Ensures the position is valid on the 8x8 chessboard.
   *
   * @param row   the starting row of the Queen (0-7)
   * @param col   the starting column of the Queen (0-7)
   * @param color the color of the Queen, either WHITE or BLACK
   * @throws IllegalArgumentException if the row or column are out of bounds
   */
  public Queen(int row, int col, Color color) throws IllegalArgumentException {
    super(row, col, color);
  }

  /**
   * Determines whether the Queen can move to the specified (row, col) position.
   * The Queen can move like a Rook (horizontally or vertically) and like a Bishop (diagonally).
   *
   * @param row the row the Queen wants to move to (0-7)
   * @param col the column the Queen wants to move to (0-7)
   * @return true if the Queen can move to the specified position, false otherwise
   */
  @Override
  public boolean canMove(int row, int col) {
    if (checkForValidMovement(row, col)) {
      return false;
    }

    return (this.getRow() == row || this.getColumn() == col
            || Math.abs(this.getRow() - row) == Math.abs(this.getColumn() - col));
  }
}
