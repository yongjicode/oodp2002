package system;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class SystemSettings {
    private static boolean showTop5MovieRatings = true;
    private static boolean showTop5MovieTickets = true;
    private static ArrayList<PublicHoliday> publicHolidays = new ArrayList<PublicHoliday>();
    private static double basePrice = 6;


    public void printSettings(){
        System.out.println("=================Settings================");
        System.out.println("Rank Top 5 Based on Movie Tickets: " + showTop5MovieTickets);
        System.out.println("Rank Top 5 Based on Movie Ratings: " + showTop5MovieRatings);
        System.out.println("Public Holidays: ");
        printPublicHolidays();
        System.out.println("Tickets base price: " + basePrice);
        System.out.println("=========================================");

    }

    public static void updateBasePrice(double newPrice){
        basePrice = newPrice;
    }

    public static double getBasePrice(){
        return basePrice;
    }
    public static void printPublicHolidays(){
        int index = 1;
        for (PublicHoliday publicHoliday: publicHolidays){
            System.out.println(index +". "+ publicHoliday.toStr());
            index+=1;
        }
    }
    public static void addPublicHoliday(LocalDateTime date, String name){
        publicHolidays.add(new PublicHoliday(date,name));
        System.out.println(name + " has been added.");
    }

    public static void removePublicHoliday(int index){
        PublicHoliday temp = publicHolidays.get(index-1);
        publicHolidays.remove(index-1);
        System.out.println(temp.getName() + " has been removed.");
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
}
