package com.filesort.org;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Scanner;

public class Filesort {

    private final String[] extension;
    private int fileAmount;
    private final String fileFolder;
    private final String currentPath;
    private final String destinationPath;
    private static Database db;
    private static final Scanner scanner = new Scanner(System.in);


    public Filesort(String fileFolder, String currentPath, String destinationPath, String[] extension) throws SQLException {
        this.fileFolder = fileFolder;
        this.currentPath = currentPath;
        this.destinationPath = destinationPath;
        this.extension = extension;
        this.fileAmount = 0;
        db = new Database(Main.DB_CON);
    }

    // Checks if the folders got created on the desktop
    public void checkFoldersExist() {
        try {
            if (Files.exists(Paths.get(destinationPath))) {
                // Nothing

            } else {
                Files.createDirectory(Paths.get(destinationPath));
                System.out.println("Folder " + destinationPath + " got created");
            }
        } catch (Exception ex) {
            System.out.println("An error occurred while checking if the file folders exist.");
        }


    }


    private void checkEnvironmentVariables() {

        try {
            if (destinationPath == null || currentPath == null) {

                if (destinationPath == null) {
                    System.out.println("The environment variable for the destined folder needs to be set in the system properties.(Folder for Word files) " +
                            "\nFor example: Word_files = C:\\Users\\YourAccount\\Desktop\\Word_documents\\");

                } else if (currentPath == null) {
                    System.out.println("The environment variable for the folder where the files are supposed to be moved away from needs to be set in the system properties.." +
                            "\n For example: (Desktop = C:\\Users\\YourAccount\\Desktop\\) and (Download_folder = C:\\Users\\YourAccount\\Download\\)");
                } else {
                    // Nothing
                }
            }
        } catch (Exception ex) {
            System.out.println("An error occurred while checking if the environment variables exist.");
        }

    }


    public int moveFiles() {
        try {
            File folder = new File(fileFolder);
            String[] files = folder.list();
            assert files != null;
            for (String file : files) {
                for (int i = 0; i <= extension.length - 1; i++) {
                    if (file.contains(extension[i])) {
                        Files.move(Paths.get(currentPath + file),
                                Paths.get(destinationPath + file));
                        fileAmount++;
                        System.out.println(file + " got successfully moved");
                    }
                }
            }

            return fileAmount;
        } catch (NullPointerException | IOException ex) {
            return 0;
        }
    }

    // Sums the found files from the download and the desktop
    public int calcTotalFilesMoved(int filesDownload, int filesDesktop) {
        return filesDesktop + filesDownload;
    }


    // Checks how many files got moved from one to another folder<
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

    public static void showOptions(String[] options) {
        System.out.println("Choose one of the options below:");
        int temp = 0;
        try {
            for (int i = 0; i <= options.length - 1; i++) {
                temp++;
                System.out.println(temp + "." + options[i]);
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public static void checkedOption() {

        int selectedOption = scanner.nextInt();
        boolean successfulInserted;
        String pathValue;
        String pathName;

        // "Sort files", "Show all paths", "Add path", "Update path ", "Delete path"
        switch (selectedOption) {
            case 1:
                break;

            case 2:
                db.showPaths();
                break;

            case 3:
                System.out.println("Type in the name for the path (My word files).");
                pathName = scanner.nextLine();
                System.out.println("Now enter the path for the folder (C:\\foldername\\..).");
                pathValue = scanner.nextLine();

                try {
                    successfulInserted = db.addPath(pathName, pathValue);
                    if (successfulInserted) {
                        System.out.println("Path got added");
                    } else {
                        System.out.println("Path couldn't get added");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case 4:
                db.showPaths();
                System.out.println("Type in the number for the path that you want to update");
                int pathNumber = scanner.nextInt();
                System.out.println("Type in the name for the path (My word files).");
                pathName = scanner.nextLine();

                System.out.println("Enter the value for the folder (C:\\foldername\\..).");
                pathValue = scanner.nextLine();

                try {
                    successfulInserted = db.updatePath(pathName, pathValue, pathNumber);
                    if (successfulInserted) {
                        System.out.println("Path got added");
                    } else {
                        System.out.println("Path couldn't get added");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case 5:
                break;
            case 6:

                Main.endProgram = true;
                break;
            default:

        }
    }
}



