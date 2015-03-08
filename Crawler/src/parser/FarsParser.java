package parser;

import common.NewsComment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.StringTokenizer;

import parser.Parser;
import util.Util;

/**
 * Created by IntelliJ IDEA.
 * User: Mahmood Neshati
 * Date: Feb 4, 2015
 * Time: 9:27:25 AM
 */
public class FarsParser extends Parser {
    Document doc;

    public FarsParser(String html) {
        doc = Jsoup.parse(html);
    }

    public String getTitle() {

        // nwstxtinfotitle
        Elements elems = doc.getElementsByClass("nwstxtinfotitle");
        if (elems.size() < 1) {
            System.err.println("No title found");
            return null;
        } else if (elems.size() > 1) {
            System.err.println("More than one title found! returning the first one");
        }
        Element element = elems.get(0);
        return element.childNode(0).toString().trim();

    }

    public String getBody() {
        StringBuilder builder = new StringBuilder();


        Element element = doc.getElementById("ctl00_bodyHolder_newstextDetail_nwstxtBodyPane");
        Elements allparags = element.getElementsByClass("rtejustify");
        for (Element allparag : allparags) {
            builder.append(allparag.childNode(0).toString().trim()).append("\r\n");
        }

        return builder.toString().trim();
    }

    public ArrayList<String> getRelatedNews() {

        ArrayList<String> list = new ArrayList<String>();
        Elements evenElems = doc.getElementsByClass("nwstxtevenrow");
        Elements rowElems = doc.getElementsByClass("nwstxtoddrow");

        for (Element evenElem : evenElems) {
            String url = evenElem.getElementsByClass("relnewstitle").get(0).child(0).attr("href");
            list.add("http://www.farsnews.com" + url);
        }

        for (Element rowElem : rowElems) {
            String url = rowElem.getElementsByClass("relnewstitle").get(0).child(0).attr("href");
            list.add("http://www.farsnews.com" + url);
        }

        return list;
    }

    public String getDate() {

        Elements elems = doc.getElementsByClass("nwstxtdt");
        if (elems.size() < 1) {
            System.err.println("No date found");
            return null;
        } else if (elems.size() > 1) {
            System.err.println("More than one date found! returning the first one");
        }
        Element element = elems.get(0);
        String date = element.childNode(0).toString().trim();
        // 93/11/15 - 12:23
        StringTokenizer tokenizer = new StringTokenizer(date, " /:-", false);
        String str = "";
        while (tokenizer.hasMoreTokens()) {
            String s = tokenizer.nextToken().trim();
            if (s.length() == 0) continue;
            str += (s + "-");
        }
        return str.substring(0, str.length() - 1);

    }

    public String getNewsSerial() {
        Elements elems = doc.getElementsByClass("nwstxtnn");
        if (elems.size() < 1) {
            System.err.println("No id found");
            return null;
        } else if (elems.size() > 1) {
            System.err.println("More than one id found! returning the first one");
        }
        Element element = elems.get(0);
        String id = element.childNode(0).toString().trim();
        id = id.replace("�����:", "");
        return Long.parseLong(id.trim()) + "";
    }

    public ArrayList<String> getCategories() {
        ArrayList<String> list = new ArrayList<String>();
        Elements elems = doc.getElementsByClass("nwstxtctglink");
        if (elems.size() < 1) {
            System.err.println("No category found");
            return null;
        } else if (elems.size() > 1) {
            System.err.println("More than one category found! returning the first one");
        }
        Element element = elems.get(0);
        for (int i = 0; i < element.childNodeSize(); i++) {
            try {
                if (element.child(i).attr("href") != null) {
                    list.add(element.child(i).attr("href"));
                }
            }
            catch (Exception e) {
                System.err.print("");
            }

        }
        return list;
    }

    @Override
    public ArrayList<String> getLables() {
        //TODO implement if possible
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ArrayList<NewsComment> getNewsComment() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public static void main(String[] args) {
        String ss = Util.readFile("C:\\Users\\navid\\Desktop\\test.html");
        FarsParser fp = new FarsParser(ss);
        ///String body = util.Normilizer.normilize(fp.getBody());
        Object d = fp.getCategories();
        System.out.println("d = " + d);
        //System.out.println("body = " + body);
    }
}
