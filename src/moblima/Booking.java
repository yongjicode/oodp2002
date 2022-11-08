package moblima;

import java.util.ArrayList;

public class Booking {
    private String customerName;
    private String mobileNumber;
    private String emailAddress;
    private String transactionId;

    private double totalPrice;

    private ArrayList<MovieTicket> tickets = new ArrayList<>();

    public Booking(String customerName, String mobileNumber, String emailAddress, String transactionId) {
        this.customerName = customerName;
        this.mobileNumber = mobileNumber;
        this.emailAddress = emailAddress;
        this.transactionId = transactionId;
        this.totalPrice = 0;
    }

    public String getCustomerName(){
        return customerName;
    }

    public void addTickets(MovieTicket ticket){
        tickets.add(ticket);
        // sum up the ticket prices
        totalPrice += ticket.getPrice();
        // increment ticket sold for that movie
        ticket.getShow().getMovie().incrementTicketSold();
        // remove seats from the show
        ticket.getShow().getSeating().bookSeat(ticket.getSeatId());
    }


    public void printBookingDetails() {
    	System.out.println();
    	System.out.println("============ Booking Details ============");
        System.out.println("Customer: " + customerName);
        System.out.println("Mobile Number: " + mobileNumber);
        System.out.println("Email: " + emailAddress);
        System.out.println("Total Price: $" + totalPrice);
        System.out.println();

        System.out.println("============ Ticket Details =============");
        for(MovieTicket ticket: tickets){
            ticket.printTicketDetails();
        }
    }

    public ArrayList<MovieTicket> getTickets() {
        return tickets;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getTransactionId() {
        return transactionId;
    }
}
