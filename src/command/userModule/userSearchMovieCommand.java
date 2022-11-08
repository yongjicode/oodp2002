package command.userModule;

import command.Command;
import moblima.Cineplex;
import moblima.Company;

import java.util.Scanner;

public class userSearchMovieCommand implements Command {
	
	public userSearchMovieCommand() {
	}
	public void execute() {
		Scanner scanner = new Scanner(System.in);
		String movieName;
		System.out.println();
		
		System.out.print("Please enter the movie name: ");
		movieName = scanner.nextLine();
		Company.searchMovieTitle(movieName);
		// scanner.close();
	}
	
}