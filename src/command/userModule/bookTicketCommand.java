package command.userModule;

import command.Command;
import moblima.*;

import java.util.Scanner;
public class bookTicketCommand implements Command {
	private Cineplex cineplex;
	private String loginId;

	public bookTicketCommand(Cineplex cineplex, String loginId) {
		this.cineplex = cineplex;
		this.loginId = loginId;
	}
	
	public void execute() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter moblima.Show ID:");
		// handle error later
		int showID = scanner.nextInt();
		scanner.nextLine();
		Show show = this.cineplex.searchShow(showID);
		if (show == null) {
			//TODO Error handling
			System.out.println("===== Show ID " + showID + " does not exist! =====");
			return;
		}
		show.printShowDetails();
		System.out.println("Mobile Number: ");
		String mobileNumber = scanner.nextLine();
		System.out.println("Email Address: ");
		String emailAddress = scanner.nextLine();
		//to implement transaction ID function
		Booking booking = new Booking(this.loginId,mobileNumber,emailAddress,"ABC123");
		System.out.println("Number of tickets:");
		int numTickets = scanner.nextInt();
		scanner.nextLine();
		for (int i = 0; i<numTickets;i++) {
			show.showSeating();
			System.out.println("Enter seat ID:");
			String seatId = scanner.nextLine();
			System.out.println("Enter age:");
			String age = scanner.nextLine();
			//can add error handling
			show.getMovie().incrementTicketSold();
			show.getSeating().bookSeat(seatId);
			booking.addTickets(new MovieTicket(seatId,show,0,age));
		}
		System.out.println();
		booking.printBookingDetails();
		Company.addBooking(booking);
		// scanner.close();
;	}

}
