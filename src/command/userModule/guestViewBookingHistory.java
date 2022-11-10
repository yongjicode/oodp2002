package command.userModule;

import moblima.SilverVillage;

import java.util.Scanner;

public class guestViewBookingHistory {

	public void execute() {
		//should search for specific customers
		Scanner scanner = new Scanner(System.in);
		System.out.println();
		System.out.print("Please enter your name: ");
		String name = scanner.nextLine();
		SilverVillage.getBookingHistory().showUserBookingHistory(name);

	}
}
