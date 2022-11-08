package command.adminModule;
import command.Command;
import moblima.SystemSettings;

public class disableTop5ReviewsCommand implements Command{
    private SystemSettings ss;
    public disableTop5ReviewsCommand(SystemSettings ss){
        this.ss = ss;
    }
    public void execute(){
        ss.disableTop5MovieRatings();
    }
}
