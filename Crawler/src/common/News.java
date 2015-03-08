package common;

import db.DBHelper;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Mahmood Neshati
 * Date: Feb 4, 2015
 * Time: 6:45:28 PM
 */
public class News {
    public String title;
    public String body;
    public ArrayList<String> relatedNews;
    public String date;
    public String newsSerial;
    public ArrayList<String> categories;
    public String url;
    public String sourceWebSite;

    public final String newsTableName = "news";

    public final String relatedNewsTableName = "relatedNews";
    public final String categoriesTableName = "categories";


    public News(String title, String body, ArrayList<String> relatedNews,
                String date, String newsSerial, ArrayList<String> categories,
                String url, String sourceWebSite) {
        this.title = title;
        this.body = body;
        this.relatedNews = relatedNews;
        this.date = date;
        this.newsSerial = newsSerial;
        this.categories = categories;
        this.url = url;
        this.sourceWebSite = sourceWebSite;
    }

    public void save() {
        saveNewsTable();
        saveRelatedNewsTable();
        saveCategoriesTable();
    }

    private void saveCategoriesTable() {
        for (String category : categories) {
            String sql = saveCategoryCommand(category);
            DBHelper.executeCommand(sql);
        }


    }

    private String saveCategoryCommand(String category) {
        return ( "insert into categories values(" + "'" +
                (url != null ? url.trim().replaceAll("'", "''") : "")
                + "','+" +
                (sourceWebSite != null ? sourceWebSite.trim().replaceAll("'", "''") : "")
                + "','" +
                (category != null ? category.trim().replaceAll("'", "''") : "")
                + "')");
    }

    private void saveRelatedNewsTable() {


        for (String url2 : relatedNews) {
            String sql = saveRelatedCommand(url2);
            DBHelper.executeCommand(sql);
        }

    }

    private String saveRelatedCommand(String url2) {
        return ("insert into relatedNews values('" +
                (url != null ? url.trim().replaceAll("'", "''") : "")
                + "','" +
                (url2 != null ? url2.trim().replaceAll("'", "''") : "")
                + "','+" +
                (sourceWebSite != null ? sourceWebSite.trim().replaceAll("'", "''") : "")
                + "')");
    }

    private void saveNewsTable() {
        String sql = saveNewsCommand();
        DBHelper.executeCommand(sql);
    }

    private String saveNewsCommand() {

        return ("insert into core values('" +
                (newsSerial != null ? newsSerial.trim().replaceAll("'", "''") : "")
                + "','" +
                (title != null ? title.trim().replaceAll("'", "''") : "")
                + "','" +
                (body != null ? body.trim().replaceAll("'", "''") : "")
                + "','" +
                (date != null ? date.trim().replaceAll("'", "''") : "")
                + "','" +
                (url != null ? url.trim().replaceAll("'", "''") : "")
                + "','" +
                (sourceWebSite != null ? sourceWebSite.trim().replaceAll("'", "''") : "")
                + "')");


    }


}
