package com.filesort.org;

import java.sql.*;

public class Database {

    private Connection conn;
    private String sql;
    private Statement statement;
    private static String DB_CON;
//
//    public enum Options {
//        MONDAY,TUESDAY,WEDNESDAY
//    }

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


    public void showMovingFolders() {

        try {
            openCon();
            String selectSql = "SELECT mff_id,  mff_name, mff_path FROM moving_files_folder";
            ResultSet results = statement.executeQuery(selectSql);
            while (results.next()) {
                System.out.println(results.getObject("mff_id") + " " + results.getObject("mff_name") + " " + results.getObject("mff_path"));
            }
            closeCon();
        } catch (SQLException e) {
            closeCon();
            e.getMessage();
        }
    }


    public void showDestinationFolders() {

        try {
            openCon();
            String selectSql = "SELECT dest_fold_id, dest_fold_name, dest_fold_path FROM destination_folder";
            ResultSet results = statement.executeQuery(selectSql);
            while (results.next()) {
                System.out.println(results.getObject("dest_fold_id") + " " + results.getObject("dest_fold_name") + " " + results.getObject("dest_fold_path"));
            }
            closeCon();
        } catch (SQLException e) {
            closeCon();
            e.getMessage();
        }
    }


    public void getAllMovingFolderIds() {

        try {
            openCon();
            String selectSql = "SELECT mff_id FROM moving_files_folder";
            ResultSet results = statement.executeQuery(selectSql);
            while (results.next()) {
                System.out.println(results.getObject("mff_id") + " " + results.getObject("mff_path"));
            }
            closeCon();
        } catch (SQLException e) {
            closeCon();
            e.getMessage();
        }

    }


    public void getAllDestinationFolderIds() {

        try {
            openCon();
            String selectSql = "SELECT dest_fold_id, dest_fold_path FROM destination_folder";
            ResultSet results = statement.executeQuery(selectSql);
            while (results.next()) {
                System.out.println(results.getObject("dest_fold_id") + " " + results.getObject("dest_fold_path"));
            }
            closeCon();
        } catch (SQLException e) {
            closeCon();
            e.getMessage();
        }

    }


//    public int moveFiles() {
//        try {
//            openCon();
//            String selectSql = "SELECT mff_id,  mff_name, mff_path FROM moving_files_folder";
//            ResultSet results = statement.executeQuery(selectSql);
//            while (results.next()) {
//                String folderName = results.getObject("mff_name").toString();
//                String folderPath = results.getObject("mff_path").toString();
//
//                File folder = new File(folderName);
//                String[] files = folder.list();
//                assert files != null;
//                for (String file : files) {
//                    for (int i = 0; i <= extension.length - 1; i++) {
//                        if (file.contains(extension[i])) {
//                            Files.move(Paths.get(currentPath + file),
//                                    Paths.get(destinationPath + file));
//                            fileAmount++;
//                            System.out.println(file + " got successfully moved");
//                        }
//                    }
//                }
//
//                return fileAmount;
//            }
//
//        } catch (NullPointerException | IOException | SQLException ex) {
//            return 0;
//        }
//    }


    public boolean addMovingFilesFolder(String folder, String path) {

        try {
            openCon();
            String sql = "INSERT INTO moving_files_folder (mff_name, mff_path) VALUES ('" + folder + "','" + path + "')";
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


    public boolean updateMovingFilesFolder(String folder, String path, int id) {

        try {
            openCon();
            String sql = "UPDATE moving_files_folder SET mff_name = '" + folder + "', mff_path = '" + path + "' WHERE mff_id = " + id + " ";
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


    public boolean deleteMovingFilesFolder(int id) {

        try {
            openCon();
            String sql = "DELETE FROM moving_files_folder WHERE mff_id = " + id + " ";
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


    public boolean addDestinationFolder(String folder, String path) {

        try {
            openCon();
            String sql = "INSERT INTO destination_folder (dest_fold_name, dest_fold_path)  VALUES  ('" + folder + "','" + path + "')";
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


    public boolean updateDestinationFolder(String folder, String path, int id) {

        try {
            openCon();
            String sql = "UPDATE destination_folder SET dest_fold_name = '" + folder + "', dest_fold_path = '" + path + "' WHERE dest_fold_id = " + id + " ";
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


    public boolean deleteDestinationFolder(int id) {

        try {
            openCon();
            String sql = "DELETE FROM destination_folder WHERE dest_fold_id = " + id + " ";
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


    public boolean connectFolders(int mff_id, int dest_folder_id) {

        try {
            openCon();
            String sql = "INSERT INTO file_moving (dest_folder_id, mff_id) VALUES (" + dest_folder_id + " , " + mff_id + ")";
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
