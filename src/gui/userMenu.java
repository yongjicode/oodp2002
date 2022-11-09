package gui;
import account.*;
import moblima.cineplex.*;

public class userMenu implements menu{
    private Account curAcc;
    private Cineplex cineplex;
    public userMenu(Cineplex cineplex, Account curAcc){
        this.curAcc = curAcc;
        this.cineplex = cineplex;
    }
    public void display(){
        System.out.println("============== User Menu ================");
        System.out.println("Logged in as User: " + curAcc.getLoginId());
        System.out.println("Cineplex Branch: " + cineplex.getLocation());
        System.out.println("1. Search Movie");
        System.out.println("2. List Movies");
        System.out.println("3. View Seat Availability");
        System.out.println("4. Book Tickets");
        System.out.println("5. View Booking History");
        System.out.println("6. Review Movie");
        System.out.println("7. Top 5 Ranking");
        System.out.println("8. Logout");
        System.out.println("9. Exit");
        System.out.println("=========================================");
        System.out.print("Please enter the option number: ");
    }
}
