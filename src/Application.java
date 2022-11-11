import account.*;
import command.admin.*;
import command.customer.*;
import exceptions.moblimaExceptions.invalidInputException;
import gui.*;
import moblima.SilverVillage;
import moblima.cineplex.Cineplex;
import moblima.cineplex.cinema.Cinema;
import moblima.cineplex.cinema.CinemaClass;
import moblima.cineplex.CineplexList;
import moblima.movie.Movie;
import moblima.movie.MovieStatus;
import moblima.movie.MovieList;
import moblima.movie.review.ReviewList;
import moblima.show.Show;
import moblima.show.ShowList;
import moblima.booking.Booking;
import moblima.booking.BookingHistory;
import moblima.booking.ticket.MovieTicket;
import system.PublicHoliday;
import system.SystemSettings;
import moblima.CSVReader;

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

		

		// ArrayList<Movie> arrayMovie = CSVReader.readMoviesFromCSV("src/database/MovieDB.csv");
		// MovieList movieList = new MovieList();
		// for (Movie movie: arrayMovie){
		// 	movieList.addMovie(movie);
		// }
		// movieList.listMovies(2);

		// ShowList showList = CSVReader.readShowsFromCSV("src/database/ShowDB.csv");
		// showList.listShows();

		// ArrayList<Cineplex> arrayCineplex = CSVReader.readCineplexFromCSV("src/database/CineplexDB.csv", showList.getShows());
		// CineplexList cineplexList = new CineplexList();
		// for (Cineplex cineplex: arrayCineplex){
		// 	cineplexList.addCineplex(cineplex);
		// }
		// cineplexList.listLocations();

		// ArrayList<Booking> arrayBooking = CSVReader.readBookingsFromCSV("src/database/BookingDB.csv");
		// BookingHistory bookingHistory = new BookingHistory();
		// for (Booking booking: arrayBooking){
		// 	bookingHistory.addBooking(booking);
		// }
		// bookingHistory.showUserBookingHistory("Jin Kai");


		// ArrayList<ReviewList> arrayReview = CSVReader.readReviewsFromCSV("src/database/ReviewListDB.csv");
		// for (ReviewList reviewList: arrayReview){
		// 	reviewList.listReviews();
		// }

		// ArrayList<Account> arrayAccount = CSVReader.readAccountsFromCSV("src\\database\\AccountDB.csv", arrayCineplex);
		// for (Account acc: arrayAccount){
		// 	System.out.println("account name: " + acc.getName());
		// }

		// ArrayList<MovieTicket> arrayMovieTicket = CSVReader.readTicketsFromCSV("src\\database\\MovieTicketDB.csv");
		// for (MovieTicket mt: arrayMovieTicket){
		// 	mt.printTicketDetails();
		// }





		SystemSettings ss = new SystemSettings();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse("2022-12-25 00:00", formatter);
		ArrayList<PublicHoliday> publicHolidays = new ArrayList<PublicHoliday>();
		publicHolidays.add(new PublicHoliday( LocalDateTime.parse("2022-12-25 00:00", formatter), "Christmas"));
		publicHolidays.add(new PublicHoliday( LocalDateTime.parse("2022-10-31 00:00", formatter), "Halloween"));

		//for (int i =0; i < publicHolidays.size(); i++){
		//	System.out.println(publicHolidays.get(i).getDate().getDayOfYear());
		//	System.out.println(publicHolidays.get(i).getName());
		//}


		Movie a = new Movie("Lion King", MovieStatus.PREVIEW,"c","d", Arrays.asList("guy1", "guy2", "woman1"), LocalDateTime.parse("2022-12-29 12:00", formatter));
		Movie b = new Movie("Toy Story",MovieStatus.NOW_SHOWING,"x","w",Arrays.asList("guy1", "woman2", "woman3"), LocalDateTime.parse("2022-12-26 12:00", formatter));
		SilverVillage.getMovieList().addMovie(a);
		SilverVillage.getMovieList().addMovie(b);
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
		accounts[0] = new UserAccount("apple","sauce",Privilege.User,"123@gmail.com","999","peter");
		accounts[1] = new CineplexAdminAccount("orange","sauce",Privilege.CineplexAdmin, tempCine,"abc@gmai.com","992","stacey");
		accounts[2] = new CineplexAdminAccount("banana","sauce",Privilege.CineplexAdmin,changiCine,"999@gmai.com","992","harold");
		accounts[3] = new CompanyAdminAccount("durian","sauce",Privilege.CompanyAdmin,"999@gmai.com","992","june");
		// end of test cases

		// load in CSV

		// end of load in CSV

		// Auto update expired movie status
		SilverVillage.getMovieList().updateExpiredMovieStatus();

		Scanner scanner = new Scanner(System.in);
		int userCh = 0;
		Privilege privilege;
		Cineplex cineplex = null;
		Account curAcc = null;
		new greetUserMenu().display();
		System.out.println();
		SilverVillage.getCineplexList().listCineplexes();

		while(true) {

			System.out.println();
			System.out.print("Please enter the cinema location number: ");

			if(scanner.hasNextInt() == false) {
				System.out.println("Invalid input format for location number. Please try again.");
				scanner.next();
				continue;
			}
			int locationCh = scanner.nextInt();
			scanner.nextLine();
			cineplex = SilverVillage.getCineplexList().getCineplexByIndex(locationCh-1);
			if (cineplex!=null) break;
			System.out.println("Invalid option");
		}

		System.out.println();
		System.out.println("The location you have chosen is: " + cineplex.getBranchName());
		//System.out.println();

		// TODO nicky add more classes
		while(true) {
			if(curAcc == null || curAcc.getPrivilege() == Privilege.User) //guest or user accounts
			{
				if(curAcc == null) new gui.guestMenu(cineplex).display();
				else new gui.userMenu(cineplex,curAcc).display();
				userCh = scanner.nextInt();
				scanner.nextLine();
				System.out.println();
				System.out.println("=========================================");

				if (userCh == 11) {
					System.out.println();
					System.out.println("Thank you for using MOBLIMA. Have a nice day!");
					System.out.println();
					System.out.println("=========================================");
					break;
				}
				switch (userCh) {
					case 1:
						new UserSearchMovieCommand().execute();
						break;
					case 2:
						new UserListMoviesCommand().execute();
						break;
					case 3:
						new ListShowsCommand(cineplex).execute();
						break;
					case 4:
						new ShowSeatAvailabilityCommand(cineplex).execute();
						break;
					case 5:

						if(curAcc == null) {
							new GuestBookTicketCommand(cineplex).execute();
						}
						else {
							new BookTicketCommand(cineplex, curAcc).execute();
						}
						break;
					case 6:
						if (curAcc == null) {
							new GuestViewBookingHistoryCommand().execute();
						}
						else {
							new ViewBookingHistoryCommand(curAcc.getName()).execute();
						}
						break;

					case 7:
						new ReviewMovieCommand().execute();
						break;
					case 8:
						if(ss.getTop5MovieTicketsBool() && ss.getTop5MovieRatingsBool()){
							new showTop5OptionsMenu().display();

							System.out.print("Please enter option number: ");
							userCh = scanner.nextInt();
							scanner.nextLine();
							if (userCh != 1  && userCh!=2){
								System.out.println("Invalid Entry");
								break;
							}
							if (userCh == 1){
								new RankTicketSalesCommand().execute();
							}
							else{
								new RankReviewRatingsCommand().execute();
							}
						}
						else if (ss.getTop5MovieRatingsBool()){
							new RankReviewRatingsCommand().execute();
						}
						else if (ss.getTop5MovieTicketsBool()){
							new RankTicketSalesCommand().execute();
						}
						else{
							System.out.println("Data unavailable");
						}
						break;

					case 9:
						ChangeLocationCommand CLC = new ChangeLocationCommand(cineplex);
						CLC.execute();
						cineplex = CLC.getCineplex();
						break;
					case 10:
						if(curAcc == null) {
							while(true){
								System.out.println();
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
							System.out.println();
							System.out.println("Logged in successfully");

						}
						else {
							curAcc = null;
							System.out.println();
							System.out.println("Logged out successfully");
						}
						break;



					default:
						System.out.println();
						System.out.println("Invalid option. Please try again.");
						break;
				}

			}
			else if (curAcc.getPrivilege() == Privilege.CineplexAdmin) {
				CineplexAdminAccount cineplexAdmin = (CineplexAdminAccount) curAcc;
				new cineplexAdminMenu(cineplexAdmin).display();

				userCh = scanner.nextInt();
				scanner.nextLine();
				System.out.println();
				System.out.println("=========================================");

				if (userCh == 5) {
					System.out.println();
					System.out.println("Thank you for using MOBLIMA. Have a nice day!");
					System.out.println();
					System.out.println("=========================================");
					break;
				}
				switch (userCh){
					case 1:
						new CreateShowCommand(cineplexAdmin.getCineplex()).execute();
						break;
					case 2:
						new UpdateShowCommand(cineplexAdmin.getCineplex()).execute();
						break;
					case 3:
						new DeleteShowCommand(cineplexAdmin.getCineplex()).execute();
						break;
					case 4:
						curAcc = null;
						System.out.println();
						System.out.println("Logged out successfully...");
						break;
					default:
						System.out.println();
						System.out.println("Invalid Option. Please try again.");
						break;
				}

			}

			else if (curAcc.getPrivilege() == Privilege.CompanyAdmin) {
				new companyAdminMenu(curAcc).display();
				userCh = scanner.nextInt();
				scanner.nextLine();
				System.out.println();
				System.out.println("=========================================");

				if (userCh == 6) {
					System.out.println();
					System.out.println("Thank you for using MOBLIMA. Have a nice day!");
					System.out.println();
					System.out.println("=========================================");
					break;
				}
				switch (userCh){
					case 1:
						new CreateMovieListingCommand().execute();
						SilverVillage.getMovieList().listMoviesForAdmin();
						break;
					case 2:
						// TODO MK fix
						new UpdateMovieListingCommand().execute();
						break;

					case 3:
						new DeleteMovieListingCommand().execute();
						SilverVillage.getMovieList().listMoviesForAdmin();
						break;

					case 4:
						ss.printSettings();
						new companySettingsMenu().display();
						System.out.println("Enter Choice:");

						userCh = scanner.nextInt();
						scanner.nextLine();
						switch(userCh){
							case 1:
								new EnableTop5TicketSalesCommand().execute();
								System.out.println("Ranking Top 5 Ticket Sales enabled");
								break;
							case 2:
								new DisableTop5TicketSalesCommand().execute();
								System.out.println("Ranking Top 5 Ticket Sales disabled");
								break;
							case 3:
								new EnableTop5ReviewsCommand().execute();
								System.out.println("Ranking by Top 5 Movie Ratings enabled");
								break;
							case 4:
								new DisableTop5ReviewsCommand().execute();
								System.out.println("Ranking by Top 5 Movie Ratings disabled");
								break;
							case 5:
								new AddPublicHolidayCommand().execute();
								break;
							case 6:
								new RemovePublicHolidayCommand().execute();
								break;
							case 7:
								new AdjustTicketBasePriceCommand().execute();
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
						break;
				}
			}


		}

		new greetUserMenu();
	}



	private static Account login(String loginId, String password, Account[] accounts){
		for (int i = 0; i < accounts.length; i++){
			if (accounts[i].getLoginId().equals(loginId)){
				if(accounts[i].login(loginId,password)!=null){
					return accounts[i];
				}
			}
		}
		return null; //account not found
	}
}