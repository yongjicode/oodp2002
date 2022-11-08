package command.userModule;
import command.Command;
import moblima.Booking;
import moblima.Movie;
import moblima.MovieTicket;
import moblima.Review;

import java.awt.print.Book;
import java.util.ArrayList;

public class reviewMovieCommand implements Command{
    private int ticketID;
    private ArrayList<Booking> arrayBooking;
    private int rating;
    private String reviewDesc;
    private ArrayList<Movie> arrayMovie;
    private int movieID;

    public reviewMovieCommand(int ticketID, ArrayList<Booking> arrayBooking, int rating, String reviewDesc, ArrayList<Movie> arrayMovie, int movieID){
        this.arrayMovie=arrayMovie;
        this.arrayBooking=arrayBooking;
        this.movieID=movieID;
        this.ticketID=ticketID;
        this.reviewDesc=reviewDesc;
        this.rating=rating;
    }
    public void execute(){
        for (Booking booking: arrayBooking){
            for (MovieTicket ticket: booking.getTickets()){
                if (ticket.getTicketID() == ticketID){
                    Review review = new Review(rating, reviewDesc);
                    for (Movie movie: arrayMovie){
                        if (movie.getMovieId()==movieID){
                            movie.getReviews().add(review);
                            System.out.println("Review Created...");
                        }
                    }
                }
            }
        }
        System.out.println("ticket ID cannot be found...");
    }
}

