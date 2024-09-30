package solution;

/**
 * Represents a Rook chess piece. The Rook can move horizontally or vertically across the board.
 * This class extends the {@link MovesAbstractClass}, which handles
 * common properties like position and color.
 */
public class Rook extends MovesAbstractClass {

  /**
   * Creates a Rook piece at the specified position and color.
   * Ensures that the row and column are within the bounds of the chessboard (0-7).
   *
   * @param row   the starting row for the Rook (0-7)
   * @param col   the starting column for the Rook (0-7)
   * @param color the color of the Rook, either WHITE or BLACK
   * @throws IllegalArgumentException if the row or column are out of bounds
   */
  public Rook(int row, int col, Color color) throws IllegalArgumentException {
    super(row, col, color);
  }

  /**
   * Determines whether the Rook can move to the specified (row, col) position.
   * The Rook can only move in straight lines, either horizontally or vertically.
   *
   * @param row the row the Rook wants to move to (0-7)
   * @param col the column the Rook wants to move to (0-7)
   * @return true if the Rook can move to the specified position, false otherwise
   */
  @Override
  public boolean canMove(int row, int col) {
    if (checkForValidMovement(row, col)) {
      return false;
    }

    return ((this.getRow() == row) || (this.getColumn() == col));
  }
}
