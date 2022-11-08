import command.userModule.*;
import command.adminModule.*;
import moblima.*;

import account.*;

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
		SystemSettings ss = new SystemSettings();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse("2022-12-25 00:00", formatter);
		ArrayList<PublicHoliday> publicHolidays = new ArrayList<PublicHoliday>();
		publicHolidays.add(new PublicHoliday( LocalDateTime.parse("2022-12-25 00:00", formatter), "Christmas"));
		publicHolidays.add(new PublicHoliday( LocalDateTime.parse("2022-10-31 00:00", formatter), "Halloween"));

		for (int i =0; i < publicHolidays.size(); i++){
			System.out.println(publicHolidays.get(i).getDate().getDayOfYear());
			System.out.println(publicHolidays.get(i).getName());
		}


		Movie a = new Movie("Lion King","b","c","d","e");
		Movie b = new Movie("Toy Story","y","x","w","v");
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
		accounts[0] = new UserAccount("apple","sauce",0,"123@gmail.com","999","peter");
		accounts[1] = new CineplexAdminAccount("orange","sauce",1, tempCine,"abc@gmai.com","992","stacey");
		accounts[2] = new CineplexAdminAccount("banana","sauce",1,changiCine,"999@gmai.com","992","harold");
		accounts[3] = new CompanyAdminAccount("durian","sauce",2,"999@gmai.com","992","june");
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
				if (userCh == 9) break;
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
						if(ss.getTop5MovieTicketsBool() && ss.getTop5MovieRatingsBool()){
							showTop5Options();
							System.out.println("Enter Choice:");
							userCh = scanner.nextInt();
							scanner.nextLine();
							if (userCh != 1  && userCh!=2){
								System.out.println("Invalid Entry");
								break;
							}
							if (userCh == 1){
								new rankTicketSalesCommand().execute();
							}
							else{
								new rankReviewRatingsCommand().execute();
							}
						}
						else if (ss.getTop5MovieRatingsBool()){
							new rankReviewRatingsCommand().execute();
						}
						else if (ss.getTop5MovieTicketsBool()){
							new rankTicketSalesCommand().execute();
						}
						else{
							System.out.println("Data unavailable");
						}
						break;
						
					case 8:
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
						ss.printSettings();
						companySettingsMenu();
						System.out.println("Enter Choice:");

						userCh = scanner.nextInt();
						scanner.nextLine();
						switch(userCh){
							case 1:
								new enableTop5TicketSalesCommand(ss).execute();
								System.out.println("Ranking Top 5 Ticket Sales enabled");
								break;
							case 2:
								new disableTop5TicketSalesCommand(ss).execute();
								System.out.println("Ranking Top 5 Ticket Sales disabled");
								break;
							case 3:
								new enableTop5ReviewsCommand(ss).execute();
								System.out.println("Ranking by Top 5 Movie Ratings enabled");
								break;
							case 4:
								new disableTop5ReviewsCommand(ss).execute();
								System.out.println("Ranking by Top 5 Movie Ratings disabled");
								break;
							case 5:
								break;
							case 6:
								break;
							case 7:
								break;

							default:
								System.out.println("Invalid Entry");
								break;
						}
						ss.printSettings();
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
			System.out.println("Currently logged in as: " + curAccount.getLoginId());
		}
		else {
			System.out.println("Currently viewing as guest");
		}
		if (loc.length() != 0) {
			System.out.println("Currently Viewing branch:"+ loc);
		}
		System.out.println("1. Search Movie");
		System.out.println("2. List Movies");
		System.out.println("3. View Seat Availability");
		System.out.println("4. Book Tickets");
		System.out.println("5. View Booking History");
		System.out.println("6. Review Movie");
		System.out.println("7. Top 5 Ranking");
		if (curAccount==null) {
			System.out.println("8. Login");
		}
		else {
			System.out.println("8. Logout");
		}
		System.out.println("9. Exit");
		System.out.println("=========================================");
	}

	private static void cineplexAdminMenu(Account curAccount, String location){
		System.out.println();
		System.out.println("=========================================");
		if (curAccount != null) {
			System.out.println("Currently logged in as cineplex admin: " + curAccount.getLoginId());
		}
		if (location.length() != 0) {
			System.out.println("Currently Viewing branch:"+ location);
		}
		System.out.println("1. Create cinema showtimes");
		System.out.println("2. Update cinema showtimes");
		System.out.println("3. Remove cinema showtimes");
		System.out.println("4. Logout");
		System.out.println("5. Exit");
		System.out.println("=========================================");

	}

	private static void companyAdminMenu(Account curAccount){
		System.out.println();
		System.out.println("========== Company Admin Menu ===========");
		if (curAccount != null) {
			System.out.println("Currently logged in as company admin: " + curAccount.getLoginId());
		}

		System.out.println("1. Create movie listing");
		System.out.println("2. Update movie listing");
		System.out.println("3. Remove movie listing");
		System.out.println("4. Configure system settings");
		System.out.println("5. Logout");
		System.out.println("6. Exit");
		System.out.println("=========================================");
	}

	private static void showTop5Options(){
		System.out.println("=========================================");
		System.out.println("1. Show Top 5 Movies by Ticket Sales");
		System.out.println("2. Show Top 5 Movies by Reviews");
		System.out.println("=========================================");

	}

	private static void companySettingsMenu(){
		System.out.println("=========================================");
		System.out.println("1. Enable Showing Top 5 Movie Based on Ticket Sales");
		System.out.println("2. Disable Showing Top 5 Movie Based on Ticket Sales");
		System.out.println("3. Enable Showing Top 5 Movie Based on Ratings");
		System.out.println("4. Disable Showing Top 5 Movie Based on Ratings");
		System.out.println("5. Add Public Holidays");
		System.out.println("6. Remove Public Holidays");
		System.out.println("7. Adjust ticket base price");
		System.out.println("=========================================");

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