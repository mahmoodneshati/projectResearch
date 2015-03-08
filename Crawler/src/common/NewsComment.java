package common;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Mahmood Neshati, PhD :)
 * Date: 3/7/15
 * Time: 1:31 PM      d
 * To change this template use File | Settings | File Templates.
 */
public class NewsComment {
    public boolean isResponce;
    public NewsComment parentComment;
    public String content;
    public String name;
    public String location;
    public Calendar date;
    public int dislike;
    public int like;
    public ArrayList<NewsComment> responses;

    public void save() {
        saveCommentTable();
    }

    private void saveCommentTable() {
        //TODO Implementing saving comment
    }


}
