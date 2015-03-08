package crawler;

import common.News;
import db.DBHelper;
import org.joda.time.Days;
import org.joda.time.DurationFieldType;
import org.joda.time.LocalDate;
import parser.FarsParser;
import util.CalenderConvetor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Mahmood Neshati
 * Date: Feb 5, 2015
 * Time: 4:23:58 PM
 */
public class FarsCrawler extends Crawler {


    private long lastSavedNewsBydate(String date) {
        String query = "SELECT max( cast([newsNumber] as bigint)) as maxNewsNumber FROM [core]\n" +
                "where source = 'farsnews' and newsNumber like '" + date + "%'";
        String set = DBHelper.selectSingleCommand(query);
        if (set != null) {
            return Long.parseLong(set);
        }
        return -1;

    }

    ArrayList<String> urls = new ArrayList<String>();
    private static final String SOURCE = "farsnews";

    public void crawlBydate(String date) {
        int countNull = 0;
        long maxsaved = lastSavedNewsBydate(date);
        for (int i = 1; i < 999999; i++) {
            News news = null;
            String url = null;
            try {
                url = createURL(date, i);
                if (Long.parseLong(date + String.format("%06d", i)) > maxsaved) {
                    String farsPageContent = fetchPage(url);
                    FarsParser parser = new FarsParser(farsPageContent);
                    news = createNews(parser, url);
                } else {
                    System.out.println("Skip crawling " + url);
                    continue;
                }
            }
            catch (Exception e) {
                System.err.println(e.getMessage());
            }

            if (news == null) {
                countNull++;
            } else {
                countNull = 0;
                news.save();
                System.out.println("News saved= " + url);
            }
            if (countNull > 20) {
                System.err.println("end of crawl on date " + date);
                break;
            }
        }
    }

    public void crawlByDateRange(String date1, String date2) {
        ArrayList<String> dateEnum = createDateEnum(date1, date2);
        for (String date : dateEnum) {
            crawlBydate(date);
        }
    }

    private ArrayList<String> createDateEnum(String date1, String date2) {
        ArrayList<String> out = new ArrayList<String>();


        String startDate = CalenderConvetor.toMilady(date1);
        String endDate = CalenderConvetor.toMilady(date2);


        LocalDate startlocal = new LocalDate(Integer.parseInt(startDate.substring(0, 4)),
                Integer.parseInt(startDate.substring(4, 6)), Integer.parseInt(startDate.substring(6, 8)));
        LocalDate endlocal = new LocalDate(Integer.parseInt(endDate.substring(0, 4)),
                Integer.parseInt(endDate.substring(4, 6)), Integer.parseInt(endDate.substring(6, 8)));
        int days = Days.daysBetween(startlocal, endlocal).getDays();
        List<LocalDate> dates = new ArrayList<LocalDate>();
        for (int i = 0; i < days; i++) {
            LocalDate d = startlocal.withFieldAdded(DurationFieldType.days(), i);
            dates.add(d);
        }
        for (LocalDate lDate : dates) {
            int year = lDate.getYear();
            int month = lDate.getMonthOfYear();
            int day = lDate.getDayOfMonth();
            String miladi = year + String.format("%02d", month) + String.format("%02d", day);
            out.add(CalenderConvetor.toshamsi(miladi));
        }
        return out;
    }

    public void crawlByStartNumber(String date, int startNumber) {
        int countNull = 0;
        for (int i = startNumber; i < 999999; i++) {
            String url = createURL(date, i);
            String farsPageContent = fetchPage(url);
            //System.out.println("url fetched= " + url);
            FarsParser parser = new FarsParser(farsPageContent);
            News news = createNews(parser, url);
            if (news == null) {
                countNull++;
            } else {
                countNull = 0;
                news.save();
                System.out.println("News saved= " + url);
            }
            if (countNull > 10) break;
        }
    }

    private String createURL(String date, int i) {
        return "http://www.farsnews.com/newstext.php?nn=" + date + String.format("%06d", i);
    }

    private News createNews(FarsParser parser, String url) {
        String newsSerial = parser.getNewsSerial();
        if (newsSerial == null)
            return null;
        return new News(parser.getTitle(), parser.getBody(), parser.getRelatedNews(), parser.getDate()
                , parser.getNewsSerial(), parser.getCategories(), url, SOURCE);
    }

    public static void main(String[] args) {
        FarsCrawler fc = new FarsCrawler();
        fc.crawlByDateRange("13931111", "13931201");

    }


}
