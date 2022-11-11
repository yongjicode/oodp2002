package command.admin;
import command.Command;
import system.SystemSettings;

public class DisableTop5TicketSalesCommand implements Command{
    public void execute(){
        SystemSettings.disableTop5MovieTickets();
    }
}
