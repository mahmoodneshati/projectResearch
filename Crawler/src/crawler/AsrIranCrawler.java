package crawler;

import common.News;
import db.DBHelper;
import parser.AsrIranParser;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by IntelliJ IDEA.
 * User: Mahmood Neshati
 * Date: Mar 6, 2015
 * Time: 2:49:36 PM
 */
public class AsrIranCrawler extends Crawler {
    HashSet<Integer> newsIDCrawled = new HashSet<Integer>();

    public static void main(String[] args) {
        AsrIranCrawler asrCrawler = new AsrIranCrawler();
        asrCrawler.crawlByNewsRange(110, 1000);
    }

    private void crawlByNewsRange(int start, int end) {
        loadCrawledNumbers();
        for (int num = start; num < end; num++) {
            if (newsIDCrawled.contains(num)) {
                System.out.println("Skip the " + num + " News!");
            }
            crawlByNumber(num);
        }
    }

    private void loadCrawledNumbers() {
        String query = "SELECT cast(newsNumber as int) as NewsNumber FROM [core]\n" +
                "where source = 'asriran'";
        ArrayList<String> set = DBHelper.selectCommand(query);
        for (String s : set) {
            newsIDCrawled.add(Integer.parseInt(s));
        }
    }

    private static final String SOURCE = "asriran";


    private News createNews(AsrIranParser parser, String url) {
        String newsSerial = parser.getNewsSerial();
        if (newsSerial == null)
            return null;
        return new News(parser.getTitle(), parser.getBody(), parser.getRelatedNews(), parser.getDate()
                , parser.getNewsSerial(), parser.getCategories(), url, SOURCE);
    }

    private void crawlByNumber(int num) {
        String url = createURL(num);
        String asriranPageContent = fetchPage(url);
        AsrIranParser parser = new AsrIranParser(asriranPageContent);
        News news = createNews(parser, url);
        if (news != null)
            news.save();
    }

    private String createURL(int num) {
        return "http://www.asriran.com/fa/news/" + num + "/";

    }

}
