package command.admin;
import command.Command;
import exceptions.moblimaExceptions.invalidInputException;
import moblima.SilverVillage;
import moblima.movie.MovieStatus;

import java.util.Scanner;

public class UpdateMovieListingCommand implements Command {
    public void execute(){
    	int temp = 0;
        Scanner input = new Scanner(System.in);
        System.out.println();
        SilverVillage.getMovieList().listMoviesForAdmin();
        System.out.println();
        System.out.print("Please enter movie ID: ");
        while(true) {
            try {
                if(input.hasNextInt() == false) {
                    throw new invalidInputException("Movie ID");
                }
                int movieID = input.nextInt();
                if (SilverVillage.getMovieList().searchMovieById(movieID) == null){
                    System.out.println();
                    System.out.println("Movie ID not found. No Movies updated.");
                    return;
                }
                input.nextLine();
                System.out.println();
                System.out.print("Please enter new status (1. Coming Soon, 2. Preview, 3. Now Showing, 4. End of showing): ");
                //error handling to ensure only can pick 1 to 4
             
                //Error handling for invalid input 
                while(true)	{
                	
        	        if(input.hasNextInt() == false) {
        				
        				System.out.println("Invalid input format for option number. Please try again.");
        				input.nextLine();
        				System.out.println();
        				System.out.print("Please enter the movie status (1. Coming Soon, 2. Preview, 3. Now Showing, 4. End of showing) again: ");
        				continue;
        			}
        	        temp = input.nextInt();
        	        if (temp != 1  &&  temp!=2 && temp!= 3 && temp!= 4){
        	        	System.out.println("Option number out of range. Please try again.");
        	        	input.nextLine();
        				System.out.println();
        				System.out.print("Please enter the movie status (1. Coming Soon, 2. Preview, 3. Now Showing, 4. End of showing) again: ");
        				
        				
        				continue;
        			}
        	        break;
                }
                
                int newStatus = temp;
                
                input.nextLine();
                SilverVillage.getMovieList().updateMovieStatus(movieID,MovieStatus.intToEnum(newStatus));
                System.out.println("Movie has been updated successfully.");
                SilverVillage.getMovieList().listMoviesForAdmin();

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
