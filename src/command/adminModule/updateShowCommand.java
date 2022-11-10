package command.adminModule;
import command.Command;
import exceptions.moblimaExceptions.invalidInputException;
import moblima.show.Show;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class updateShowCommand implements Command{
    private ArrayList<Show> showArray;
    public updateShowCommand(ArrayList<Show> showArray){
        this.showArray=showArray;
    }
    public void execute(){
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.print("Please enter show ID: ");

        while(true) {
            try {
                if(input.hasNextInt() == false) {
                    throw new invalidInputException("show ID");
                }


                int id = input.nextInt();

                System.out.print("Please enter the new Time (YYYY-MM-DD HH:MM): ");
                input.next();

                while(true) {
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        LocalDateTime newDateTime = LocalDateTime.parse(input.nextLine(),formatter);
                        Show showToBeUpdated = null;
                        for (Show show: showArray){
                            if (show.getShowId() == id){
                                showToBeUpdated = show;
                                showToBeUpdated.setShowTime(newDateTime);
                                System.out.println("Show successfully updated...");
                                return;
                            }
                        }
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
