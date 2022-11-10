import account.*;
import command.adminModule.*;
import command.userModule.*;
import exceptions.moblimaExceptions.invalidInputException;
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
		accounts[1] = new CineplexAdminAccount("orange","sauce",Privilege.CinelexAdmin, tempCine,"abc@gmai.com","992","stacey");
		accounts[2] = new CineplexAdminAccount("banana","sauce",Privilege.CinelexAdmin,changiCine,"999@gmai.com","992","harold");
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
						new userSearchMovieCommand().execute();
						break;
					case 2:
						new userListMoviesCommand().execute();
						break;
					case 3:
						new listShowsCommand(cineplex).execute();
						break;
					case 4:
						new showSeatAvailabilityCommand(cineplex).execute();
						break;
					case 5:

						if(curAcc == null) {
							new guestBookTicketCommand(cineplex).execute();
						}
						else {
							new bookTicketCommand(cineplex, curAcc).execute();
						}
						break;
					case 6:
						if (curAcc == null) {
							new guestViewBookingHistoryCommand().execute();
						}
						else {
							new viewBookingHistoryCommand(curAcc.getName()).execute();
						}
						break;

					case 7:
						Scanner input = new Scanner(System.in);
						System.out.println();
						System.out.print("Please enter the ticket ID: ");
						while(true) {
							try {
								if(input.hasNextInt() == false) {
									throw new invalidInputException("ticket ID");
								}

								int ticketID = input.nextInt();

								System.out.println();

								System.out.print("Please enter the movie ID you wish to rate: ");

								while(true) {
									try {
										if(input.hasNextInt() == false) {
											throw new invalidInputException("movie ID");
										}
										int movieID = input.nextInt();
										System.out.println();
										System.out.print("Please enter your rating: ");

										while(true) {
											try {
												if(input.hasNextInt() == false) {
													throw new invalidInputException("rating");
												}
												int reviewRating = input.nextInt();


												System.out.println();
												System.out.print("Please enter your review: ");
												input.next();
												String reviewDesc = input.nextLine();
												// TODO MK FIX
												//new reviewMovieCommand(ticketID, SilverVillage.getBookingHistory().getBookings(), reviewRating, reviewDesc, SilverVillage.getMovieList().getMovies(), movieID).execute();
												break;
											}
											catch (invalidInputException e) {
												System.out.println(e.getMessage());

											}
											System.out.println();
											System.out.print("Please enter your rating again: ");
											input.next();
											continue;
										}

										break;
									}
									catch (invalidInputException e) {
										System.out.println(e.getMessage());

									}
									System.out.println();
									System.out.print("Please enter the movie ID you wish to rate again: ");
									input.next();
									continue;
								}

								break;
							}
							catch (invalidInputException e) {
								System.out.println(e.getMessage());

							}
							System.out.println();
							System.out.print("Please input the ticket ID again: ");
							input.next();
							continue;
						}




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

					case 9:
						changeLocationCommand CLC = new changeLocationCommand(cineplex);
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
			else if (curAcc.getPrivilege() == Privilege.CinelexAdmin) {
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
						new createShowCommand(cineplexAdmin.getCineplex()).execute();
						break;
					case 2:
						new updateShowCommand(cineplexAdmin.getCineplex()).execute();
						break;
					case 3:
						new deleteShowCommand(cineplexAdmin.getCineplex()).execute();
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
						new createMovieListingCommand().execute();
						SilverVillage.getMovieList().listMovies(2);
						break;
					case 2:
						// TODO MK fix
						new updateMovieListingCommand().execute();
						break;

					case 3:
						new deleteMovieListingCommand().execute();
						SilverVillage.getMovieList().listMovies(2);
						break;

					case 4:
						ss.printSettings();
						new companySettingsMenu().display();
						System.out.println("Enter Choice:");

						userCh = scanner.nextInt();
						scanner.nextLine();
						switch(userCh){
							case 1:
								new enableTop5TicketSalesCommand().execute();
								System.out.println("Ranking Top 5 Ticket Sales enabled");
								break;
							case 2:
								new disableTop5TicketSalesCommand().execute();
								System.out.println("Ranking Top 5 Ticket Sales disabled");
								break;
							case 3:
								new enableTop5ReviewsCommand().execute();
								System.out.println("Ranking by Top 5 Movie Ratings enabled");
								break;
							case 4:
								new disableTop5ReviewsCommand().execute();
								System.out.println("Ranking by Top 5 Movie Ratings disabled");
								break;
							case 5:
								new addPublicHolidayCommand().execute();
								break;
							case 6:
								new removePublicHolidayCommand().execute();
								break;
							case 7:
								new adjustTicketBasePriceCommand().execute();
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