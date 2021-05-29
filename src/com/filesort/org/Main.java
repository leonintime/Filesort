package com.filesort.org;

import java.sql.SQLException;

public class Main {

    private final static String MOVE_FOLDER_ONE = System.getenv("MoveFolder1");
    private final static String MOVE_FOLDER_2 = System.getenv("MoveFolder2");
    private final static String WORD_FILES = System.getenv("Word_files");
    private final static String EXCEL_FILES = System.getenv("Excel_files");
    private final static String PDF_FILES = System.getenv("Pdf_files");
    private final static String PICTURE_FILES = System.getenv("Pictures");
    private final static String POWERPOINT_FILES = System.getenv("Powerpoints");
    private static final String[] WORD_EXTENSIONS = new String[]{".docx", ".dotx", "docm", ".dotm", ".docb"};
    private static final String[] EXCEL_EXTENSIONS = new String[]{".xlsx", ".xlsm", ".xltx", ".xltm "};
    private static final String[] PDF_EXTENSIONS = new String[]{".pdf"};
    private static final String[] POWERPOINT_EXTENSIONS = new String[]{".pptx"};
    private static final String[] PICTURE_EXTENSIONS = new String[]{".jpg", ".jpeg", ".gif", ".png", ".PNG"};
    private static final String[] OPTIONS = new String[]{"Sort files", "Show paths", "Add path", "Update path ",
            "Delete path", "Connect moving files folder with destination folder", "End program"};
    public static final String DB_CON = "jdbc:sqlite:db\\folders.db";
    public static boolean endProgram = false;
    private static int countRunAmount = 0;


    public Main() {

    }

    public static void main(String[] args) {

        while (!endProgram) {

            if (countRunAmount < 1) {
                System.out.println("Searching for files...");
                try {
                    sortFiles();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                countRunAmount++;
            } else {
                try {
                    Filesort.showOptions(OPTIONS);
                    Filesort.checkedOption();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public static void sortFiles() throws SQLException {
        // Objects for the download sorting get created
        Filesort sortWordFilesDownload = new Filesort(MOVE_FOLDER_ONE, MOVE_FOLDER_ONE, WORD_FILES, WORD_EXTENSIONS);


        // Objects for the desktop sorting get created
        Filesort sortWordFilesDesktop = new Filesort(MOVE_FOLDER_2, MOVE_FOLDER_2, WORD_FILES, WORD_EXTENSIONS);


        // Files in download folder are getting moved to the destination folder
        int wordFilesCountedDownload = Filesort.db.moveFiles();


        // Files on the desktop folder are getting moved to the destination folder
        int wordFilesCountedDesktop = Filesort.db.moveFiles();


        // Desktop and download files are added together to calculate the total amount
        int totalWordFilesMoved = sortWordFilesDownload.calcTotalFilesMoved(wordFilesCountedDownload,
                wordFilesCountedDesktop);


//        sortWordFilesDesktop.checkFilesMoved(wordFilesCountedDownload, wordFilesCountedDesktop, "word",
//                totalWordFilesMoved);
    }

}
