package moblima.movie;

import moblima.movie.review.Review;
import moblima.movie.review.ReviewList;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Movie{

    private static int currentId=1;
    private int movieId;
    private String title;
    private String synopsis;
    private String director;
    private List<String> casts;
    private MovieStatus status;
    private LocalDateTime expiryDate;
    private int ticketSold = 0;
    private int rating = 0;
    private ReviewList reviews = new ReviewList();

    public Movie(String title,
                 MovieStatus status,
                 String synopsis,
                 String director,
                 List<String> casts,
                 LocalDateTime expiryDate) {
        this.movieId = currentId++;
        this.title = title;
        this.status = status;
        this.synopsis = synopsis;
        this.director = director;
        this.casts = casts;
        this.expiryDate = expiryDate;
    }
    public int getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setStatus(MovieStatus status) {
        this.status = status;
    }

    public int getRating() {
        return reviews.showAverageRating();
    }

    public int getTicketSold() {
        return ticketSold;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public String getCasts() {
        String output = "";
        int count=0;
        for (String cast: casts){
            if (count++ == 0) output+=cast;
            else {
                output+=';';
                output+=cast;
            }
        }

        return output;
    }

    public String getDirector() {
        return director;
    }

    public MovieStatus getStatus() {
        return status;
    }

    public ReviewList getReviews() {
        return reviews;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void incrementTicketSold(){
        this.ticketSold++;
    }

    public void printMovieDetails() {
    	String commaCasts = casts.toString();
        commaCasts = commaCasts.replace(" ", " ");
        System.out.println();
        System.out.println("Movie ID: " + movieId);
        System.out.println("Title: " + title);
        System.out.println("Status: " + status);
        System.out.println("Synopsis: " + synopsis);
        System.out.println("Director: " + director);
        System.out.print("Cast(s): " + commaCasts);
       
        System.out.print("\n");
        System.out.println("Average Rating: " + rating);
        System.out.println("-----------------------------------------");
        System.out.println("                 Reviews                 ");
        System.out.println("-----------------------------------------");
        System.out.println();
        reviews.listReviews();
        System.out.println();
        System.out.println("-----------------------------------------");
        
    }

    public void addReview(Review review){
        reviews.add(review);
        rating = reviews.showAverageRating();
    }

    public static MovieStatus convertToMovieStatus(String movieStatus){
        switch(movieStatus.toLowerCase()){
        case "coming soon":
            return MovieStatus.COMING_SOON;
        case "preview":
            return MovieStatus.PREVIEW;
        case "now showing":
            return MovieStatus.NOW_SHOWING;
        case "end of showing":
            return MovieStatus.END_OF_SHOWING;
        }
        return null;
    }
    public static String convertMovieStatusToString(MovieStatus movieStatus){
        if (movieStatus == MovieStatus.COMING_SOON) return "coming soon";
        else if (movieStatus == MovieStatus.PREVIEW) return "preview";
        else if (movieStatus == MovieStatus.NOW_SHOWING) return "now showing";
        else return "end of showing";
    }
}
