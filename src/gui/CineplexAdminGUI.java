package gui;
import account.*;
import java.util.Scanner;
import command.admin.*;

public class CineplexAdminGUI implements Menu,Logout,GetCommand{
    private CineplexAdminAccount cineplexAdmin;
    public CineplexAdminGUI(CineplexAdminAccount cineplexAdmin){
        this.cineplexAdmin = cineplexAdmin;
    }
    public void display(){
        System.out.println();
        System.out.println("========== Cineplex Admin Menu ==========");
        System.out.println();
        System.out.println("Logged in as Cineplex Admin: " + cineplexAdmin.getLoginId());
        System.out.println();
        System.out.println("Cineplex Branch: "+ cineplexAdmin.getCineplex().getBranchName());
        System.out.println();
        System.out.println("1. Create cinema showtimes");
        System.out.println("2. Update cinema showtimes");
        System.out.println("3. Remove cinema showtimes");
        System.out.println("4. Logout");
        System.out.println("5. Exit");
        System.out.println("=========================================");
        System.out.println();

    };
    public int execute(){
        System.out.print("Please enter the option number: ");
        Scanner scanner = new Scanner(System.in);
        int userCh = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        System.out.println("=========================================");

        if (userCh == 5) {
            return 0;
        }
        switch (userCh){
            case 1:
                new CreateShowCommand(cineplexAdmin.getCineplex()).execute();
                break;
            case 2:
                new UpdateShowCommand(cineplexAdmin.getCineplex()).execute();
                break;
            case 3:
                new DeleteShowCommand(cineplexAdmin.getCineplex()).execute();
                break;
            case 4:
                logout();
                break;
            default:
                System.out.println();
                System.out.println("Invalid Option. Please try again.");
                break;
        }
        return 1;
    }


    public void logout(){
        cineplexAdmin = null;
    }

    public Account getAccount(){
        return this.cineplexAdmin;
    }
}