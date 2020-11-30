package com.filesort.org;

import java.sql.*;

public class Database {

    private final Connection conn;
    private String sql;
    private Statement statement;

    public Database(String DB_CON) throws SQLException {
        conn = DriverManager.getConnection(DB_CON);
        this.statement = conn.createStatement();
    }


    public void showPaths() {

        try {
            String selectSql = "SELECT path_name,path_value FROM paths";
            ResultSet results = statement.executeQuery(selectSql);
            while (results.next()) {
                System.out.println(results.getObject("path_name") + " " + results.getObject("path_value"));
            }
            closeCon();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public boolean addPath(String pathName, String pathValue) {

        try {
            String sql = "INSERT INTO paths (path_name, path_value) VALUES ('" + pathName + "','" + pathValue + "')";
            statement = conn.createStatement();
            statement.execute(sql);
            closeCon();
            return true;
        } catch (SQLException e) {
            e.getMessage();
            return false;
        }
    }


    public boolean updatePath(String pathName, String pathValue, int pathNumber) {

        try {
            String sql = "UPDATE paths SET path_name = '" + pathName + "', path_value = '" + pathValue + "' WHERE path_id = + " + pathNumber + " ";
            statement = conn.createStatement();
            statement.execute(sql);
            closeCon();
            return true;
        } catch (SQLException e) {
            e.getMessage();
            return false;
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
