package command.customer;

import command.Command;
import moblima.SilverVillage;

import java.util.Scanner;

public class UserSearchMovieCommand implements Command {
	
	public UserSearchMovieCommand() {
	}
	public void execute() {
		Scanner scanner = new Scanner(System.in);
		String movieName;
		System.out.println();
		
		System.out.print("Please enter a keyword to search for Movie title: ");
		movieName = scanner.nextLine();
		SilverVillage.getMovieList().searchMovieByKeyword(movieName);
		// scanner.close();
	}
	
}