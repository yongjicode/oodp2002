package moblima.show;

import java.util.ArrayList;

public class ShowList {
    private ArrayList<Show> shows = new ArrayList<>();

    public void addShow(Show show){ //for admin
        shows.add(show);
    }

    public void removeShowById(int showId){
        for(Show show : shows){
            if(show.getShowId()==showId){
                shows.remove(show);
                System.out.println();
                System.out.println("Show " + showId + " has been removed.");
                System.out.println();
                System.out.println("Show successfully deleted.");
                return;
            }
        }
        System.out.println();
        System.out.println("Show " + showId + " does not exist.");

    }

    public void listShows(){
        System.out.println();
        
        System.out.println("-----------------------------------------");
        System.out.println("           Available Show List           ");
        System.out.println("-----------------------------------------");
        for(Show show: shows){
            if(show.isShowing()){
                show.printShowDetails();
            }
        }
        System.out.println("-----------------------------------------");
       
    }

    public Show searchShowById(int showId){
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
