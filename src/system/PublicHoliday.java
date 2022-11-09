package system;
import java.time.LocalDateTime;
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
