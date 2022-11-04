package command.adminModule;
import command.Command;
import java.util.Scanner;
import moblima.Company;
import moblima.Movie;

public class createMovieListingCommand implements Command {
    public void execute(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter movie title: ");
        String movieTitle = scanner.nextLine();
        System.out.println("Enter movie status:");
        String movieStatus = scanner.nextLine();
        System.out.println("Enter movie synopsis: ");
        String movieSynopsis = scanner.nextLine();
        System.out.println("Enter movie director: ");
        String movieDirector = scanner.nextLine();
        System.out.println("Enter movie cast: ");
        String movieCast = scanner.nextLine();
        Company.addMovie(new Movie(movieTitle,movieStatus,movieSynopsis,movieDirector,movieCast));

    }
}