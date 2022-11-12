package command.admin;
import command.Command;
import system.SystemSettings;
import java.util.Scanner;

public class RemovePublicHolidayCommand implements Command{
    public void execute(){
        
	    Scanner scanner = new Scanner(System.in);
	    
	    int temp = SystemSettings.printPublicHolidays();
	    if(temp == -1) {
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
