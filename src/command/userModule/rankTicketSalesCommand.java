package command.userModule;

import command.Command;
import moblima.SilverVillage;

public class rankTicketSalesCommand implements Command {

	public rankTicketSalesCommand() {
	}

	public void execute() {
		SilverVillage.getMovieList().showTopMoviesBySale();
	}

}
