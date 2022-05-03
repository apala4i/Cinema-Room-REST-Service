package cinema.cinemaRestController.cinemaInfo;

import cinema.Ticket;

import java.util.UUID;

public class PrivateTicketInfo {
    private final UUID token;
    private final PublicTicketInfo ticket;

    public PrivateTicketInfo(UUID token, PublicTicketInfo ticket) {
        this.token = token;
        this.ticket = ticket;
    }

    public PrivateTicketInfo(Ticket ticket) {
        this.token = ticket.getUuid();
        this.ticket = new PublicTicketInfo(ticket);
    }

    public PublicTicketInfo getTicket() {
        return ticket;
    }

    public UUID getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "UUID: " + token + " " + ticket;
    }

}
