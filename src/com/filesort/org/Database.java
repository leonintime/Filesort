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
    private static String SELECT_SQL;
    private static String INSERT_SQL;
    private static String UPDATE_SQL;
    private static final String SELECT = "SELECT ";
    private static final String DISTINCT = "DISTINCT ";
    public static final String FROM = " FROM ";
    public static final String WHERE = " WHERE ";
    public static final String AND = " AND ";
    public static final String EQUALS = " = ";
    public static final String SEPARATOR = ",";
    public static final String SPACE = " ";
    public static final String DOT = ".";
    public static final String INSERT_INTO = "INSERT INTO ";
    public static final String LEFT_BRACKET = "(";
    public static final String RIGHT_BRACKET = ")";
    public static final String VALUES = " VALUES ";
    public static final String SINGLE_QUOTATION_MARK = "'";
    public static final String UPDATE = "UPDATE ";
    public static final String SET = " SET ";

    // TABLES
    public static final String DESTINATION_FOLDER = "destination_folder";
    public static final String FILE_EXTENSIONS = "file_extensions";
    public static final String FILE_MOVING = "file_moving";
    public static final String MOVING_FILES_FOLDER = "moving_files_folder";

    // COLUMNS

    // DESTINATION_FOLDER
    public static final String DF_DEST_FOLD_ID = "dest_fold_id";
    public static final String DF_DEST_FOLD_NAME = "dest_fold_name";
    public static final String DF_DEST_FOLD_PATH = "dest_fold_path";

    // FILE_EXTENSIONS
    public static final String FE_FILE_EXT_ID = "file_ext_id";
    public static final String FE_FILE_EXT_EXT = "file_ext_ext";
    public static final String FE_DEST_FOLDER_ID = "dest_folder_id";

    // FILE_MOVING
    public static final String FM_DEST_FOLDER_ID = "dest_folder_id";
    public static final String FM_MFF_ID = "mff_id";

    // MOVING_FILES_FOLDERS
    public static final String MFF_MFF_ID = "mff_id";
    public static final String MFF_MFF_NAME = "mff_name";
    public static final String MFF_MFF_PATH = "mff_path";

    // SQL STATEMENTS
    public static String SELECT_DESTINATION_FOLDER = SELECT + DF_DEST_FOLD_ID + SEPARATOR + SPACE + DF_DEST_FOLD_NAME
            + SEPARATOR + SPACE + DF_DEST_FOLD_PATH + FROM + DESTINATION_FOLDER;
    public static String SELECT_MOVING_FILES_FOLDER = SELECT + MFF_MFF_ID + SEPARATOR + MFF_MFF_NAME + SEPARATOR
            + MFF_MFF_PATH + FROM + MOVING_FILES_FOLDER;
    public static String SELECT_ALL_MOVING_FOLDER_IDS = SELECT + MFF_MFF_ID + SEPARATOR + MFF_MFF_PATH + FROM
            + MOVING_FILES_FOLDER;
    public static String SELECT_ALL_DESTINATION_FOLDER_IDS = SELECT + DF_DEST_FOLD_ID + SEPARATOR + DF_DEST_FOLD_PATH
            + FROM + DESTINATION_FOLDER;
    public static String SELECT_CONNECTED_FOLDERS = SELECT + DISTINCT + MOVING_FILES_FOLDER + DOT + MFF_MFF_NAME
            + SEPARATOR + SPACE + MOVING_FILES_FOLDER + DOT + MFF_MFF_PATH + SEPARATOR + SPACE + DF_DEST_FOLD_NAME
            + SEPARATOR + SPACE + MOVING_FILES_FOLDER + DOT + MFF_MFF_ID + SEPARATOR + SPACE + DF_DEST_FOLD_PATH
            + SEPARATOR + SPACE + DF_DEST_FOLD_ID + FROM + FILE_MOVING + SEPARATOR + SPACE + DESTINATION_FOLDER
            + SEPARATOR + SPACE + FILE_EXTENSIONS + SEPARATOR + SPACE + MOVING_FILES_FOLDER + WHERE + FILE_MOVING + DOT
            + FM_DEST_FOLDER_ID + EQUALS + DESTINATION_FOLDER + DOT + DF_DEST_FOLD_ID + AND + FILE_MOVING + DOT
            + FM_MFF_ID + EQUALS + MOVING_FILES_FOLDER + DOT + MFF_MFF_ID;
    public static String SELECT_CONNECTED_FOLDER_EXTENSIONS = SELECT + DISTINCT + DF_DEST_FOLD_NAME + SEPARATOR + SPACE
            + FE_FILE_EXT_EXT + FROM + FILE_MOVING + SEPARATOR + SPACE + DESTINATION_FOLDER + SEPARATOR
            + FILE_EXTENSIONS + SEPARATOR + SPACE + MOVING_FILES_FOLDER + WHERE + FILE_MOVING + DOT + FM_DEST_FOLDER_ID
            + EQUALS + DESTINATION_FOLDER + DOT + DF_DEST_FOLD_ID + AND + FILE_MOVING + DOT + FM_MFF_ID + EQUALS
            + MOVING_FILES_FOLDER + DOT + MFF_MFF_ID + AND + FILE_EXTENSIONS + DOT + FE_DEST_FOLDER_ID + EQUALS
            + DESTINATION_FOLDER + DOT + DF_DEST_FOLD_ID + AND + FILE_MOVING + DOT + FM_DEST_FOLDER_ID + EQUALS;

    public static String INSERT_INTO_MOVING_FILES_FOLDER = INSERT_INTO + MOVING_FILES_FOLDER + LEFT_BRACKET
            + MFF_MFF_NAME + SEPARATOR + SPACE + MFF_MFF_PATH + RIGHT_BRACKET + VALUES;

    public static String UPDATE_MOVING_FILES_FOLDER = UPDATE + MOVING_FILES_FOLDER + SET;

    // String sql = "UPDATE moving_files_folder SET mff_name = '" + folder + "',
    // mff_path = '" + path
    // + "' WHERE mff_id = " + id + " ";

    public Database(String DB_CON) throws SQLException {
        Database.DB_CON = DB_CON;
        conn = DriverManager.getConnection(DB_CON);
        this.statement = conn.createStatement();
    }

    private void OpenCon() throws SQLException {
        if (conn.isClosed()) {
            conn = DriverManager.getConnection(DB_CON);
            this.statement = conn.createStatement();
        }
    }

    public void ShowMovingFolders() {

        try {
            OpenCon();
            SELECT_SQL = SELECT_MOVING_FILES_FOLDER;
            ResultSet results = statement.executeQuery(SELECT_SQL);
            while (results.next()) {
                System.out.println(results.getObject(MFF_MFF_ID) + " " + results.getObject(MFF_MFF_NAME) + " "
                        + results.getObject(MFF_MFF_PATH));
            }
            CloseCon();
        } catch (SQLException e) {
            CloseCon();
            e.getMessage();
        }
    }

    public void ShowDestinationFolders() {

        try {
            OpenCon();
            SELECT_SQL = SELECT_DESTINATION_FOLDER;
            ResultSet results = statement.executeQuery(SELECT_SQL);
            while (results.next()) {
                System.out.println(results.getObject(DF_DEST_FOLD_ID) + " " + results.getObject(DF_DEST_FOLD_NAME) + " "
                        + results.getObject(DF_DEST_FOLD_PATH));
            }
            CloseCon();
        } catch (SQLException e) {
            CloseCon();
            e.getMessage();
        }
    }

    public void GetAllMovingFolderIds() {

        try {
            OpenCon();
            SELECT_SQL = SELECT_ALL_MOVING_FOLDER_IDS;
            ResultSet results = statement.executeQuery(SELECT_SQL);
            while (results.next()) {
                System.out.println(results.getObject(MFF_MFF_ID) + " " + results.getObject(MFF_MFF_PATH));
            }
            CloseCon();
        } catch (SQLException e) {
            CloseCon();
            e.getMessage();
        }

    }

    public void GetAllDestinationFolderIds() {

        try {
            OpenCon();
            SELECT_SQL = SELECT_ALL_DESTINATION_FOLDER_IDS;
            ResultSet results = statement.executeQuery(SELECT_SQL);
            while (results.next()) {
                System.out.println(results.getObject(DF_DEST_FOLD_ID) + " " + results.getObject(DF_DEST_FOLD_PATH));
            }
            CloseCon();
        } catch (SQLException e) {
            CloseCon();
            e.getMessage();
        }

    }

    public void MoveFiles() {
        String currentDestFolderPath = null;
        String currentMoveFromFolder = null;
        int mff_id = 0;
        int dest_fold_id = 0;
        int fileAmount = 0;
        ArrayList<String> extensionList = new ArrayList<>();
        ArrayList<String> destFolderPaths = new ArrayList<>();
        ArrayList<Integer> destFolderIds = new ArrayList<>();
        ArrayList<String> movingFilePaths = new ArrayList<>();
        ArrayList<Integer> movingFilePathIds = new ArrayList<>();
        int recordAmount;
        int loopCounter = 0;
        ResultSet extensions;

        try {

            ResultSet results = GetConnectedFolders();
            while (results.next()) {
                // Powerpoint, word etc
                destFolderPaths.add((String) results.getObject(DF_DEST_FOLD_PATH));
                movingFilePaths.add((String) results.getObject(MFF_MFF_PATH));
                destFolderIds.add((Integer) results.getObject(DF_DEST_FOLD_ID));
                movingFilePathIds.add((Integer) results.getObject(MFF_MFF_ID));
            }

            if (isArrayListSizeAboveZero(destFolderPaths)) {

            } else {
                System.out.println(
                        "There are no destination folders set yet, please go to the menu and add folders you want to move files to.");
            }

            if (isArrayListSizeAboveZero(movingFilePaths)) {

            } else {
                System.out.println(
                        "There are no moving file folders set yet, please go to the menu and add folders you want to move files from (desktop, download folder).");
            }

            recordAmount = destFolderIds.size();

            while (recordAmount > loopCounter) {

                currentDestFolderPath = "";
                currentMoveFromFolder = "";
                extensions = GetConnectedFolderExtensions(destFolderIds.get(loopCounter));
                while (extensions.next()) {
                    extensionList.add((String) extensions.getObject(FE_FILE_EXT_EXT));
                }

                currentDestFolderPath = destFolderPaths.get(loopCounter);
                currentMoveFromFolder = movingFilePaths.get(loopCounter);
                assert currentDestFolderPath != null;
                File folder = new File(currentMoveFromFolder);
                String[] files = folder.list();
                assert files != null;

                for (String file : files) {
                    for (String extension : extensionList) {
                        if (file.contains(extension)) {
                            Files.move(Paths.get(currentMoveFromFolder + file),
                                    Paths.get(currentDestFolderPath + file));
                            fileAmount++;
                            System.out.println(file + " -> " + currentDestFolderPath);
                        }
                    }
                }
                extensionList.clear();
                loopCounter++;
            }

        } catch (NullPointerException | IOException | SQLException ex) {
            CloseCon();
            System.out.println(ex);
        }
    }

    public ResultSet GetConnectedFolders() throws SQLException {
        OpenCon();
        SELECT_SQL = SELECT_CONNECTED_FOLDERS;
        return statement.executeQuery(SELECT_SQL);
    }

    public ResultSet GetConnectedFolderExtensions(int id) throws SQLException {
        OpenCon();
        SELECT_SQL = SELECT_CONNECTED_FOLDER_EXTENSIONS + id;

        return statement.executeQuery(SELECT_SQL);
    }

    private boolean isArrayListSizeAboveZero(ArrayList<String> arrayList) {

        return arrayList.size() > 0 && arrayList != null;
    }

    public boolean AddMovingFilesFolder(String folder, String path) {

        try {
            OpenCon();
            INSERT_SQL = INSERT_INTO_MOVING_FILES_FOLDER + LEFT_BRACKET + SINGLE_QUOTATION_MARK + folder
                    + SINGLE_QUOTATION_MARK + SEPARATOR + SPACE + SINGLE_QUOTATION_MARK + path + SINGLE_QUOTATION_MARK
                    + RIGHT_BRACKET;
            statement = conn.createStatement();
            statement.execute(INSERT_SQL);
            CloseCon();
            return true;
        } catch (SQLException e) {
            CloseCon();
            e.getMessage();
            return false;
        }
    }

    public boolean UpdateMovingFilesFolder(String folder, String path, int id) {

        try {
            OpenCon();
            UPDATE_SQL = UPDATE_MOVING_FILES_FOLDER + MFF_MFF_NAME + EQUALS + SINGLE_QUOTATION_MARK + folder
                    + SINGLE_QUOTATION_MARK + SEPARATOR + SPACE + MFF_MFF_PATH + EQUALS + SINGLE_QUOTATION_MARK + path
                    + SINGLE_QUOTATION_MARK + WHERE + MFF_MFF_ID + EQUALS + id;
            statement = conn.createStatement();
            statement.execute(UPDATE_SQL);
            CloseCon();
            return true;
        } catch (SQLException e) {
            CloseCon();
            e.getMessage();
            return false;
        }
    }

    public boolean DeleteMovingFilesFolder(int id) {

        try {
            OpenCon();
            String sql = "DELETE FROM moving_files_folder WHERE mff_id = " + id + " ";
            statement = conn.createStatement();
            statement.execute(sql);
            CloseCon();
            return true;
        } catch (SQLException e) {
            CloseCon();
            e.getMessage();
            return false;
        }
    }

    public boolean AddDestinationFolder(String folder, String path) {

        try {
            OpenCon();
            String sql = "INSERT INTO destination_folder (dest_fold_name, dest_fold_path)  VALUES  ('" + folder + "','"
                    + path + "')";
            statement = conn.createStatement();
            statement.execute(sql);
            CloseCon();
            return true;
        } catch (SQLException e) {
            CloseCon();
            e.getMessage();
            return false;
        }
    }

    public boolean UpdateDestinationFolder(String folder, String path, int id) {

        try {
            OpenCon();
            String sql = "UPDATE destination_folder SET dest_fold_name = '" + folder + "', dest_fold_path = '" + path
                    + "' WHERE dest_fold_id = " + id + " ";
            statement = conn.createStatement();
            statement.execute(sql);
            CloseCon();
            return true;
        } catch (SQLException e) {
            CloseCon();
            e.getMessage();
            return false;
        }
    }

    public boolean DeleteDestinationFolder(int id) {

        try {
            OpenCon();
            String sql = "DELETE FROM destination_folder WHERE dest_fold_id = " + id + " ";
            statement = conn.createStatement();
            statement.execute(sql);
            CloseCon();
            return true;
        } catch (SQLException e) {
            CloseCon();
            e.getMessage();
            return false;
        }
    }

    public boolean ConnectFolders(int mff_id, int dest_folder_id) {

        try {
            OpenCon();
            String sql = "INSERT INTO file_moving (dest_folder_id, mff_id) VALUES (" + dest_folder_id + " , " + mff_id
                    + ")";
            statement = conn.createStatement();
            statement.execute(sql);
            CloseCon();
            return true;
        } catch (SQLException e) {
            CloseCon();
            e.getMessage();
            return false;
        }
    }

    public boolean ConnectExtentionToFolder(int dest_fold_id, String extension) {

        try {
            OpenCon();
            String sql = "INSERT INTO file_extensions (file_ext_ext, dest_folder_id) VALUES ('" + extension + "'  ,"
                    + dest_fold_id + ")";
            statement = conn.createStatement();
            statement.execute(sql);
            CloseCon();
            return true;
        } catch (SQLException e) {
            CloseCon();
            e.getMessage();
            return false;
        }
    }

    public void CloseCon() {
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
