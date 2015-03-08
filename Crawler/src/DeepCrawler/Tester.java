package DeepCrawler;

import org.joda.time.LocalDate;
import org.joda.time.Days;
import org.joda.time.DurationFieldType;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: Mahmood Neshati
 * Date: Mar 5, 2015
 * Time: 8:49:05 AM
 */
public class Tester {
    public static void main(String[] args) {
        List<LocalDate> dates = new ArrayList<LocalDate>();
        LocalDate startDate = new LocalDate(2010, 3, 11);
        LocalDate endDate = new LocalDate(2015, 3, 14);
        int days = Days.daysBetween(startDate, endDate).getDays();
        for (int i = 0; i < days; i++) {
            LocalDate d = startDate.withFieldAdded(DurationFieldType.days(), i);
            dates.add(d);
        }
        for (LocalDate localDate : dates) {
            System.out.println(localDate);
            int year = localDate.getYear();
            int month = localDate.getMonthOfYear();
            int day = localDate.getDayOfMonth();
        }

    }
}
