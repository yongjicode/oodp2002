package moblima.cineplex;

import moblima.cineplex.cinema.Cinema;

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

    public Cineplex getCineplexByName(String branchName){
        for (Cineplex cineplex: cineplexes){
            if (branchName.equals(cineplex.getBranchName())){
                return cineplex;
            }
        }
        return null;
    }

    public Cineplex getCineplexByShow(int showId){
        for (Cineplex cineplex: cineplexes){
            if (cineplex.getShowList().searchShowById(showId) != null) return cineplex;
        }
        return null;
    }

}
