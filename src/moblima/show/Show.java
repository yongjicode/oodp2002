package moblima.show;

import moblima.cineplex.Cinema;
import moblima.movie.Movie;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println();
        System.out.println("ShowId: " + showId);
        System.out.println("Movie Title: " + movie.getTitle());
        System.out.println("Cinema Hall: " + cinema.getCinemaCode());
        System.out.println("Cinema Class: " + cinema.getClassLevel());
        System.out.println("Day & Time: " + showTime.format(formatter));
    }


    public Cinema getCinema() {
        return cinema;
    }

    public LocalDateTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }
}
