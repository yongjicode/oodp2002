package command.admin;
import command.Command;
import system.SystemSettings;
import java.util.Scanner;
/**
 * Represents a command for Company Admin to remove public holiday dates from the System Settings.
 */
public class RemovePublicHolidayCommand implements Command{
	/**
	 * Gets input from the user to search for and remove a Public Holiday Object from the Public Holidays list in System Settings
	 */
    public void execute(){
        
	    Scanner scanner = new Scanner(System.in);
	    
	    int temp = SystemSettings.printPublicHolidays();
	    if(temp == -1) {
	    	System.out.println();
	    	System.out.println("No holiday to be removed.");
	    	return;
	    }
	    else {
		    System.out.println();
		    
		    System.out.print("Please enter the option number of the holiday you would like to remove: ");
		    while(true) {
		        if(scanner.hasNextInt()==false) {
		        	System.out.println("Invalid input format for option number. Please try again.");
		        	System.out.println();
		        	System.out.print("Please enter the option number again: ");
		        	scanner.next();
		        	continue;
		        }
		        int ch = scanner.nextInt();
		        scanner.nextLine();
		        SystemSettings.removePublicHoliday(ch);
		        break;
		    }
	    }
    }
}
