
public class rankTicketSalesCommand implements Command {
	private Cineplex cineplex;
	//should be Company?
	public rankTicketSalesCommand(Cineplex cineplex) {
		// TODO Auto-generated constructor stub
		this.cineplex = cineplex;
	}
	
	public void execute() {
		this.cineplex.showTopSaleMovies();
	}

}
