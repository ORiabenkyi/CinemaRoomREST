package cinema.secondstart;

public class Statistics {
    private int income;
    private int available;
    private int purchased;

    public int getIncome() {
        return income;
    }

    public int getAvailable() {
        return available;
    }

    public int getPurchased() {
        return purchased;
    }

    public Statistics() {
        purchased = Main.cinemaManager.getPurchaseSeats().size();
        available = Main.cinemaManager.getCinema().getSeats().size();
        income = 0;
        Main.cinemaManager.getPurchaseSeats().forEach((key,value)->{
            income+= value.getTicket().getPrice();
        });
    }
}
