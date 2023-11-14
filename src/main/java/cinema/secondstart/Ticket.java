package cinema.secondstart;

public class Ticket {
    private Seats ticket;

    public Ticket(Seats ticket) {
        this.ticket = ticket;
    }

    public Seats getTicket() {
        return ticket;
    }
}
