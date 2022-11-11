package moblima.movie;

import moblima.SilverVillage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MovieList {
    private final ArrayList<Movie> movies = new ArrayList<>();

    public void updateExpiredMovieStatus(){
        for(Movie movie: movies){
            if(movie.getExpiryDate().isBefore(LocalDateTime.now())){
                if(movie.getStatus()!= MovieStatus.END_OF_SHOWING){
                    movie.setStatus(MovieStatus.END_OF_SHOWING);
                }
            }
        }
    }

    public void addMovie(Movie movie){
        movies.add(movie);
    }
    public void delistMovie(int movieId){
        this.updateMovieStatus(movieId,MovieStatus.END_OF_SHOWING);
    }

    public void listMoviesForUser() {
        int movieCount = 1;
        System.out.println();
        System.out.println("List of Movies");
        System.out.println();
        for (Movie movie : movies) {
            if (movie.getStatus() != MovieStatus.END_OF_SHOWING) {
                System.out.println("============= Result No. " + movieCount++ + " ==============");
                movie.printMovieDetails();
                System.out.println();
            }
        }
        System.out.println("===== There are " + (movieCount - 1) + " movies available! =====");
    }
    // For Admin
    public void listMoviesForAdmin(){
        int movieCount = 1;
        System.out.println("List of Movies");
        System.out.println();
        for(Movie movie: movies){
            System.out.println("============= Result No. " + movieCount++ + " ==============");
            movie.printMovieDetails();
            System.out.println();
        }
        System.out.println("===== There are " + movies.size() + " movies available! =====");
    }

    public void searchMovieByKeyword(String keyword){
        int numOfResults = 0;
        System.out.println();
        System.out.println("Search Results for movie titled \"" + keyword + "\"");
        System.out.println();
        for(Movie movie: movies){
            if(movie.getTitle().toLowerCase().contains(keyword.toLowerCase())){
                System.out.println("============= Result No. " + ++numOfResults + " ==============");
                movie.printMovieDetails();
                System.out.println();
            }
        }
        System.out.println("====== There are " + numOfResults + " search results! ======");

    }

    public Movie searchMovieById(int movieId){
        for(Movie movie: movies){
            if(movie.getMovieId()==movieId){
                return movie;
            }
        }
        return null;
    }

    public void updateMovieStatus(int movieId, MovieStatus status){
        SilverVillage.getMovieList().searchMovieById(movieId).setStatus(status);
    }

    public void showTopMoviesByRating(){
        //reversed cause highest should be at top
        Collections.sort(movies, Comparator.comparingInt(Movie::getRating).reversed());
        System.out.println("Top 5 Movies by Ratings");
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

    public void showTopMoviesBySale(){
        Collections.sort(movies, Comparator.comparingInt(Movie::getTicketSold).reversed());
        System.out.println("Top 5 Movies by Ticket Sales");
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
    public Movie getMovieByIndex(int index){
        if (index<0 || index>= movies.size()){
            return null;
        }
        return movies.get(index);
    }

//    public ArrayList<Movie> getMovies() {
//        return movies;
//    }

}
