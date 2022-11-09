package moblima.show;

import java.util.ArrayList;

public class ShowList {
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

    public void listShows(){
        System.out.println();
        System.out.println("========== Available Show List ==========");
        for(Show show: shows){
            show.printShowDetails();
        }
        System.out.println("=========================================");
    }

    public Show searchShow(int showId){
        for(Show show: shows){
            if(show.getShowId()==showId){
                return show;
            }
        }
        return null;
    }

    public ArrayList<Show> getShows() {
        return shows;
    }
}
