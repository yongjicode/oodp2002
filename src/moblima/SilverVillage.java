package moblima;

import moblima.booking.BookingHistory;
import moblima.cineplex.CineplexList;
import moblima.movie.MovieList;

/**
 * Represents the company this application is created for
 * Contains all bookings, movies and cineplexes the company owns
 */
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
