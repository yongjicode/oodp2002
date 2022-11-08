import command.userModule.*;
import command.adminModule.*;
import moblima.*;
import account.Account;
import account.UserAccount;
import account.CineplexAdminAccount;
import account.CompanyAdminAccount;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;


public class Application {
	

	public static void main(String[] args) {
		new Application().run();
	}

	public void run() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse("2022-12-25 00:00", formatter);
		ArrayList<PublicHoliday> publicHolidays = new ArrayList<PublicHoliday>();
		publicHolidays.add(new PublicHoliday( LocalDateTime.parse("2022-12-25 00:00", formatter), "Christmas"));
		publicHolidays.add(new PublicHoliday( LocalDateTime.parse("2022-10-31 00:00", formatter), "Halloween"));

		for (int i =0; i < publicHolidays.size(); i++){
			System.out.println(publicHolidays.get(i).getDate().getDayOfYear());
			System.out.println(publicHolidays.get(i).getName());
		}


		Movie a = new Movie("Lion King",MovieStatus.PREVIEW,"c","d","e", LocalDateTime.parse("2022-12-29 12:00", formatter));
		Movie b = new Movie("Toy Story",MovieStatus.NOW_SHOWING,"x","w","v", LocalDateTime.parse("2022-12-26 12:00", formatter));
		Company.addMovie(a);
		Company.addMovie(b);
		Cineplex tempCine = new Cineplex("hello cinema","bedok");
		Cineplex changiCine = new Cineplex("Golden Village","changi");
		Cinema cn = new Cinema(CinemaClass.GOLD);
		Cinema cn2 = new Cinema(CinemaClass.NORMAL);
		tempCine.addCinema(cn);
		tempCine.addCinema(cn2);
		Cinema cn3 = new Cinema(CinemaClass.PLATINUM);
		Cinema cn4 = new Cinema(CinemaClass.GOLD);
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
		//Company.add
		Account[] accounts = new Account[4];
		accounts[0] = new UserAccount("apple","sauce",0,"123@gmail.com","999","peter");
		accounts[1] = new CineplexAdminAccount("orange","sauce",1, tempCine,"abc@gmai.com","992","stacey");
		accounts[2] = new CineplexAdminAccount("banana","sauce",1,changiCine,"999@gmai.com","992","harold");
		accounts[3] = new CompanyAdminAccount("durian","sauce",2,"999@gmai.com","992","june");
		// end of test cases

		// load in CSV

		// end of load in CSV

		// Auto update expired movie status
		Company.updateExpiredMovieStatus();

		Scanner scanner = new Scanner(System.in);
		int userCh = 0;
		int privilege;
		Cineplex cineplex = null;
		Account curAcc = null;
		greetUser();
		System.out.println();
		
		while(true) {
			Company.listLocations();
			System.out.print("Please choose cinema location: ");
			int locationCh = scanner.nextInt();
			scanner.nextLine();
			cineplex = Company.getCineplexByIndex(locationCh-1);
			if (cineplex!=null) break;
			System.out.println("Invalid option");
		}
		
		System.out.println();
		System.out.println("The location you have chosen is: " + cineplex.getLocation());
		
		while(true) {
			if(curAcc == null || curAcc.getPrivilege() == 0) //guest or user accounts
			{
				userMenu(curAcc,cineplex.getLocation());
				System.out.print("Please enter the option number: ");
				userCh = scanner.nextInt();
				scanner.nextLine();
				if (userCh == 10) break;
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
						if(curAcc == null) {
							new guestBookTicketCommand(cineplex).execute();
						}
						else {
							new bookTicketCommand(cineplex, curAcc).execute();
						}
						
						break;
					case 5:
						if (curAcc == null) {
							new guestViewBookingHistory().execute();
						}
						else {
							new viewBookingHistoryCommand(curAcc.getName()).execute();
						}
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
						
					case 9:
						if(curAcc == null) {
							while(true){
								System.out.print("Please enter your Login ID: ");
								String userLogin = scanner.nextLine();
								System.out.print("Please enter your Password: ");
								String password = scanner.nextLine();
								curAcc = login(userLogin.toLowerCase(),password,accounts);
								if (curAcc==null){
									System.out.println("Invalid Details. Please try again.");
									continue;
								}
								else{
									privilege = curAcc.getPrivilege();
									break;
								}
							}
							System.out.println("Logged in successfully");
							
						}
						else {
							curAcc = null;
							System.out.println("Logged out successfully");
						}
						break;
						
					default:
						System.out.println("Invalid option. Please try again.");
						break;
				}
				
			}
			else if (curAcc.getPrivilege() == 1) {
				CineplexAdminAccount cineplexAdmin = (CineplexAdminAccount) curAcc;
				cineplexAdminMenu(curAcc,cineplexAdmin.getCineplex().getLocation());
				System.out.print("Please enter the option number: ");
				userCh = scanner.nextInt();
				scanner.nextLine();
				if (userCh == 5) break;
				switch (userCh){
					case 1:
						new createShowCommand(cineplexAdmin.getCineplex()).execute();
						break;
					case 2:
						//to implement
						break;

					case 3:
						new deleteShowCommand(cineplexAdmin.getCineplex()).execute();
						break;
						
					case 4:
						curAcc = null;
						System.out.println("Logged out successfully...");
						break;

					default:
						System.out.println("Invalid Option. Please try again.");
				}
				
			}
			
			else if (curAcc.getPrivilege() == 2) {
				companyAdminMenu(curAcc);
				System.out.print("Please enter the option number: ");
				userCh = scanner.nextInt();
				scanner.nextLine();
				if (userCh == 6) break;
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
						//systemConfig
						break;

					case 5:
						curAcc = null;
						System.out.println("Logged out successfully...");
						break;

					default:
						System.out.println("Invalid Option. Please try again.");
				}
			}
			
		}

		endProgram();
	}

	private static void greetUser(){
		System.out.println("Hello I'm MOBLIMA");
		System.out.println("I am a movie booking & listing management application!");
	}
	

	private static void userMenu(Account curAccount, String loc) {
		System.out.println();
		System.out.println("=========================================");

		if (curAccount != null) {
			System.out.println("Logged in as User: " + curAccount.getLoginId());
		}
		else {
			System.out.println("Viewing as: Guest");
		}
		if (loc.length() != 0) {
			System.out.println("Cineplex Branch: "+ loc);
		}
		System.out.println("============== User Menu ================");
		System.out.println("1. Search Movie");
		System.out.println("2. List Movies");
		System.out.println("3. View Seat Availability");
		System.out.println("4. Book Tickets");
		System.out.println("5. View Booking History");
		System.out.println("6. Review Movie");
		System.out.println("7. Top 5 Ranking");
		if (curAccount==null) {
			System.out.println("9. Login");
		}
		else {
			System.out.println("9. Logout");
		}
		System.out.println("10. Exit");
		System.out.println("=========================================");
	}

	private static void cineplexAdminMenu(Account curAccount, String location){
		System.out.println();
		System.out.println("=========================================");
		if (curAccount != null) {
			System.out.println("Logged in as Cineplex Admin: " + curAccount.getLoginId());
		}
		if (location.length() != 0) {
			System.out.println("Cineplex Branch: "+ location);
		}
		System.out.println("========== Cineplex Admin Menu ==========");
		System.out.println("1. Create cinema showtimes");
		System.out.println("2. Update cinema showtimes");
		System.out.println("3. Remove cinema showtimes");
		System.out.println("4. Logout");
		System.out.println("5. Exit");
		System.out.println("=========================================");

	}

	private static void companyAdminMenu(Account curAccount){
		System.out.println();
		System.out.println("=========================================");
		if (curAccount != null) {
			System.out.println("Logged in as Company Admin: " + curAccount.getLoginId());
		}
		System.out.println("========== Company Admin Menu ===========");
		System.out.println("1. Create movie listing");
		System.out.println("2. Update movie listing");
		System.out.println("3. Remove movie listing");
		System.out.println("4. Configure system settings");
		System.out.println("5. Logout");
		System.out.println("6. Exit");
		System.out.println("=========================================");
	}

	private static void showTop5Options(boolean showTickets, boolean showReviews){
		if (showTickets == false){
			System.out.println("Showing Top 5 Movies by Reviews");
		}
		else if (showReviews == false){

		}
		else{
			System.out.println("1. Show Top 5 Movies by Ticket Sales");
			System.out.println("2. Show Top 5 Movies by Reviews");
		}
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