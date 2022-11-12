package command.admin;
import command.Command;
import system.SystemSettings;
import java.util.Scanner;
public class AdjustTicketBasePriceCommand implements Command{

    public void execute(){
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Current Base Price: " + SystemSettings.getBasePrice());
        System.out.println();
        System.out.print("Please enter the new base price:");
        while(true) {
	        if(scanner.hasNextInt()==false) {
	        	System.out.println("Invalid input format for base price. Please try again.");
	        	System.out.println();
	        	System.out.print("Please enter the base price again: ");
	        	scanner.next();
	        	continue;
	        }
	        break;
        }
        int basePrice = scanner.nextInt();
        scanner.nextLine();
        SystemSettings.updateBasePrice(basePrice);
        System.out.println();
        System.out.println("Updated ticket base price to " + basePrice);
    }
}
