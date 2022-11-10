package gui;
import account.*;

public class companyAdminMenu implements menu{
    private Account curAcc;
    public companyAdminMenu(Account curAcc){
        this.curAcc = curAcc;
    }
    public void display(){
        System.out.println();
        System.out.println("========== Cineplex Admin Menu ==========");


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
        System.out.print("Please enter the option number: ");
    };
}