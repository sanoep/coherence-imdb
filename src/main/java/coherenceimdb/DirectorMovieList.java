package coherenceimdb;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shanny
 * Date: 15/06/13
 * Time: 11:32
 */
public class DirectorMovieList {
    private String director;

    private List<String> movies = new ArrayList<String>();

    public DirectorMovieList(String director, String movie) {
        this.director = director;
        addMovie(movie);
    }

    public DirectorMovieList() {
    }

    public List<String> getMovies() {
        return movies;
    }

    public void setMovies(List<String> movie) {
        this.movies = movie;
    }

    public void addMovie(String movie) {
        this.movies.add(movie);
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "DirectorMovieList{" +
                "director='" + director + '\'' +
                ", movies=" + movies +
                '}';
    }

}
