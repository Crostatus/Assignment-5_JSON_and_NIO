import com.github.cliftonlabs.json_simple.JsonObject;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class MyDate {

    public static JsonObject getRandomDate(){
        long minDay = LocalDate.of(2017, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2019, 10, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        JsonObject date = new JsonObject();
        date.put("day", randomDate.getDayOfMonth());
        date.put("month", randomDate.getMonthValue());
        date.put("year", randomDate.getYear());
        return date;
    }


}
