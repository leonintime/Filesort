package com.filesort.org;

import java.sql.SQLException;

public class Main {

    private static final String[] OPTIONS = new String[]{"Sort files", "Show paths", "Add path", "Update path ",
            "Delete path", "Connect moving files folder with destination folder", "End program"};
    public static final String DB_CON = "jdbc:sqlite:db\\folders.db";
    public static boolean endProgram = false;
    private static int countRunAmount = 0;


    public Main() {

    }

    public static void main(String[] args) {

        while (!endProgram) {

            if (countRunAmount < 1) {
                System.out.println("Searching for files...");
                System.out.println("FILE LOG:");
                try {
                    SortFiles();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                countRunAmount++;
            } else {
                try {
                    Filesort.ShowOptions(OPTIONS);
                    Filesort.CheckedOption();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public static void SortFiles() throws SQLException {

        // Files in download folder are getting moved to the destination folder
        Filesort.db.MoveFiles();


    }

}
