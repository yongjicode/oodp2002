package command.customer;

import command.Command;
import moblima.SilverVillage;

public class UserListMoviesCommand implements Command {
	public UserListMoviesCommand() {
	}
	
	public void execute() {
		
		SilverVillage.getMovieList().listMoviesForUser();
		System.out.println();
	}
}
