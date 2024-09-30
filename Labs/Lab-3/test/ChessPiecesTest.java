import org.junit.Before;
import org.junit.Test;

import solution.ChessPiece;
import solution.Color;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * An abstract test class for chess pieces. This class contains the common testing functionality
 * for any chess piece, such as verifying movement and killing abilities. Each specific chess piece
 * (like Rook, Bishop, etc.) will extend this class and provide their own implementations for
 * creating and testing specific behaviors.
 */
public abstract class ChessPiecesTest {

  protected int row;
  protected int col;
  protected boolean[][] results;

  /**
   * Initializes the `results` array to be an 8x8 grid, representing the chessboard.
   * This method runs before each test, ensuring the array is reset.
   */
  @Before
  public void setup() {
    results = new boolean[8][8];
  }

  /**
   * Abstract method for creating a specific chess piece. Each subclass must implement this
   * to return an instance of the specific chess piece (like Bishop, Rook, etc.) being tested.
   *
   * @param row   The starting row of the chess piece (0-7)
   * @param col   The starting column of the chess piece (0-7)
   * @param color The color of the chess piece (either BLACK or WHITE)
   * @return The chess piece to be tested
   */
  protected abstract ChessPiece chessPiece(int row, int col, Color color);

  /**
   * Abstract method for setting up the expected valid moves for a given chess piece.
   * Each subclass will implement this to define the valid moves for the specific chess piece.
   *
   * @param row The starting row of the chess piece
   * @param col The starting column of the chess piece
   */
  protected abstract void setupResults(int row, int col);

  /**
   * Verifies that the piece's movement is as expected by comparing the actual movement
   * results with the `results` array, which contains the expected valid moves.
   *
   * @param piece The chess piece being tested
   */
  protected void verifyMoveResults(ChessPiece piece) {
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if ((i == piece.getRow()) && (j == piece.getColumn())) {
          continue;
        }

        System.out.println("Expected result for (" + i + "," + j + "): " + results[i][j]);
        System.out.println("Actual result for (" + i + "," + j + "): " + piece.canMove(i, j));

        assertEquals("Piece at :" + piece.getRow() + "," + piece.getColumn()
                        + ", Unexpected canMove result "
                        + "for "
                        + "i=" + i + " j="
                        + j,
                results[i][j], piece.canMove(i, j));

      }
    }
  }

  /**
   * Verifies that the piece's ability to kill another piece is as expected.
   * It checks whether the piece can kill an opponent at every possible position on the board.
   *
   * @param piece The chess piece being tested
   */
  protected void verifyKillResults(ChessPiece piece) {

    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {

        if ((i == piece.getRow()) && (j == piece.getColumn())) {
          continue;
        }
        ChessPiece another = chessPiece(i, j,
                Color.values()[(piece.getColor().ordinal() + 1)
                        % Color.values().length]);


        assertEquals("Unexpected canKill result for "
                        + "i=" + i + " j="
                        + j,
                results[i][j], piece.canKill(another));

      }
    }
  }

  /**
   * Initializes the `results` array to all `false` before setting up
   * expected valid moves or kills.
   */
  protected void initializeResults() {
    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        results[row][col] = false;
      }
    }
  }

  /**
   * Tests the getters for the chess piece's row, column, and color to ensure
   * that they match the values with which the piece was initialized.
   */
  @Test(timeout = 500)
  public void testGetters() {
    ChessPiece piece;

    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        for (Color c : Color.values()) {
          piece = chessPiece(row, col, c);
          assertEquals("Row number does not match what was initialized", row,
                  piece.getRow());
          assertEquals("Column number does not match what was initialized",
                  col, piece.getColumn());
          assertEquals("solution.Color does not match what was initialized",
                  c, piece.getColor());

        }
      }
    }
  }

  /**
   * Tests that invalid row or column values throw an IllegalArgumentException
   * when trying to create a chess piece.
   */
  @Test(timeout = 500)
  public void testInvalidConstructions() {
    ChessPiece piece;

    for (Color c : Color.values()) {
      for (int i = 0; i < 8; i++) {


        try {
          piece = chessPiece(i, -1, c);
          fail("Did not throw an exception when chess piece is created with invalid "
                  + "row");
        } catch (IllegalArgumentException e) {
          //passes
        }

        try {
          piece = chessPiece(-1, i, c);
          fail("Did not throw an exception when chess piece is created with invalid "
                  + "column");
        } catch (IllegalArgumentException e) {
          //passes
        }

      }
    }
  }

  /**
   * Tests the movement capabilities of the chess piece for all valid
   * positions on the chessboard.
   */
  @Test(timeout = 500)
  public void testChessPieceMoves() {
    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        initializeResults();
        ChessPiece piece = chessPiece(row, col, Color.BLACK);

        setupResults(row, col);
        verifyMoveResults(piece);
      }
    }
  }

  /**
   * Tests the killing capabilities of the chess piece by checking if it can kill
   * pieces of the opposite color at all positions on the chessboard.
   */
  @Test(timeout = 500)
  public void testChessPieceKills() {

    for (Color c : Color.values()) {
      for (int row = 0; row < 8; row++) {
        for (int col = 0; col < 8; col++) {
          initializeResults();
          ChessPiece piece = chessPiece(row, col, c);

          setupResults(row, col);
          verifyKillResults(piece);
        }
      }
    }
  }

}
