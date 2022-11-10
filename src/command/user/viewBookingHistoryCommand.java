package command.user;

import command.Command;
import moblima.SilverVillage;

public class viewBookingHistoryCommand implements Command {
	private String name;
	public viewBookingHistoryCommand(String name) {
		this.name = name;
	}
	
	public void execute() {
		//should search for specific customers
		SilverVillage.getBookingHistory().showUserBookingHistory(name);
	}

}
