package command.customer;

import moblima.SilverVillage;
import command.Command;
import java.util.Scanner;

public class GuestViewBookingHistoryCommand implements Command {

    public void execute() {
        //should search for specific customers
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("Please enter your Name: ");
        String name = scanner.nextLine();
        SilverVillage.getBookingHistory().showUserBookingHistory(name);

    }
}
