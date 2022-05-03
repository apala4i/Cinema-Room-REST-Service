package cinema;

import java.util.function.Function;

public class Hall {
    private int rows;
    private int columns;
    private final Function<Seat, Integer> getPrice;

    public Hall(int rows, int columns, Function<Seat, Integer> getPrice) {
        this.rows = rows;
        this.columns = columns;
        this.getPrice = getPrice;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Function<Seat, Integer> getGetPrice() {
        return getPrice;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Hall)) {
            return false;
        }

        Hall hall = (Hall)obj;

        return rows == hall.rows &&
                columns == hall.columns &&
                getPrice.equals(hall.getPrice);
    }
}
