package command;

import moblima.Cineplex;

public class rankReviewRatingsCommand implements Command {
	private Cineplex cineplex;
	public rankReviewRatingsCommand(Cineplex cineplex) {
		// TODO Auto-generated constructor stub
		this.cineplex = cineplex;
	}
	
	public void execute() {
		this.cineplex.showTopRatingMovies();
	}

}
