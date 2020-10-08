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


    public void sortFiles() {
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
                    if (temp != null) {
                        System.out.println(fileName + "got successfully moved");
                    } else {
                        System.out.println("Failed to move the file");
                    }
                }

                // Excel Dateien werden sortiert
                else if (fileName.contains(".xlsx")) {
                    Path temp = Files.move(Paths.get(currentPath + fileName),
                            Paths.get(destinationPath + fileName));
                    excelFileAmount++;
                    if (temp != null) {
                        System.out.println(fileName + "got successfully moved");
                    } else {
                        System.out.println("Failed to move the file");
                    }
                }


                // Bild Dateien werden sortiert
                else if (fileName.contains(".jpg") || fileName.contains(".jpeg") || fileName.contains(".gif") || fileName.contains(".png")) {
                    Path temp = Files.move(Paths.get(currentPath + fileName),
                            Paths.get(destinationPath + fileName));
                    picAmount++;
                    if (temp != null) {
                        System.out.println(fileName + "got successfully moved");
                    } else {
                        System.out.println("Failed to move the file");
                    }

                    // Pdf Dateien werden sortiert
                } else if (fileName.contains(".pdf")) {
                    Path temp = Files.move(Paths.get(currentPath + fileName),
                            Paths.get(destinationPath + fileName));
                    pdfAmount++;
                    if (temp != null) {
                        System.out.println(fileName + "got successfully moved");
                    } else {
                        System.out.println("Failed to move the file");
                    }
                }

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }







}
