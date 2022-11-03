package command;

import moblima.Company;

public class viewBookingHistoryCommand implements Command {
	private Company company;
	public viewBookingHistoryCommand(Company company) {
		// TODO Auto-generated constructor stub
		this.company = company;
	}
	
	public void execute() {
		//should search for specific customers
		this.company.showBookingHistory();
	}

}
