import java.io.File;
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


    public int sortFiles() {
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
                    System.out.println(fileName + "got successfully moved");
                }

                // Excel Dateien werden sortiert
                else if (fileName.contains(".xlsx")) {
                    Path temp = Files.move(Paths.get(currentPath + fileName),
                            Paths.get(destinationPath + fileName));
                    excelFileAmount++;
                    System.out.println(fileName + "got successfully moved");
                }


                // Bild Dateien werden sortiert
                else if (fileName.contains(".jpg") || fileName.contains(".jpeg") || fileName.contains(".gif") || fileName.contains(".png")) {
                    Path temp = Files.move(Paths.get(currentPath + fileName),
                            Paths.get(destinationPath + fileName));
                    picAmount++;
                    System.out.println(fileName + "got successfully moved");

                    // Pdf Dateien werden sortiert
                } else if (fileName.contains(".pdf")) {
                    Path temp = Files.move(Paths.get(currentPath + fileName),
                            Paths.get(destinationPath + fileName));
                    pdfAmount++;
                    System.out.println(fileName + "got successfully moved");
                }
            }
            if (wordFileAmount > 0) {
                return wordFileAmount;
            } else if (excelFileAmount > 0) {
                return excelFileAmount;
            } else if (pdfAmount > 0) {
                return pdfAmount;
            } else if (picAmount > 0) {
                return picAmount;
            } else {
                return 0;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }

    public int calcTotalFilesMoved(int filesDownload, int filesDesktop) {
        return filesDesktop + filesDownload;
    }


}
