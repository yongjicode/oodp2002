package command.adminModule;

import command.Command;
import moblima.SilverVillage;
import moblima.cineplex.Cinema;
import moblima.cineplex.Cineplex;
import moblima.movie.Movie;
import moblima.show.Show;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class createShowCommand implements Command{
    private Cineplex cineplex;
    public createShowCommand(Cineplex cineplex){
        this.cineplex = cineplex;
    }
    public void execute(){
        Scanner scanner = new Scanner(System.in);
        SilverVillage.getMovieList().listMovies(1);
        //error handling req for index
        System.out.println();
        System.out.print("Enter Movie ID: ");
        int movieId = scanner.nextInt();
        Movie movie = SilverVillage.getMovieList().searchMovieById(movieId);
        if (movie == null){
            //handle error
            System.out.println("Movie does not exist");
        }
        System.out.print("Enter Time (YYYY-MM-DD HH:MM): ");
        scanner.nextLine();
        String str = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        System.out.println();
       
        System.out.println("============ List of cinemas ============");
        cineplex.listCinema();
        System.out.print("Enter Cinema Code: ");
        String cinemaCode = scanner.nextLine();
        scanner.nextLine();
        Cinema cinema = cineplex.searchCinema(cinemaCode);
        //error handling
        cineplex.getShowList().addShow(new Show(dateTime,cinema,movie));
        System.out.println("Show successfully added");
        cineplex.getShowList().listShows();

    }
}
