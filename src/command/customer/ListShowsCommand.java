package command.customer;
import moblima.cineplex.*;

public class ListShowsCommand {
    private Cineplex cineplex;
    public ListShowsCommand(Cineplex cineplex){
        this.cineplex = cineplex;
    }
    public void execute(){
        cineplex.getShowList().listShows();
    }
}
