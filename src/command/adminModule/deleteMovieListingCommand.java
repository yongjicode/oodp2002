package command.adminModule;

import command.Command;
import exceptions.moblimaExceptions.invalidInputException;
import moblima.SilverVillage;


import java.util.Scanner;

public class deleteMovieListingCommand implements Command{
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
                SilverVillage.getCineplexList().removeShowsByMovieId(movieID);
                SilverVillage.getMovieList().delistMovie(movieID);
                break;
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
