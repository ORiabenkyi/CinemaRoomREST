package cinema.secondstart;

public class Seats {
    private int row, column;
    private int price;
    public Seats(int row, int column) {
        this.row = row;
        this.column = column;
        this.price = 8;
        if (row<=4) this.price +=2;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getPrice() {
        return price;
    }
}