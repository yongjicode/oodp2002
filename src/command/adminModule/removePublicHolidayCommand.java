package command.adminModule;
import command.Command;
import system.SystemSettings;
import java.util.Scanner;

public class removePublicHolidayCommand implements Command{
    private SystemSettings ss;
    public removePublicHolidayCommand(SystemSettings ss){
        this.ss = ss;
    }
    public void execute(){
        Scanner scanner = new Scanner(System.in);
        SystemSettings.printPublicHolidays();
        System.out.println("Enter index of holiday you would like to remove:");
        int ch = scanner.nextInt();
        scanner.nextLine();
        ss.removePublicHoliday(ch);


    }

}
