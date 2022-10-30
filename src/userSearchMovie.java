import java.util.Scanner;

public class userSearchMovie implements Command {
	private Cineplex cineplex;
	
	public userSearchMovie(Cineplex cineplex) {
		this.cineplex = cineplex;
	}
	public void execute() {
		Scanner scanner = new Scanner(System.in);
		String movieName;
		System.out.println("Enter movie name:");
		movieName = scanner.nextLine();
		this.cineplex.searchMovieTitle(movieName);
		scanner.close();
	}
	
}