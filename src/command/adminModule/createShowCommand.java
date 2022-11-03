package command.adminModule;
import moblima.Cineplex;
import moblima.Company;
import java.util.Scanner;
import command.Command;
import moblima.Cinema;
import moblima.Movie;
import moblima.Show;


import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class createShowCommand implements Command{
    private Cineplex cineplex;
    public createShowCommand(Cineplex cineplex){
        this.cineplex = cineplex;
    }
    public void execute(){
        Scanner scanner = new Scanner(System.in);
        Company.listMovies();
        //error handling req for index
        System.out.println("Enter Movie ID:");
        int movieId = scanner.nextInt();
        Movie movie = Company.searchMovieById(movieId);
        if (movie == null){
            //handle error
            System.out.println("Movie does not exist");
        }
        System.out.println("Enter Time (YYYY-MM-DD HH:MM):");
        scanner.nextLine();
        String str = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        System.out.println("List of cinemas:");
        cineplex.listCinema();
        System.out.println("Enter Cinema Code: ");
        int cinemaCode = scanner.nextInt();
        scanner.nextLine();
        Cinema cinema = cineplex.searchCinema(cinemaCode);
        //error handling
        cineplex.addShow(new Show(dateTime,cinema,movie));

    }
}
