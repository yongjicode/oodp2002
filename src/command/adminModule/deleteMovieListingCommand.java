package command.adminModule;
import command.Command;
import moblima.Company;
import java.util.Scanner;

public class deleteMovieListingCommand implements Command{
    public void execute(){
        Scanner scanner = new Scanner(System.in);
        Company.listMovies();
        System.out.println();
        System.out.print("Enter Movie ID to delete: ");
        int movieID = scanner.nextInt();
        Company.removeMovie(movieID);
    }
}
