package command.userModule;
import moblima.cineplex.*;

public class listShowsCommand {
    private Cineplex cineplex;
    public listShowsCommand(Cineplex cineplex){
        this.cineplex = cineplex;
    }
    public void execute(){
        cineplex.getShowList().listShows();
    }
}
