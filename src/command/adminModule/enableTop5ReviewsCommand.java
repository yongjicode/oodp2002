package command.adminModule;
import command.Command;
import moblima.SystemSettings;

public class enableTop5ReviewsCommand implements Command{

    private SystemSettings ss;
    public enableTop5ReviewsCommand(SystemSettings ss){
        this.ss = ss;
    }
    public void execute(){
        ss.enableTop5MovieRatings();
    }

}
