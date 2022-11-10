package command.userModule;
import command.Command;
import moblima.cineplex.*;
import moblima.SilverVillage;
import java.util.Scanner;


public class changeLocationCommand implements Command{
    private Cineplex cineplex;
    public changeLocationCommand(Cineplex cineplex){
        this.cineplex = cineplex;
    }
    public void execute(){
        Scanner scanner = new Scanner(System.in);
        SilverVillage.getCineplexList().listCineplexes();
        while (true){
            System.out.println();
            System.out.print("Please enter the cinema location number: ");

            if(scanner.hasNextInt() == false) {
                System.out.println("Invalid input format for location number. Please try again.");
                scanner.next();

            }
            int locationCh = scanner.nextInt();
            scanner.nextLine();
            cineplex = SilverVillage.getCineplexList().getCineplexByIndex(locationCh-1);
            if (cineplex!=null) break;
            System.out.println("Invalid option");
        }
        this.cineplex = cineplex;
    }

    public Cineplex getCineplex(){
        return this.cineplex;
    }
}
