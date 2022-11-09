package command.userModule;

import account.Account;
import command.Command;
import moblima.SilverVillage;
import moblima.booking.Booking;
import moblima.show.ticket.MovieTicket;
import moblima.cineplex.Cineplex;
import moblima.show.Show;

import java.util.Scanner;

import static moblima.show.ticket.MovieTicket.checkCustomerAge;

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
		System.out.print("Please enter the movie's show ID: ");
		// handle error later
		int showID = scanner.nextInt();
		scanner.nextLine();
		Show show = this.cineplex.getShowList().searchShow(showID);
		if (show == null) {
			//TODO Error handling
			System.out.println("======= Show ID " + showID + " does not exist! =======");
			return;
		}
		show.printShowDetails();
		System.out.println();
		//to implement transaction ID function
		Booking booking = new Booking(curAcc.getName(),curAcc.getPhoneNo(),curAcc.getEmail());
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
			int book = show.getSeating().bookSeat(seatId);
			if(book == 0) {
				System.out.println("Seat already booked!");
				i-=1;
				continue;
			}

			else if(book==-1){
				System.out.println("Invalid Entry!");
				i-=1;
				continue;
			}
			
			booking.addTickets(new MovieTicket(seatId, show, checkCustomerAge(age)));
		}
		SilverVillage.getBookingHistory().addBooking(booking);
		booking.printBookingDetails();
	}



}
