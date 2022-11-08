package command.adminModule;
import command.Command;
import moblima.movie.Movie;
import moblima.movie.MovieStatus;

import java.util.ArrayList;
import java.util.Scanner;

public class updateMovieListingCommand implements Command {
    private ArrayList<Movie> movieArray;
    public updateMovieListingCommand(ArrayList<Movie> movieArray){
        this.movieArray=movieArray;
    }
    public void execute(){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter movie ID: ");
        int id = input.nextInt();
        System.out.println("Please enter new status");
        String newStatus = input.nextLine();
        Movie movieToBeUpdated = null;
        for (Movie movie: movieArray){
            if (movie.getMovieId() == id){
                movieToBeUpdated = movie;
                movieToBeUpdated.setStatus(Movie.convertToMovieStatus(newStatus));
                System.out.println("Movie successfully updated...");
                return;
            }
        }
        System.out.println("Movie not found...");
        return;
    }
}
