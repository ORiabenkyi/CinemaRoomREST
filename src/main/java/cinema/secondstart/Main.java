package cinema.secondstart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import java.util.ArrayList;

@SpringBootApplication
public class Main {

    public static CinemaRoomManager cinemaManager;
    public static void main(String[] args) {
        cinemaManager = new CinemaRoomManager();
        SpringApplication.run(Main.class, args);
    }
}

