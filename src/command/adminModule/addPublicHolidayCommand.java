package command.adminModule;
import command.Command;
import system.SystemSettings;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class addPublicHolidayCommand implements Command{

    public void execute(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter holiday name: ");
        String holidayName = scanner.nextLine();
        //need to change parser
        System.out.print("Enter Date (YYYY-MM-DD HH:MM): ");
        String dateStr = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
        SystemSettings.addPublicHoliday(dateTime,holidayName);
    }
}


