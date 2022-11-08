package moblima;

public class MovieTicket {
    private static final double basePrice = 6;
    private String seatId;
    private static int staticTicketID=1;
    private int ticketID;
    private Show show;
    private double price;
    private String age; //enum adult, senior, child

    public MovieTicket(String seatId, Show show, double price, String age) {
        this.seatId = seatId;
        this.show = show;
        this.price = price;
        this.age = age;
        this.ticketID = staticTicketID++;
    }

    public void printTicketDetails(){
        
        System.out.println("Seat: " + seatId);
        System.out.println("Price: " + price);
        System.out.println("Type: " + age);
        show.printShowDetails();
        System.out.println("_________________________________________");
        //System.out.println();
    }
    
    
    
    public Show getShow() {
        return show;
    }

    public double getPrice(){
        // do later
        return basePrice;
    }

    public String getSeatId() {
        return seatId;
    }

    public String getAge() {
        return age;
    }

    public int getTicketID() {
        return ticketID;
    }
}
