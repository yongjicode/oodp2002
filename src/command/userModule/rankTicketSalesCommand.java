package command.userModule;

import command.Command;
import moblima.Cineplex;
import moblima.Company;

public class rankTicketSalesCommand implements Command {

	public rankTicketSalesCommand() {
	}
	
	public void execute() {
		Company.showTopSaleMovies();
	}

}
