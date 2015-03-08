package db;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Mahmood Neshati
 * Date: Feb 5, 2015
 * Time: 3:07:11 PM
 */
public class DBHelper {
    static String url = "jdbc:sqlserver://localhost;databasename=news;user=sa;password=pass";
    static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String userName = "sa";
    String password = "pass";
    private static Connection connection = null;


    private static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName(driverName);
                connection = DriverManager.getConnection(url);
                return connection;

            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return connection;
    }

    public static void executeCommand(String sqlCommand) {
        try {
            Connection con = getConnection();
            if (con == null) {
                System.err.println("Can not make a connection to DB!");
                return;
            }
            Statement stmt = con.createStatement();
            stmt.execute(sqlCommand);
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static String selectSingleCommand(String selectCommand) {
        String out = null;
        try {
            Connection con = getConnection();
            if (con == null) {
                System.err.println("Can not make a connection to DB!");
                return null;
            }
            Statement stmt = con.createStatement();
            ResultSet set = stmt.executeQuery(selectCommand);
            if (set.next()) {
                out = set.getString(1);
            }
            con.close();
            return out;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        DBHelper.executeCommand("insert into categories values('url1','source','cat')");
    }

    public static ArrayList<String> selectCommand(String query) {
          ArrayList<String> out = new ArrayList<String>();
        try {
            Connection con = getConnection();
            if (con == null) {
                System.err.println("Can not make a connection to DB!");
                return null;
            }
            Statement stmt = con.createStatement();
            ResultSet set = stmt.executeQuery(query);
            while (set.next()) {
                out.add(set.getString(1));
            }
            con.close();
            return out;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
