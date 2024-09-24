
public class Queen implements ChessPiece {

    private int row;
    private int col;
    private Color color;

    public Queen(int row, int col, Color color) throws IllegalArgumentException {
        if ((row < 0) || (col < 0)) {
            throw new IllegalArgumentException("Illegal position");
        }
        this.row = row;
        this.col = col;
        this.color = color;
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
    public int getRow() {
        return row;
    }

    @Override
    public int getColumn() {
        return col;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public boolean canKill(ChessPiece piece) {
        return (this.getColor() != piece.getColor()) && canMove(
                piece.getRow(),
                piece.getColumn());
    }
}
