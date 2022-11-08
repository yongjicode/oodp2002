package command.adminModule;

import command.Command;
import moblima.SilverVillage;

import java.util.Scanner;

public class deleteMovieListingCommand implements Command{
    //
    public void execute(){
        Scanner scanner = new Scanner(System.in);
        SilverVillage.getMovieList().listMovies();
        System.out.println();
        System.out.print("Enter Movie ID to delete: ");
        int movieID = scanner.nextInt();
        SilverVillage.getMovieList().removeMovie(movieID);
    }
}
