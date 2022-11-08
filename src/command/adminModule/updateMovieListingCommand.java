package command.adminModule;
import command.Command;
import moblima.Movie;

import java.util.ArrayList;

public class updateMovieListingCommand implements Command {
    private ArrayList<Movie> movieArray;
    private int movieID;
    private String status;
    public updateMovieListingCommand(ArrayList<Movie> movieArray, int movieID, String status){
        this.movieArray=movieArray;
        this.movieID=movieID;
        this.status=status;
    }
    public void execute(){
        Movie movieToBeUpdated = null;
        for (Movie movie: movieArray){
            if (movie.getMovieId() == movieID){
                movieToBeUpdated = movie;
                break;
            }
            else System.out.println("Movie not found...upda");
        }

        movieToBeUpdated.setStatus(status);
        System.out.println("Movie successfully updated...");
    }
}
