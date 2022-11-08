package moblima;

public class MovieTicket {

    private String seatId;

    private Show show;
    private double price;
    private CustomerAge age;

    public MovieTicket(String seatId, Show show, CustomerAge age) {
        this.seatId = seatId;
        this.show = show;
        this.age = age;
        this.price = calculatePrice(show, age);
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
}
