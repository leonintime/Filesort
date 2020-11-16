public class Main {

    private final static String downloadFolder = System.getenv("Download_folder");
    private final static String desktop = System.getenv("Desktop");
    private final static String wordFiles = System.getenv("Word_files");
    private final static String excelFiles = System.getenv("Excel_files");
    private final static String pdfFiles = System.getenv("Pdf_files");
    private final static String pictureFiles = System.getenv("Pictures");
    private final static String powerpointFiles = System.getenv("Powerpoints");
    private static final String[] wordExtensions = new String[]{".docx", ".dotx", "docm"};
    private static final String[] excelExtensions = new String[]{".xlsx"};
    private static final String[] pdfExtensions = new String[]{".pdf"};
    private static final String[] powerpointExtensions = new String[]{".pptx"};
    private static final String[] pictureExtensions = new String[]{".jpg", ".jpeg", ".gif", ".png", ".PNG"};


    public static void main(String[] args) {

        System.out.println("Moving files...");

        // Objects for the download sorting get created
        Filesort sortWordFilesDownload = new Filesort(downloadFolder, downloadFolder, wordFiles, wordExtensions);
        Filesort sortExcelFilesDownload = new Filesort(downloadFolder, downloadFolder, excelFiles, excelExtensions);
        Filesort sortPdfFilesDownload = new Filesort(downloadFolder, downloadFolder, pdfFiles, pdfExtensions);
        Filesort sortPicturesDownload = new Filesort(downloadFolder, downloadFolder, pictureFiles, pictureExtensions);
        Filesort sortPowerpointsDownload = new Filesort(downloadFolder, downloadFolder, powerpointFiles, powerpointExtensions);

        // Objects for the desktop sorting get created
        Filesort sortWordFilesDesktop = new Filesort(desktop, desktop, wordFiles, wordExtensions);
        Filesort sortExcelFilesDesktop = new Filesort(desktop, desktop, excelFiles, excelExtensions);
        Filesort sortPdfFilesDesktop = new Filesort(desktop, desktop, pdfFiles, pdfExtensions);
        Filesort sortPicturesDesktop = new Filesort(desktop, desktop, pictureFiles, pictureExtensions);
        Filesort sortPowerpointsDesktop = new Filesort(desktop, desktop, powerpointFiles, powerpointExtensions);


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

        // Checks how many files got moved from each folder
        checkFilesMoved(wordFilesCountedDownload, wordFilesCountedDesktop, "word", totalWordFilesMoved);
        checkFilesMoved(excelFilesCountedDownload, excelFilesCountedDesktop, "excel", totalExcelFilesMoved);
        checkFilesMoved(pdfFilesCountedDownload, pdfFilesCountedDesktop, "pdf", totalPdfFilesMoved);
        checkFilesMoved(pictureFilesCountedDownload, picturesFilesCountedDesktop, "pictures", totalPictureFilesMoved);
        checkFilesMoved(powerpointFilesCountedDownload, powerpointsFilesCountedDesktop, "powerpoints", totalPowerpointFilesMoved);
    }



    public static void checkFilesMoved(int filesCountedDownload, int filesCountedDesktop, String fileType, int totalFilesMoved) {

        switch (fileType) {

            case "word":
                System.out.println("/// WORD FILES ///");
                if (filesCountedDownload <= 0 && filesCountedDesktop <= 0) {
                    System.out.println("No movable word file/s found.\n");
                } else {
                    System.out.println(filesCountedDownload + " word file/s got moved from your download folder");
                    System.out.println(filesCountedDesktop + "  word file/s got moved from your desktop");
                    System.out.println("Total word file/s moved: " + totalFilesMoved + "\n");
                }
                break;

            case "excel":
                System.out.println("/// EXCEL FILES ///");
                if (filesCountedDownload <= 0 && filesCountedDesktop <= 0) {
                    System.out.println("No movable excel file/s found.\n");
                } else {
                    System.out.println(filesCountedDownload + " excel file/s got moved from your download folder");
                    System.out.println(filesCountedDesktop + "  excel file/s got moved from your desktop");
                    System.out.println("Total  excel file/s moved: " + totalFilesMoved + "\n");
                }
                break;


            case "pdf":
                System.out.println("/// PDF FILES ///");
                if (filesCountedDownload <= 0 && filesCountedDesktop <= 0) {
                    System.out.println("No movable pdf file/s found.\n");
                } else {
                    System.out.println(filesCountedDownload + " pdf file/s got moved from your download folder");
                    System.out.println(filesCountedDesktop + "  pdf file/s got moved from your desktop");
                    System.out.println("Total pdf files moved: " + totalFilesMoved + "\n");
                }
                break;


            case "pictures":
                System.out.println("/// PICTURES ///");
                if (filesCountedDownload <= 0 && filesCountedDesktop <= 0) {
                    System.out.println("No movable picture/s found.\n");
                } else {
                    System.out.println(filesCountedDownload + " picture/s got moved from your download folder");
                    System.out.println(filesCountedDesktop + "  picture/s got moved from your desktop");
                    System.out.println("Total picture/s moved: " + totalFilesMoved + "\n");
                }
                break;


            case "powerpoints":
                System.out.println("/// POWERPOINT FILES ///");
                if (filesCountedDownload <= 0 && filesCountedDesktop <= 0) {
                    System.out.println("No movable picture/s found.\n");
                } else {
                    System.out.println(filesCountedDownload + " picture/s got moved from your download folder");
                    System.out.println(filesCountedDesktop + "  picture/s got moved from your desktop");
                    System.out.println("Total picture/s moved: " + totalFilesMoved);
                }
                break;

        }


    }


}
