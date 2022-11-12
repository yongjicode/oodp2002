package command.customer;

import command.Command;
import exceptions.moblimaExceptions;
import moblima.SilverVillage;
import moblima.booking.ticket.MovieTicket;
import moblima.movie.review.Review;

import java.util.Scanner;

public class ReviewMovieCommand implements Command{

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
                input.nextLine();
                MovieTicket ticket = SilverVillage.getBookingHistory().searchTicketByTicketId(ticketID);
                if (ticket == null) {
                    System.out.println();
                    System.out.println("Ticket ID cannot be found. No review created.");
                    return;
                }
                System.out.println();
                System.out.print("Please enter your rating (1-5, 5 being the best): ");

                while (true) {
                    try {
                        if (input.hasNextInt() == false) {
                            throw new moblimaExceptions.invalidInputException("rating");
                        }
                        int reviewRating = input.nextInt();
                        
                        while(true) {
                        	
	                        if(reviewRating <= 0 || reviewRating > 5) {
	                        	System.out.println("Rating out of range. Please try again.");
	                        	System.out.println();
	                            System.out.print("Please enter your rating (1-5, 5 being the best) again: ");
	                            reviewRating = input.nextInt();
	                        	continue;
	                        }
	                        input.nextLine();
                        	break;
                    	}
                        System.out.println();
                        System.out.print("Please enter your review: ");
                        String reviewDesc = input.nextLine();
                        
                        Review review = new Review(reviewRating, reviewDesc);
                        ticket.getShow().getMovie().addReview(review);
                        System.out.println();
                        System.out.println("Review added successfully.");
                        return;
                    }
                    catch (moblimaExceptions.invalidInputException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println();
                    System.out.print("Please enter your rating (1-5, 5 being the best) again: ");
                    input.nextLine();
                    
                    continue;
                }
            }
            catch (moblimaExceptions.invalidInputException e) {
                System.out.println(e.getMessage());
                
            }
            System.out.println();
            System.out.print("Please enter ticket ID again: ");
            input.nextLine();
            continue;
        }
    }
}

