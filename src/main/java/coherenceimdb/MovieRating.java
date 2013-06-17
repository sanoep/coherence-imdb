package coherenceimdb;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: shanny
 * Date: 17/06/13
 * Time: 15:31
 * To change this template use File | Settings | File Templates.
 */
public class MovieRating {
    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "MovieRating{" +
                "movie='" + movie + '\'' +
                ", rating=" + rating +
                '}';
    }

    public BigDecimal getRating() {
        return  rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    private String movie;
    private BigDecimal rating;

}
