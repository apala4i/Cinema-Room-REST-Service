package cinema;

public class Seat {
    private int row;
    private int column;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Seat() {}

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public boolean equals(Object seat) {
        if (seat == this) {
            return true;
        }

        if (!(seat instanceof Seat)) {
            return false;
        }

        return row == ((Seat) seat).row && column == ((Seat) seat).getColumn();
    }
}
