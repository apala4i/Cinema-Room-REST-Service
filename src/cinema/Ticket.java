package cinema;

import java.util.UUID;

public class Ticket {
    private final int price;
    private final Seat seat;
    private final UUID uuid;

    public Ticket(Seat seat, int price) {
        this.price = price;
        this.seat = seat;
        this.uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    public int getPrice() {
        return price;
    }

    public boolean ticketIsOnSeat(Seat seat) {
        return this.seat.equals(seat);
    }

    public Seat getSeat() {
        return seat;
    }
}
