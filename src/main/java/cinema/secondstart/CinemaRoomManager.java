package cinema.secondstart;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CinemaRoomManager {
    public static final int ROWS = 9;
    public static final int COLUMNS = 9;
    private ArrayList<Seats> allSeats;
    private Map<String, SoldSeat> purchaseSeats;
    private CinemaRoom cinema;
    public CinemaRoomManager() {
        cinema = new CinemaRoom(ROWS,COLUMNS);
        allSeats = new ArrayList<Seats>();
        purchaseSeats = new HashMap<>();
        for (Seats place: cinema.getSeats()) {
            allSeats.add(place);
        }
    }

    public ArrayList<Seats> getAllSeats() {
        return allSeats;
    }

    public CinemaRoom getCinema() {
         return cinema;
    }

    public Map<String, SoldSeat> getPurchaseSeats() {
        return purchaseSeats;
    }

    public SoldSeat purchase(int row,int column){
        SoldSeat soldSeat = null;
        if ((row<1||row>ROWS) || ((column<1||column>COLUMNS))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "The number of a row or a column is out of bounds!");
        }

        Seats seat = getSeat(row,column);
        if (cinema.getSeats().contains(seat)){
            //seat available
            cinema.getSeats().remove(seat);
            soldSeat = new SoldSeat(seat);
            getPurchaseSeats().put(soldSeat.getToken(),soldSeat);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "The ticket has been already purchased!");
        }
        return soldSeat;
    }

    public SoldSeat purchase(Seats seat){
        SoldSeat soldSeat = new SoldSeat(seat);

        return soldSeat;
    }
    public Ticket returnTicket(String token) {
        Ticket result = null;
        if (purchaseSeats.containsKey(token)) {
            SoldSeat returned = purchaseSeats.get(token);
            result = new Ticket(returned.getTicket());
        } else {throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Wrong token!");}
        return result;
    }
    public Seats getSeat(int row,int column) {
        for (Seats place:getAllSeats()) {
            if ((place.getRow() ==row)&&(place.getColumn()==column)){
                return place;
            }
        }
        return null;
    }
}
