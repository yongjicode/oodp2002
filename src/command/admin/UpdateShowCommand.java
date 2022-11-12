package command.admin;
import command.Command;
import exceptions.moblimaExceptions.invalidInputException;
import moblima.show.Show;
import moblima.SilverVillage;
import moblima.cineplex.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
/**
 * Represents a command for cineplex admin to edit the show time
 */
public class UpdateShowCommand implements Command{
    private Cineplex cineplex;
    /**
     * Creates an UpdateShowCommand object with the given Cineplex
     * @param cineplex Cinplex object which stores list of Shows available at Cineplex
     */
    public UpdateShowCommand(Cineplex cineplex){
        this.cineplex=cineplex;
    }
    /**
     * Gets input from user to search for and update the showing time of a Show object from the list of Shows stored in Cineplex
     */
    public void execute(){
        Scanner input = new Scanner(System.in);
        cineplex.getShowList().listShows();
        System.out.println();
        System.out.print("Please the enter the Show ID: ");

        while(true) {
            try {
                if(input.hasNextInt() == false) {
                    throw new invalidInputException("Show ID");
                }


                int showId = input.nextInt();
                if (SilverVillage.getMovieList().searchMovieById(showId) == null){
                    System.out.println();
                    System.out.println("Show ID not found. No Shows updated.");
                    return;
                }
                input.nextLine();
                System.out.println();
                System.out.print("Please enter the new Date (YYYY-MM-DD HH:MM): ");

                while(true) {
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        LocalDateTime newDateTime = LocalDateTime.parse(input.nextLine(),formatter);
                        Show showToBeUpdated = cineplex.getShowList().searchShowById(showId);
                        showToBeUpdated.setShowTime(newDateTime);
                        System.out.println();
                        System.out.println("Show has been updated.");
                        cineplex.getShowList().listShows();
                        return;

                    }
                    catch (DateTimeParseException e) {
                        System.out.println("Invalid format for Date and Time. Please try again.");
                        System.out.println();
                        System.out.print("Please enter the Date (YYYY-MM-DD HH:MM) again: ");

                        continue;
                    }
                }

            }
            catch (invalidInputException e) {
                System.out.println(e.getMessage());
                System.out.println();
                System.out.print("Please enter the Show ID again: ");
                input.next();
                continue;
            }

        }
    }
}
