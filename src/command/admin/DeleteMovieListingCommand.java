package command.admin;

import command.Command;
import exceptions.moblimaExceptions.invalidInputException;
import moblima.SilverVillage;


import java.util.Scanner;

public class DeleteMovieListingCommand implements Command{
    //
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
