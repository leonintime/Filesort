package com.filesort.org;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;

public class Database {

    private Connection conn;
    private Statement statement;
    private static String DB_CON;
    //
    // public enum Options {
    // MONDAY,TUESDAY,WEDNESDAY
    // }

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
                System.out.println(results.getObject("mff_id") + " " + results.getObject("mff_name") + " "
                        + results.getObject("mff_path"));
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
                System.out.println(results.getObject("dest_fold_id") + " " + results.getObject("dest_fold_name") + " "
                        + results.getObject("dest_fold_path"));
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
            String selectSql = "SELECT mff_id,mff_path  FROM moving_files_folder";
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

    public int moveFiles() {
        String destFolderPath = null;
        String moveFromFolder = null;
        int mff_id = 0;
        int dest_fold_id = 0; 
        int fileAmount = 0;
        ArrayList<String> extensionList = new ArrayList<>();
        // String[] extensions = new String[]{};
        try {

            ResultSet results = getConnectedFolders();

            while (results.next()) {
                // Powerpoint, word etc
                destFolderPath = results.getObject("dest_fold_path").toString();
                moveFromFolder = results.getObject("mff_path").toString();
                mff_id = (int) results.getObject("mff_id");
                dest_fold_id = (int) results.getObject("dest_fold_id");

                ResultSet extensions = getConnectedFolderExtensions(dest_fold_id);

                
                while (extensions.next()) {
                    extensionList.add((String) extensions.getObject("file_ext_ext"));
                }

           

                assert destFolderPath != null;
                File folder = new File(moveFromFolder);
                String[] files = folder.list();
                assert files != null;
                for (String file : files) {
                    for (int i = 0; i < extensionList.size(); i++) {
                        if (file.contains(extensionList.get(i))) {
                            Files.move(Paths.get(moveFromFolder + file), Paths.get(destFolderPath + file));
                            fileAmount++;
                            System.out.println(file + " got successfully moved");
                        }
                    }
                }
                closeCon();
            }

            return fileAmount;

        } catch (NullPointerException | IOException | SQLException ex) {
            closeCon();
            System.out.println(ex);
            return 0;
        }
    }

    public ResultSet getConnectedFolders() throws SQLException {
        openCon();
        String selectSql = "select distinct moving_files_folder.mff_name, moving_files_folder.mff_path, dest_fold_name, moving_files_folder.mff_id,  dest_fold_path, dest_fold_id\n"
                + "from file_moving,\n" + "     destination_folder,\n" + "     file_extensions,\n"
                + "     moving_files_folder\n" + "\n"
                + "WHERE file_moving.dest_folder_id = destination_folder.dest_fold_id\n"
                + "  AND file_moving.mff_id = moving_files_folder.mff_id";
        ResultSet results = statement.executeQuery(selectSql);
        return results;
    }

    public ResultSet getConnectedFolderExtensions(int id) throws SQLException {
        openCon();
        String selectSql = "SELECT dest_fold_name, file_ext_ext FROM file_moving, destination_folder, file_extensions, moving_files_folder WHERE file_moving.dest_folder_id = destination_folder.dest_fold_id AND file_moving.mff_id = moving_files_folder.mff_id AND file_extensions.dest_folder_id = destination_folder.dest_fold_id AND file_moving.dest_folder_id = "
                + id;
        ResultSet results = statement.executeQuery(selectSql);
        return results;
    }

    public boolean addMovingFilesFolder(String folder, String path) {

        try {
            openCon();
            String sql = "INSERT INTO moving_files_folder (mff_name, mff_path) VALUES ('" + folder + "','" + path
                    + "')";
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
            String sql = "UPDATE moving_files_folder SET mff_name = '" + folder + "', mff_path = '" + path
                    + "' WHERE mff_id = " + id + " ";
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
            String sql = "INSERT INTO destination_folder (dest_fold_name, dest_fold_path)  VALUES  ('" + folder + "','"
                    + path + "')";
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
            String sql = "UPDATE destination_folder SET dest_fold_name = '" + folder + "', dest_fold_path = '" + path
                    + "' WHERE dest_fold_id = " + id + " ";
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
            String sql = "INSERT INTO file_moving (dest_folder_id, mff_id) VALUES (" + dest_folder_id + " , " + mff_id
                    + ")";
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
