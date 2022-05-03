package cinema.cinemaRestController.cinemaInfo;

public class ReturnedTicket {
    PublicTicketInfo returned_ticket;

    public ReturnedTicket(PublicTicketInfo returned_ticket) {
        this.returned_ticket = returned_ticket;
    }

    public PublicTicketInfo getReturned_ticket() {
        return returned_ticket;
    }

    public void setReturned_ticket(PublicTicketInfo returned_ticket) {
        this.returned_ticket = returned_ticket;
    }
}
