package moblima.cineplex;

import java.util.ArrayList;

public class CineplexList {

    private final ArrayList<Cineplex> cineplexes = new ArrayList<>();

    public Cineplex getCineplexByIndex(int index){
        if (index < 0 || index >= cineplexes.size()) {
            return null;
        }
        return cineplexes.get(index);
    }

    public void addCineplex(Cineplex cineplex){
        cineplexes.add(cineplex);
    }

    public void listCineplexes(){
        int i = 1;
        System.out.println("Cineplexes:");
        for (Cineplex cineplex: cineplexes){
            System.out.print((i++) + ". ");
            cineplex.printCineplexDetails();
            System.out.println("\n");
        }
    }

    public void removeShowsByMovieId(int movieId){
        for (Cineplex cineplex: cineplexes){
            cineplex.removeShowById(movieId);
        }
    }

}
