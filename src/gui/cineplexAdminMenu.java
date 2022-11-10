package gui;
import account.*;

public class cineplexAdminMenu implements menu{
    private CineplexAdminAccount cineplexAcc;
    public cineplexAdminMenu(CineplexAdminAccount cineplexAcc){
        this.cineplexAcc = cineplexAcc;
    }
    public void display(){
        System.out.println();
        System.out.println("========== Cineplex Admin Menu ==========");
        System.out.println();
        System.out.println("Logged in as Cineplex Admin: " + cineplexAcc.getLoginId());
        System.out.println();
        System.out.println("Cineplex Branch: "+ cineplexAcc.getCineplex().getLocation());
        System.out.println();
        System.out.println("1. Create cinema showtimes");
        System.out.println("2. Update cinema showtimes");
        System.out.println("3. Remove cinema showtimes");
        System.out.println("4. Logout");
        System.out.println("5. Exit");
        System.out.println("=========================================");
        System.out.println();
        System.out.print("Please enter the option number: ");
    };
}
