package command.adminModule;
import command.Command;
import system.SystemSettings;

public class enableTop5TicketSalesCommand implements Command{

    private SystemSettings ss;
    public enableTop5TicketSalesCommand(SystemSettings ss){
        this.ss = ss;
    }
    public void execute(){
        ss.enableTop5MovieTickets();
    }
}

