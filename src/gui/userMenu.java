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
        System.out.println();
        System.out.println("============== User Menu ================");
        System.out.println();
        System.out.println("Logged in as User: " + curAcc.getLoginId());
        System.out.println();
        System.out.println("Cineplex Branch: " + cineplex.getBranchName());
        System.out.println();
        System.out.println("1. Search Movie");
        System.out.println("2. List Movies");
        System.out.println("3. List Shows");
        System.out.println("4. View Seat Availability");
        System.out.println("5. Book Tickets");
        System.out.println("6. View Booking History");
        System.out.println("7. Review Movie");
        System.out.println("8. Top 5 Ranking");
        System.out.println("9. Change Location");
        System.out.println("10. Logout");
        System.out.println("11. Exit");
        System.out.println("=========================================");
        System.out.println();
        System.out.print("Please enter the option number: ");
    }
}
