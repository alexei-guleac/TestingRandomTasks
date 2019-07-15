import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocaleDateTime {
    public static void main(String[] args) {

        //текущая дата.время
        LocalDate.now();
        LocalTime.now();
        LocalDateTime.now();

        //задать дату время
        LocalDate.of(2020, Month.SEPTEMBER, 23); // 2020-09-23
        LocalTime.of(12, 10);

        //добавление дней.недель.месяцев
        LocalDate now = LocalDate.now();
        LocalDate plus2Days = now.plusDays(2);
        LocalDate plusWeek = now.plusWeeks(1);
        LocalDate plus3Months = now.plusMonths(3);

        now = LocalDate.now();
        String nativ = now.format(DateTimeFormatter.ofPattern("dd MMM yyyy")); // 28 Jan 2018
        String french = now.format(DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.FRANCE)); // 28 janv. 2018

    }

}
