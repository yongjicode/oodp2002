package moblima.booking;

import java.util.ArrayList;

public class BookingHistory {
    private ArrayList<Booking> bookings = new ArrayList<>();

    public void showBookingHistory(){
        System.out.println("Booking History:");
        for(Booking booking: bookings){
            booking.printBookingDetails();
        }
        System.out.println("===== There are " + bookings.size() + " in the booking history! =====");
    }

    public void showUserBookingHistory(String username){
        System.out.println("User Booking History");
        int i = 1;
        boolean hasPastTransaction = false;
        //System.out.println("=========================================");
        for(Booking booking: bookings){
            if (booking.getCustomerName().equals(username)){
                hasPastTransaction = true;
                System.out.println("=========================================");
                System.out.println("Booking " + i );
                booking.printBookingDetails();
                i++;
                System.out.println();
            }

        }
        if (!hasPastTransaction) {
            System.out.println("===========No Booking History============");
        }
    }

    public void addBooking(Booking booking){
        booking.generateTransactionId(booking.getTickets().get(0).getShow().getCinema().getCinemaCode());
        bookings.add(booking);
    }
    public ArrayList<Booking> getBookings() {
        return bookings;
    }


}
