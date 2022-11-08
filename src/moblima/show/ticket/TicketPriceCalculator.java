package moblima.show.ticket;

import moblima.cineplex.CinemaClass;
import system.SystemSettings;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class TicketPriceCalculator {

    private static double basePrice = 6;
    CustomerAge customerAge;
    CinemaClass cinemaClass;
    LocalDateTime date;

    public TicketPriceCalculator(CustomerAge customerAge, CinemaClass cinemaClass, LocalDateTime date){
        this.cinemaClass = cinemaClass;
        this.customerAge = customerAge;
        this.date = date;
    }

    public void updateBasePrice(double newBasePrice){
        basePrice = newBasePrice;
    }

    public double calculatePrice(){
        double additionalCharge = 0;

        // Add price based on Customer age bracket
        switch(customerAge){
        case CHILD:
            additionalCharge += 1;
            break;
        case ADULT:
            additionalCharge += 4;
            break;
        case SENIOR:
            additionalCharge += 2;
            break;
        }

        // Scale price based on Cinema Class level
        switch(cinemaClass){
        case NORMAL:
            additionalCharge += 0;
            break;
        case GOLD:
            additionalCharge += 1.5;
            break;
        case PLATINUM:
            additionalCharge += 2.5;
            break;
        }

        // Public Holiday and Weekend Surcharge of $3
        if(SystemSettings.isPublicHoliday(date) || date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY){
            additionalCharge += 3.0;
        }

        return basePrice + additionalCharge;
    }
}
