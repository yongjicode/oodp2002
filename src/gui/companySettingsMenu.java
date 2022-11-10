package gui;

public class companySettingsMenu implements menu {
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
}
