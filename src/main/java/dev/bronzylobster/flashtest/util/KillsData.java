package dev.bronzylobster.flashtest.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class KillsData {

    private final String url;

    public KillsData() throws Exception {

        url = "jdbc:sqlite:plugins/Flashtest/kills.db";
        Class.forName("org.sqlite.JDBC").getConstructor().newInstance();

        Connection c = getConnection();
        Statement s = c.createStatement();

        s.executeUpdate("CREATE TABLE IF NOT EXISTS sword ('ID' TEXT, 'kills' INTEGER)");

        s.close();
        c.close();
    }

    public  Connection getConnection() throws Exception {
        return DriverManager.getConnection(url);
    }

    public void add(String ID, int kills) {
        try {
            Connection c = DriverManager.getConnection(url);
            Statement s = c.createStatement();

            s.executeUpdate("INSERT INTO sword VALUES ('" + ID + "', '" + kills + "')");

            s.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int get(String ID) {
       try {
           Connection c = this.getConnection();
           Statement s = c.createStatement();

           ResultSet result = s.executeQuery("SELECT kills FROM sword WHERE ID = '" + ID + "'");
           int temp = result.getInt(1);

           s.close();
           c.close();

           return temp;
       } catch (Exception e) {
           e.printStackTrace();
           return 0;
       }
    }

    public void set(String ID, int kills) {
        try {
            Connection c = this.getConnection();
            Statement s = c.createStatement();

            s.executeUpdate("UPDATE sword SET kills = " + kills + " WHERE ID = '" + ID + "')");

            s.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
