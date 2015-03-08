package crawler;

import java.net.URLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: Mahmood Neshati
 * Date: Feb 4, 2015
 * Time: 8:29:42 AM
 */
public class Crawler {
    URLConnection connection = null;

    public String fetchPage(String url) {
        String content = null;

        try {
            connection = new URL(url).openConnection();
            Scanner scanner = new Scanner(connection.getInputStream(),"utf8");
            scanner.useDelimiter("\\Z");
            content = scanner.next();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return content;
    }

}
