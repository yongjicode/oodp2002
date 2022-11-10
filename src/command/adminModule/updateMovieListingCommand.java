package command.adminModule;
import command.Command;
import exceptions.moblimaExceptions.invalidInputException;
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
        System.out.println();
        System.out.print("Please enter movie ID: ");
        while(true) {
            try {
                if(input.hasNextInt() == false) {
                    throw new invalidInputException("Movie ID");
                }



                int id = input.nextInt();

                System.out.print("Please enter new status: ");
                input.next();
                String newStatus = input.nextLine();
                Movie movieToBeUpdated = null;
                for (Movie movie: movieArray){
                    if (movie.getMovieId() == id){
                        movieToBeUpdated = movie;
                        movieToBeUpdated.setStatus(Movie.convertToMovieStatus(newStatus));
                        System.out.println();
                        System.out.println("Movie successfully updated...");
                        return;
                    }
                }
                System.out.println();
                System.out.println("Movie not found...");
                return;
            }
            catch (invalidInputException e) {
                System.out.println(e.getMessage());
                System.out.println();
                System.out.print("Please enter the movie's Movie ID again: ");
                input.next();
                continue;
            }
        }
    }
}
