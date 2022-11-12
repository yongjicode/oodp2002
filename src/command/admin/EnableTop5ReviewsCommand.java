package command.admin;
import command.Command;
import system.SystemSettings;
/**
 * Represents a command for Company Admin to enable showing the ranking of top 5 movies according to reviews to customers and guests
 */
public class EnableTop5ReviewsCommand implements Command{
    /**
     * Enables option to show ranking of top 5 movies according to reviews to customers and guests in System Settings
     */
    public void execute(){
        SystemSettings.enableTop5MovieRatings();
    }

}
