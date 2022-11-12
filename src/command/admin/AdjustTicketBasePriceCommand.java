package command.admin;
import command.Command;
import system.SystemSettings;
import java.util.Scanner;

/**
 * Represents a command for company admin to change the base price of movie tickets
 */
public class AdjustTicketBasePriceCommand implements Command{
	/**
	 * Gets input from user to update the base price of movie tickets
	 */
    public void execute(){
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Current Base Price: " + SystemSettings.getBasePrice());
        System.out.println();
        System.out.print("Please enter the new base price: ");
        while(true) {
	        if(scanner.hasNextInt()==false) {
	        	System.out.println("Invalid input format for base price. Please try again.");
	        	System.out.println();
	        	System.out.print("Please enter the base price again: ");
	        	scanner.next();
	        	continue;
	        }
	        int basePrice = scanner.nextInt();
	        scanner.nextLine();
	        if(basePrice < 0) {
	        	System.out.println("Base price cannot be negative. Please try again.");
	        	System.out.println();
	        	System.out.print("Please enter the base price again: ");
	        	
	        	continue;
	        }
	        SystemSettings.updateBasePrice(basePrice);
	        System.out.println();
	        System.out.println("Updated ticket base price to " + basePrice);
	        break;
        }
        
    }
}
