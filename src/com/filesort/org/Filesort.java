package com.filesort.org;

import java.io.FileWriter;
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
    private static final Scanner SCANNER = new Scanner(System.in);
    public static Database db;
    private static final String[] FOLDER_OPTIONS = new String[] { "Moving files folders", "Destination folder" };

    static {
        try {
            db = new Database(Main.DB_CON);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Filesort(String fileFolder, String currentPath, String destinationPath, String[] extension) {
        this.fileFolder = fileFolder;
        this.currentPath = currentPath;
        this.destinationPath = destinationPath;
        this.extension = extension;
        this.fileAmount = 0;
        // CheckFoldersExist();

    }

    // Checks if the folders got created on the desktop
    public void CheckFoldersExist() {
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

    public static void ShowOptions(String[] options) {
        System.out.println("\nChoose one of the options below:");
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

    public static void CheckedOption() throws SQLException {

        int selectedOption = SCANNER.nextInt();
        int selectedInnerOption;
        boolean sqlSuccessful;
        String path = null;
        String folder = null;
        int id = 0;
        int optionCounter = 1;
        int dest_fold_id = 0;
        int mff_id = 0;

        // "Sort files", "Show all paths", "Add path", "Update path ", "Delete path",
        // "Exit"
        switch (selectedOption) {
            case 1:
                Main.SortFiles();
                break;

            case 2:
                System.out.println("Would you like to see the destination folders or the moving files folders?");
                optionCounter = 1;
                for (String option : FOLDER_OPTIONS) {
                    System.out.println(optionCounter + "." + option);
                    optionCounter++;
                }
                selectedInnerOption = SCANNER.nextInt();
                switch (selectedInnerOption) {
                    case 1:
                        db.ShowMovingFolders();
                        System.out.println("\n");
                        break;
                    case 2:
                        db.ShowDestinationFolders();
                        System.out.println("\n");
                        break;
                }
                break;

            case 3:

                System.out.println("What kind of folder do you want to add? :");

                for (String option : FOLDER_OPTIONS) {
                    System.out.println(optionCounter + "." + option);
                    optionCounter++;
                }
                selectedInnerOption = SCANNER.nextInt();
                switch (selectedInnerOption) {
                    case 1:
                        System.out.println("Type in the name for the name for the folder");
                        folder = SCANNER.next();
                        System.out.println("Now enter the path for the folder (C:\\foldername\\..).");
                        path = SCANNER.next();
                        if (path.charAt(path.length() - 1) != '\\') {
                            path += '\\';
                            try {
                                sqlSuccessful = db.AddMovingFilesFolder(folder, path);
                                if (sqlSuccessful) {
                                    System.out.println("Folder got added");
                                } else {
                                    System.out.println("Folder couldn't get added");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                sqlSuccessful = db.AddMovingFilesFolder(folder, path);
                                if (sqlSuccessful) {
                                    System.out.println("Folder got added");
                                } else {
                                    System.out.println("Folder couldn't get added");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        System.out.println("\n");
                        break;
                    case 2:

                        System.out.println("Type in the name for the folder");
                        folder = SCANNER.next();
                        System.out.println("Now enter the path for the folder (C:\\foldername\\..).");
                        path = SCANNER.next();
                        if (path.charAt(path.length() - 1) != '\\') {
                            path += '\\';
                            try {
                                sqlSuccessful = db.AddDestinationFolder(folder, path);
                                if (sqlSuccessful) {
                                    System.out.println("Folder got added");
                                } else {
                                    System.out.println("Folder couldn't get added");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            System.out.println("\n");
                            break;
                        } else {
                            System.out.println("Type in the name for the folder");
                            folder = SCANNER.next();
                            System.out.println("Now enter the path for the folder (C:\\foldername\\..).");
                            path = SCANNER.next();

                            try {
                                sqlSuccessful = db.AddDestinationFolder(folder, path);
                                if (sqlSuccessful) {
                                    System.out.println("Folder got added");
                                } else {
                                    System.out.println("Folder couldn't get added");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            System.out.println("\n");
                            break;
                        }

                }

                break;

            case 4:
                System.out.println("What kind of folder do you want to update?");

                for (String option : FOLDER_OPTIONS) {
                    System.out.println(optionCounter + "." + option);
                    optionCounter++;
                }
                selectedInnerOption = SCANNER.nextInt();
                switch (selectedInnerOption) {
                    case 1:
                        try {
                            System.out.println("Type in the number of the folder you want to edit");
                            id = SCANNER.nextInt();
                            System.out.println("Type in the name for the folder");
                            folder = SCANNER.next();
                            System.out.println("Now enter the path for the folder (C:\\foldername\\..).");
                            path = SCANNER.next();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        try {
                            sqlSuccessful = db.UpdateMovingFilesFolder(folder, path, id);
                            if (sqlSuccessful) {
                                System.out.println("Folder got updated");
                            } else {
                                System.out.println("Folder couldn't get updated");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.println("\n");
                        break;
                    case 2:
                        System.out.println("Type in the number of the folder you want to edit");
                        id = SCANNER.nextInt();
                        System.out.println("Type in the name for the folder");
                        folder = SCANNER.next();
                        System.out.println("Now enter the path for the folder (C:\\foldername\\..).");
                        path = SCANNER.next();

                        try {
                            sqlSuccessful = db.UpdateDestinationFolder(folder, path, id);
                            if (sqlSuccessful) {
                                System.out.println("Folder got updated");
                            } else {
                                System.out.println("Folder couldn't get updated");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.println("\n");
                        break;
                }

            case 5:
                System.out.println("What kind of folder do you want to remove? :");

                for (String option : FOLDER_OPTIONS) {
                    System.out.println(optionCounter + "." + option);
                    optionCounter++;
                }
                selectedInnerOption = SCANNER.nextInt();
                switch (selectedInnerOption) {
                    case 1:
                        try {
                            System.out.println("Moving file folders: ");
                            db.GetAllMovingFolderIds();
                            System.out.println("Type in the number of the folder you want to remove");
                            id = SCANNER.nextInt();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        try {
                            sqlSuccessful = db.DeleteMovingFilesFolder(id);
                            if (sqlSuccessful) {
                                System.out.println("Folder got removed");
                            } else {
                                System.out.println("Folder couldn't get removed");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.println("\n");
                        break;
                    case 2:
                        try {
                            System.out.println("Destination folders: ");
                            db.GetAllDestinationFolderIds();
                            System.out.println("Type in the number of the folder you want to remove");
                            id = SCANNER.nextInt();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        try {
                            sqlSuccessful = db.DeleteDestinationFolder(id);
                            if (sqlSuccessful) {
                                System.out.println("Folder got removed");
                            } else {
                                System.out.println("Folder couldn't get removed");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.println("\n");
                        break;
                }

            case 6:
                System.out.println("Destination folders: ");
                db.GetAllDestinationFolderIds();
                System.out.println("\n");
                System.out.println("Moving file folders: ");
                db.GetAllMovingFolderIds();

                System.out.println(" Number of the folder where the files are supposed to be moved away from");
                try {
                    if (SCANNER.hasNextInt()) {
                        mff_id = SCANNER.nextInt();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("Number of the folder where the files are supposed to be moved to");

                try {
                    if (SCANNER.hasNextInt()) {
                        dest_fold_id = SCANNER.nextInt();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    sqlSuccessful = db.ConnectFolders(mff_id, dest_fold_id);
                    if (sqlSuccessful) {
                        System.out.println("Folders got connected");
                    } else {
                        System.out.println("Folders couldn't get connected");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("\n");
                break;

            case 7:
                System.out.println("Destination folders: ");
                db.GetAllDestinationFolderIds();
                System.out.println("\n");

                System.out.println("Number of destination folder you want to connect an extension to");
                try {
                    if (SCANNER.hasNextInt()) {
                        dest_fold_id = SCANNER.nextInt();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println("Now enter the extension (.pptx for example) ");
                String extension = SCANNER.next();

                try {
                    sqlSuccessful = db.ConnectExtentionToFolder(dest_fold_id, extension);
                    if (sqlSuccessful) {
                        System.out.println("Extension got added");
                    } else {
                        System.out.println("Extension couldn't get added");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("\n");

                break;

            case 8:
                Main.endProgram = true;
                break;
            default:
                System.out.println("Sorry, there is no such option.");
        }
    }

    public static String outputMovedFile(String file, String currentDestFolderPath) {
        return file + " -> " + currentDestFolderPath;
    }

    // public static void writeOutputToFile(String file, String currentDestFolderPath) {

    //     try {
    //         FileWriter fileWriter = new FileWriter("log.txt");
    //         fileWriter.write(outputMovedFile(file, currentDestFolderPath));
    //         fileWriter.close();
    //     } catch (IOException e) {

    //         e.printStackTrace();
    //     }
    // }

}
