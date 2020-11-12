import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Filesort {

    private String[] extension;
    private int fileAmount;
    private String fileFolder;
    private String currentPath;
    private String destinationPath;


    public Filesort(String fileFolder, String currentPath, String destinationPath, String[] extension) {
        this.fileFolder = fileFolder;
        this.currentPath = currentPath;
        this.destinationPath = destinationPath;
        this.extension = extension;
        this.fileAmount = 0;
    }

    // Checks if the folders got created on the desktop
    public void checkFoldersExist() throws IOException {

        if (Files.exists(Paths.get(destinationPath)) || destinationPath == "Download_folder" ||
                destinationPath == "Desktop") {
            // Nothing

        } else {
            Files.createDirectory(Paths.get(destinationPath));
            System.out.println("Folder " + destinationPath + " got created");
        }

    }


    private void checkEnvironmentVariables() {

        if (destinationPath == null || currentPath == null) {

            if (destinationPath == null) {
                System.out.println("The path for the destined folder needs to be set.");
            } else if (currentPath == null) {
                System.out.println("The path from the folder where the files are supposed to be moved away needs to be set.");
            } else {
                        // Nothing
            }
        }
    }


    // Sorts the files
    public int sortFiles() {
        try {
            checkEnvironmentVariables();
            checkFoldersExist();
            File folder = new File(fileFolder);
            File[] listedFiles = folder.listFiles();
            for (File file : listedFiles) {
                String fileName = file.getName();
                for (int i = 0; i <= extension.length - 1; i++) {
                    if (fileName.contains(extension[i])) {
                        Path temp = Files.move(Paths.get(currentPath + fileName),
                                Paths.get(destinationPath + fileName));
                        fileAmount++;
                        System.out.println(fileName + " got successfully moved");
                    }
                }
            }
            return fileAmount;

        } catch (NullPointerException | IOException ex) {
            System.out.println("There was an error while sorting the files!");
            return -1;
        }
    }

    // Sums the found files from the download and the desktop
    public int calcTotalFilesMoved(int filesDownload, int filesDesktop) {
        return filesDesktop + filesDownload;
    }


}
