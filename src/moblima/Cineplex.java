package moblima;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Cineplex {
    private String name;
    private String location;
    private static ArrayList<Cinema> cinemas = new ArrayList<>();

    private static ArrayList<Show> shows = new ArrayList<>();

    public void addCinema(Cinema cinema){
        cinemas.add(cinema);
    }

    public void addShow(Show show){ //for admin
        shows.add(show);
    }

    public void removeShow(int showId){
        for(Show show : shows){
            if(show.getShowId()==showId){
                shows.remove(show);
                System.out.println("moblima.Show " + showId + " has been removed.");
                return;
            }
        }
        System.out.println("moblima.Show " + showId + " does not exist.");
    }

    public void listShows(){
        for(Show show: shows){
            show.printShowDetails();
        }
    }

    public Show searchShow(int showId){
        for(Show show: shows){
            if(show.getShowId()==showId){
                return show;
            }
        }
        return null;
    }

}
