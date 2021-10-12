package com.filesort.org;

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

    // private void checkEnvironmentVariables() {
    //
    // try {
    // if (destinationPath == null || currentPath == null) {
    //
    // if (destinationPath == null) {
    // System.out.println(
    // "The environment variable for the destined folder needs to be set in the
    // system properties.(Folder for Word files) "
    // + "\nFor example: Word_files =
    // C:\\Users\\YourAccount\\Desktop\\Word_documents\\");
    //
    // } else {
    // System.out.println(
    // "The environment variable for the folder where the files are supposed to be
    // moved away from needs to be set in the system properties.."
    // + "\n For example: (Desktop = C:\\Users\\YourAccount\\Desktop\\) and
    // (Download_folder = C:\\Users\\YourAccount\\Download\\)");
    // }
    // }
    // } catch (Exception ex) {
    // System.out.println("An error occurred while checking if the environment
    // variables exist.");
    // }
    //
    // }

    // Sums the found files from the download and the desktop
    public int CalcTotalFilesMoved(int filesDownload, int filesDesktop) {
        return filesDesktop + filesDownload;
    }

    // Checks how many files got moved from one to another folder
    public void CheckFilesMoved(int filesCountedDownload, int filesCountedDesktop, String fileType,
            int totalFilesMoved) {

        switch (fileType) {

            default:
                System.out.println("Couldn't find this file format");

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
                        System.out.println("\n");
                        break;
                    case 2:
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
                Main.endProgram = true;
                break;
            default:
                System.out.println("Sorry, there is no such option.");
        }
    }
}
