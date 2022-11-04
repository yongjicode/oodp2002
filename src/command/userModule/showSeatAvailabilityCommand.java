package command.userModule;

import command.Command;
import moblima.Cineplex;
import moblima.Show;

import java.util.Scanner;
public class showSeatAvailabilityCommand implements Command {
	private Cineplex cineplex;
	public showSeatAvailabilityCommand(Cineplex cineplex) {
		this.cineplex = cineplex;
	}
	public void execute() {
		Scanner scanner = new Scanner(System.in);
		cineplex.listShows();
		System.out.println("Enter show ID:");
		try {
			int showId = scanner.nextInt();
			Show curShow = cineplex.searchShow(showId);
			if (curShow == null) {
				System.out.println("===== Show ID " + showId + " does not exist! =====");
			} else {
				curShow.showSeating();
			}
		} catch (Exception e) {
			System.out.println("Invalid Show ID! Please try again.");
		}


		// scanner.close();
	}
}