package models;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class Model<E> {

    private Connection con;
    private Statement stmt;
    private boolean isConnected;
    private String message;
    protected String table;
    protected String primaryKey;
    protected String select = "*";
    private String where = "";
    private String join = "";
    private String otherQuery = "";

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pbo", "root", "");
            stmt = con.createStatement();
            isConnected = true;
            message = "Database Terkoneksi";
        } catch (ClassNotFoundException | SQLException e) {
            isConnected = false;
            message = e.getMessage();
        }
    }

    public void disconnect() {
        try {
            stmt.close();
            con.close();
        } catch (SQLException e) {
            message = e.getMessage();
        }
    }

    ArrayList<Object> toRow(ResultSet rs) {
        ArrayList<Object> res = new ArrayList<>();
        try {
            int columnCount = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                res.add(rs.getObject(i));
            }
        } catch (SQLException e) {

        }
        return res;
    }

    public ArrayList<ArrayList<Object>> query(String query) {
        ArrayList<ArrayList<Object>> res = new ArrayList<>();
        try {
            connect();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                res.add(toRow(rs));
            }
        } catch (SQLException e) {
            message = e.getMessage();
        } finally {
            disconnect();
        }
        return res;
    }

    abstract E toModel(ResultSet rs);

    public ArrayList<E> get() {
        ArrayList<E> res = new ArrayList<>();
        try {
            this.connect();
            String query = "SELECT " + select + " FROM " + table;
            if (!join.equals("")) {
                query += join;
            }
            if (!where.equals("")) {
                query += " WHERE " + where;
            }
            if (!otherQuery.equals("")) {
                query += " " + otherQuery;
            }
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                res.add(toModel(rs));
            }
        } catch (SQLException e) {
            message = e.getMessage();
        } finally {
            disconnect();
            select = "*";
            where = "";
            join = "";
            otherQuery = "";
        }
        return res;
    }

    public E find(String pkValue) {
        try {
            connect();
            String query = "SELECT " + select + " FROM " + table + " WHERE " + primaryKey + " = '" + pkValue + "'";
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return toModel(rs);
            }
        } catch (SQLException e) {
            message = e.getMessage();
        } finally {
            disconnect();
            select = "*";
        }
        return null;
    }

    public void select(String cols) {
        select = cols;
    }

    public void join(String table, String on) {
        join += " JOIN " + table + " ON " + on;
    }

    public void where(String cond) {
        where = cond;
    }

    public void addQuery(String query) {
        otherQuery = query;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

}
