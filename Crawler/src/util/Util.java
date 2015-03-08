package util;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: Mahmood Neshati
 * Date: Feb 4, 2015
 * Time: 10:44:05 AM
 */
public class Util {

    public static String readFile(String path) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF8"));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\r\n");
            }
            reader.close();
            return builder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final String arabic = "\u06f0\u06f1\u06f2\u06f3\u06f4\u06f5\u06f6\u06f7\u06f8\u06f9";
    public static String arabicToDecimal(String number) {
        char[] chars = new char[number.length()];
        for(int i=0;i<number.length();i++) {
            char ch = number.charAt(i);
            int index = arabic.indexOf(ch);
            if (index >= 0)
                ch = (char) (index + '0');
            chars[i] = ch;
        }
        return new String(chars);
    }
}
