package moblima;

import java.util.ArrayList;

public class Company {

    private static ArrayList<Cineplex> cineplexes = new ArrayList<>();

    private static ArrayList<Booking> bookings = new ArrayList<>();

    public void showBookingHistory(){
        for(Booking booking: bookings){
            booking.printBookingDetails();
        }
    }
    public void addBooking(Booking booking){
        bookings.add(booking);
    }

}
