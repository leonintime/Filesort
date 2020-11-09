
import java.io.IOException;

public class Main {

    private final static String downloadFolder = System.getenv("Download_folder");
    private final static String desktop = System.getenv("Desktop");
    private final static String wordFiles = System.getenv("Word_files");
    private final static String excelFiles = System.getenv("Excel_files");
    private final static String pdfFiles = System.getenv("Pdf_files");
    private final static String pictures = System.getenv("Pictures");

    public static void main(String[] args) {

        System.out.println("Moving files...");


        // Objects for the download sorting get created
        Filesort sortWordFilesDownload = new Filesort(downloadFolder, downloadFolder, wordFiles);
        Filesort sortExcelFilesDownload = new Filesort(downloadFolder, downloadFolder, excelFiles);
        Filesort sortPdfFilesDownload = new Filesort(downloadFolder, downloadFolder, pdfFiles);
        Filesort sortPicturesDownload = new Filesort(downloadFolder, downloadFolder, pictures);

        // Objects for the desktop sorting get created
        Filesort sortWordFilesDesktop = new Filesort(desktop, desktop, wordFiles);
        Filesort sortExcelFilesDesktop = new Filesort(desktop, desktop, excelFiles);
        Filesort sortPdfFilesDesktop = new Filesort(desktop, desktop, pdfFiles);
        Filesort sortPicturesDesktop = new Filesort(desktop, desktop, pictures);


        // Files in download folder are getting moved to the destination folder
        int wordFilesCountedDownload = sortWordFilesDownload.sortFilesWord();
        int excelFilesCountedDownload = sortExcelFilesDownload.sortFilesExcel();
        int pdfFilesCountedDownload = sortPdfFilesDownload.sortFilesPdfs();
        int pictureFilesCountedDownload = sortPicturesDownload.sortFilesPictures();

        // Files on the desktop folder are getting moved to the destination folder
        int wordFilesCountedDesktop = sortWordFilesDesktop.sortFilesWord();
        int excelFilesCountedDesktop = sortExcelFilesDesktop.sortFilesExcel();
        int pdfFilesCountedDesktop = sortPdfFilesDesktop.sortFilesPdfs();
        int picturesFilesCountedDesktop = sortPicturesDesktop.sortFilesPictures();

        // Desktop and download files are added together to calculate the total amount
        int totalWordFilesMoved = sortWordFilesDownload.calcTotalFilesMoved(wordFilesCountedDownload, wordFilesCountedDesktop);
        int totalExcelFilesMoved = sortWordFilesDownload.calcTotalFilesMoved(excelFilesCountedDownload, excelFilesCountedDesktop);
        int totalPdfFilesMoved = sortWordFilesDownload.calcTotalFilesMoved(pdfFilesCountedDownload, pdfFilesCountedDesktop);
        int totalPictureFilesMoved = sortWordFilesDownload.calcTotalFilesMoved(pictureFilesCountedDownload, picturesFilesCountedDesktop);


        // Files moved from the download folder
        System.out.println("\n");
        System.out.println("/////Word files/////");
        if (wordFilesCountedDownload <= 0 && wordFilesCountedDesktop <= 0) {
            System.out.println("No movable word files were found.\n");
        } else {
            System.out.println(wordFilesCountedDownload + " word files got moved from " + downloadFolder);
            System.out.println(wordFilesCountedDesktop + " word files got moved from " + desktop);
            System.out.println("Total word files that got moved: " + totalWordFilesMoved + "\n");
        }
        System.out.println("/////Excel files/////");
        if (excelFilesCountedDownload <= 0 && excelFilesCountedDesktop <= 0) {
            System.out.println("No movable excel files were found.\n");
        } else {
            System.out.println(excelFilesCountedDownload + " excel files got moved from " + downloadFolder);
            System.out.println(excelFilesCountedDesktop + " excel files got moved from " + desktop);
            System.out.println("Total excel files that got moved: " + totalExcelFilesMoved + "\n");
        }

        System.out.println("/////Pdf files/////");
        if (pdfFilesCountedDownload <= 0 && pdfFilesCountedDesktop <= 0) {
            System.out.println("No movable pdf files were found.\n");
        } else {
            System.out.println(pdfFilesCountedDownload + " pdf files got moved from " + downloadFolder);
            System.out.println(pdfFilesCountedDesktop + " pdf files got moved from " + desktop);
            System.out.println("Total pdf files that got moved: " + totalPdfFilesMoved + " \n");
        }
        System.out.println("///// Pictures /////");
        if (pictureFilesCountedDownload <= 0 && picturesFilesCountedDesktop <= 0) {
            System.out.println("No movable pictures were found.");
        } else {
            System.out.println(pictureFilesCountedDownload + " pictures got moved from " + downloadFolder);
            System.out.println(picturesFilesCountedDesktop + " pictures got moved from " + desktop);
            System.out.println("Total pdf files that got moved: " + totalPictureFilesMoved);
        }


    }


}
