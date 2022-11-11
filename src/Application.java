import account.*;
import command.admin.*;
import gui.*;
import moblima.SilverVillage;
import moblima.cineplex.Cineplex;
import moblima.cineplex.cinema.Cinema;
import moblima.cineplex.cinema.CinemaClass;
import moblima.movie.Movie;
import moblima.movie.MovieStatus;
import moblima.show.Show;
import system.PublicHoliday;
import system.SystemSettings;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Application {


	public static void main(String[] args) {
		new Application().run();
	}

	public void run() {

//		ShowList showList = CSVReader.readShowsFromCSV("src/database/showDB.csv");
//		ArrayList<Movie> arrayMovie = CSVReader.readMoviesFromCSV("src/database/movieDB.csv");
//		MovieList movieList = new MovieList();
//		for (Movie movie: arrayMovie){
//			movieList.addMovie(movie);
//		}
//
//		ArrayList<Booking> arrayBooking = CSVReader.readBookingsFromCSV("src/database/bookingDB.csv");
//		BookingHistory bookingHistory = new BookingHistory();
//		for (Booking booking: arrayBooking){
//			bookingHistory.addBooking(booking);
//		}
//
//		ArrayList<Cineplex> arrayCineplex = CSVReader.readCineplexFromCSV("src/database/companyDB.csv", showList.getShows());
//		CineplexList cineplexList = new CineplexList();
//		for (Cineplex cineplex: arrayCineplex){
//			cineplexList.addCineplex(cineplex);
//		}
//
//		ArrayList<ReviewList> arrayReview = CSVReader.readReviewsFromCSV("src/database/reviewListDB.csv");
//
//
//		ArrayList<Account> arrayAccount = CSVReader.readAccountsFromCSV("src/database/accountDB.csv", arrayCineplex);


		SystemSettings ss = new SystemSettings();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse("2022-12-25 00:00", formatter);
		ArrayList<PublicHoliday> publicHolidays = new ArrayList<PublicHoliday>();
		publicHolidays.add(new PublicHoliday(LocalDateTime.parse("2022-12-25 00:00", formatter), "Christmas"));
		publicHolidays.add(new PublicHoliday(LocalDateTime.parse("2022-10-31 00:00", formatter), "Halloween"));

		//for (int i =0; i < publicHolidays.size(); i++){
		//	System.out.println(publicHolidays.get(i).getDate().getDayOfYear());
		//	System.out.println(publicHolidays.get(i).getName());
		//}


		Movie a = new Movie("Lion King", MovieStatus.PREVIEW, "c", "d", Arrays.asList("guy1", "guy2", "woman1"), LocalDateTime.parse("2022-12-29 12:00", formatter));
		Movie b = new Movie("Toy Story", MovieStatus.NOW_SHOWING, "x", "w", Arrays.asList("guy1", "woman2", "woman3"), LocalDateTime.parse("2022-12-26 12:00", formatter));
		SilverVillage.getMovieList().addMovie(a);
		SilverVillage.getMovieList().addMovie(b);
		Cineplex tempCine = new Cineplex("hello cinema", "bedok");
		Cineplex changiCine = new Cineplex("Golden Village", "changi");
		Cinema cn = new Cinema(CinemaClass.GOLD);
		Cinema cn2 = new Cinema(CinemaClass.NORMAL);
		tempCine.addCinema(cn);
		tempCine.addCinema(cn2);
		Cinema cn3 = new Cinema(CinemaClass.PLATINUM);
		Cinema cn4 = new Cinema(CinemaClass.GOLD);
		changiCine.addCinema(cn3);
		changiCine.addCinema(cn4);
		tempCine.getShowList().addShow(new Show(LocalDateTime.of(2015,
				Month.JULY, 29, 19, 30), cn, a));
		tempCine.getShowList().addShow(new Show(LocalDateTime.of(2017,
				Month.JULY, 29, 16, 20), cn2, a));
		tempCine.getShowList().addShow(new Show(LocalDateTime.of(2018,
				Month.JULY, 29, 16, 20), cn2, b));
		tempCine.getShowList().addShow(new Show(LocalDateTime.of(2022,
				Month.JULY, 29, 16, 20), cn, b));
		changiCine.getShowList().addShow(new Show(LocalDateTime.of(2026,
				Month.JULY, 29, 16, 20), cn3, b));
		changiCine.getShowList().addShow(new Show(LocalDateTime.of(2026,
				Month.JULY, 29, 16, 20), cn4, a));

		SilverVillage.getCineplexList().addCineplex(tempCine);
		SilverVillage.getCineplexList().addCineplex(changiCine);
		Account[] accounts = new Account[4];
		accounts[0] = new UserAccount("apple", "sauce", Privilege.User, "123@gmail.com", "999", "peter");
		accounts[1] = new CineplexAdminAccount("orange", "sauce", Privilege.CineplexAdmin, tempCine, "abc@gmai.com", "992", "stacey");
		accounts[2] = new CineplexAdminAccount("banana", "sauce", Privilege.CineplexAdmin, changiCine, "999@gmai.com", "992", "harold");
		accounts[3] = new CompanyAdminAccount("durian", "sauce", Privilege.CompanyAdmin, "999@gmai.com", "992", "june");
		SystemSettings.addAccount(accounts[0]);
		SystemSettings.addAccount(accounts[1]);
		SystemSettings.addAccount(accounts[2]);
		SystemSettings.addAccount(accounts[3]);
		// end of test cases

		// load in CSV

		// end of load in CSV

		// Auto update expired movie status
		SilverVillage.getMovieList().updateExpiredMovieStatus();
		new GreetUserMenu().display();
		Scanner scanner = new Scanner(System.in);
		int userCh = 0;
		Privilege privilege;
		Cineplex cineplex = null;
		Account curAcc = null;

		System.out.println();
		SilverVillage.getCineplexList().listCineplexes();

		while (true) {

			System.out.println();
			System.out.print("Please enter the cinema location number: ");

			if (scanner.hasNextInt() == false) {
				System.out.println("Invalid input format for location number. Please try again.");
				scanner.next();
				continue;
			}
			int locationCh = scanner.nextInt();
			scanner.nextLine();
			cineplex = SilverVillage.getCineplexList().getCineplexByIndex(locationCh - 1);
			if (cineplex != null) break;
			System.out.println("Invalid option");
		}

		System.out.println();
		System.out.println("The location you have chosen is: " + cineplex.getBranchName());

		while (true) {
			if (curAcc == null) {
				GuestGUI guestGUI = new GuestGUI(cineplex, curAcc);
				guestGUI.display();
				if (guestGUI.execute() == 0) {
					break;
				}
				curAcc = guestGUI.getAccount();
				cineplex = guestGUI.getCineplex();
			} else if (curAcc.getPrivilege() == Privilege.User) {
				UserGUI userGUI = new UserGUI(cineplex, curAcc);
				userGUI.display();
				if (userGUI.execute() == 0) {
					break;
				}
				curAcc = userGUI.getAccount();
				cineplex = userGUI.getCineplex();
			} else if (curAcc.getPrivilege() == Privilege.CineplexAdmin) {
				CineplexAdminAccount cineplexAdmin = (CineplexAdminAccount) curAcc;
				System.out.println("yo");
				CineplexAdminGUI cineplexAdminGUI = new CineplexAdminGUI(cineplexAdmin);
				System.out.println("test");
				cineplexAdminGUI.display();
				if(cineplexAdminGUI.execute()==0){
					break;
				}
				curAcc = cineplexAdminGUI.getAccount();

			} else if (curAcc.getPrivilege() == Privilege.CompanyAdmin) {
				CompanyAdminGUI companyAdminGUI = new CompanyAdminGUI(curAcc);
				companyAdminGUI.display();
				if(companyAdminGUI.execute()==0){
					break;
				}
				curAcc = companyAdminGUI.getAccount();
			}
		}
		new EndProgramMenu().display();
	}


}