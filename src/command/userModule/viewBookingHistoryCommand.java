package command.userModule;

import command.Command;
import moblima.Company;

public class viewBookingHistoryCommand implements Command {
	private String name;
	public viewBookingHistoryCommand(String name) {
		this.name = name;
	}
	
	public void execute() {
		//should search for specific customers
		Company.showUserBookingHistory(name);
	}

}
