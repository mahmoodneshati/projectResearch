package util;

/**
 * Created by IntelliJ IDEA.
 * User: Mahmood Neshati
 * Date: Feb 4, 2015
 * Time: 1:31:05 PM
 */
public class Normilizer {
    public static String normilize(String s){
        String s1 = removeSpace(s);
        return s1.trim();
    }

    private static String removeSpace(String s) {
        return s.replace("&nbsp;"," ");
    }
}
