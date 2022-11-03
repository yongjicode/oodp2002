import command.userModule.*;
import command.adminModule.*;
import moblima.Cinema;
import moblima.Cineplex;
import moblima.Company;
import moblima.Movie;
import account.Account;
import account.UserAccount;
import account.CineplexAdminAccount;
import account.CompanyAdminAccount;

import java.util.Scanner;


public class Application {
	/**
	 * Main entry-point for the java.duke.Duke application.
	 */
	public static void main(String[] args) {
		new Application().run();
	}



	public void run() {
		Company.addMovie(new Movie("a","b","c","d","e"));
		Company.addMovie(new Movie("z","y","x","w","v"));
		Cineplex tempCine = new Cineplex("hello cinema","bedok");
		tempCine.addCinema(new Cinema("gold class"));
		Company.addCineplex(tempCine);
		Account[] accounts = new Account[3];
		Account curAcc;
		accounts[0] = new UserAccount("apple","sauce",0);
		accounts[1] = new CineplexAdminAccount("orange","sauce",1, "999", "bedok");
		accounts[2] = new CompanyAdminAccount("durian","sauce",2,"995");
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
			System.out.println("Logged in as user: " + curAcc.getLoginId());
			//get Cinema LOCATION
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
						new showSeatAvailabilityCommand(tempCine).execute();
						break;
					case 4:
						new bookTicketCommand(tempCine).execute();
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


		else if (privilege==1){
			CineplexAdminAccount cineplexAdmin = (CineplexAdminAccount) curAcc;
			System.out.println("Logged in as Cineplex Admin: " + cineplexAdmin.getLoginId());
			while (true) {
				System.out.println("=================");
				cineplexAdminMenu();
				userCh = scanner.nextInt();
				if (userCh == 4) break;
				switch (userCh){
					case 1:
						Cineplex cineplex = Company.searchCineplexByLocation(cineplexAdmin.getLocation());
						new createShowCommand(cineplex).execute();
						cineplex.listShows();
						break;
					case 2:
						//to implement
						break;

					case 3:
						new deleteShowCommand(tempCine).execute();
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
		System.out.println("6. Top 5 ranking by ticket sales");
		System.out.println("7. Top 5 ranking by review ratings");
		System.out.println("8. Exit");
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