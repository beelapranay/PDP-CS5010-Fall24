import org.junit.Before;
import org.junit.Test;


import Bishop;
import ChessPiece;
import Color;
import Queen;
import Rook;
import Knight;
import Pawn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by ashesh on 1/27/2017.
 */
public class ChessPieceGraderTest {
  private boolean[][] results;

  @Before
  public void setup() {
    results = new boolean[8][8];

  }

  private void verifyMoveResults(ChessPiece piece) {
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if ((i == piece.getRow()) && (j == piece.getColumn())) {
          continue;
        }

        assertEquals("Piece at :" + piece.getRow() + "," + piece.getColumn() +
                     ", Unexpected canMove result "
                     + "for "
                     + "i=" + i + " j=" +
                     j + "",
                results[i][j], piece.canMove(i, j));

      }
    }
  }

  private void verifyKillResults(ChessPiece piece) {

    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {

        if ((i == piece.getRow()) && (j == piece.getColumn()))
          continue;
        ChessPiece another = new Knight(i, j,
                Color.values()[(piece.getColor().ordinal() + 1)
                               % Color.values().length]);


        assertEquals("Unexpected canKill result for "
                     + "i=" + i + " j=" +
                     j + "",
                results[i][j], piece.canKill(another));

      }
    }
  }


  @Test(timeout = 500)
  public void testGetters() {
    ChessPiece[] pieces = new ChessPiece[5];


    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        for (Color c : Color.values()) {
          pieces[0] = new Pawn(row, col, c);
          pieces[1] = new Rook(row, col, c);
          pieces[2] = new Queen(row, col, c);
          pieces[3] = new Knight(row, col, c);
          pieces[4] = new Bishop(row, col, c);

          for (int i = 0; i < pieces.length; i++) {
            assertEquals("Row number does not match what was initialized", row,
                    pieces[i].getRow());
            assertEquals("Column number does not match what was initialized",
                    col, pieces[i].getColumn());
            assertEquals(" Color does not match what was initialized",
                    c, pieces[i].getColor());
          }
        }
      }
    }


  }

  @Test(timeout = 500)
  public void testInvalidConstructions() {
    ChessPiece piece;

    for (Color c : Color.values()) {
      for (int i = 0; i < 8; i++) {
        try {
          piece = new Knight(i, -1, c);
          fail("Did not throw an exception when knight is created with invalid "
               +
               "row");
        } catch (IllegalArgumentException e) {
          //passes
        }

        try {
          piece = new Knight(-1, i, c);
          fail("Did not throw an exception when knight is created with invalid "
               +
               "column");
        } catch (IllegalArgumentException e) {
          //passes
        }

        try {
          piece = new Pawn(i, -1, c);
          fail("Did not throw an exception when pawn is created with invalid " +
               "row");
        } catch (IllegalArgumentException e) {
          //passes
        }

        try {
          piece = new Pawn(-1, i, c);
          fail("Did not throw an exception when pawn is created with invalid " +
               "column");
        } catch (IllegalArgumentException e) {
          //passes
        }

        try {
          piece = new Queen(i, -1, c);
          fail("Did not throw an exception when queen is created with invalid "
               +
               "row");
        } catch (IllegalArgumentException e) {
          //passes
        }

        try {
          piece = new Queen(-1, i, c);
          fail("Did not throw an exception when queen is created with invalid "
               +
               "column");
        } catch (IllegalArgumentException e) {
          //passes
        }

        try {
          piece = new Rook(i, -1, c);
          fail("Did not throw an exception when rook is created with invalid " +
               "row");
        } catch (IllegalArgumentException e) {
          //passes
        }

        try {
          piece = new Rook(-1, i, c);
          fail("Did not throw an exception when rook is created with invalid " +
               "column");
        } catch (IllegalArgumentException e) {
          //passes
        }

        try {
          piece = new Bishop(i, -1, c);
          fail("Did not throw an exception when rook is created with invalid " +
               "row");
        } catch (IllegalArgumentException e) {
          //passes
        }

        try {
          piece = new Bishop(-1, i, c);
          fail("Did not throw an exception when rook is created with invalid " +
               "column");
        } catch (IllegalArgumentException e) {
          //passes
        }

      }
    }
  }

  @Test(timeout = 500)
  public void testKnightMoves() {


    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        initializeResults();
        ChessPiece piece = new Knight(row, col, Color.BLACK);

        setupResultsForKnight(row, col);
        verifyMoveResults(piece);
      }
    }


  }

  @Test(timeout = 500)
  public void testKnightKills() {

    for (Color c : Color.values()) {
      for (int row = 0; row < 8; row++) {
        for (int col = 0; col < 8; col++) {
          initializeResults();
          ChessPiece piece = new Knight(row, col, c);

          setupResultsForKnight(row, col);
          verifyKillResults(piece);
        }
      }
    }

  }


  @Test(timeout = 500)
  public void testQueenMoves() {
    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        initializeResults();
        ChessPiece piece = new Queen(row, col, Color.BLACK);

        setupResultsForQueen(row, col);
        verifyMoveResults(piece);
      }
    }
  }

  @Test(timeout = 500)
  public void testQueenKills() {

    for (Color c : Color.values()) {
      for (int row = 0; row < 8; row++) {
        for (int col = 0; col < 8; col++) {
          initializeResults();
          ChessPiece piece = new Queen(row, col, c);

          setupResultsForQueen(row, col);
          verifyKillResults(piece);
        }
      }
    }
  }

  @Test(timeout = 500)
  public void testBishopMoves() {
    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        initializeResults();
        ChessPiece piece = new Bishop(row, col, Color.BLACK);

        setupResultsForBishop(row, col);
        verifyMoveResults(piece);
      }
    }
  }

  @Test(timeout = 500)
  public void testBishopKills() {

    for (Color c : Color.values()) {
      for (int row = 0; row < 8; row++) {
        for (int col = 0; col < 8; col++) {
          initializeResults();
          ChessPiece piece = new Bishop(row, col, c);

          setupResultsForBishop(row, col);
          verifyKillResults(piece);
        }
      }
    }
  }

  @Test(timeout = 500)
  public void testRookMoves() {
    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        initializeResults();
        ChessPiece piece = new Rook(row, col, Color.BLACK);

        setupResultsForRook(row, col);
        verifyMoveResults(piece);
      }
    }
  }

  @Test(timeout = 500)
  public void testRookKills() {

    for (Color c : Color.values()) {
      for (int row = 0; row < 8; row++) {
        for (int col = 0; col < 8; col++) {
          initializeResults();
          ChessPiece piece = new Rook(row, col, c);

          setupResultsForRook(row, col);
          verifyKillResults(piece);
        }
      }
    }
  }

  @Test(timeout = 500)
  public void testPawnMoves() {
    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        initializeResults();
        ChessPiece piece = new Pawn(row, col, Color.BLACK);

        setupResultsForPawn(row, col, piece.getColor());
        verifyMoveResults(piece);
      }
    }
  }

  @Test(timeout = 500)
  public void testPawnKills() {

    for (Color c : Color.values()) {
      for (int row = 0; row < 8; row++) {
        for (int col = 0; col < 8; col++) {
          initializeResults();
          ChessPiece piece = new Pawn(row, col, c);

          setupKillResultsForPawn(row, col, piece.getColor());
          verifyKillResults(piece);
        }
      }
    }
  }


  private void initializeResults() {
    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        results[row][col] = false;
      }
    }
  }

  private void setupResultsForKnight(int row, int col) {
    initializeResults();
    for (int k = 0; k <= 1; k++) {
      int one = k + 1;
      int other = 1 + (k + 1) % 2;
      if ((row + one) < 8) {
        if ((col + other) < 8) {
          results[row + one][col + other] = true;
        }
        if (col >= other) {
          results[row + one][col - other] = true;
        }
      }

      if (row >= one) {
        if ((col + other) < 8) {
          results[row - one][col + other] = true;
        }
        if (col >= other) {
          results[row - one][col - other] = true;
        }
      }
    }
  }

  private void setupResultsForQueen(int row, int col) {
    //check if canMove works
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

  private void setupResultsForBishop(int row, int col) {
    //check if canMove works
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

  private void setupResultsForRook(int row, int col) {
    //check if canMove works
    for (int i = 0; i < 8; i++) {
      results[i][col] = true;
      results[row][i] = true;

    }
  }

  private void setupResultsForPawn(int row, int col, Color color) {
    //check if canMove works
    if (color == Color.BLACK) {
      if (row >= 1) {
        results[row - 1][col] = true;
      }
    } else {
      if (row < 7) {
        results[row + 1][col] = true;
      }
    }
  }

  private void setupKillResultsForPawn(int row, int col, Color color) {
    if (color == Color.BLACK) {
      if (row >= 1) {
        if (col >= 1) {
          results[row - 1][col - 1] = true;
        }
        if (col < 7) {
          results[row - 1][col + 1] = true;
        }
      }
    } else {
      if (row < 7) {
        if (col >= 1) {
          results[row + 1][col - 1] = true;
        }
        if (col < 7) {
          results[row + 1][col + 1] = true;
        }
      }
    }

  }

}