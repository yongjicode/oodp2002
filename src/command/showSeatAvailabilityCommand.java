package command;

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
		int showId = scanner.nextInt();
		Show curShow = cineplex.searchShow(showId);
		curShow.showSeating();
		scanner.close();
	}
}
