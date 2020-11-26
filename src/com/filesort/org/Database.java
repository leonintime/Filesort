package com.filesort.org;

import java.sql.*;

public class Database {

    private final Connection conn;
    private String sql;
    private Statement statement;

    public Database(String conString) throws SQLException {
        conn = DriverManager.getConnection(conString);
        this.statement = conn.createStatement();
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }

    public boolean addPath(String sql) {

        try {
            statement = conn.createStatement();
            statement.execute(sql);
            return true;
        } catch (SQLException e) {
            e.getMessage();
            return false;
        }
    }


    public void updatePath(String sql) {

        try {
            statement = conn.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.getMessage();
        }
    }


    public void showPaths() {

        try {
            String selectSql = "SELECT path_name,path_value FROM paths";
            ResultSet results = statement.executeQuery(selectSql);
            while (results.next()) {
                System.out.println(results.getObject("path_name") + " " + results.getObject("path_value"));
            }
            results.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }


    public void closeCon() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
