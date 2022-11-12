package command.admin;
import command.Command;
import system.SystemSettings;
/**
 * Represents a command for Company Admin to disable showing the ranking of top 5 movies according to ticket sales to customers and guests
 */
public class DisableTop5TicketSalesCommand implements Command{
    /**
     * Disables option to show ranking of top 5 movies according to ticket sales to customers and guests in System Settings
     */
    public void execute(){
        SystemSettings.disableTop5MovieTickets();
    }
}
