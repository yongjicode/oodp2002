package command.adminModule;
import command.Command;
import moblima.show.ticket.TicketPriceCalculator;
import system.SystemSettings;
import java.util.Scanner;
public class adjustTicketBasePriceCommand implements Command{

    public void execute(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Current Base Price: " + TicketPriceCalculator.getBasePrice());
        System.out.println("Enter new base price:");
        int basePrice = scanner.nextInt();
        scanner.nextLine();
        TicketPriceCalculator.updateBasePrice(basePrice);
        System.out.println("Updated ticket base price to " + basePrice);
    }
}
