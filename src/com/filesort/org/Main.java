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
    private static final String[] OPTIONS = new String[]{"Sort files", "Show paths", "Add path", "Update path ", "Delete path", "End program"};
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
        Filesort sortExcelFilesDownload = new Filesort(MOVE_FOLDER_ONE, MOVE_FOLDER_ONE, EXCEL_FILES, EXCEL_EXTENSIONS);
        Filesort sortPdfFilesDownload = new Filesort(MOVE_FOLDER_ONE, MOVE_FOLDER_ONE, PDF_FILES, PDF_EXTENSIONS);
        Filesort sortPicturesDownload = new Filesort(MOVE_FOLDER_ONE, MOVE_FOLDER_ONE, PICTURE_FILES, PICTURE_EXTENSIONS);
        Filesort sortPowerpointsDownload = new Filesort(MOVE_FOLDER_ONE, MOVE_FOLDER_ONE, POWERPOINT_FILES, POWERPOINT_EXTENSIONS);

        // Objects for the desktop sorting get created
        Filesort sortWordFilesDesktop = new Filesort(MOVE_FOLDER_2, MOVE_FOLDER_2, WORD_FILES, WORD_EXTENSIONS);
        Filesort sortExcelFilesDesktop = new Filesort(MOVE_FOLDER_2, MOVE_FOLDER_2, EXCEL_FILES, EXCEL_EXTENSIONS);
        Filesort sortPdfFilesDesktop = new Filesort(MOVE_FOLDER_2, MOVE_FOLDER_2, PDF_FILES, PDF_EXTENSIONS);
        Filesort sortPicturesDesktop = new Filesort(MOVE_FOLDER_2, MOVE_FOLDER_2, PICTURE_FILES, PICTURE_EXTENSIONS);
        Filesort sortPowerpointsDesktop = new Filesort(MOVE_FOLDER_2, MOVE_FOLDER_2, POWERPOINT_FILES, POWERPOINT_EXTENSIONS);


        // Files in download folder are getting moved to the destination folder
        int wordFilesCountedDownload = sortWordFilesDownload.moveFiles();
        int excelFilesCountedDownload = sortExcelFilesDownload.moveFiles();
        int pdfFilesCountedDownload = sortPdfFilesDownload.moveFiles();
        int pictureFilesCountedDownload = sortPicturesDownload.moveFiles();
        int powerpointFilesCountedDownload = sortPowerpointsDownload.moveFiles();

        // Files on the desktop folder are getting moved to the destination folder
        int wordFilesCountedDesktop = sortWordFilesDesktop.moveFiles();
        int excelFilesCountedDesktop = sortExcelFilesDesktop.moveFiles();
        int pdfFilesCountedDesktop = sortPdfFilesDesktop.moveFiles();
        int picturesFilesCountedDesktop = sortPicturesDesktop.moveFiles();
        int powerpointsFilesCountedDesktop = sortPowerpointsDesktop.moveFiles();

        // Desktop and download files are added together to calculate the total amount
        int totalWordFilesMoved = sortWordFilesDownload.calcTotalFilesMoved(wordFilesCountedDownload, wordFilesCountedDesktop);
        int totalExcelFilesMoved = sortExcelFilesDownload.calcTotalFilesMoved(excelFilesCountedDownload, excelFilesCountedDesktop);
        int totalPdfFilesMoved = sortPdfFilesDownload.calcTotalFilesMoved(pdfFilesCountedDownload, pdfFilesCountedDesktop);
        int totalPictureFilesMoved = sortPicturesDownload.calcTotalFilesMoved(pictureFilesCountedDownload, picturesFilesCountedDesktop);
        int totalPowerpointFilesMoved = sortPowerpointsDownload.calcTotalFilesMoved(powerpointFilesCountedDownload, powerpointsFilesCountedDesktop);


        sortWordFilesDesktop.checkFilesMoved(wordFilesCountedDownload, wordFilesCountedDesktop, "word", totalWordFilesMoved);
        sortExcelFilesDesktop.checkFilesMoved(excelFilesCountedDownload, excelFilesCountedDesktop, "excel", totalExcelFilesMoved);
        sortPdfFilesDesktop.checkFilesMoved(pdfFilesCountedDownload, pdfFilesCountedDesktop, "pdf", totalPdfFilesMoved);
        sortPicturesDesktop.checkFilesMoved(pictureFilesCountedDownload, picturesFilesCountedDesktop, "pictures", totalPictureFilesMoved);
        sortPowerpointsDesktop.checkFilesMoved(powerpointFilesCountedDownload, powerpointsFilesCountedDesktop, "powerpoints", totalPowerpointFilesMoved);
    }


}