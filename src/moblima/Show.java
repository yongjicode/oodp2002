package moblima;

import moblima.Cinema;
import moblima.Movie;
import moblima.Seating;

import java.time.LocalDateTime;

public class Show {

    private static int currentId=1;

    private int showId;
    private LocalDateTime showTime;
    private Cinema cinema;

    private Movie movie;

    private Seating seating;

    public Show(LocalDateTime showTime, Cinema cinema, Movie movie) {
        this.showId = currentId++;
        this.showTime = showTime;
        this.cinema = cinema;
        this.movie = movie;
        this.seating = new Seating();
    }
    public Movie getMovie() {
        return movie;
    }
    public Seating getSeating() {
        return seating;
    }
    public int getShowId() {
        return showId;
    }
    public void showSeating(){ //for user
        seating.printSeats();
    }

    public void printShowDetails(){
        System.out.println("ShowId: " + showId);
        System.out.println("moblima.Movie: " + movie);
        System.out.println("moblima.Cinema: " + cinema);
        System.out.println("Day & Time: " + showTime);
    }


}
