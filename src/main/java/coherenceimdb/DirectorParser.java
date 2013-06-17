package coherenceimdb;

import static com.google.common.base.Preconditions.*;

/**
 * Created with IntelliJ IDEA.
 * User: shanny
 * Date: 15/06/13
 * Time: 11:09
 */
public class DirectorParser implements IMDBParser {
    //private String currentDirector = null;
    private DirectorMovieList currentDirector = null;

    public DirectorParser() {

    }

    private boolean isLineEmpty(String line) {
        checkNotNull(line);

        boolean result = false;
        if ("".equals(line)) {
            result = true;
        }
        return result;
    }

    private String[] splitOnTabs(String line) {
        String[] split = line.split("\\t");
        return split;
    }

    private String splitMovieLine(String line) {
        String movie = null;

        checkNotNull(line);
        checkState(currentDirector != null, "Current Director cannot be null when parsing single movie line.");

        String[] split = splitOnTabs(line);

        DirectorMovie result = null;
        if (split.length > 2) {
            movie = split[3];
            checkState(movie != null && !movie.isEmpty(), "Movie result is empty while splitting movie line: " + line);
        }

        return movie;
    }

    private DirectorMovie splitDirectorMovieLine(String line) {
        checkNotNull(line);

        String[] split = splitOnTabs(line);

        DirectorMovie result = null;
        if (split.length > 1) {
            String director = split[0];
            checkState(director != null && !director.isEmpty(), "Director result is empty while splitting director-movie line: " + line);

            String movie = split[1];
            if ("".equals(movie)) {
                movie = findNonEmptyEntry(split, 1);
            }
            checkState(movie != null && !movie.isEmpty(), "Movie result is empty while splitting director-movie line: " + line + ", director [" + director + "], split-length ["+split.length+"]");

            result = new DirectorMovie(split[0], movie);
        }

        return result;
    }

    private String findNonEmptyEntry(String[] split, int startIndex) {
        checkNotNull(split);
        checkArgument(startIndex > 0 && startIndex < split.length);

        for(int i = startIndex; i < split.length; i++) {
            String entry = split[i];
            if (entry != null && !entry.isEmpty()) {
                return entry;
            }
        }
        return null;
    }


    public void parseNextLine(String line, int index) {

        if (index > 234 && index < 2031814) {
            if (isLineEmpty(line)) {
                if (currentDirector != null) {
                    emit(currentDirector);
                }
                currentDirector = null;
                //System.out.println(index + "<empty>");
                return;
            } else {
                if (currentDirector == null) {
                    DirectorMovie dm = splitDirectorMovieLine(line);
                    if (dm != null) {
                        currentDirector = new DirectorMovieList(dm.getDirector(), dm.getMovie());
                    }
                } else {
                    String movie = splitMovieLine(line);
                    currentDirector.addMovie(movie);
                }
            }
        }
    }

    private void emit(DirectorMovieList dm) {
        if (dm != null) {
            //System.out.println(dm);
            Cache.add(dm);
        }
    }
}
