package command.userModule;

import command.Command;
import moblima.SilverVillage;

public class userListMoviesCommand implements Command {
	public userListMoviesCommand() {
	}
	
	public void execute() {
		SilverVillage.getMovieList().listMovies(0);
	}
}
