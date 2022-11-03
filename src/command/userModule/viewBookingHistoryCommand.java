package command.userModule;

import command.Command;
import moblima.Company;

public class viewBookingHistoryCommand implements Command {

	public viewBookingHistoryCommand() {
		// TODO Auto-generated constructor stub
	}
	
	public void execute() {
		//should search for specific customers
		Company.showBookingHistory();
	}

}
