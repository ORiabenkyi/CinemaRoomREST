package cinema.secondstart;


import java.util.UUID;

public class SoldSeat {
    private UUID token;
    private Seats ticket;
    public SoldSeat(int row, int column) {
        this.token = UUID.randomUUID();
        if (this.ticket!=null) {
//            CinemaRoomManager.getSeats().remove(this.seat);
        }
        else {
            //throw new PurchaseNotFoundException("The ticket has been already purchased!");
        }
    }

    public SoldSeat(Seats seat) {
        this.token = UUID.randomUUID();
        this.ticket = seat;
    }

    public String getToken() {
        return token.toString();
    }
    public Seats getTicket() {
        return ticket;
    }


}