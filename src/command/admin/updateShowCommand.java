package command.admin;
import command.Command;
import exceptions.moblimaExceptions.invalidInputException;
import moblima.show.Show;
import moblima.cineplex.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class updateShowCommand implements Command{
    private Cineplex cineplex;
    public updateShowCommand(Cineplex cineplex){
        this.cineplex=cineplex;
    }
    public void execute(){
        Scanner input = new Scanner(System.in);
        cineplex.getShowList().listShows();
        System.out.println();
        System.out.print("Please enter show ID: ");

        while(true) {
            try {
                if(input.hasNextInt() == false) {
                    throw new invalidInputException("show ID");
                }


                int showId = input.nextInt();
                input.nextLine();
                System.out.print("Please enter the new Time (YYYY-MM-DD HH:MM): ");

                while(true) {
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        LocalDateTime newDateTime = LocalDateTime.parse(input.nextLine(),formatter);
                        Show showToBeUpdated = cineplex.getShowList().searchShowById(showId);
                        showToBeUpdated.setShowTime(newDateTime);
                        System.out.println("Show has been updated");
                        cineplex.getShowList().listShows();
                        return;

                    }
                    catch (DateTimeParseException e) {
                        System.out.println("Invalid format for Date and Time");
                        System.out.println();
                        System.out.print("Please enter the Time (YYYY-MM-DD HH:MM) again: ");

                        continue;
                    }
                }

            }
            catch (invalidInputException e) {
                System.out.println(e.getMessage());
                System.out.println();
                System.out.print("Please enter the show ID again: ");
                input.next();
                continue;
            }

        }
    }
}
