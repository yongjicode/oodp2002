package moblima.cineplex;

import moblima.cineplex.cinema.Cinema;
import moblima.show.ShowList;

import java.util.ArrayList;
import java.util.Objects;

public class Cineplex {

    private String branchName; // BEDOK SV
    private String branchAddress;
    private ArrayList<Cinema> cinemas = new ArrayList<>();

    private ShowList showList = new ShowList();

    public Cineplex(String branchName, String branchAddress) {
        this.branchName = branchName;
        this.branchAddress = branchAddress;
    }

    public ShowList getShowList() {
        return showList;
    }

    public void addCinema(Cinema cinema){
        cinema.generateCinemaCode(branchName.substring(0,2));
        cinemas.add(cinema);
    }

    public void removeShowByMovieId(int movieId){
        showList.removeShowByMovieId(movieId);
    }

    public void printCineplexDetails(){
        System.out.print("Branch: " + branchName + ", Address: " + branchAddress);
    }

    public String getBranchName() {
        return this.branchName;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void listCinemas(){
        for(Cinema cinema:cinemas){
            cinema.printDetails();
        }
    }

    public Cinema searchCinemaById(String cinemaId){
        for (Cinema cinema: cinemas){
            if (Objects.equals(cinema.getCinemaCode(), cinemaId)){
                return cinema;
            }
        }
        return null;
    }
}
