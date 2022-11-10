package command.adminModule;
import command.Command;
import system.SystemSettings;
import java.util.Scanner;
public class adjustTicketBasePriceCommand implements Command{

    public void execute(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Current Base Price: " + SystemSettings.getBasePrice());
        System.out.println("Enter new base price:");
        int basePrice = scanner.nextInt();
        scanner.nextLine();
        SystemSettings.updateBasePrice(basePrice);
        System.out.println("Updated ticket base price to " + basePrice);
    }
}
