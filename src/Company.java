import java.util.ArrayList;

public class Company {

    private static ArrayList<Cineplex> cineplexes = new ArrayList<>();

    private static ArrayList<Booking> bookings = new ArrayList<>();

    public static void showBookingHistory(){
        for(Booking booking: bookings){
            booking.printBookingDetails();
        }
    }
    public static void addBooking(Booking booking){
        bookings.add(booking);
    }

}
