package cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CinemaConfiguration {
    private static final int TASK_ROWS = 9;
    private static final int TASK_COLUMNS = 9;

    @Autowired
    Hall TaskHall;

    @Bean
    Cinema getCinema() {
        var cinema = new Cinema();
        cinema.addHall(TaskHall);

        return cinema;
    }

    @Bean
    Hall getTaskHall() {
        return new Hall(TASK_ROWS, TASK_COLUMNS, x -> {
            if (x.getRow() <= 4) {
                return 10;
            } else {
                return 8;
            }
        });
    }
}
