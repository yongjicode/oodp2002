package command.customer;

import command.Command;
import moblima.SilverVillage;

public class RankTicketSalesCommand implements Command {

	public RankTicketSalesCommand() {
	}

	public void execute() {
		SilverVillage.getMovieList().showTopMoviesBySale();
	}

}
