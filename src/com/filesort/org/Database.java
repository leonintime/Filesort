package com.filesort.org;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

public class Database {

    private Connection conn;
    private String sql;
    private Statement statement;
    private static String DB_CON;

    public Database(String DB_CON) throws SQLException {
        Database.DB_CON = DB_CON;
        conn = DriverManager.getConnection(DB_CON);
        this.statement = conn.createStatement();
    }


    private void openCon() throws SQLException {
        if (conn.isClosed()) {
            conn = DriverManager.getConnection(DB_CON);
            this.statement = conn.createStatement();
        } else {

        }
    }


    public void showPaths() {

        try {
            openCon();
            String selectSql = "SELECT path_id, path_name,path_value FROM paths";
            ResultSet results = statement.executeQuery(selectSql);
            while (results.next()) {
                System.out.println(results.getObject("path_id") + " " + results.getObject("path_name") + " " + results.getObject("path_value"));
            }
            closeCon();
        } catch (SQLException e) {
            closeCon();
            e.getMessage();
        }
    }


    public int moveFiles() {
        try {
            openCon();
            String selectSql = "SELECT path_id, path_name,path_value FROM paths";
            ResultSet results = statement.executeQuery(selectSql);
            while (results.next()) {
                String folderName = results.getObject("path_name").toString();
                String folderPath = results.getObject("path_value").toString();


                File folder = new File(fileFolder);
                String[] files = folder.list();
                assert files != null;
                for (String file : files) {
                    for (int i = 0; i <= extension.length - 1; i++) {
                        if (file.contains(extension[i])) {
                            Files.move(Paths.get(currentPath + file),
                                    Paths.get(destinationPath + file));
                            fileAmount++;
                            System.out.println(file + " got successfully moved");
                        }
                    }
                }

                return fileAmount;
            }

        } catch (NullPointerException | IOException | SQLException ex) {
            return 0;
        }
    }


    public boolean addPath(String pathName, String pathValue) {

        try {
            openCon();
            String sql = "INSERT INTO paths (path_name, path_value) VALUES ('" + pathName + "','" + pathValue + "')";
            statement = conn.createStatement();
            statement.execute(sql);
            closeCon();
            return true;
        } catch (SQLException e) {
            closeCon();
            e.getMessage();
            return false;
        }
    }


    public boolean updatePath(String pathName, String pathValue, int pathNumber) {

        try {
            openCon();
            String sql = "UPDATE paths SET path_name = '" + pathName + "', path_value = '" + pathValue + "' WHERE path_id = " + pathNumber + " ";
            statement = conn.createStatement();
            statement.execute(sql);
            closeCon();
            return true;
        } catch (SQLException e) {
            closeCon();
            e.getMessage();
            return false;
        }
    }


    public boolean deletePath(int pathNumber) {

        try {
            openCon();
            String sql = "DELETE FROM paths WHERE path_id = " + pathNumber + " ";
            statement = conn.createStatement();
            statement.execute(sql);
            closeCon();
            return true;
        } catch (SQLException e) {
            closeCon();
            e.getMessage();
            return false;
        }
    }


    public void closeCon() {
        try {
            if (!conn.isClosed()) {
                conn.close();
            } else {

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
