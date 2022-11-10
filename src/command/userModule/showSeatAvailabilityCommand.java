package command.userModule;

import command.Command;
import exceptions.moblimaExceptions.invalidInputException;
import moblima.cineplex.Cineplex;
import moblima.show.Show;

import java.util.Scanner;
public class showSeatAvailabilityCommand implements Command {
	private Cineplex cineplex;
	public showSeatAvailabilityCommand(Cineplex cineplex) {
		this.cineplex = cineplex;
	}
	public void execute() {
		Scanner scanner = new Scanner(System.in);
		cineplex.getShowList().listShows();
		System.out.println();

		System.out.print("Please enter the show ID: ");
		while(true) {
			try {
				if(scanner.hasNextInt() == false) {
					throw new invalidInputException("Show ID");
				}

				int showId = scanner.nextInt();
				Show curShow = cineplex.getShowList().searchShow(showId);
				if (curShow == null) {
					System.out.println();
					System.out.println("======= Show ID " + showId + " does not exist! =======");
					return;
				}
				else if(curShow != (Show)curShow) {
					throw new invalidInputException("Show ID");
				}

				else {
					curShow.showSeating();
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


		// scanner.close();
	}
}
