import org.w3c.dom.ls.LSOutput;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {


        String downloadFolder = System.getenv("Download_folder");
        String desktop = System.getenv("Desktop");
        String wordFiles = System.getenv("Word_files");
        String excelFiles = System.getenv("Excel_files");
        String pdfFiles = System.getenv("Pdf_files");
        String pictures = System.getenv("Pictures");


        Filesort sortWordFilesDownload = new Filesort(downloadFolder, downloadFolder, wordFiles);
        Filesort sortExcelFilesDownload = new Filesort(downloadFolder, downloadFolder, excelFiles);
        Filesort sortPdfFilesDownload = new Filesort(downloadFolder, downloadFolder, pdfFiles);
        Filesort sortPicturesDownload = new Filesort(downloadFolder, downloadFolder, pictures);


        Filesort sortWordFilesDesktop = new Filesort(desktop, desktop, wordFiles);
        Filesort sortExcelFilesDesktop = new Filesort(desktop, desktop, excelFiles);
        Filesort sortPdfFilesDesktop = new Filesort(desktop, desktop, pdfFiles);
        Filesort sortPicturesDesktop = new Filesort(desktop, desktop, pictures);


        // Files in download folder are getting moved to the destination folder
        int wordFilesCountedDownload = sortWordFilesDownload.sortFiles();
        int excelFilesCountedDownload = sortExcelFilesDownload.sortFiles();
        int pdfFilesCountedDownload = sortPdfFilesDownload.sortFiles();
        int pictureFilesCountedDownload = sortPicturesDownload.sortFiles();

        // Files on the desktop folder are getting moved to the destination folder
        int wordFilesCountedDesktop = sortWordFilesDesktop.sortFiles();
        int excelFilesCountedDesktop = sortExcelFilesDesktop.sortFiles();
        int pdfFilesCountedDesktop = sortPdfFilesDesktop.sortFiles();
        int picturesFilesCountedDesktop = sortPicturesDesktop.sortFiles();

        // Desktop and download files are added together to calculate the total amount
        int totalWordFilesMoved = sortWordFilesDownload.calcTotalFilesMoved(wordFilesCountedDownload, wordFilesCountedDesktop);
        int totalExcelFilesMoved = sortWordFilesDownload.calcTotalFilesMoved(excelFilesCountedDownload, excelFilesCountedDesktop);
        int totalPdfFilesMoved = sortWordFilesDownload.calcTotalFilesMoved(pdfFilesCountedDownload, pdfFilesCountedDesktop);
        int totalPictureFilesMoved = sortWordFilesDownload.calcTotalFilesMoved(pictureFilesCountedDownload, picturesFilesCountedDesktop);


        // Files moved from the download folder
        System.out.println("\n");
        System.out.println("/////Download files/////");
        System.out.println(wordFilesCountedDownload + " word files got moved from " + downloadFolder);
        System.out.println(excelFilesCountedDownload + " excel files got moved from " + downloadFolder);
        System.out.println(pdfFilesCountedDownload + " pdf files got moved from " + downloadFolder);
        System.out.println(pictureFilesCountedDownload + " pictures got moved from " + downloadFolder);
        System.out.println("\n");


        // Files moved from the desktop
        System.out.println("/////Desktop files/////");
        System.out.println(wordFilesCountedDownload + " word files got moved from " + desktop);
        System.out.println(excelFilesCountedDownload + " excel files got moved from " + desktop);
        System.out.println(pdfFilesCountedDownload + " pdf files got moved from " + desktop);
        System.out.println(pictureFilesCountedDownload + " pictures got moved from " + desktop);
        System.out.println("\n");

        System.out.println("/////Total files /////");
        System.out.println(totalWordFilesMoved + " word files got moved");
        System.out.println(totalExcelFilesMoved + " excel files got moved");
        System.out.println(totalPdfFilesMoved + " pdf files got moved");
        System.out.println(totalPictureFilesMoved + " pictures got moved");


    }


}
