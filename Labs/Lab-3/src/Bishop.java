
public class Bishop implements ChessPiece {
    int row;
    public int col;
    Color color;

    public Bishop(int row, int col, Color color) throws IllegalArgumentException {
        if ((row < 0) || (col < 0)) {
            throw new IllegalArgumentException("Illegal position");
        }
        this.row = row;
        this.col = col;
        this.color = color;
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
}
