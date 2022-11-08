package system;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class SystemSettings {
    private boolean showTop5MovieRatings = true;
    private boolean showTop5MovieTickets = true;
    private static ArrayList<PublicHoliday> publicHolidays = new ArrayList<PublicHoliday>();


    public void printSettings(){
        System.out.println("====================Settings====================");
        System.out.println("Rank Top 5 Based on Movie Tickets: " + showTop5MovieTickets);
        System.out.println("Rank Top 5 Based on Movie Ratings: " + showTop5MovieRatings);
        System.out.println("Public Holidays: ");
        printPublicHolidays();
        System.out.println("Tickets base price: ");
        System.out.println("================================================");

    }



    public static void printPublicHolidays(){
        for (PublicHoliday publicHoliday: publicHolidays){
            System.out.println(publicHoliday.toStr());
        }
    }
    public boolean getTop5MovieRatingsBool(){
        return showTop5MovieRatings;
    }
    public void addPublicHoliday(PublicHoliday publicHoliday){
        publicHolidays.add(publicHoliday);
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
