package command.user;

import command.Command;
import exceptions.moblimaExceptions;
import moblima.SilverVillage;
import moblima.booking.Booking;
import moblima.movie.Movie;
import moblima.booking.ticket.MovieTicket;
import moblima.movie.review.Review;

import java.util.Scanner;

public class reviewMovieCommand implements Command{

//    private int ticketID;
//    private int rating;
//    private String reviewDescription;

    public reviewMovieCommand(){
//        this.ticketID = ticketID;
//        this.rating = rating;
//        this.reviewDescription = reviewDescription;
    }


    public void execute(){
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.print("Please enter the ticket ID: ");
        while(true) {
            try {
                if (input.hasNextInt() == false) {
                    throw new moblimaExceptions.invalidInputException("ticket ID");
                }
                int ticketID = input.nextInt();
                System.out.println();
                System.out.print("Please enter your rating: ");

                while (true) {
                    try {
                        if (input.hasNextInt() == false) {
                            throw new moblimaExceptions.invalidInputException("rating");
                        }
                        int reviewRating = input.nextInt();
                        input.nextLine();
                        System.out.println();
                        System.out.print("Please enter your review: ");
                        String reviewDesc = input.nextLine();
                        MovieTicket ticket = SilverVillage.getBookingHistory().searchTicketByTicketId(ticketID);
                        if (ticket == null) {
                            System.out.println();
                            System.out.println("Ticket ID cannot be found. No review created.");
                            return;
                        }
                        Review review = new Review(reviewRating, reviewDesc);
                        ticket.getShow().getMovie().addReview(review);
                        System.out.println("Review added successfully...");
                        return;
                    }
                    catch (moblimaExceptions.invalidInputException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println();
                    System.out.print("Please enter your rating again: ");
                    input.next();
                    continue;
                }
            }
            catch (moblimaExceptions.invalidInputException e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }
}

