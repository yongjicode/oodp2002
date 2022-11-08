package command.adminModule;

import command.Command;
import moblima.Company;
import moblima.Movie;
import moblima.MovieStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class createMovieListingCommand implements Command {
    public void execute(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter movie title: ");
        String movieTitle = scanner.nextLine();
        System.out.print("Enter movie status (1-4): ");
        System.out.print("1 - Coming Soon");
        System.out.print("2 - Preview");
        System.out.print("3 - Now Showing");
        System.out.print("4 - End of Showing");
        int movieStatus = scanner.nextInt();
        System.out.print("Enter movie synopsis: ");
        String movieSynopsis = scanner.nextLine();
        System.out.print("Enter movie director: ");
        String movieDirector = scanner.nextLine();
        System.out.print("Enter movie cast: ");
        String movieCast = scanner.nextLine();
        System.out.print("Enter expiry date (yyyy-MM-dd HH:mm): ");
        String expiryDate = scanner.nextLine();
        Company.addMovie(new Movie(movieTitle,checkMovieStatus(movieStatus),movieSynopsis,movieDirector,movieCast, LocalDateTime.parse(expiryDate, formatter)));
    }

    public MovieStatus checkMovieStatus(int status){
        switch(status){
        case 1:
            return MovieStatus.COMING_SOON;
        case 2:
            return MovieStatus.PREVIEW;
        case 3:
            return MovieStatus.NOW_SHOWING;
        case 4:
            return MovieStatus.END_OF_SHOWING;
        }
        return null;
    }
}