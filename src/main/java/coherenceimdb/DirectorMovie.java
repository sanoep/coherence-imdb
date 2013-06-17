package coherenceimdb;

/**
 * Created with IntelliJ IDEA.
 * User: shanny
 * Date: 15/06/13
 * Time: 11:32
 */
public class DirectorMovie {
    private String director;
    private String movie;

    public DirectorMovie(String director, String movie) {
        this.director = director;
        this.movie = movie;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }




}
