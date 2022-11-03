package command;

import moblima.Cineplex;

public class userListMoviesCommand implements Command {
	private Cineplex cineplex;
	public userListMoviesCommand(Cineplex cineplex) {
		this.cineplex = cineplex;
	}
	
	public void execute() {
		this.cineplex.listMovies();
	}
}
