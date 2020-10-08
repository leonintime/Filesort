import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {


        Filesort sortWordFilesDownload = new Filesort(System.getenv("Download_folder"), System.getenv("Download_folder"), System.getenv("Word_files"));
        Filesort sortExcelFilesDownload = new Filesort(System.getenv("Download_folder"), System.getenv("Download_folder"), System.getenv("Excel_files"));
        Filesort sortPdfFilesDownload = new Filesort(System.getenv("Download_folder"), System.getenv("Download_folder"), System.getenv("Pdf_files"));
        Filesort sortPicturesDownload = new Filesort(System.getenv("Download_folder"), System.getenv("Download_folder"), System.getenv("Pictures"));


        Filesort sortWordFilesDesktop = new Filesort(System.getenv("Desktop"), System.getenv("Desktop"), System.getenv("Word_files"));
        Filesort sortExcelFilesDesktop = new Filesort(System.getenv("Desktop"), System.getenv("Desktop"), System.getenv("Excel_files"));
        Filesort sortPdfFilesDesktop = new Filesort(System.getenv("Desktop"), System.getenv("Desktop"), System.getenv("Pdf_files"));
        Filesort sortPicturesDesktop = new Filesort(System.getenv("Desktop"), System.getenv("Desktop"), System.getenv("Pictures"));


        // Files in download folder are getting moved to the destination folder
        sortWordFilesDownload.sortFiles();
        sortExcelFilesDownload.sortFiles();
        sortPdfFilesDownload.sortFiles();
        sortPicturesDownload.sortFiles();

        // Files on the desktop folder are getting moved to the destination folder
        sortWordFilesDesktop.sortFiles();
        sortExcelFilesDesktop.sortFiles();
        sortPdfFilesDesktop.sortFiles();
        sortPicturesDesktop.sortFiles();


    }


}
