package command.userModule;

import command.Command;
import moblima.*;

import java.util.ArrayList;
import java.util.Scanner;

public class reviewMovieCommand implements Command{
    private ArrayList<Booking> arrayBooking;
    private ArrayList<Movie> arrayMovie;
    public reviewMovieCommand(ArrayList<Booking> arrayBooking, ArrayList<Movie> arrayMovie){
        this.arrayBooking = arrayBooking;
        this.arrayMovie = arrayMovie;
    }
    public void execute(){
        Scanner input = new Scanner(System.in);
        System.out.println("Please input the ticket ID: ");
        int ticketID = input.nextInt();
        System.out.println("Please input the movie ID you with to rate: ");
        int movieID = input.nextInt();
        System.out.println("Please input your rating: ");
        int reviewRating = input.nextInt();
        System.out.println("Please input your review: ");
        String reviewDesc = input.nextLine();
        for (Booking booking: arrayBooking){
            for (MovieTicket ticket: booking.getTickets()){
                if (ticket.getTicketID() == ticketID){
                    Review review = new Review(reviewRating, reviewDesc);
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

