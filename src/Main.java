import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        Filesort filesort = new Filesort(System.getenv("Download_folder"), System.getenv("Download_folder"), System.getenv("Word_files"));
        filesort.sortFiles();
    }


}
