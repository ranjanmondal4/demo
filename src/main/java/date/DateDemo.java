package date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.util.Locale;

public class DateDemo {
    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("Date " + dateTime);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = dateTime.format(formatter);
        System.out.println("Date " + formattedDateTime);

        try {
            int result = 10 / 0;
        }catch (Exception e){
            StringBuilder builder = new StringBuilder();
            builder.append(e);
            for(StackTraceElement element : e.getStackTrace()){
                builder.append("\n").append(element);
            }
            System.out.println(builder.toString());
        }
    }
}
