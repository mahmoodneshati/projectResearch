package test;

import com.ghasemkiani.util.icu.PersianCalendar;
import com.ghasemkiani.util.PersianCalendarHelper;
import com.ibm.icu.util.ULocale;
import com.ibm.icu.util.TimeZone;
import com.ibm.icu.util.GregorianCalendar;
import com.ibm.icu.text.DateFormat;

import java.util.Date;
import java.text.SimpleDateFormat;

import util.Roozh;
import util.CalenderConvetor;

/**
 * Created by IntelliJ IDEA.
 * User: Mahmood Neshati
 * Date: Mar 6, 2015
 * Time: 7:18:06 AM
 */
public class CalenderTest {
    public static void main(String[] args) {
        System.out.println(CalenderConvetor.toMilady("13931215"));
    }
}
