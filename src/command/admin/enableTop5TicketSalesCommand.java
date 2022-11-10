package command.admin;
import command.Command;
import system.SystemSettings;

public class enableTop5TicketSalesCommand implements Command{

    public void execute(){
        SystemSettings.enableTop5MovieTickets();
    }
}

