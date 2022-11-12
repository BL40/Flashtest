package dev.bronzylobster.flashtest.util;

import java.sql.*;

public class DataBase {

    private final String url;

    public DataBase() throws Exception {

        url = "jdbc:sqlite:plugins/Flashtest/FlashTest.db";
        Class.forName("org.sqlite.JDBC").getConstructor().newInstance();

        Connection c = getConnection();
        Statement s = c.createStatement();

        s.executeUpdate("CREATE TABLE IF NOT EXISTS amount ('item' TEXT, 'ID' INTEGER)");
        s.executeUpdate("CREATE TABLE IF NOT EXISTS sword ('ID' TEXT, 'amount' INTEGER, 'nick' TEXT)");
        s.executeUpdate("CREATE TABLE IF NOT EXISTS shovel ('ID' TEXT, 'amount' INTEGER, 'nick' TEXT)");
        s.executeUpdate("CREATE TABLE IF NOT EXISTS hoe ('ID' TEXT, 'amount' INTEGER, 'nick' TEXT)");
        s.executeUpdate("CREATE TABLE IF NOT EXISTS pickaxe ('ID' TEXT, 'amount' INTEGER, 'nick' TEXT)");
        s.executeUpdate("CREATE TABLE IF NOT EXISTS axe ('ID' TEXT, 'amount' INTEGER, 'nick' TEXT)");

        s.close();
        c.close();
    }

    public  Connection getConnection() throws Exception {
        return DriverManager.getConnection(url);
    }

    public void setID(String item, int ID) {
        try {
            Connection c = this.getConnection();
            Statement s = c.createStatement();

            s.executeUpdate("UPDATE amount SET ID = " + ID + " WHERE item = '" + item + "'");

            s.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getID (String item) {
        try {
            Connection c = this.getConnection();
            Statement s = c.createStatement();

            ResultSet result = s.executeQuery("SELECT ID FROM amount WHERE item = '" + item + "'");
            int temp = result.getInt(1);

            s.close();
            c.close();

            return temp;
        } catch (Exception e) {
            try {
            Connection c = this.getConnection();
            Statement s = c.createStatement();

            s.executeUpdate("INSERT INTO amount VALUES ('" + item + "', '0')");

            s.close();
            c.close();

            return 0;
            } catch (Exception ex) {
                ex.printStackTrace();
                return 0;
            }
        }
    }

    public void add(String item, String ID, int amount, String nick) {
        try {
            Connection c = DriverManager.getConnection(url);
            Statement s = c.createStatement();

            s.executeUpdate("INSERT INTO " + item + " VALUES ('" + ID + "', '" + amount + "', '" + nick + "')");

            s.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getA(String item, String ID) {
       try {
           Connection c = this.getConnection();
           Statement s = c.createStatement();

           ResultSet result = s.executeQuery("SELECT amount FROM " + item + " WHERE ID = '" + ID + "'");
           int temp = result.getInt(1);

           s.close();
           c.close();

           return temp;
       } catch (Exception e) {
           e.printStackTrace();
           return 0;
       }
    }

    public String getN(String item, String ID) {
        try {
            Connection c = this.getConnection();
            Statement s = c.createStatement();

            ResultSet result = s.executeQuery("SELECT nick FROM " + item + " WHERE ID = '" + ID + "'");
            String temp = result.getString(1);

            s.close();
            c.close();

            return temp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void set(String item, String ID, int kills) {
        try {
            Connection c = this.getConnection();
            Statement s = c.createStatement();

            s.executeUpdate("UPDATE " + item + " SET amount = " + kills + " WHERE ID = '" + ID + "'");

            s.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
