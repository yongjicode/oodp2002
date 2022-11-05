import command.userModule.*;
import command.adminModule.*;
import moblima.Cinema;
import moblima.Cineplex;
import moblima.Company;
import moblima.Movie;
import moblima.Show;
import account.Account;
import account.UserAccount;
import account.CineplexAdminAccount;
import account.CompanyAdminAccount;
import moblima.Booking;
import moblima.MovieTicket;
import moblima.ReviewList;
import moblima.Review;
import moblima.Movie;
import moblima.Show;
import moblima.CSVReader;


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

		//test cases below
		Movie a = new Movie("a","b","c","d","e");
		Movie b = new Movie("z","y","x","w","v");
		Company.addMovie(a);
		Company.addMovie(b);
		Cineplex tempCine = new Cineplex("hello cinema","bedok");
		Cineplex changiCine = new Cineplex("Golden Village","changi");
		Cinema cn = new Cinema("gold class");
		Cinema cn2 = new Cinema("Poor people");
		tempCine.addCinema(cn);
		tempCine.addCinema(cn2);
		Cinema cn3 = new Cinema("Business Class");
		Cinema cn4 = new Cinema("Economy");
		changiCine.addCinema(cn3);
		changiCine.addCinema(cn4);
		tempCine.addShow(new Show(LocalDateTime.of(2015,
				Month.JULY, 29, 19, 30), cn, a));
		tempCine.addShow(new Show(LocalDateTime.of(2017,
				Month.JULY, 29, 16, 20), cn2, a));
		tempCine.addShow(new Show(LocalDateTime.of(2018,
				Month.JULY, 29, 16, 20), cn2, b));
		tempCine.addShow(new Show(LocalDateTime.of(2022,
				Month.JULY, 29, 16, 20), cn, b));
		changiCine.addShow(new Show(LocalDateTime.of(2026,
				Month.JULY, 29, 16, 20), cn3, b));
		changiCine.addShow(new Show(LocalDateTime.of(2026,
				Month.JULY, 29, 16, 20), cn4, a));

		Company.addCineplex(tempCine);
		Company.addCineplex(changiCine);
		Account[] accounts = new Account[4];
		Account curAcc;
		accounts[0] = new UserAccount("apple","sauce",0);
		accounts[1] = new CineplexAdminAccount("orange","sauce",1, "999", "bedok");
		accounts[2] = new CineplexAdminAccount("banana","sauce",1, "999", "changi");
		accounts[3] = new CompanyAdminAccount("durian","sauce",2,"995");
		Scanner scanner = new Scanner(System.in);
		int userCh = 0;
		int privilege;
		
		greetUser();
		while(true){
			System.out.println("Login ID:");
			String userLogin = scanner.nextLine();
			System.out.println("Password:");
			String password = scanner.nextLine();
			curAcc = login(userLogin,password,accounts);
			if (curAcc==null){
				System.out.println("Invalid Details");
				continue;
			}
			else{
				privilege = curAcc.getPrivilege();
				break;
			}
		}

		if (privilege == 0){
			UserAccount userAcc = (UserAccount) curAcc;
			System.out.println("Logged in as user: " + curAcc.getLoginId());
			Company.listLocations();
			System.out.println("Enter Cinema Location: ");
			String location = scanner.nextLine();
			Cineplex userCineplex = Company.searchCineplexByLocation(location);
			System.out.println(userCineplex.getLocation());
			while (true) {

				System.out.println("=================");
				userMenu();
				userCh = scanner.nextInt();
				if (userCh == 9) break;
				switch (userCh) {
					case 1:
						new userSearchMovieCommand().execute();
						break;
					case 2:
						new userListMoviesCommand().execute();
						break;
					case 3:
						new showSeatAvailabilityCommand(userCineplex).execute();
						break;
					case 4:
						new bookTicketCommand(userCineplex, userAcc.getLoginId()).execute();
						break;
					case 5:
						new viewBookingHistoryCommand(userAcc.getLoginId()).execute();
						break;

					case 6:
						//need to implement
						//new reviewMovieCommand().execute();
						break;
					case 7:
						new rankTicketSalesCommand().execute();
						break;
					case 8:
						new rankReviewRatingsCommand().execute();
						break;
					default:
						System.out.println("Invalid command.Command");
						break;
				}
			}

		}


		else if (privilege==1){
			CineplexAdminAccount cineplexAdmin = (CineplexAdminAccount) curAcc;
			System.out.println("Logged in as Cineplex Admin: " + cineplexAdmin.getLoginId());
			Cineplex cineplex = Company.searchCineplexByLocation(cineplexAdmin.getLocation());
			while (true) {
				System.out.println("=================");
				cineplexAdminMenu();
				userCh = scanner.nextInt();

				if (userCh == 4) break;
				switch (userCh){
					case 1:
						new createShowCommand(cineplex).execute();
						cineplex.listShows();
						break;
					case 2:
						//to implement
						break;

					case 3:
						new deleteShowCommand(cineplex).execute();
						tempCine.listShows();
						break;

					default:
						System.out.println("Invalid Option");
				}
			}


		}

		else if (privilege==2){
			System.out.println("Logged in as Company Admin: " + curAcc.getLoginId());
			while(true){
				System.out.println("=================");
				companyAdminMenu();
				userCh = scanner.nextInt();
				if (userCh == 5) break;
				switch (userCh){
					case 1:
						new createMovieListingCommand().execute();
						Company.listMovies();
						break;
					case 2:
						//to implement editMovieListingCommand
						break;

					case 3:
						new deleteMovieListingCommand().execute();
						Company.listMovies();
						break;

					case 4:
						break;

					default:
						System.out.println("Invalid Option");
				}
			}
		}

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