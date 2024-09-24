package solution;
import java.awt.*;

public class Piece implements ChessPiece {
  private int row;
  private int col;
  private Color color;

  public Piece(int row, int col, Color color) throws IllegalArgumentException {
    if(row < 0 || col < 0) {
      throw new IllegalArgumentException("row and col are negative");
    } else {
      this.row = row;
      this.col = col;
      this.color = color;
    }
  }

  @Override
  public boolean canMove(int row, int col) {
    if ((row < 0) || (col < 0) || (row >= 8) || (col >= 8)) {
      return false;
    }
    return ((this.row == row) || (this.col == col)
            || (Math.abs(this.row - row) == Math.abs(this.col - col)));
  }

  @Override
  public boolean canKill(ChessPiece piece) {
    return false;
  }

  @Override
  public int getRow() {
    return this.row;
  }

  @Override
  public int getColumn() {
    return this.col;
  }

  @Override
  public Color getColor() {
    return this.color;
  }
}
