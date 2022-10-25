import java.time.LocalDateTime;

public class Show {

    private LocalDateTime showTime;
    private Cinema cinema;
    private Movie movie;
    private Seating seating;


    public void showSeating(){
        seating.printSeats();
    }
}
