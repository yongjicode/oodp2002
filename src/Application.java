import command.userModule.*;
import command.adminModule.*;
import moblima.*;
import moblima.Movie;
import moblima.Show;


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Scanner;
import java.util.ArrayList;

public class Application {
	/**
	 * Main entry-point for the java.duke.Duke application.
	 */
	public static void main(String[] args) {
		new Application().run();
	}



	public void run() throws IOException {
		// ACCOUNT DB
		ArrayList<Account> accounts = CSVReader.readAccountsFromCSV("src\\database\\accountDB.csv");
        // for (Account account : accounts) { 
        //     System.out.println("Login ID: " + account.getLoginId());
        // }
		
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
		// for (Show show : shows) { 
		//     show.printShowDetails();
		// }
		// MOVIE TICKETS
		ArrayList<MovieTicket> tickets = CSVReader.readTicketsFromCSV("src\\database\\ticketDB.csv");
		// for (MovieTicket ticket : tickets) { 
		//     ticket.printTicketDetails();
		// }

		//test cases below

		// Scanner scanner = new Scanner(System.in);
		// int userCh = 0;
		// int privilege;
		
		// greetUser();
		// while(true){
		// 	System.out.println("Login ID:");
		// 	String userLogin = scanner.nextLine();
		// 	System.out.println("Password:");
		// 	String password = scanner.nextLine();
		// 	curAcc = login(userLogin,password,accounts);
		// 	if (curAcc==null){
		// 		System.out.println("Invalid Details");
		// 		continue;
		// 	}
		// 	else{
		// 		privilege = curAcc.getPrivilege();
		// 		break;
		// 	}
		// }

		// if (privilege == 0){
		// 	UserAccount userAcc = (UserAccount) curAcc;
		// 	System.out.println("Logged in as user: " + curAcc.getLoginId());
		// 	Company.listLocations();
		// 	System.out.println("Enter Cinema Location: ");
		// 	String location = scanner.nextLine();
		// 	Cineplex userCineplex = Company.searchCineplexByLocation(location);
		// 	System.out.println(userCineplex.getLocation());
		// 	while (true) {

		// 		System.out.println("=================");
		// 		userMenu();
		// 		userCh = scanner.nextInt();
		// 		if (userCh == 9) break;
		// 		switch (userCh) {
		// 			case 1:
		// 				new userSearchMovieCommand().execute();
		// 				break;
		// 			case 2:
		// 				new userListMoviesCommand().execute();
		// 				break;
		// 			case 3:
		// 				new showSeatAvailabilityCommand(userCineplex).execute();
		// 				break;
		// 			case 4:
		// 				new bookTicketCommand(userCineplex, userAcc.getLoginId()).execute();
		// 				break;
		// 			case 5:
		// 				new viewBookingHistoryCommand(userAcc.getLoginId()).execute();
		// 				break;

		// 			case 6:
		// 				//need to implement
		// 				//new reviewMovieCommand().execute();
		// 				break;
		// 			case 7:
		// 				new rankTicketSalesCommand().execute();
		// 				break;
		// 			case 8:
		// 				new rankReviewRatingsCommand().execute();
		// 				break;
		// 			default:
		// 				System.out.println("Invalid command.Command");
		// 				break;
		// 		}
		// 	}

		// }


		// else if (privilege==1){
		// 	CineplexAdminAccount cineplexAdmin = (CineplexAdminAccount) curAcc;
		// 	System.out.println("Logged in as Cineplex Admin: " + cineplexAdmin.getLoginId());
		// 	Cineplex cineplex = Company.searchCineplexByLocation(cineplexAdmin.getLocation());
		// 	while (true) {
		// 		System.out.println("=================");
		// 		cineplexAdminMenu();
		// 		userCh = scanner.nextInt();

		// 		if (userCh == 4) break;
		// 		switch (userCh){
		// 			case 1:
		// 				new createShowCommand(cineplex).execute();
		// 				cineplex.listShows();
		// 				break;
		// 			case 2:
		// 				//to implement
		// 				break;

		// 			case 3:
		// 				new deleteShowCommand(cineplex).execute();
		// 				tempCine.listShows();
		// 				break;

		// 			default:
		// 				System.out.println("Invalid Option");
		// 		}
		// 	}


		// }

		// else if (privilege==2){
		// 	System.out.println("Logged in as Company Admin: " + curAcc.getLoginId());
		// 	while(true){
		// 		System.out.println("=================");
		// 		companyAdminMenu();
		// 		userCh = scanner.nextInt();
		// 		if (userCh == 5) break;
		// 		switch (userCh){
		// 			case 1:
		// 				new createMovieListingCommand().execute();
		// 				Company.listMovies();
		// 				break;
		// 			case 2:
		// 				//to implement editMovieListingCommand
		// 				break;

		// 			case 3:
		// 				new deleteMovieListingCommand().execute();
		// 				Company.listMovies();
		// 				break;

		// 			case 4:
		// 				break;

		// 			default:
		// 				System.out.println("Invalid Option");
		// 		}
		// 	}
		// }


		//Update Functions
//		CSVUpdater.updateAccounts("src\\database\\accountDB.csv", accounts);
//		CSVUpdater.updateBookings("src\\database\\bookingDB.csv", bookings);
//		CSVUpdater.updateCompany("src\\database\\CompanyDB.csv", );
		CSVUpdater.updateMovies("src\\database\\movieDB.csv", movies);
		CSVUpdater.updateShows("src\\database\\showDB.csv", shows);
		CSVUpdater.updateTickets("src\\database\\ticketDB.csv", bookings);
		CSVUpdater.updateReviewList("src\\database\\reviewListDB.csv", movies);
		endProgram();
	}

	private static void greetUser(){
		System.out.println("Hello I'm MOBLIMA");
		System.out.println("I am a movie booking & listing management application!");
	}

	private static void userMenu() {
		System.out.println("1. Search Movie");
		System.out.println("2. List Movies");
		System.out.println("3. View Seat Availability");
		System.out.println("4. Book Tickets");
		System.out.println("5. View Booking History");
		System.out.println("6. Review Movie");
		System.out.println("7. Top 5 ranking by ticket sales");
		System.out.println("8. Top 5 ranking by review ratings");
		System.out.println("9. Exit");
	}

	private static void cineplexAdminMenu(){
		System.out.println("1. Create cinema showtimes");
		System.out.println("2. Update cinema showtimes");
		System.out.println("3. Remove cinema showtimes");
		System.out.println("4. Exit");

	}

	private static void companyAdminMenu(){
		System.out.println("1. Create movie listing");
		System.out.println("2. Update movie listing");
		System.out.println("3. Remove movie listing");
		System.out.println("4. Configure system settings");
		System.out.println("5. Exit");
	}

	private static Account login(String loginId, String password, Account[] accounts){
		for (int i = 0; i < accounts.length; i++){
			if (accounts[i].getLoginId().equals(loginId)){
				if(accounts[i].login(loginId,password)!=-1){
					return accounts[i];
				}
			}
		}
		return null; //account not found
	}

	private static void showLine(){
		System.out.println("____________________________");
	}

	private static void endProgram(){
		System.out.println("Bye!");
	}
}