import java.util.ArrayList;

public class Cinema {
    private String classLevel; // enum
    private String location;

    private ArrayList<Show> shows = new ArrayList<>();

    public void addShow(Show show){ //for admin
        shows.add(show);
    }

    public void removeShow(int showId){
        for(Show show : shows){
            if(show.getShowId()==showId){
                shows.remove(show);
                System.out.println("Show " + showId + " has been removed.");
                return;
            }
        }
        System.out.println("Show " + showId + " does not exist.");
    }



}
