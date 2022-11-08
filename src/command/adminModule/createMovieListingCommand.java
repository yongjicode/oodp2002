package command.adminModule;

import command.Command;
import moblima.SilverVillage;
import moblima.movie.Movie;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static moblima.movie.Movie.convertToMovieStatus;

public class createMovieListingCommand implements Command {
    public void execute(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter movie title: ");
        String movieTitle = scanner.nextLine();
        System.out.print("Enter movie status (Coming Soon, Preview, Now Showing, End of Showing): ");
        String movieStatus = scanner.nextLine();
        System.out.print("Enter movie synopsis: ");
        String movieSynopsis = scanner.nextLine();
        System.out.print("Enter movie director: ");
        String movieDirector = scanner.nextLine();
        System.out.print("Enter movie cast: ");
        String movieCast = scanner.nextLine();
        System.out.print("Enter expiry date (yyyy-MM-dd HH:mm): ");
        String expiryDate = scanner.nextLine();
        SilverVillage.getMovieList().addMovie(new Movie(movieTitle,convertToMovieStatus(movieStatus),movieSynopsis,movieDirector,movieCast, LocalDateTime.parse(expiryDate, formatter)));
    }

}