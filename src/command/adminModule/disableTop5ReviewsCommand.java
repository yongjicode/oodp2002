package command.adminModule;
import command.Command;
import system.SystemSettings;

public class disableTop5ReviewsCommand implements Command{
    public void execute(){
        SystemSettings.disableTop5MovieRatings();
    }
}
