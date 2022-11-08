package command.adminModule;
import command.Command;
import moblima.cineplex.Cineplex;
import java.util.Scanner;

public class deleteShowCommand implements Command{
    private Cineplex cineplex;
    public deleteShowCommand(Cineplex cineplex){
        this.cineplex = cineplex;
    }
    public void execute(){
        Scanner scanner = new Scanner(System.in);
        cineplex.getShowList().listShows();
        System.out.println();
        System.out.print("Please enter the Show ID to delete: ");
        int showID = scanner.nextInt();
        this.cineplex.getShowList().removeShow(showID);
        System.out.println("Show successfully deleted...");
        this.cineplex.getShowList().listShows();
    }
}
