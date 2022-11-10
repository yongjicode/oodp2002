package command.admin;
import command.Command;
import system.SystemSettings;

public class enableTop5ReviewsCommand implements Command{

    public void execute(){
        SystemSettings.enableTop5MovieRatings();
    }

}
