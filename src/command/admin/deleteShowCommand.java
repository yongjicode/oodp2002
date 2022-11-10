package command.admin;
import command.Command;
import exceptions.moblimaExceptions.invalidInputException;
import moblima.cineplex.Cineplex;
import java.util.Scanner;

public class deleteShowCommand implements Command{
    private Cineplex cineplex;
    public deleteShowCommand(Cineplex cineplex){
        this.cineplex = cineplex;
    }
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

