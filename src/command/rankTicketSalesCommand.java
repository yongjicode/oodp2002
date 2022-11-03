package command;

import moblima.Cineplex;

public class rankTicketSalesCommand implements Command {
	private Cineplex cineplex;
	//should be company.Company?
	public rankTicketSalesCommand(Cineplex cineplex) {
		// TODO Auto-generated constructor stub
		this.cineplex = cineplex;
	}
	
	public void execute() {
		this.cineplex.showTopSaleMovies();
	}

}
