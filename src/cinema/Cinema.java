package cinema;

import java.util.HashMap;
import java.util.Map;

public class Cinema {

    private Map<Hall, Session> hallSessionMap = new HashMap<>();

    public Cinema(Map<Hall, Session> halls) {
        this.hallSessionMap = halls;
    }

    public Cinema() {

    }

    public void addHall(Hall hall) {
        hallSessionMap.put(hall, new Session(hall));
    }

    public Session getSessionByHall(Hall hall) {
        return hallSessionMap.get(hall);
    }

    public Map<Hall, Session> getHallSessionMap() {
        return hallSessionMap;
    }
}
