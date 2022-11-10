package moblima;

import moblima.booking.BookingHistory;
import moblima.cineplex.CineplexList;
import moblima.movie.MovieList;

public class SilverVillage {

    private static final BookingHistory bookingHistory = new BookingHistory();

    private static final MovieList movieList = new MovieList();

    private static final CineplexList cineplexList = new CineplexList();

    public static BookingHistory getBookingHistory() {
        return bookingHistory;
    }
    public static MovieList getMovieList() {
        return movieList;
    }
    public static CineplexList getCineplexList() {
        return cineplexList;
    }

}
