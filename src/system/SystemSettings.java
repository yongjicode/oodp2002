package system;

import java.util.ArrayList;
import java.time.LocalDateTime;
import moblima.show.ticket.*;

public class SystemSettings {
    private boolean showTop5MovieRatings = true;
    private boolean showTop5MovieTickets = true;
    private static ArrayList<PublicHoliday> publicHolidays = new ArrayList<PublicHoliday>();
    private static float basePrice;


    public void printSettings(){
        System.out.println("=================Settings================");
        System.out.println("Rank Top 5 Based on Movie Tickets: " + showTop5MovieTickets);
        System.out.println("Rank Top 5 Based on Movie Ratings: " + showTop5MovieRatings);
        System.out.println("Public Holidays: ");
        printPublicHolidays();
        System.out.println("Tickets base price: " + TicketPriceCalculator.getBasePrice());
        System.out.println("=========================================");

    }



    public static void printPublicHolidays(){
        int index = 1;
        for (PublicHoliday publicHoliday: publicHolidays){
            System.out.println(index +". "+ publicHoliday.toStr());
            index+=1;
        }
    }
    public void addPublicHoliday(LocalDateTime date, String name){
        publicHolidays.add(new PublicHoliday(date,name));
        System.out.println(name + " has been added.");
    }

    public void removePublicHoliday(int index){
        PublicHoliday temp = publicHolidays.get(index-1);
        publicHolidays.remove(index-1);
        System.out.println(temp.getName() + " has been removed.");
    }
    public boolean getTop5MovieRatingsBool(){
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
    public boolean getTop5MovieTicketsBool(){
        return showTop5MovieTickets;
    }

    public void enableTop5MovieTickets(){
        this.showTop5MovieTickets = true;
    }

    public void disableTop5MovieTickets(){
        this.showTop5MovieTickets = false;
    }

    public void enableTop5MovieRatings(){
        this.showTop5MovieRatings = true;
    }

    public void disableTop5MovieRatings(){
        this.showTop5MovieRatings = false;
    }
}
