package command.admin;
import command.Command;
import system.SystemSettings;

public class DisableTop5ReviewsCommand implements Command{
    public void execute(){
        SystemSettings.disableTop5MovieRatings();
    }
}
