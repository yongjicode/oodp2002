package command.adminModule;
import command.Command;
import moblima.SystemSettings;

public class disableTop5TicketSalesCommand implements Command{private SystemSettings ss;
    public disableTop5TicketSalesCommand(SystemSettings ss){
        this.ss = ss;
    }
    public void execute(){
        ss.disableTop5MovieTickets();
    }
}
