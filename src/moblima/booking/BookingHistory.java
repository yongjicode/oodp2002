package moblima.booking;

import moblima.booking.ticket.MovieTicket;

import java.util.ArrayList;

public class BookingHistory {
    private ArrayList<Booking> bookings = new ArrayList<>();

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

    public MovieTicket searchTicketByTicketId(int ticketId){
        for (Booking booking:bookings){
            MovieTicket tempTicket = booking.getTicket(ticketId);
            if(tempTicket != null)
                return tempTicket;
        }
        return null;
    }
    public void addBooking(Booking booking){
        booking.generateTransactionId(booking.getTickets().get(0).getShow().getCinema().getCinemaCode());
        bookings.add(booking);
    }

//    public ArrayList<Booking> getBookings() {
//        return bookings;
//    }


}
