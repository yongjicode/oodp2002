package command.admin;

import command.Command;
import moblima.SilverVillage;
import moblima.movie.Movie;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static moblima.movie.Movie.convertToMovieStatus;

public class CreateMovieListingCommand implements Command {
    public void execute(){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("Please enter the movie title: ");
        String movieTitle = scanner.nextLine();
        System.out.print("Please enter the movie status: ");
        String movieStatus = scanner.nextLine();
        System.out.print("Please enter the movie synopsis: ");
        String movieSynopsis = scanner.nextLine();
        System.out.print("Please enter the movie director: ");
        String movieDirector = scanner.nextLine();
        System.out.print("Please enter the number of movie cast(s): ");
        int numOfCast = scanner.nextInt();
        List<String> movieCasts = new ArrayList<>();
        for(int i=0; i<numOfCast; i++){
            System.out.print("Cast " + (i+1) + " Name: ");
            String movieCast = scanner.nextLine();
            movieCasts.add(movieCast);
        }
        System.out.print("Please enter expiry date (yyyy-MM-dd HH:mm): ");
        while(true) {
            try {
                String expiryDate = scanner.nextLine();
                SilverVillage.getMovieList().addMovie(new Movie(movieTitle,convertToMovieStatus(movieStatus),movieSynopsis,movieDirector,movieCasts, LocalDateTime.parse(expiryDate, formatter)));
                break;
            }
            catch (DateTimeParseException e) {
                System.out.println("Invalid format for Date and Time");
                System.out.println();
                System.out.print("Please enter the Time (YYYY-MM-DD HH:MM) again: ");

                continue;
            }
        }
    }

}