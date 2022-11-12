package gui;
import account.*;
import command.admin.*;
import moblima.SilverVillage;
import system.SystemSettings;

import java.util.Scanner;

public class CompanyAdminGUI implements Menu,Logout,GetCommand {
    private Account curAcc;
    public CompanyAdminGUI(Account curAcc){
        this.curAcc = curAcc;
    }
    public void display(){
    	System.out.println();
        //System.out.println("-----------------------------------------");
        System.out.println("=========================================");
        System.out.println("           Company Admin Menu            ");
        System.out.println("=========================================");
        //System.out.println("-----------------------------------------");
        System.out.println();
        System.out.println("Logged in as Company admin: " + curAcc.getLoginId());
        System.out.println();
        System.out.println("1. Create movie listing");
        System.out.println("2. Update movie listing");
        System.out.println("3. Remove movie listing");
        System.out.println("4. Configure system settings");
        System.out.println("5. Logout");
        System.out.println("6. Exit");
        System.out.println("=========================================");
        System.out.println();
    };
    public int execute(){
        Scanner scanner = new Scanner(System.in);
      //Error handling for invalid input 
        while(true)	{
        	System.out.print("Please enter the option number: ");
	        if(scanner.hasNextInt() == false) {
				
				System.out.println("Invalid input format for option number. Please try again.");
				scanner.nextLine();
				System.out.println();
				continue;
			}
		
	        int userCh = scanner.nextInt();
	        scanner.nextLine();
	        System.out.println();
	        System.out.println("=========================================");
	        
        if (userCh == 6) {
            return 0;
        }
        switch (userCh){
            case 1:
                new CreateMovieListingCommand().execute();
                SilverVillage.getMovieList().listMoviesForAdmin();
                break;
            case 2:
                new UpdateMovieListingCommand().execute();
                break;

            case 3:
                new DeleteMovieListingCommand().execute();
                SilverVillage.getMovieList().listMoviesForAdmin();
                
                break;

            case 4:
            	System.out.println();
                SystemSettings.printSettings();
                CompanySettingsGUI companySettingsGUI = new CompanySettingsGUI();
                companySettingsGUI.display();
                companySettingsGUI.execute();
                break;

            case 5:
                curAcc = null;
                System.out.println("Logged out successfully.");
                break;

            default:
            	System.out.println();
                System.out.println("Option number out of range. Please try again.");
                break;
        }
        return 1;
       }
    }

    public void logout(){
        curAcc = null;
    }

    public Account getAccount(){
        return curAcc;
    }
}
