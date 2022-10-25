import java.util.ArrayList;
import java.util.Optional;

public class Cineplex {
    private String name;
    private static ArrayList<Cinema> cinemas = new ArrayList<>();
    private static ArrayList<Movie> movies = new ArrayList<>();

    public static void addCinema(Cinema cinema){
        cinemas.add(cinema);
    }
    public static void addMovie(Movie movie){
        movies.add(movie);
    }

    public static void removeMovie(int movieId){
        for(Movie movie: movies){
            //if(movie.)
        }
    }

    public static void listMovies(){
        for(Movie movie: movies){
            movie.printMovieDetails();
        }
    }

    public static void searchMovieTitle(String keyword){
        System.out.println("Search Results for " + keyword + ":");
        for(Movie movie: movies){
            if(movie.getTitle().toLowerCase().contains(keyword.toLowerCase())){
                movie.printMovieDetails();
            }
        }
    }

}
