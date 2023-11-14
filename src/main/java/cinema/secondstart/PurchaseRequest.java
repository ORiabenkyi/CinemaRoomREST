package cinema.secondstart;

public class PurchaseRequest {
    int row;
    int column;

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "PurchaseRequest{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }
}
