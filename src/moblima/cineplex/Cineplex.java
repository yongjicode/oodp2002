package moblima.cineplex;

import moblima.cineplex.cinema.Cinema;
import moblima.show.ShowList;

import java.util.ArrayList;
import java.util.Objects;

public class Cineplex {
    private String name;
    private String location;
    private ArrayList<Cinema> cinemas = new ArrayList<>();

    private ShowList showList = new ShowList();

    public Cineplex(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public ShowList getShowList() {
        return showList;
    }

    public void addCinema(Cinema cinema){
        cinema.setCinemaCode(location.substring(0,2));
        cinemas.add(cinema);
    }

    public String getLocation(){
        return this.location;
    }

    public void listCinema(){
        for(Cinema cinema:cinemas){
            cinema.printDetails();
        }
    }

    public Cinema searchCinema(String cinemaId){
        for (Cinema cinema: cinemas){
            if (Objects.equals(cinema.getCinemaCode(), cinemaId)){
                return cinema;
            }
        }
        return null;
    }

    public ArrayList<Cinema> getCinemas() {
        return cinemas;
    }
}
