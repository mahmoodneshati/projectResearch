package parser;

import common.NewsComment;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Mahmood Neshati
 * Date: Feb 4, 2015
 * Time: 9:26:23 AM
 */
public abstract class Parser {
    public abstract String getTitle();
    public abstract String getBody();
    public abstract ArrayList<String> getRelatedNews();
    public abstract String getDate();
    public abstract String getNewsSerial();
    public abstract ArrayList<String> getCategories();
    public abstract ArrayList<String> getLables();
    public abstract ArrayList<NewsComment> getNewsComment();





}
