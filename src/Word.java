//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//public class Word extends Filesort {
//
//    private int wordFileAmount;
//
//    public Word(String fileFolder, String currentPath, String destinationPath) {
//        super(fileFolder, currentPath, destinationPath);
//    }
//
//    // Sorts the word files
//    public int sortFilesWord() {
//        try {
//            File folder = new File(fileFolder);
//            File[] listedFiles = folder.listFiles();
//            for (File file : listedFiles) {
//                String fileName = file.getName();
//                // Word Dokumente werden sortiert
//                if (fileName.contains(".docx")) {
//                    Path temp = Files.move(Paths.get(currentPath + fileName),
//                            Paths.get(destinationPath + fileName));
//                    wordFileAmount++;
//                    System.out.println(fileName + " got successfully moved");
//                }
//            }
//            return wordFileAmount;
//
//        } catch (NullPointerException | IOException ex) {
//            System.out.println("There was an error while sorting the word files!");
//            return -1;
//        }
//    }
//
//}
