package command.userModule;
import command.Command;
import moblima.Company;
import java.util.Scanner;

public class guestViewBookingHistory {

	public void execute() {
		//should search for specific customers
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter your name:");
		String name = scanner.nextLine();
		Company.showUserBookingHistory(name);
		
	}
}
