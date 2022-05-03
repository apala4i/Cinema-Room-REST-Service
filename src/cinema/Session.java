package cinema;

import cinema.restExceptions.SeatIsBoughtException;
import cinema.restExceptions.WrongSeatException;
import cinema.restExceptions.WrongTokenException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Session {
    private final List<Seat> available_seats = new ArrayList<>();
    private final List<Ticket> bought_tickets = new ArrayList<>();
    private final Hall hall;
    private int income = 0;

    public Session(Hall hall) {
        this.hall = hall;
        for (int i = 0; i < hall.getRows(); ++i) {
            for (int j = 0; j < hall.getColumns(); ++j) {
                available_seats.add(new Seat(i + 1, j + 1));
            }
        }
    }

    public Hall getHall() {
        return hall;
    }

    public List<Seat> getAvailable_seats() {
        return available_seats;
    }

    public Ticket buyTicket(Seat seat) {
        if (!checkSeat(seat)) {
            throw new WrongSeatException();
        }

        if (!SeatIsAvailable(seat)) {
            throw  new SeatIsBoughtException();
        }

        available_seats.remove(seat);
        int price = hall.getGetPrice().apply(seat);
        income += price;

        var boughtTicket = new Ticket(seat, price);

        bought_tickets.add(boughtTicket);

        return boughtTicket;

    }

    public Ticket returnTicket(UUID uuid) {
        Optional<Ticket> returnedTicket = bought_tickets.stream()
                .filter(x -> x.getUuid().equals(uuid))
                .findAny();

        if (returnedTicket.isPresent()) {
            income -= returnedTicket.get().getPrice();
            bought_tickets.remove(returnedTicket.get());
            available_seats.add(returnedTicket.get().getSeat());
            return returnedTicket.get();
        } else {
            throw new WrongTokenException();
        }
    }

    public SessionStatistic getStatistic() {
        return  new SessionStatistic(income, available_seats.size(), bought_tickets.size());
    }

    private boolean SeatIsAvailable(Seat seat) {
        return available_seats.contains(seat);
    }

    public boolean checkSeat(Seat seat) {
        int row = seat.getRow();
        int column = seat.getColumn();
        return row <= hall.getRows() && row > 0
                && column <= hall.getColumns() && column > 0;
    }

}
