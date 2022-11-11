package gui;

import command.admin.*;
import system.SystemSettings;
import java.util.Scanner;

public class CompanySettingsGUI implements Menu,GetCommand {
    public void display(){
        System.out.println("================Options===================");
        System.out.println("1. Enable Showing Top 5 Movie Based on Ticket Sales");
        System.out.println("2. Disable Showing Top 5 Movie Based on Ticket Sales");
        System.out.println("3. Enable Showing Top 5 Movie Based on Ratings");
        System.out.println("4. Disable Showing Top 5 Movie Based on Ratings");
        System.out.println("5. Add Public Holidays");
        System.out.println("6. Remove Public Holidays");
        System.out.println("7. Adjust ticket base price");
        System.out.println("=========================================");
    }
    public int execute(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Choice:");
        int userCh = scanner.nextInt();
        scanner.nextLine();
        switch(userCh){
            case 1:
                new EnableTop5TicketSalesCommand().execute();
                System.out.println("Ranking Top 5 Ticket Sales enabled");
                break;
            case 2:
                new DisableTop5TicketSalesCommand().execute();
                System.out.println("Ranking Top 5 Ticket Sales disabled");
                break;
            case 3:
                new EnableTop5ReviewsCommand().execute();
                System.out.println("Ranking by Top 5 Movie Ratings enabled");
                break;
            case 4:
                new DisableTop5ReviewsCommand().execute();
                System.out.println("Ranking by Top 5 Movie Ratings disabled");
                break;
            case 5:
                new AddPublicHolidayCommand().execute();
                break;
            case 6:
                new RemovePublicHolidayCommand().execute();
                break;
            case 7:
                new AdjustTicketBasePriceCommand().execute();
                break;

            default:
                System.out.println("Invalid Entry");
                break;
        }
        SystemSettings.printSettings();
        return 0;
    }


}
