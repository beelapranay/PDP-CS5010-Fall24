package solution;

/**
 * This abstract class provides the basic functionality that most chess pieces have in common,
 * such as their position on the board (row and column) and color.
 * Any specific chess piece like a Knight, Bishop, etc., will extend this class
 * and provide its own movement logic.
 */
public abstract class MovesAbstractClass implements ChessPiece {
  private int row;
  private int col;
  private Color color;

  /**
   * Sets up the piece at a specific position with a color.
   * Throws an error if you try to place the piece outside the bounds of the chessboard.
   *
   * @param row   The row where the piece will start (0-7)
   * @param col   The column where the piece will start (0-7)
   * @param color The color of the piece (white or black)
   */
  public MovesAbstractClass(int row, int col, Color color) {
    if (row < 0 || col < 0 || row >= 8 || col >= 8) {
      throw new IllegalArgumentException("Invalid position");
    }
    this.row = row;
    this.col = col;
    this.color = color;
  }

  /**
   * Gets the row where this piece is currently located.
   *
   * @return The row (0-7)
   */
  @Override
  public int getRow() {
    return row;
  }

  /**
   * Gets the row where this piece is currently located.
   *
   * @return The row (0-7)
   */
  @Override
  public int getColumn() {
    return col;
  }

  /**
   * Gets the color of this piece (either white or black).
   *
   * @return The color of this piece
   */
  @Override
  public Color getColor() {
    return color;
  }

  /**
   * Checks if a given position on the board is valid (i.e., within the 8x8 grid).
   * This is a quick helper to avoid pieces moving off the board.
   *
   * @param row The row to check
   * @param col The column to check
   * @return True if the position is valid, false otherwise
   */
  protected boolean checkForValidMovement(int row, int col) {
    return (row < 0) || (col < 0) || (row >= 8) || (col >= 8);
  }

  /**
   * Each specific chess piece (like Knight, Rook, etc.) will implement this method to
   * define how it moves. This method needs to be overridden by any subclass.
   *
   * @param row The destination row
   * @param col The destination column
   * @return True if the piece can move to this position, false otherwise
   */
  @Override
  public abstract boolean canMove(int row, int col);

  /**
   * Determines if this piece can capture another piece.
   * A piece can capture another if it can move to the other piece's position
   * and if they are of opposite colors.
   *
   * @param piece The other chess piece that may be captured
   * @return True if this piece can capture the other one, false otherwise
   */
  @Override
  public boolean canKill(ChessPiece piece) {
    return (this.getColor() != piece.getColor()) && canMove(
            piece.getRow(),
            piece.getColumn());
  }
}
