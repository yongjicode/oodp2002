package command.customer;

import command.Command;
import moblima.SilverVillage;

public class ViewBookingHistoryCommand implements Command {
	private String name;
	public ViewBookingHistoryCommand(String name) {
		this.name = name;
	}
	
	public void execute() {
		//should search for specific customers
		SilverVillage.getBookingHistory().showUserBookingHistory(name);
	}

}
