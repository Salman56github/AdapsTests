package AWH_DSR;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateAndTime {
    public static void main(String[] args) {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd");
        if (date.format(format).contains("0")) {
            DateTimeFormatter format1 = DateTimeFormatter.ofPattern("d");
            System.out.println(date.format(format1));
        } else System.out.println("khan");

    }
}
