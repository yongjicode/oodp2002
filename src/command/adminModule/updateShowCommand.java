package command.adminModule;
import command.Command;
import moblima.Show;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class updateShowCommand implements Command{
    private ArrayList<Show> showArray;
    private int showID;
    private LocalDateTime newDateTime;
    public updateShowCommand(ArrayList<Show> showArray, int showID, LocalDateTime newDateTime){
        this.newDateTime=newDateTime;
        this.showArray=showArray;
        this.showID=showID;
    }
    public void execute(){
        Show showToBeUpdated;
        for (Show show: showArray){
            if (show.getShowId() == showID){
                showToBeUpdated = show;
                break;
            }
            else System.out.println("Show not found...");;
        }
        showToBeUpdated.setShowTime(newDateTime);
        System.out.println("Show successfully updated...");
    }
}
