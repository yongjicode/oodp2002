package command.admin;
import command.Command;
import exceptions.moblimaExceptions.invalidInputException;
import moblima.cineplex.Cineplex;
import java.util.Scanner;

/**
 *Represents a command for Cineplex Admin to delete a Show Object from the list of Shows in the Cineplex
 */
public class DeleteShowCommand implements Command{
    private Cineplex cineplex;
    /**
     * Creates a DeleteShowCommand Object with the given Cineplex
     * @param cineplex Cineplex object which stores list of Shows available at Cineplex
     */
    public DeleteShowCommand(Cineplex cineplex){
        this.cineplex = cineplex;
    }
    /**
     * Gets input from user to delete a Show Object from list of Shows stored in cineplex
     */
    public void execute(){
        Scanner scanner = new Scanner(System.in);
        cineplex.getShowList().listShows();
        System.out.println();
        System.out.print("Please enter the Show ID to delete: ");

        while(true) {
            try {
                if(scanner.hasNextInt() == false) {
                    throw new invalidInputException("Show ID");
                }

                int showID = scanner.nextInt();
                scanner.nextLine();

                this.cineplex.getShowList().removeShowById(showID);

                this.cineplex.getShowList().listShows();
                break;
            }
            catch (invalidInputException e) {
                System.out.println(e.getMessage());
            }
            System.out.println();
            System.out.print("Please enter the Show ID again: ");
            scanner.next();
            continue;


        }
    }
}

