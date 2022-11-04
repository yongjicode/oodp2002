package command.adminModule;
import command.Command;
import moblima.Cineplex;
import java.util.Scanner;

public class deleteShowCommand implements Command{
    private Cineplex cineplex;
    public deleteShowCommand(Cineplex cineplex){
        this.cineplex = cineplex;
    }
    public void execute(){
        Scanner scanner = new Scanner(System.in);
        cineplex.listShows();
        System.out.println("Enter Show ID to delete:");
        int showID = scanner.nextInt();
        this.cineplex.removeShow(showID);
    }
}
