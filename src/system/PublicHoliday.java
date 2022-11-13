package system;
import java.time.LocalDateTime;

/**
 * Represents a recognised Public Holiday for the company, such that Shows which falls on a public holiday will have surcharges
 */
public class PublicHoliday {

    private LocalDateTime date;
    private String name;

    public PublicHoliday(LocalDateTime date, String name){
        this.date = date;
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public LocalDateTime getDate(){
        return date;
    }

    public String toStr(){
        return name +" : "+ date.getDayOfWeek() + "-" + date.getDayOfMonth() + "/" + date.getMonth() + "/" + date.getYear();
    }



}
