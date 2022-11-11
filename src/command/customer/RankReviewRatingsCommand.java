package command.customer;

import command.Command;
import moblima.SilverVillage;

public class RankReviewRatingsCommand implements Command {
	public RankReviewRatingsCommand() {
	}

	public void execute() {
		SilverVillage.getMovieList().showTopMoviesByRating();
	}

}
