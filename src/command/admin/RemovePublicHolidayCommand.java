package command.admin;
import command.Command;
import system.SystemSettings;
import java.util.Scanner;

public class RemovePublicHolidayCommand implements Command{
    public void execute(){
        Scanner scanner = new Scanner(System.in);
        SystemSettings.printPublicHolidays();
        System.out.println("Enter index of holiday you would like to remove:");
        int ch = scanner.nextInt();
        scanner.nextLine();
        SystemSettings.removePublicHoliday(ch);

    }

}
