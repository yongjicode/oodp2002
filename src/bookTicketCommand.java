import java.util.Scanner;
public class bookTicketCommand implements Command{
	private Cineplex cineplex;
	private Company company;
	public bookTicketCommand(Cineplex cineplex, Company company) {
		this.cineplex = cineplex;
		this.company = company;
	}
	
	public void execute() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter Show ID:");
		int showID = scanner.nextInt();
		Show show = this.cineplex.searchShow(showID);
		if (show == null) {
			//TODO Error handling
			return;
		}
		show.printShowDetails();
		System.out.println("Name: ");
		String customerName = scanner.nextLine();
		System.out.println("Mobile Number: ");
		String mobileNumber = scanner.nextLine();
		System.out.println("Email Address: ");
		String emailAddress = scanner.nextLine();
		//to implement transaction ID function
		Booking booking = new Booking(customerName,mobileNumber,emailAddress,"ABC123");
		System.out.println("Number of tickets:");
		int numTickets = scanner.nextInt();
		for (int i = 0; i<numTickets;i++) {
			System.out.println("Enter seat ID:");
			String seatId = scanner.nextLine();
			System.out.println("Enter age:");
			String age = scanner.nextLine();
			booking.addTickets(new MovieTicket(seatId,show,0,age));
		}
		System.out.println();
		booking.printBookingDetails();
		this.company.addBooking(booking);
		//logic for adding numTicketsSold
		//logic for setting seat as occupied
		scanner.close();
;	}

}
