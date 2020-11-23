import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Filesort {

    private final String[] extension;
    private int fileAmount;
    private final String fileFolder;
    private final String currentPath;
    private final String destinationPath;


    public Filesort(String fileFolder, String currentPath, String destinationPath, String[] extension) {
        this.fileFolder = fileFolder;
        this.currentPath = currentPath;
        this.destinationPath = destinationPath;
        this.extension = extension;
        this.fileAmount = 0;
    }

    // Checks if the folders got created on the desktop
    public void checkFoldersExist() throws IOException {

        if (Files.exists(Paths.get(destinationPath)) || destinationPath.equals("Download_folder") ||
                destinationPath.equals("Desktop")) {
            // Nothing

        } else {
            Files.createDirectory(Paths.get(destinationPath));
            System.out.println("Folder " + destinationPath + " got created");
        }

    }



    // Sorts the files
    public int sortFiles() {
        try {
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
            return 0;
        }
    }

    // Sums the found files from the download and the desktop
    public int calcTotalFilesMoved(int filesDownload, int filesDesktop) {
        return filesDesktop + filesDownload;
    }
    public void checkFilesMoved(int filesCountedDownload, int filesCountedDesktop, String fileType, int totalFilesMoved) {

        switch (fileType) {

            case "word":
                System.out.println("/// WORD FILES ///");
                if (filesCountedDownload <= 0 && filesCountedDesktop <= 0) {
                    System.out.println("No movable word file/s found.\n");
                } else {
                    System.out.println(filesCountedDownload + " word file/s got moved to " + destinationPath);
                    System.out.println(filesCountedDesktop + "  word file/s got moved to " + destinationPath);
                    System.out.println("Total word file/s moved: " + totalFilesMoved + "\n");
                }
                break;

            case "excel":
                System.out.println("/// EXCEL FILES ///");
                if (filesCountedDownload <= 0 && filesCountedDesktop <= 0) {
                    System.out.println("No movable excel file/s found.\n");
                } else {
                    System.out.println(filesCountedDownload + " excel file/s got moved to " + destinationPath);
                    System.out.println(filesCountedDesktop + "  excel file/s got moved to " + destinationPath);
                    System.out.println("Total  excel file/s moved: " + totalFilesMoved + "\n");
                }
                break;


            case "pdf":
                System.out.println("/// PDF FILES ///");
                if (filesCountedDownload <= 0 && filesCountedDesktop <= 0) {
                    System.out.println("No movable pdf file/s found.\n");
                } else {
                    System.out.println(filesCountedDownload + " pdf file/s got moved to " + destinationPath);
                    System.out.println(filesCountedDesktop + "  pdf file/s got moved to " + destinationPath);
                    System.out.println("Total pdf files moved: " + totalFilesMoved + "\n");
                }
                break;


            case "pictures":
                System.out.println("/// PICTURES ///");
                if (filesCountedDownload <= 0 && filesCountedDesktop <= 0) {
                    System.out.println("No movable picture/s found.\n");
                } else {
                    System.out.println(filesCountedDownload + " picture/s got moved to " + destinationPath);
                    System.out.println(filesCountedDesktop + "  picture/s got moved to " + destinationPath);
                    System.out.println("Total picture/s moved: " + totalFilesMoved + "\n");
                }
                break;


            case "powerpoints":
                System.out.println("/// POWERPOINT FILES ///");
                if (filesCountedDownload <= 0 && filesCountedDesktop <= 0) {
                    System.out.println("No movable picture/s found.\n");
                } else {
                    System.out.println(filesCountedDownload + " picture/s got moved to " + destinationPath);
                    System.out.println(filesCountedDesktop + "  picture/s got moved to " + destinationPath);
                    System.out.println("Total picture/s moved: " + totalFilesMoved);
                }
                break;

        }


    }


}


