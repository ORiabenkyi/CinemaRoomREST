package cinema.secondstart;

import java.util.ArrayList;

public class CinemaRoom {
    private int rows, columns;
    private ArrayList<Seats> seats;

    public CinemaRoom(int row, int column) {
        this.rows = row;
        this.columns = column;

        seats = new ArrayList<Seats>();
        for (int i = 1;i <=rows;i++)
            for (int j = 1;j<=columns;j++){
                Seats seat = new Seats(i,j);
                seats.add(seat);
            }
    }

    public ArrayList<Seats> getSeats() {
        return seats;
    }
    public int getRows() {
        return rows;
    }
    public int getColumns() {
        return columns;
    }


    public Seats findTSeat(int row,int column) {
        for (Seats seat:this.getSeats()) {
            if ((seat.getRow() == row)&&(seat.getColumn()==column)) {
                return seat;
            }
        }
        return null;
    }

}
