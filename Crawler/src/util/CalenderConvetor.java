package util;

/**
 * Created by IntelliJ IDEA.
 * User: Mahmood Neshati
 * Date: Mar 6, 2015
 * Time: 9:42:19 AM
 */
public class CalenderConvetor {

    public static String toMilady(String shmasi) {
        int year = Integer.parseInt(shmasi.substring(0, 4));
        int month = Integer.parseInt(shmasi.substring(4, 6));
        int day = Integer.parseInt(shmasi.substring(6, 8));
        Roozh r = new Roozh();
        r.PersianToGregorian(year, month, day);
        return "" + r.getYear() + String.format("%02d", r.getMonth())  + String.format("%02d", r.getDay());
    }

        public static String toshamsi(String miladi) {
        int year = Integer.parseInt(miladi.substring(0, 4));
        int month = Integer.parseInt(miladi.substring(4, 6));
        int day = Integer.parseInt(miladi.substring(6, 8));
        Roozh r = new Roozh();
        r.GregorianToPersian(year, month, day);
        return "" + r.getYear() + String.format("%02d", r.getMonth())  + String.format("%02d", r.getDay());
    }
}
