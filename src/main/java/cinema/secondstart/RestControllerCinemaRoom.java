package cinema.secondstart;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
class RestControllerCinemaRoom {

    @GetMapping("/seats")
    public CinemaRoom returnSeatsInfo() {
        return Main.cinemaManager.getCinema();
    }

    @GetMapping("/stats")
    public ResponseEntity returnStatsInfo(@RequestParam(required = false) String password) {

        if (password==null)
            return ResponseEntity.status(HttpStatusCode.valueOf(401)).body(
                    new ConcurrentHashMap<>(Map.of("error", "The password is wrong!")));
        if (password.equals("super_secret"))
            return ResponseEntity.ok(new Statistics());
        return ResponseEntity.status(HttpStatusCode.valueOf(401)).body(
                new ConcurrentHashMap<>(Map.of("error", "The password is wrong!")));

    }

    @PostMapping("/purchase")
    public ResponseEntity purchaseTicke(@RequestBody PurchaseRequest seat) {
        if (seat.getRow() < 1 || seat.getRow() > CinemaRoomManager.ROWS ||
                seat.getColumn() < 1 || seat.getColumn() > CinemaRoomManager.COLUMNS) {
            return ResponseEntity.badRequest().body(
                    new ConcurrentHashMap<>(Map.of("error", "The number of a row or a column is out of bounds!")));
        } else if (!(Main.cinemaManager.getCinema().getSeats().contains(Main.cinemaManager.getSeat(seat.getRow(),seat.getColumn())))) {
            return ResponseEntity.badRequest().body(
                    new ConcurrentHashMap<>(Map.of("error", "The ticket has been already purchased!"))
            );
        }
        Seats place = Main.cinemaManager.getSeat(seat.getRow(),seat.getColumn());
        Main.cinemaManager.getCinema().getSeats().remove(place);
        SoldSeat soldSeat = new SoldSeat(place);
        Main.cinemaManager.getPurchaseSeats().put(soldSeat.getToken(),soldSeat);
        return ResponseEntity.ok(soldSeat);
    }

    @PostMapping("/return")
    public ResponseEntity postReturn(@RequestBody ReturnRequest token) {
        if (token.getToken()==null)
            return ResponseEntity.badRequest().body(
                    new ConcurrentHashMap<>(Map.of("error", "Wrong token!")));
        if (Main.cinemaManager.getPurchaseSeats().containsKey(token.getToken())) {
            SoldSeat returned = Main.cinemaManager.getPurchaseSeats().get(token.getToken());
            Main.cinemaManager.getPurchaseSeats().remove(token.getToken());
            Main.cinemaManager.getCinema().getSeats().add(returned.getTicket());
            return ResponseEntity.ok(new Ticket(returned.getTicket()));
        } else
            return ResponseEntity.badRequest().body(new ConcurrentHashMap<>(Map.of("error", "Wrong token!")));

    }

}

