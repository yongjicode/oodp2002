package command.admin;

import command.Command;
import moblima.SilverVillage;
import moblima.movie.Movie;
import moblima.movie.MovieStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static moblima.movie.Movie.convertToMovieStatus;

public class CreateMovieListingCommand implements Command {
    public void execute(){
    	int temp = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("Please enter the Movie Title: ");
        String movieTitle = scanner.nextLine();
        System.out.print("Please enter the Movie Status (1. Coming Soon, 2. Preview, 3. Now Showing, 4. End of showing): ");
        
      //Error handling for invalid input 
        while(true)	{
        	
	        if(scanner.hasNextInt() == false) {
				
				System.out.println("Invalid input format for option number. Please try again.");
				scanner.nextLine();
				System.out.println();
				System.out.print("Please enter the Movie Status (1. Coming Soon, 2. Preview, 3. Now Showing, 4. End of showing) again: ");
				continue;
			}
	        temp = scanner.nextInt();
	        if (temp != 1  &&  temp!=2 && temp!= 3 && temp!= 4){
	        	System.out.println("Option number out of range. Please try again.");
	        	scanner.nextLine();
				System.out.println();
				System.out.print("Please enter the Movie Status (1. Coming Soon, 2. Preview, 3. Now Showing, 4. End of showing) again: ");
				
				
				continue;
			}
	        break;
        }
        
		int movieStatusInt = temp;
        
        
        scanner.nextLine();
        MovieStatus movieStatus = MovieStatus.intToEnum(movieStatusInt);
        System.out.print("Please enter the Movie synopsis: ");
        String movieSynopsis = scanner.nextLine();
        System.out.print("Please enter the Movie director: ");
        String movieDirector = scanner.nextLine();
        System.out.print("Please enter the number of Movie Cast(s): ");
        while(true)	{
        	
	        if(scanner.hasNextInt() == false) {
				
				System.out.println("Invalid input format for number of Movie Cast(s). Please try again.");
				scanner.nextLine();
				System.out.println();
				System.out.print("Please enter the number of Movie Cast(s) again: ");
				continue;
			}
	        break;
        }
        int numOfCast = scanner.nextInt();
        scanner.nextLine();
        List<String> movieCasts = new ArrayList<>();
        for(int i=0; i<numOfCast; i++){
            System.out.print("Cast " + (i+1) + " Name: ");
            String movieCast = scanner.nextLine();
            movieCasts.add(movieCast);
        }
        System.out.println();
        System.out.print("Please enter expiry Date (YYYY-MM-DD HH:MM): ");
        while(true) {
            try {
                String expiryDate = scanner.nextLine();
                SilverVillage.getMovieList().addMovie(new Movie(movieTitle,movieStatus,movieSynopsis,movieDirector,movieCasts, LocalDateTime.parse(expiryDate, formatter)));
                break;
            }
            catch (DateTimeParseException e) {
                System.out.println("Invalid input format for Date and Time. Please try again.");
                System.out.println();
                System.out.print("Please enter the Date (YYYY-MM-DD HH:MM) again: ");

                continue;
            }
        }
    }

}