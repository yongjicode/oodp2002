package command.admin;
import command.Command;
import system.SystemSettings;

public class EnableTop5ReviewsCommand implements Command{

    public void execute(){
        SystemSettings.enableTop5MovieRatings();
    }

}
