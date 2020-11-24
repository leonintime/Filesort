package com.filesort.org;

import java.sql.SQLException;

public class Main {

    private static final String dbCon = "jdbc:sqlite:C:\\Users\\leond\\Desktop\\Filesort\\folders.db";
    private final static String moveFolderOne = System.getenv("MoveFolder1");
    private final static String moveFolderTwo = System.getenv("MoveFolder2");
    private final static String wordFiles = System.getenv("Word_files");
    private final static String excelFiles = System.getenv("Excel_files");
    private final static String pdfFiles = System.getenv("Pdf_files");
    private final static String pictureFiles = System.getenv("Pictures");
    private final static String powerpointFiles = System.getenv("Powerpoints");
    private static final String[] wordExtensions = new String[]{".docx", ".dotx", "docm", ".dotm", ".docb"};
    private static final String[] excelExtensions = new String[]{".xlsx", ".xlsm", ".xltx", ".xltm "};
    private static final String[] pdfExtensions = new String[]{".pdf"};
    private static final String[] powerpointExtensions = new String[]{".pptx"};
    private static final String[] pictureExtensions = new String[]{".jpg", ".jpeg", ".gif", ".png", ".PNG"};


    public static void main(String[] args) {

        try {

            Database db = new Database(dbCon);
            db.showPaths();
        } catch (SQLException e) {

            System.out.println("Something went wrong: " + e.getMessage());
        }


        System.out.println("Moving files...");

        // Objects for the download sorting get created
        Filesort sortWordFilesDownload = new Filesort(moveFolderOne, moveFolderOne, wordFiles, wordExtensions);
        Filesort sortExcelFilesDownload = new Filesort(moveFolderOne, moveFolderOne, excelFiles, excelExtensions);
        Filesort sortPdfFilesDownload = new Filesort(moveFolderOne, moveFolderOne, pdfFiles, pdfExtensions);
        Filesort sortPicturesDownload = new Filesort(moveFolderOne, moveFolderOne, pictureFiles, pictureExtensions);
        Filesort sortPowerpointsDownload = new Filesort(moveFolderOne, moveFolderOne, powerpointFiles, powerpointExtensions);

        // Objects for the desktop sorting get created
        Filesort sortWordFilesDesktop = new Filesort(moveFolderTwo, moveFolderTwo, wordFiles, wordExtensions);
        Filesort sortExcelFilesDesktop = new Filesort(moveFolderTwo, moveFolderTwo, excelFiles, excelExtensions);
        Filesort sortPdfFilesDesktop = new Filesort(moveFolderTwo, moveFolderTwo, pdfFiles, pdfExtensions);
        Filesort sortPicturesDesktop = new Filesort(moveFolderTwo, moveFolderTwo, pictureFiles, pictureExtensions);
        Filesort sortPowerpointsDesktop = new Filesort(moveFolderTwo, moveFolderTwo, powerpointFiles, powerpointExtensions);


        // Files in download folder are getting moved to the destination folder
        int wordFilesCountedDownload = sortWordFilesDownload.sortFiles();
        int excelFilesCountedDownload = sortExcelFilesDownload.sortFiles();
        int pdfFilesCountedDownload = sortPdfFilesDownload.sortFiles();
        int pictureFilesCountedDownload = sortPicturesDownload.sortFiles();
        int powerpointFilesCountedDownload = sortPowerpointsDownload.sortFiles();

        // Files on the desktop folder are getting moved to the destination folder
        int wordFilesCountedDesktop = sortWordFilesDesktop.sortFiles();
        int excelFilesCountedDesktop = sortExcelFilesDesktop.sortFiles();
        int pdfFilesCountedDesktop = sortPdfFilesDesktop.sortFiles();
        int picturesFilesCountedDesktop = sortPicturesDesktop.sortFiles();
        int powerpointsFilesCountedDesktop = sortPowerpointsDesktop.sortFiles();

        // Desktop and download files are added together to calculate the total amount
        int totalWordFilesMoved = sortWordFilesDownload.calcTotalFilesMoved(wordFilesCountedDownload, wordFilesCountedDesktop);
        int totalExcelFilesMoved = sortExcelFilesDownload.calcTotalFilesMoved(excelFilesCountedDownload, excelFilesCountedDesktop);
        int totalPdfFilesMoved = sortPdfFilesDownload.calcTotalFilesMoved(pdfFilesCountedDownload, pdfFilesCountedDesktop);
        int totalPictureFilesMoved = sortPicturesDownload.calcTotalFilesMoved(pictureFilesCountedDownload, picturesFilesCountedDesktop);
        int totalPowerpointFilesMoved = sortPowerpointsDownload.calcTotalFilesMoved(powerpointFilesCountedDownload, powerpointsFilesCountedDesktop);

        System.out.println("\n");


        sortWordFilesDesktop.checkFilesMoved(wordFilesCountedDownload, wordFilesCountedDesktop, "word", totalWordFilesMoved);
        sortExcelFilesDesktop.checkFilesMoved(excelFilesCountedDownload, excelFilesCountedDesktop, "excel", totalExcelFilesMoved);
        sortPdfFilesDesktop.checkFilesMoved(pdfFilesCountedDownload, pdfFilesCountedDesktop, "pdf", totalPdfFilesMoved);
        sortPicturesDesktop.checkFilesMoved(pictureFilesCountedDownload, picturesFilesCountedDesktop, "pictures", totalPictureFilesMoved);
        sortPowerpointsDesktop.checkFilesMoved(powerpointFilesCountedDownload, powerpointsFilesCountedDesktop, "powerpoints", totalPowerpointFilesMoved);


    }


}
