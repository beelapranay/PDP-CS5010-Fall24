

public interface ChessPiece {
    /**
     * Can this chess piece be moved from its current location to the location
     * (row,col).
     *
     * @param row the row to which this chess piece can be moved
     * @param col the col to which this chess piece can be moved
     * @return true if it can be moved to this position, false otherwise
     */
    boolean canMove(int row, int col);

    /**
     * Can this chess piece kill the chess piece passed to this method?.
     *
     * @param piece the piece that may or may not be killed by this piece
     * @return true if this piece can kill the other, false otherwise
     */
    boolean canKill(ChessPiece piece);

    /**
     * Get the row of the current position of this piece. Rows begin with 0.
     *
     * @return the row of the current position of this piece
     */
    int getRow();

    /**
     * Get the column of the current position of this piece Columns begin with 0.
     *
     * @return the column of the current position of this piece
     */
    int getColumn();

    /**
     * Get the color of this piece. The color can be one of WHITE or BLACK.
     *
     * @return the color of this chess piece
     */
    Color getColor();
}
