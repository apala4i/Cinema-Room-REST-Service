package cinema.cinemaRestController;


import cinema.*;
import cinema.cinemaRestController.cinemaInfo.*;
import cinema.restExceptions.SeatIsBoughtException;
import cinema.restExceptions.WrongPasswordException;
import cinema.restExceptions.WrongSeatException;
import cinema.restExceptions.WrongTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
public class CinemaRestController {
    @Autowired
    Cinema cinema;

    String pass = "super_secret";

    @Autowired
    Hall TaskHall;

    @GetMapping("/seats")
    public SeatsCondition getSeatsCondition() {
        return new SeatsCondition(cinema.getSessionByHall(TaskHall));
    }

    @PostMapping("/purchase")
    public PrivateTicketInfo purchaseTicket(@RequestBody Seat seat) {
        var taskSession = cinema.getSessionByHall(TaskHall);
        var boughtTicket = taskSession.buyTicket(seat);
        return new PrivateTicketInfo(boughtTicket);
    }

    @PostMapping("/return")
    public ReturnedTicket returnTicket(@RequestBody Token token) {
        var TaskSession = cinema.getSessionByHall(TaskHall);
        return new ReturnedTicket(new PublicTicketInfo(TaskSession.returnTicket(UUID.fromString(token.getToken()))));
    }

    @PostMapping("/stats")
    public SessionStatistic getStats(@RequestParam Optional<String> password) {
        if (password.isPresent() && password.get().equals(pass)) {
            var TaskSession = cinema.getSessionByHall(TaskHall);
            return TaskSession.getStatistic();
        } else {
            throw new WrongPasswordException();
        }
    }


    // Exception Handlers
    @ExceptionHandler(WrongPasswordException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public Map<String, String> HandleWrongPasswordException() {
        var resError = new HashMap<String, String>();
        resError.put("error", "The password is wrong!");
        return resError;
    }

    @ExceptionHandler(SeatIsBoughtException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Map<String, String> HandleWrongSeatException() {
        var resError = new HashMap<String, String>();
        resError.put("error", "The ticket has been already purchased!");
        return resError;
    }

    @ExceptionHandler(WrongSeatException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Map<String, String> handleWrongSeatException() {
        var res = new HashMap<String, String>();
        res.put("error", "The number of a row or a column is out of bounds!");
        return res;
    }

    @ExceptionHandler(WrongTokenException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Map<String, String> handleWrongTokenException() {
        var resMap = new HashMap<String, String>();
        resMap.put("error", "Wrong token!");
        return resMap;
    }

}

