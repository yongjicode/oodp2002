package moblima.movie;

import moblima.SilverVillage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MovieList {
    private ArrayList<Movie> movies = new ArrayList<>();

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

    public void removeMovie(int movieId){
        for(Movie movie: movies){
            if(movie.getMovieId() == movieId){
                movies.remove(movie);
                System.out.println();
                System.out.println("Movie \"" + movieId + "\" has been removed.");
                return;
            }
        }
        System.out.println();
        System.out.println("Movie \"" + movieId + "\" does not exist. No movie removed.");
    }

    public void listMovies(int privilege){
        int movieCount = 1;
        System.out.println();
        // For User
        if (privilege == 0){
            System.out.println("List of Movies");
            System.out.println();
            for(Movie movie: movies){
                if (movie.getStatus() != MovieStatus.END_OF_SHOWING){
                    System.out.println("============= Result No. " + movieCount++ + " ==============");
                    movie.printMovieDetails();
                    System.out.println();
                }
            }
            System.out.println("===== There are " + (movieCount-1) + " movies available! =====");
        }
        // For Admin
        else if (privilege != 0){
            System.out.println("List of Movies");
            System.out.println();
            for(Movie movie: movies){
                System.out.println("============= Result No. " + movieCount++ + " ==============");
                movie.printMovieDetails();
                System.out.println();
            }
            System.out.println("===== There are " + movies.size() + " movies available! =====");
        }
    }

    public void searchMovieTitle(String keyword){
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

    public void showTopRatingMovies(){
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

    public void showTopSaleMovies(){
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

    public ArrayList<Movie> getMovies() {
        return movies;
    }

}
