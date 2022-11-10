package gui;
import moblima.cineplex.*;
public class guestMenu implements menu{
    private Cineplex cineplex;
    public guestMenu(Cineplex cineplex){
        this.cineplex = cineplex;
    }
    public void display(){
        System.out.println();
        System.out.println("============== Guest Menu ===============");
        System.out.println();
        System.out.println("Viewing as: Guest");
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
        System.out.println("10. Login");
        System.out.println("11. Exit");
        System.out.println("=========================================");
        System.out.println();
        System.out.print("Please enter the option number: ");
    }
}