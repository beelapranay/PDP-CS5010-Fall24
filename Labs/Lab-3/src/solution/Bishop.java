package solution;

/**
 * Represents a Bishop chess piece. The Bishop can move diagonally on the chessboard.
 * This class extends the MovesAbstractClass which handles
 * common functionality for all chess pieces.
 */
public class Bishop extends MovesAbstractClass {

  /**
   * Constructs a Bishop piece at the specified position with the specified color.
   * The position must be within the bounds of the chessboard (0-7 for both row and column).
   *
   * @param row   the row position of the Bishop (0-7)
   * @param col   the column position of the Bishop (0-7)
   * @param color the color of the Bishop, either BLACK or WHITE
   * @throws IllegalArgumentException if the row or column is out of bounds (not between 0 and 7)
   */
  public Bishop(int row, int col, Color color) throws IllegalArgumentException {
    super(row, col, color);
  }

  /**
   * Determines whether the Bishop can move to the specified position (row, col).
   * The Bishop can only move diagonally, which means the absolute difference
   * between the rows must be equal to the absolute difference between the columns.
   *
   * @param row the destination row (0-7)
   * @param col the destination column (0-7)
   * @return true if the Bishop can move to the specified position, false otherwise
   */
  @Override
  public boolean canMove(int row, int col) {
    if (checkForValidMovement(row, col)) {
      return false;
    }

    return (Math.abs(this.getRow() - row) == Math.abs(this.getColumn() - col));
  }
}
