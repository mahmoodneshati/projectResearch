package parser;

import common.NewsComment;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.CalenderConvetor;
import util.Util;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: Mahmood Neshati
 * Date: Mar 6, 2015
 * Time: 2:34:56 PM
 */
public class AsrIranParser extends Parser {
    Document doc;

    public AsrIranParser(String html) {
        doc = Jsoup.parse(html);
    }

    public String getTitle() {
        Elements elems = doc.getElementsByClass("title");
        if (elems.size() < 1) {
            System.err.println("No title found");
            return null;
        }
        Element h1 = null;
        for (Element elem : elems) {
            if (elem.getElementsByTag("h1") != null) {
                h1 = elem.getElementsByTag("h1").get(0);
            }
        }
        if (h1 == null) {
            System.err.println("No title found");
            return null;
        }
        return h1.child(0).text().trim();
    }

    public String getBody() {
        Elements elems = doc.getElementsByClass("body");
        StringBuilder builder = new StringBuilder();
        for (Element elem1 : elems) {
            builder.append(elem1.text().trim()).append("\r\n");
        }
        return builder.toString().trim();
    }

    public ArrayList<String> getRelatedNews() {
        ArrayList<String> related = new ArrayList<String>();

        Elements elems = doc.select("#news > div:eq(1) > div:eq(1) > div:eq(1)");

        boolean found = false;
        for (Element elem1 : elems.get(0).children()) {
            if (found) {
                Elements elements = elem1.getElementsByTag("a");
                for (Element target : elements) {
                    String str = normilizeURL(target.attr("href"));
                    if (str != null)
                        related.add(str);

                }
                found = false;

            }
            if (elem1.tagName().equalsIgnoreCase("div") && elem1.text().equalsIgnoreCase("مطالب مرتبط :")) {
                found = true;
            }

        }
        return related;


    }

    private String normilizeURL(String href) {
        // this method remove tags from URL
        String reg = "^http://www\\.asriran\\.com/fa/news/\\d*/";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(href);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    public String getDate() {
        Elements elems = doc.getElementsByClass("news_pdate_c");
        return elems.get(0).childNode(3).childNode(0).toString();
    }

    public String getNewsSerial() {

        Elements elems = doc.getElementsByClass("news_id_c");
        if (elems.size() < 1) {
            System.err.println("No News found");
            return null;
        }
        try {
            return Integer.parseInt(doc.getElementsByClass("news_id_c").get(0).text().replaceAll("کد خبر:", "").trim()) + "";
        } catch (Exception e) {
            System.err.println("No News found");
            return null;
        }

    }

    public ArrayList<String> getCategories() {

        ArrayList<String> categories = new ArrayList<String>();

        Elements elems = doc.getElementsByClass("news_path");
        if (elems.size() < 1) {
            System.err.println("No News found");
            return null;
        }
        for (Element elem1 : elems.get(0).children()) {
            if (elem1.tagName().equalsIgnoreCase("a")) {
                categories.add(elem1.text().trim());
            }
        }

        return categories;
    }

    @Override
    public ArrayList<String> getLables() {
        ArrayList<String> lables = new ArrayList<String>();

        Elements elems = doc.getElementsByClass("tags_item");
        if (elems.size() < 1) {
            System.err.println("No lables found");
            return null;
        }
        for (Element elem1 : elems) {
            if (elem1.tagName().equalsIgnoreCase("a")) {
                lables.add(elem1.text().trim());
            }
        }

        return lables;
    }

    @Override
    public ArrayList<NewsComment> getNewsComment() {
        ArrayList<NewsComment> list = new ArrayList<NewsComment>();
        Elements commentItems = doc.getElementsByClass("comments_item");

        for (Element commentItem : commentItems) {
            list.add(parseComment(commentItem));
        }

        return list;
    }

    private NewsComment parseComment(Element commentItem) {
        NewsComment out = new NewsComment();
        out.isResponce = false;
        out.parentComment = null;
        out.name = commentItem.getElementsByClass("comm_info_name").text();
        out.location = commentItem.getElementsByClass("comm_info_country").get(0).child(0).attr("title");
        out.date = ConvertToDate(Util.arabicToDecimal(commentItem.getElementsByClass("comm_info_date").get(0).text()));
        out.dislike = Integer.parseInt(commentItem.getElementsByClass("rating_down").get(0).text());
        out.like = Integer.parseInt(commentItem.getElementsByClass("rating_up").get(0).text());
        out.content = commentItem.getElementsByClass("comments").get(0).text();
        //
        ArrayList<NewsComment> resList = new ArrayList<NewsComment>();
        Elements responses = commentItem.getElementsByClass("comm_answer_content");

        for (Element responce : responses) {
            resList.add(getResponseComment(responce,out));
        }
        out.responses = resList;
        return out;
    }

    private NewsComment getResponseComment(Element responce, NewsComment parent) {

        NewsComment out = new NewsComment();
        out.isResponce = true;
        out.parentComment = parent;
        out.name = responce.getElementsByClass("comment_answer_2").text();
        out.location = responce.getElementsByClass("comment_answer_2").get(0).child(0).attr("title");
        out.date = ConvertToDate(Util.arabicToDecimal(responce.getElementsByClass("comment_answer_5").get(0).text()));
        out.dislike = 0;
        out.like = 0;
        out.content = responce.getElementsByClass("comm_answer_line").get(0).text();
        out.responses = null;
        return out;
    }

    private GregorianCalendar ConvertToDate(String comm_info_date) {
        String persianDate = extractPersianDate(comm_info_date);
        String time = extractTime(comm_info_date);
        String milady = CalenderConvetor.toMilady(persianDate.replace("/", ""));
        int year = Integer.parseInt(milady.substring(0, 4));
        int month = Integer.parseInt(milady.substring(4, 6));
        int day = Integer.parseInt(milady.substring(6, 8));
        int hh = Integer.parseInt(time.substring(0, 2));
        int mm = Integer.parseInt(time.substring(3, 5));
        return new GregorianCalendar(year, month, day, hh, mm);

    }

    private String extractPersianDate(String str) {
        //
        String reg = "\\d\\d\\d\\d/\\d\\d/\\d\\d";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    private String extractTime(String str) {
        //
        String reg = "\\d\\d:\\d\\d";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    public static void main(String[] args) {
        String ss = Util.readFile("C:\\Users\\user\\Desktop\\test.html");
        AsrIranParser fp = new AsrIranParser(ss);
       /* String s = fp.getDate();
        System.out.println(s);

        String title = fp.getTitle();
        System.out.println("title = " + title);


        String body = fp.getBody();
        System.out.println("body = " + body);

        String serial = fp.getNewsSerial();
        System.out.println("serial = " + serial);


        ArrayList<String> cats = fp.getCategories();
        System.out.println("cats = " + cats);


        ArrayList<String> lables = fp.getLables();
        System.out.println("lables = " + lables);


        ArrayList<String> related = fp.getRelatedNews();
        System.out.println("related = " + related);
*/
        GregorianCalendar cc = fp.ConvertToDate(Util.arabicToDecimal("۱۳:۴۸ - ۱۳۹۳/۱۲/۱۵"));
        System.out.println(cc.get(GregorianCalendar.YEAR));
        System.out.println(cc.get(GregorianCalendar.MONTH));
        System.out.println(cc.get(GregorianCalendar.DAY_OF_MONTH));
        System.out.println(cc.get(GregorianCalendar.HOUR));
        System.out.println(cc.get(GregorianCalendar.MINUTE));


        System.out.println(cc.toString());

    }
}
