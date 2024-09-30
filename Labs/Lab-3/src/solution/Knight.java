package solution;

/**
 * Represents a Knight chess piece. The Knight moves in an "L" shape, either two squares
 * in one direction and one square in a perpendicular direction or vice versa.
 * This class extends the MovesAbstractClass which handles common
 * functionality for all chess pieces.
 */
public class Knight extends MovesAbstractClass {

  /**
   * Constructs a Knight piece at the specified position with the specified color.
   * The position must be within the bounds of the chessboard (0-7 for both row and column).
   *
   * @param row   the row position of the Knight (0-7)
   * @param col   the column position of the Knight (0-7)
   * @param color the color of the Knight, either BLACK or WHITE
   * @throws IllegalArgumentException if the row or column is out of bounds (not between 0 and 7)
   */
  public Knight(int row, int col, Color color) throws IllegalArgumentException {
    super(row, col, color);
  }

  /**
   * Determines whether the Knight can move to the specified position (row, col).
   * The Knight moves in an "L" shape: either two squares in one direction and one square
   * in a perpendicular direction, or one square in one direction and two
   * squares in a perpendicular direction.
   *
   * @param row the destination row (0-7)
   * @param col the destination column (0-7)
   * @return true if the Knight can move to the specified position, false otherwise
   */
  @Override
  public boolean canMove(int row, int col) {
    if (checkForValidMovement(row, col)) {
      return false;
    }


    return (Math.abs(this.getRow() - row) == 2 && Math.abs(this.getColumn() - col) == 1)
            || (Math.abs(this.getRow() - row) == 1 && Math.abs(this.getColumn() - col) == 2);
  }
}
