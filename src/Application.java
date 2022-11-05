import command.userModule.*;
import moblima.Cinema;
import moblima.Cineplex;
import moblima.Company;
import moblima.Booking;
import moblima.MovieTicket;
import moblima.ReviewList;
import moblima.Review;
import moblima.Movie;
import moblima.Show;
import moblima.CSVReader;

import java.util.Scanner;
import java.util.ArrayList;

public class Application {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new Application().run();
    }

    public void run() {

		// TODO: ACCOUNT DB
		
		// BOOKING
		ArrayList<Booking> bookings = CSVReader.readBookingsFromCSV("src\\database\\bookingDB.csv");
        // for (Booking booking : bookings) { 
        //     booking.printBookingDetails();
        // }
		// REVIEW LISTS
		ArrayList<ReviewList> reviewLists = CSVReader.readReviewsFromCSV("src\\database\\reviewListDB.csv");
        // for (ReviewList reviewList : reviewLists) { 
        //     reviewList.listReviews();
        // }
		// MOVIES 
		ArrayList<Movie> movies = CSVReader.readMoviesFromCSV("src\\database\\movieDB.csv");
        // for (Movie movie : movies) { 
        //     movie.printMovieDetails();
        // }
		// CINEMAS
		ArrayList<Cinema> cinemas = CSVReader.readCinemasFromCSV("src\\database\\companyDB.csv");
		// for (Cinema cinema : cinemas) { 
		//     cinema.printCinemaDetails();
		// }
		// SHOWS
		ArrayList<Show> shows = CSVReader.readShowsFromCSV("src\\database\\showDB.csv");
		for (Show show : shows) { 
		    show.printShowDetails();
		}
		// TODO - MOVIE TICKETS
		// ArrayList<MovieTicket> tickets = CSVReader.readTicketsFromCSV("src\\database\\ticketDB.csv");
		// for (MovieTicket ticket : tickets) { 
		//     ticket.printTicketDetails();
		// }

    	Company company = new Company();
    	Cineplex cineplex = new Cineplex();
    	//Cinema cinema = new Cinema("test1","test2");
    	Scanner scanner = new Scanner(System.in);
        greetUser();
        mainMenu();
        int ch = 0;
        while(true) {
        	ch = scanner.nextInt();
        	if (ch == 1 || ch ==2 || ch == 3)
        		break;

        	System.out.println("Invalid Entry");
	
        }
        if (ch==1) {
        	int userCh = 0;
        	while (true) {
        		System.out.println("=================");
        		userMenu();
        		userCh = scanner.nextInt();
        		if (userCh == 8) break;
        		switch (userCh) {
        		case 1:
        			new userSearchMovieCommand().execute();
        			break;
        		case 2:
        			new userListMoviesCommand().execute();
        			break;
        		case 3:
        			new showSeatAvailabilityCommand(cineplex).execute();
        			break;
        		case 4:
        			new bookTicketCommand(cineplex).execute();
        			break;
        		case 5:
        			new viewBookingHistoryCommand().execute();
        			break;
        		case 6:
        			new rankTicketSalesCommand().execute();
        			break;
        		case 7:
        			new rankReviewRatingsCommand().execute();
        			break;
        		default:
        			System.out.println("Invalid command.Command");
        			break;
        		}
        	}
        	
        }
        else if (ch==2){
        	//admin module
        }
        	
        endProgram();
    }

    private static void greetUser(){
        System.out.println("Hello I'm MOBLIMA");
        System.out.println("I am a movie booking & listing management application!");
    }
    
    private static void mainMenu() {
    	System.out.println("1. User");
        System.out.println("2. Admin");
        System.out.println("3. Exit");
    }
    private static void userMenu() {
    	System.out.println("1. Search moblima.Movie");
    	System.out.println("2. List Movies");
    	System.out.println("3. View Seat Availability");
    	System.out.println("4. Book Tickets");
    	System.out.println("5. View moblima.Booking History");
    	System.out.println("6. Top 5 ranking by ticket sales");
    	System.out.println("7. Top 5 ranking by review ratings");
    	System.out.println("8. Exit");
    }
    private static String readCommand(){
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
    private static void showLine(){
        System.out.println("____________________________");
    }

    private static void endProgram(){
        System.out.println("Bye!");
    }
}
