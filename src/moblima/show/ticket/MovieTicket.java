package moblima.show.ticket;

import moblima.show.Show;

public class MovieTicket {

    private String seatId;
    private static int staticTicketID=1;
    private int ticketID;
    private Show show;
    private double price;
    private CustomerAge age;

    public MovieTicket(String seatId, Show show, CustomerAge age) {
        this.seatId = seatId;
        this.show = show;
        this.age = age;
        this.price = calculatePrice(show, age);
        this.ticketID = staticTicketID++;
    }

    public void printTicketDetails(){
        
        System.out.println("Seat: " + seatId);
        System.out.println("Price: $" + price);
        System.out.println("Type: " + age);
        show.printShowDetails();
        System.out.println("_________________________________________");
        //System.out.println();
    }

    public Show getShow() {
        return show;
    }

    public double calculatePrice(Show show, CustomerAge age){
        TicketPriceCalculator ticketPriceCalculator = new TicketPriceCalculator(age, show.getCinema().getClassLevel(), show.getShowTime());
        return ticketPriceCalculator.calculatePrice();
    }

    public String getSeatId() {
        return seatId;
    }

    public CustomerAge getAge() {
        return age;
    }

    public double getPrice() {
        return price;
    }
    public int getTicketID() {
        return ticketID;
    }

    public static CustomerAge checkCustomerAge(String ageString){
        int ageInt = Integer.parseInt(ageString);
        if(ageInt<=12){
            return CustomerAge.CHILD;
        } else if(ageInt<=54){
            return CustomerAge.ADULT;
        } else{
            return CustomerAge.SENIOR;
        }
    }
}
