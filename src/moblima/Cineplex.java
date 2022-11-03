package moblima;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Cineplex {
    private String name;
    private String location;
    private static ArrayList<Cinema> cinemas = new ArrayList<>();
    private static ArrayList<Movie> movies = new ArrayList<>();

    private static ArrayList<Show> shows = new ArrayList<>();

    public void addCinema(Cinema cinema){
        cinemas.add(cinema);
    }
    public void addMovie(Movie movie){
        movies.add(movie);
    }

    public void removeMovie(int movieId){
        for(Movie movie: movies){
            if(movie.getMovieId() == movieId){
                movies.remove(movie);
                System.out.println("moblima.Movie " + movieId + " has been removed.");
            }
        }
        System.out.println("moblima.Movie " + movieId + " does not exist.");
    }

    public void listMovies(){
        for(Movie movie: movies){
            movie.printMovieDetails();
        }
    }

    public void searchMovieTitle(String keyword){
        System.out.println("Search Results for " + keyword + ":");
        for(Movie movie: movies){
            if(movie.getTitle().toLowerCase().contains(keyword.toLowerCase())){
                movie.printMovieDetails();
            }
        }
    }

    public static Movie searchMovieById(int movieId){
        for(Movie movie: movies){
            if(movie.getMovieId()==movieId){
                return movie;
            }
        }
        return null;
    }

    public void updateMovieStatus(int movieId, String status){
        Cineplex.searchMovieById(movieId).setStatus(status);
    }

    public void showTopRatingMovies(){
        Collections.sort(movies, Comparator.comparingInt(Movie::getRating));
        if(movies.size()<=5){
            for(int i=0; i<movies.size(); i++){
                movies.get(i).printMovieDetails();
            }
        } else {
            for(int i=0; i<5; i++){
                movies.get(i).printMovieDetails();
            }
        }
    }

    public void showTopSaleMovies(){
        Collections.sort(movies, Comparator.comparingInt(Movie::getTicketSold));
        System.out.println("Top Ticket Sales Movies:");
        if(movies.size()<=5){
            for(int i=0; i<movies.size(); i++){
                movies.get(i).printMovieDetails();
            }
        } else {
            for(int i=0; i<5; i++){
                movies.get(i).printMovieDetails();
            }
        }
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
