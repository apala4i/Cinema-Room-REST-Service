package cinema.cinemaRestController.cinemaInfo;

import cinema.Session;

import java.util.List;
import java.util.stream.Collectors;

public class SeatsCondition {
    private final int total_rows;
    private final int total_columns;
    private final List<PublicTicketInfo> available_seats;

    public SeatsCondition(int total_rows, int total_columns, List<PublicTicketInfo> available_seats) {
        this.available_seats = available_seats;
        this.total_rows = total_rows;
        this.total_columns = total_columns;
    }

    public SeatsCondition(Session session) {
        var hall = session.getHall();
        total_rows = session.getHall().getRows();
        total_columns = session.getHall().getColumns();

        available_seats = session.getAvailable_seats()
                .stream().map(x -> new PublicTicketInfo(x, hall.getGetPrice().apply(x)))
                .collect(Collectors.toList());
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public List<PublicTicketInfo> getAvailable_seats() {
        return available_seats;
    }
}
