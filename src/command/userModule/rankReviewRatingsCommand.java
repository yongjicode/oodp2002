package command.userModule;

import command.Command;
import moblima.Cineplex;
import moblima.Company;

public class rankReviewRatingsCommand implements Command {
	public rankReviewRatingsCommand() {
	}
	
	public void execute() {
		Company.showTopRatingMovies();
	}

}
