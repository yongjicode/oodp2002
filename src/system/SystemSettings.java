package system;

import java.util.ArrayList;
import java.time.LocalDateTime;
import account.*;

public class SystemSettings {
    private static boolean showTop5MovieRatings = true;
    private static boolean showTop5MovieTickets = true;
    private static ArrayList<PublicHoliday> publicHolidays = new ArrayList<PublicHoliday>();
    private static double basePrice = 6;
    private static ArrayList<Account> accounts = new ArrayList<Account>();

    public static void addAccount(Account account){
        accounts.add(account);
    }

    public static Account login(String loginId, String password){
        for (Account account: accounts){
            if (account.getLoginId().equals(loginId) && account.login(loginId,password) != null){
                return account;
            }
        }
        return null; //account not found
    }

    public static void printSettings(){
    	
    	System.out.println("-----------------------------------------");
        System.out.println("                Settings                 ");
        System.out.println("-----------------------------------------");
        //System.out.println("================ Settings ===============");
        System.out.println("Rank Top 5 Based on Movie Tickets: " + showTop5MovieTickets);
        System.out.println("Rank Top 5 Based on Movie Ratings: " + showTop5MovieRatings);
        
        printPublicHolidays();
        System.out.println();
        System.out.println("Tickets base price: " + basePrice);
        //System.out.println("=========================================");
        System.out.println("-----------------------------------------");
    }

    public static void updateBasePrice(double newPrice){
        basePrice = newPrice;
    }

    public static double getBasePrice(){
        return basePrice;
    }
    public static int printPublicHolidays(){
    	int index = 1;
        
        if(publicHolidays.isEmpty()) {
        	System.out.println();
        	System.out.println("Public Holidays: No public holidays have been added.");
        	
        	return -1;
        }
        System.out.println("Public Holidays: ");
        for (PublicHoliday publicHoliday: publicHolidays){
        	
            System.out.println(index +". "+ publicHoliday.toStr());
            //System.out.println();
            index+=1;
        }
        return 1;
        
    }
    public static void addPublicHoliday(LocalDateTime date, String name){
        publicHolidays.add(new PublicHoliday(date,name));
        System.out.println();
        System.out.println(name + " has been added.");
    }

    public static void removePublicHoliday(int index){
    	if(publicHolidays.size() == 0) {
    		System.out.println();
         	System.out.println("No holidays exist to be removed.");
         	return;
    	}
    	 if(publicHolidays.size() < index) {
    		System.out.println();
         	System.out.println("Option number out of range. No holiday removed.");
         	return;
         }
    	 
        PublicHoliday temp = publicHolidays.get(index-1);
       
        publicHolidays.remove(index-1);
        System.out.println();
        System.out.println("\"" + temp.getName() + "\" has been removed.");
      
    }
    public static boolean getTop5MovieRatingsBool(){
        return showTop5MovieRatings;
    }


    public static boolean isPublicHoliday(LocalDateTime date){
        for (PublicHoliday publicHoliday: publicHolidays){
            if (publicHoliday.getDate().getDayOfYear() == date.getDayOfYear()){
                return true;
            }
        }
        return false;

    }
    public static boolean getTop5MovieTicketsBool(){
        return showTop5MovieTickets;
    }

    public static void enableTop5MovieTickets(){
        showTop5MovieTickets = true;
    }

    public static void disableTop5MovieTickets(){
        showTop5MovieTickets = false;
    }

    public static void enableTop5MovieRatings(){
        showTop5MovieRatings = true;
    }

    public static void disableTop5MovieRatings(){
        showTop5MovieRatings = false;
    }

    public static ArrayList<Account> getAccounts() {
        return accounts;
    }
}
