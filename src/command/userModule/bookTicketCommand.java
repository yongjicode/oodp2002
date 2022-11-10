package command.userModule;

import account.Account;
import command.Command;
import exceptions.moblimaExceptions.invalidInputException;
import moblima.SilverVillage;
import moblima.booking.Booking;
import moblima.booking.ticket.MovieTicket;
import moblima.cineplex.Cineplex;
import moblima.show.Show;

import java.util.Scanner;

import static moblima.booking.ticket.MovieTicket.checkCustomerAge;

public class bookTicketCommand implements Command {
	private Cineplex cineplex;
	private Account curAcc;
	public bookTicketCommand(Cineplex cineplex, Account curAcc) {
		this.cineplex = cineplex;
		this.curAcc = curAcc;
	}

	public void execute() {
		Scanner scanner = new Scanner(System.in);
		cineplex.getShowList().listShows();
		System.out.println();
		System.out.print("Please enter the movie's show ID: ");
		while(true) {
			try {
				if(scanner.hasNextInt() == false) {
					throw new invalidInputException("Show ID");
				}
				int showID = scanner.nextInt();
				scanner.nextLine();
				Show show = this.cineplex.getShowList().searchShow(showID);
				if (show == null) {
					System.out.println();
					System.out.println("======= Show ID " + showID + " does not exist! =======");
					return;
				}
				else if(show != (Show)show) {
					throw new invalidInputException("Show ID");
				}
				else {
					show.printShowDetails();
					System.out.println();
					//to implement transaction ID function
					Booking booking = new Booking(curAcc.getName(),curAcc.getPhoneNo(),curAcc.getEmail());
					System.out.print("Please enter the number of tickets to be purchased: ");
					int numTickets = scanner.nextInt();
					scanner.nextLine();

					if(numTickets > 0) {
						for (int i = 0; i<numTickets;i++) {
							show.showSeating();
							System.out.print("Please enter the seat ID in this format (eg. B6): ");
							String seatId = scanner.nextLine();
							System.out.print("Please enter the age of movie goer: ");
							String age = scanner.nextLine();

							//Error handling: when seat ID format wrong or if age format is wrong
							while(true) {
								try {


									if ((Character.isAlphabetic(seatId.charAt(0)) == false) || (Character.isUpperCase(seatId.charAt(0)) == false) || (Character.isDigit(seatId.charAt(1)) == false)) {
										//seatID wrong age wrong
										if(!age.matches("[0-9]+") || age.length() > 2 ) {
											throw new invalidInputException("seat ID and age");

										}
										//seatID wrong age correct
										else {
											throw new invalidInputException("seat Id");
										}


									}
									// seatID correct age wrong
									else if((Character.isAlphabetic(seatId.charAt(0)) == true) && (Character.isUpperCase(seatId.charAt(0)) == true) && (Character.isDigit(seatId.charAt(1)) == true)) {
										if(( !age.matches("[0-9]+") || age.length() > 2)) {

											throw new invalidInputException("age");
										}

									}


									break;

								}
								catch (invalidInputException e) {
									System.out.println(e.getMessage());

								}
								System.out.println();
								System.out.print("Please enter the seat ID in this format (eg. B6) again: ");
								seatId = scanner.nextLine();
								System.out.print("Please enter the age of movie goer again: ");
								age = scanner.nextLine();
								continue;
							}

							//check if the seatID already taken// input out of range
							while(true) {
								if(show.getSeating().bookSeat(seatId) == 1) {
									break;
								}
								else {
									if (show.getSeating().bookSeat(seatId) == 0)
										System.out.println("Seat already taken.");
									else {
										System.out.println("Seat does not exist");
									}

								}
								System.out.println();
								System.out.print("Please enter the seat ID in this format (eg. B6) again: ");

								seatId = scanner.nextLine();

								continue;
							}

							show.getMovie().incrementTicketSold();
							booking.addTickets(new MovieTicket(seatId,show,checkCustomerAge(age)));
						}
						SilverVillage.getBookingHistory().addBooking(booking);
						booking.printBookingDetails();

					}
					else {
						System.out.println("No tickets purchased.");
					}

				}

				break;
			}
			catch (invalidInputException e) {
				System.out.println(e.getMessage());

			}
			System.out.println();
			System.out.print("Please enter the movie's Show ID again: ");
			scanner.next();
			continue;
		}

	}

}