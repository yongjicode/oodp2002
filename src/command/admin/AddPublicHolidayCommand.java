package command.admin;
import command.Command;
import system.SystemSettings;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
/**
 * Represents a command for company admin to add public holiday dates into the System Settings.
 */
public class AddPublicHolidayCommand implements Command{
	/**
	 * Gets input from the user to create a Public Holiday Object and adds it into the Public Holidays list in System Settings
	 */
    public void execute(){
    	Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("Please enter the holiday name: ");
        String holidayName = scanner.nextLine();
        //need to change parser
        System.out.println();
        System.out.print("Please enter the Date (YYYY-MM-DD HH:MM): ");
      //Error handling: when datetime is in the wrong format
        while(true) {
	        try {
	   
	        	String dateStr = scanner.nextLine();
        
	        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	        	LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
	        	SystemSettings.addPublicHoliday(dateTime,holidayName);
	        	break;
	        }
	        catch (DateTimeParseException e) {
		    	System.out.println("Invalid format for Date and Time. Please try again.");
		        System.out.println();
				System.out.print("Please enter the Date (YYYY-MM-DD HH:MM) again: ");
				//str = scanner.nextLine();
				continue;
		    }
        }
    }
}
        

