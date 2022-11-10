package command.userModule;

import command.Command;
import moblima.SilverVillage;

public class rankReviewRatingsCommand implements Command {
	public rankReviewRatingsCommand() {
	}

	public void execute() {
		SilverVillage.getMovieList().showTopMoviesByRating();
	}

}
