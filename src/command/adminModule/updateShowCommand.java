package command.adminModule;
import command.Command;
import moblima.Show;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class updateShowCommand implements Command{
    private ArrayList<Show> showArray;
    public updateShowCommand(ArrayList<Show> showArray){
        this.showArray=showArray;
    }
    public void execute(){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter show ID: ");
        int id = input.nextInt();
        System.out.println("Please enter the new DateTime");
        LocalDateTime newDateTime = LocalDateTime.parse(input.nextLine());

        Show showToBeUpdated = null;
        for (Show show: showArray){
            if (show.getShowId() == id){
                showToBeUpdated = show;
                showToBeUpdated.setShowTime(newDateTime);
                System.out.println("Show successfully updated...");
                return;
            }
        }
        return;
    }
}
