package coherenceimdb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class App {

    public static void processTextFile(String filePath, IMDBParser parser) {
        File file = new File(filePath);
        Scanner input = null;
        try {
            input = new Scanner(file, "cp1252");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int i = 0;
        while (input.hasNextLine()) {
            String nextLine = input.nextLine();
            parser.parseNextLine(nextLine, i);
            i++;
        }

        input.close();
    }

    public static void main(String[] args) {
        String directorFile = "c:\\development\\source\\coherence-imdb\\input\\directors.list";
        String ratingsFile = "c:\\development\\source\\coherence-imdb\\input\\ratings.list";

        Cache.init();
        processTextFile(directorFile, new DirectorParser());
        processTextFile(ratingsFile, new RatingParser());
        Cache.count();

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            Cache.shutdown();
        }
    }

}
