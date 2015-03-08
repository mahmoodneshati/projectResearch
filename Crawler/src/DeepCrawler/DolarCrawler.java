package DeepCrawler;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.joda.time.Days;
import org.joda.time.DurationFieldType;
import org.joda.time.LocalDate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Mahmood Neshati, PhD
 * Date: 3/4/15
 * Time: 6:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class DolarCrawler {
    public static void main(String[] args) throws InterruptedException {
        HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.CHROME); // true meaning javascript support (Using rhino i be leave)
        String baseUrl = "http://www.eranico.com/fa/signin";
        login(driver, baseUrl);
        getPriceByRange(driver, 2013, 1, 5, 2015, 3, 4);


    }

    private static void login(HtmlUnitDriver driver, String baseUrl) {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(baseUrl);
        driver.findElement(By.id("ctl00_MainContentPlaceHolder_SignInUserControl_UserNameTextBox")).clear();
        driver.findElement(By.id("ctl00_MainContentPlaceHolder_SignInUserControl_UserNameTextBox")).
                sendKeys("mahmood.neshati@gmail.com");
        driver.findElement(By.id("ctl00_MainContentPlaceHolder_SignInUserControl_PasswordTextBox")).clear();
        driver.findElement(By.id("ctl00_MainContentPlaceHolder_SignInUserControl_PasswordTextBox")).sendKeys("eranicobabat2000");
        driver.findElement(By.id("ctl00_MainContentPlaceHolder_SignInUserControl_SignInButton")).click();
        driver.getPageSource();
    }

    private static void getPriceByRange(HtmlUnitDriver driver, int y1, int m1, int d1, int y2, int m2, int d2) throws InterruptedException {
        List<LocalDate> dates = new ArrayList<LocalDate>();
        LocalDate startDate = new LocalDate(y1, m1, d1);
        LocalDate endDate = new LocalDate(y2, m2, d2);
        int days = Days.daysBetween(startDate, endDate).getDays();
        for (int i = 0; i < days; i++) {
            LocalDate d = startDate.withFieldAdded(DurationFieldType.days(), i);
            dates.add(d);
        }
        for (LocalDate localDate : dates) {
            int year = localDate.getYear();
            int month = localDate.getMonthOfYear();
            int day = localDate.getDayOfMonth();
            String result = getPriceByDate(driver, year, month, day);
            if (result != null) {

                try {
                    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Dolar.txt", true)));
                    out.print(result);
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private static String getPriceByDate(HtmlUnitDriver driver, int year, int mounth, int day) {
        StringBuilder builder = new StringBuilder();
        driver.get("http://www.eranico.com/fa/archive/currency/20/1/" + year + "/" + day + "/" + mounth);
        String htmlContent = driver.getPageSource();

        Document doc;
        doc = Jsoup.parse(htmlContent);
        Element table = doc.getElementById("ctl00_MainContentPlaceHolder_PriceListGridView");
        if (table == null) {
            System.err.println("No info found on date " + year + "/" + mounth + "/" + day);

            return null;
        }
        Elements rows = table.select("tr");

        for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.
            Element row = rows.get(i);
            Elements cols = row.select("td");
            String source = cols.get(1).text();
            String price = cols.get(11).text();
            builder.append(year).append("/").append(mounth).append("/").append(day)
                    .append("\t").
                    append(source).
                    append("\t").
                    append(price).append("\r\n");
        }

        System.out.println("Prices are fetched for date  " + year + "/" + mounth + "/" + day);


        return builder.toString();

    }
}
