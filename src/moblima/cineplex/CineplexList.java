package moblima.cineplex;

import java.util.ArrayList;

public class CineplexList {

    private ArrayList<Cineplex> cineplexes = new ArrayList<>();

    public Cineplex getCineplexByIndex(int index){
        if (index < 0 || index >= cineplexes.size()) {
            return null;
        }
        return cineplexes.get(index);
    }

    public void addCineplex(Cineplex cineplex){
        cineplexes.add(cineplex);
    }

    public void listLocations(){
        int i = 1;
        System.out.println("Locations:");
        for (Cineplex cineplex: cineplexes){
            System.out.println((i++) + ". " + cineplex.getLocation());
        }
    }

    public ArrayList<Cineplex> getCineplexes() {
        return cineplexes;
    }
}
