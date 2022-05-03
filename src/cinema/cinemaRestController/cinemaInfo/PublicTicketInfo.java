package cinema.cinemaRestController.cinemaInfo;

import cinema.Seat;
import cinema.Ticket;

public class PublicTicketInfo{
    private final int row;
    private final int column;
    private final int price;

    public PublicTicketInfo(int row, int column, int price) {
        this.row = row;
        this.column = column;
        this.price = price;
    }

    public PublicTicketInfo(Seat seat, int price) {
        this.row = seat.getRow();
        this.column = seat.getColumn();
        this.price = price;
    }

    public PublicTicketInfo(Ticket ticket) {
        this.row = ticket.getSeat().getRow();
        this.column = ticket.getSeat().getColumn();
        this.price = ticket.getPrice();
    }

    public int getPrice() {
        return price;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    @Override
    public String toString() {
        return "row: " + row + " column: " + column + " price: " + price;
    }
}
