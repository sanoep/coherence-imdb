package coherenceimdb;

import java.math.BigDecimal;
import java.util.Scanner;

import static com.google.common.base.Preconditions.*;

/**
 * Created with IntelliJ IDEA.
 * User: shanny
 * Date: 15/06/13
 * Time: 11:09
 */
public class RatingParser implements IMDBParser {

    public RatingParser() {

    }

    private boolean isLineEmpty(String line) {
        checkNotNull(line);

        boolean result = false;
        if ("".equals(line)) {
            result = true;
        }
        return result;
    }


    public void parseNextLine(String line, int index) {

        if (index > 297 && index < 465084) {
            if (isLineEmpty(line)) {
            } else {
                MovieRating mr = parseRating(line);
                //System.out.println(line);
                //System.out.println(mr);
                emit(mr);
           }
        }
    }

    private MovieRating parseRating(String line) {
        MovieRating mr = new MovieRating();

        Scanner scanner = new Scanner(line);

        if (scanner.hasNext()) {
            scanner.next();
        } else {
            throw new IllegalArgumentException("Invalid line: " + line);
        }

        if (scanner.hasNextInt()) {
            scanner.nextInt();
        } else {
            throw new IllegalArgumentException("Invalid line: " + line);
        }

        if (scanner.hasNextBigDecimal()) {
            BigDecimal rating = scanner.nextBigDecimal();
            mr.setRating(rating);
        } else {
            throw new IllegalArgumentException("Invalid line, no rating: " + line);
        }

        scanner.useDelimiter("\\z");
        if (scanner.hasNext()) {
            String movie = scanner.next();
            movie = movie.trim();
            mr.setMovie(movie);
        } else {
            throw new IllegalArgumentException("Invalid line, no movie: " + line);
        }

        checkNotNull(mr.getMovie(), "Invalid line, movie null");
        checkNotNull(mr.getRating(), "Invalid line, rating null");
        checkState(!"".equals(mr.getMovie()) && mr.getRating().compareTo(BigDecimal.ZERO) > 0, "Invalid line, empty movie or rating below zero: " + line);

        return mr;
    }

    private void emit(MovieRating mr) {
        if (mr != null) {
            Cache.add(mr);
        }
    }
}
