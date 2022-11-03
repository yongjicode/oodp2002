package command.userModule;

import command.Command;
import moblima.Company;

public class viewBookingHistoryCommand implements Command {
	private String username;
	public viewBookingHistoryCommand(String username) {
		this.username = username;
	}
	
	public void execute() {
		//should search for specific customers
		Company.showUserBookingHistory(username);
	}

}
