package command.admin;

import command.Command;
import exceptions.moblimaExceptions.invalidInputException;
import moblima.SilverVillage;


import java.util.Scanner;
/**
 * Represents a command for Company Admin to delete a Movie
 */
public class DeleteMovieListingCommand implements Command{
    /**
     * Gets input from users to delete Movie from list of Movies in SilverVillage by setting movie status to END_OF_SHOWING and deleting all Shows from all Cineplexes which are playing the deleted Movie
     */
    public void execute(){
        Scanner scanner = new Scanner(System.in);
        SilverVillage.getMovieList().listMoviesForAdmin();
        
        System.out.println();
        System.out.print("Please enter the Movie ID to delete: ");

        while(true) {
            try {
                if(scanner.hasNextInt() == false) {
                    throw new invalidInputException("Movie ID");
                }
                int movieID = scanner.nextInt();
                
                if (SilverVillage.getMovieList().searchMovieById(movieID) == null){
                    System.out.println();
                    System.out.println("Movie ID not found. No Movies removed.");
                    System.out.println("-----------------------------------------");
                    
                    break;
                }
                else {
                //scanner.nextLine();
                
                SilverVillage.getCineplexList().removeShowsByMovieId(movieID);
                SilverVillage.getMovieList().delistMovie(movieID);
                SilverVillage.getMovieList().listMoviesForAdmin();
                break;
                }
            }
        
            catch (invalidInputException e) {
            System.out.println(e.getMessage());
            }
            System.out.println();
            System.out.print("Please enter the Movie ID again: ");
            scanner.next();
            continue;
         }

       
    }
}
