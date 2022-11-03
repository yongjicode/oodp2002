package command.userModule;

import command.Command;
import moblima.Cineplex;
import moblima.Company;

public class userListMoviesCommand implements Command {
	public userListMoviesCommand() {
	}
	
	public void execute() {
		Company.listMovies();
	}
}
