import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Filesort {

    private int picAmount;
    private int wordFileAmount;
    private int excelFileAmount;
    private int pdfAmount;

    private String fileFolder;
    private String currentPath;
    private String destinationPath;


    public Filesort(String fileFolder, String currentPath, String destinationPath) {
        this.fileFolder = fileFolder;
        this.currentPath = currentPath;
        this.destinationPath = destinationPath;
        this.picAmount = 0;
        this.wordFileAmount = 0;
        this.excelFileAmount = 0;
        this.pdfAmount = 0;
    }

    // Sorts the word files
    public int sortFilesWord() {
        try {
            File folder = new File(fileFolder);
            File[] listedFiles = folder.listFiles();
            for (File file : listedFiles) {
                String fileName = file.getName();
                // Word Dokumente werden sortiert
                if (fileName.contains(".docx")) {
                    Path temp = Files.move(Paths.get(currentPath + fileName),
                            Paths.get(destinationPath + fileName));
                    wordFileAmount++;
                    System.out.println(fileName + " got successfully moved");
                }
            }
            return wordFileAmount;

        } catch (NullPointerException | IOException ex) {
            System.out.println("There was an error while sorting the word files!");
            return -1;
        }
    }

    // Sorts the excel files
    public int sortFilesExcel() {
        try {
            File folder = new File(fileFolder);
            File[] listedFiles = folder.listFiles();
            for (File file : listedFiles) {
                String fileName = file.getName();

                // Excel Dateien werden sortiert
                if (fileName.contains(".xlsx")) {
                    Path temp = Files.move(Paths.get(currentPath + fileName),
                            Paths.get(destinationPath + fileName));
                    excelFileAmount++;
                    System.out.println(fileName + " got successfully moved");
                }
            }

        } catch (NullPointerException | IOException ex) {
            System.out.println("There was an error while sorting the excel files!");
            return -1;
        }
        return excelFileAmount;
    }

    // Sorts the word files
    public int sortFilesPdfs() {
        try {
            File folder = new File(fileFolder);
            File[] listedFiles = folder.listFiles();
            for (File file : listedFiles) {
                String fileName = file.getName();

                if (fileName.contains(".pdf")) {
                    Path temp = Files.move(Paths.get(currentPath + fileName),
                            Paths.get(destinationPath + fileName));
                    pdfAmount++;
                    System.out.println(fileName + " got successfully moved");
                }
            }

        } catch (NullPointerException | IOException ex) {
            System.out.println("There was an error while sorting the pdf files!");
            return -1;
        }
        return pdfAmount;
    }

    // Sorts the word pictures
    public int sortFilesPictures() {
        try {
            File folder = new File(fileFolder);
            File[] listedFiles = folder.listFiles();
            for (File file : listedFiles) {
                String fileName = file.getName();

                if (fileName.contains(".jpg") || fileName.contains(".jpeg") || fileName.contains(".gif") || fileName.contains(".png")) {
                    Path temp = Files.move(Paths.get(currentPath + fileName),
                            Paths.get(destinationPath + fileName));
                    picAmount++;
                    System.out.println(fileName + " got successfully moved");

                }
            }

        } catch (NullPointerException | IOException ex) {
            System.out.println("There was an error while sorting the pictures!");
            return -1;
        }
        return picAmount;
    }

    // Sums the found files from the download and the desktop
    public int calcTotalFilesMoved(int filesDownload, int filesDesktop) {
        return filesDesktop + filesDownload;
    }


}
