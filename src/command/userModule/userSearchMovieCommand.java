package command.userModule;

import command.Command;
import moblima.Cineplex;
import moblima.Company;

import java.util.Scanner;

public class userSearchMovieCommand implements Command {
	private Cineplex cineplex;
	
	public userSearchMovieCommand() {
	}
	public void execute() {
		Scanner scanner = new Scanner(System.in);
		String movieName;
		System.out.println("Enter movie name:");
		movieName = scanner.nextLine();
		Company.searchMovieTitle(movieName);
		// scanner.close();
	}
	
}