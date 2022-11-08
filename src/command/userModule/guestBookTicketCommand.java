package command.userModule;

import command.Command;
import moblima.*;

import java.util.Scanner;

import static moblima.MovieTicket.checkCustomerAge;

public class guestBookTicketCommand implements Command{
	
	private Cineplex cineplex;

	public guestBookTicketCommand(Cineplex cineplex) {
		this.cineplex = cineplex;
	}


	public void execute() {
		Scanner scanner = new Scanner(System.in);
		cineplex.listShows();
		System.out.print("Please enter the movie's show ID: ");
		// handle error later
		int showID = scanner.nextInt();
		scanner.nextLine();
		Show show = this.cineplex.searchShow(showID);
		if (show == null) {
			//TODO Error handling
			System.out.println("======= Show ID " + showID + " does not exist! =======");
			return;
		}
		show.printShowDetails();
		System.out.println();
		System.out.println("Please enter your Name:");
		String name = scanner.nextLine();
		System.out.print("Please enter your Mobile Number: ");
		String mobileNumber = scanner.nextLine();
		System.out.print("Please enter your Email Address: ");
		String emailAddress = scanner.nextLine();
		//to implement transaction ID function
		Booking booking = new Booking(name,mobileNumber,emailAddress,"ABC123");
		System.out.print("Please enter the Number of tickets to be purchased: ");
		int numTickets = scanner.nextInt();
		scanner.nextLine();
		for (int i = 0; i<numTickets;i++) {
			show.showSeating();
			System.out.print("Please enter the seat ID in this format (eg. B6): ");
			String seatId = scanner.nextLine();
			System.out.print("Please enter the age of movie goer: ");
			String age = scanner.nextLine();
			//can add error handling
			show.getMovie().incrementTicketSold();
			if(show.getSeating().bookSeat(seatId) == 0) {
				System.out.println("Seat already booked!");
				i-=1;
				continue;
			};
			booking.addTickets(new MovieTicket(seatId,show,checkCustomerAge(age)));
		}
		
		booking.printBookingDetails();
		Company.addBooking(booking);
	}


}
