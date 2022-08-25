package com.filesort.org;

import java.sql.SQLException;

public class Main {

    private static final String[] OPTIONS = new String[] { "Sort files", "Show paths", "Add path", "Update path ",
            "Delete path", "Connect moving files folder with destination folder",
            "Connect extension with destination folder", "End program" };
    public static final String DB_CON = "jdbc:sqlite:db\\folders.db";
    public static boolean endProgram = false;
    private static int countRunAmount = 0;

    public Main() {

    }

    public static void main(String[] args) {

        while (!endProgram) {

            if (countRunAmount < 1) {
                System.out.println("Searching for files...");
                System.out.println("Moving Files:");
                try {
                    sortFiles();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                countRunAmount++;
            } else {
                try {
                    Filesort.showOptions(OPTIONS);
                    Filesort.checkedOption();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    endProgram = true;
                }
            }

        }

    }

    public static void sortFiles() throws SQLException {
        Filesort.db.createFolderIfNotExists();
        Filesort.db.moveFiles();
    }

}
