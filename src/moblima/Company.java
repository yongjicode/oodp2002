package moblima;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Company {

    private static ArrayList<Cineplex> cineplexes = new ArrayList<>();

    private static ArrayList<Movie> movies = new ArrayList<>();
    private static ArrayList<Booking> bookings = new ArrayList<>();

    //function might not be needed
    public static void showBookingHistory(){
        System.out.println("Booking History:");
        for(Booking booking: bookings){
            booking.printBookingDetails();
        }
        System.out.println("===== There are " + bookings.size() + " in the booking history! =====");
    }

    public static void showUserBookingHistory(String username){
        System.out.println("User Booking History:");
        int i = 1;
        System.out.println("============");
        for(Booking booking: bookings){
            if (booking.getCustomerName() == username){
                System.out.println("Booking 1:");
                booking.printBookingDetails();
                System.out.println();
            }

        }
    }

    public static void addBooking(Booking booking){
        bookings.add(booking);
    }

    public static void addMovie(Movie movie){
        movies.add(movie);
    }

    public static void removeMovie(int movieId){
        for(Movie movie: movies){
            if(movie.getMovieId() == movieId){
                movies.remove(movie);
                System.out.println("moblima.Movie " + movieId + " has been removed.");
                return;
            }
        }
        System.out.println("moblima.Movie " + movieId + " does not exist.");
    }

    public static void listLocations(){
        int i = 1;
        System.out.println("Locations:");
        for (Cineplex cineplex: cineplexes){
            System.out.println((i++) + ". " + cineplex.getLocation());
        }
    }

    public static void addCineplex(Cineplex cineplex){
        cineplexes.add(cineplex);
    }

    public static void listMovies(){
        int movieCount = 1;
        System.out.println("List of Movies:");
        for(Movie movie: movies){
            System.out.println("===== Result No. " + movieCount++ + " =====");
            movie.printMovieDetails();
        }
        System.out.println("===== There are " + movies.size() + " movies available! =====");
    }

    public static void searchMovieTitle(String keyword){
        int numOfResults = 0;
        System.out.println("Search Results for " + keyword + ":");
        for(Movie movie: movies){
            if(movie.getTitle().toLowerCase().contains(keyword.toLowerCase())){
                System.out.println("===== Result No. " + ++numOfResults + " =====");
                movie.printMovieDetails();
            }
        }
        System.out.println("===== There are " + numOfResults + " search results! =====");
    }

    public static Movie searchMovieById(int movieId){
        for(Movie movie: movies){
            if(movie.getMovieId()==movieId){
                return movie;
            }
        }
        return null;
    }

    public static Cineplex searchCineplexByLocation(String location){
        for(Cineplex cineplex: cineplexes){
            if(cineplex.getLocation().equals(location)){
                return cineplex;
            }
        }
        return null;
    }

    public void updateMovieStatus(int movieId, String status){
        Company.searchMovieById(movieId).setStatus(status);
    }

    public static void showTopRatingMovies(){
        //reversed cause highest should be at top
        Collections.sort(movies, Comparator.comparingInt(Movie::getRating).reversed());
        System.out.println("Top 5 Movies by Ratings:");
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

    public static void showTopSaleMovies(){
        Collections.sort(movies, Comparator.comparingInt(Movie::getTicketSold).reversed());
        System.out.println("Top 5 Movies by Ticket Sales:");
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
}