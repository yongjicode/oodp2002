package moblima;

import moblima.Review;
import moblima.ReviewList;

public class Movie{

    private static int currentId=1;

    public int getMovieId() {
        return movieId;
    }

    private int movieId;
    private String title;

    private String status; //should be enum
    private String synopsis;
    private String director;
    private String cast; //need change to list of casts
    private ReviewList reviews;

    private int ticketSold;

    private int rating;

    public Movie(){}

    public Movie(String title,
                 String status,
                 String synopsis,
                 String director,
                 String cast) {
        this.movieId = currentId++;
        this.title = title;
        this.status = status;
        this.synopsis = synopsis;
        this.director = director;
        this.cast = cast;
        this.reviews = new ReviewList();
        this.ticketSold = 0;
        this.rating = 0;
    }

    public String getTitle() {
        return title;
    }
    public String getSynopsis(){
        return synopsis;
    }
    public String getDirector(){
        return cast;
    }
    public String getStatus(){
        return status;
    }
    public String getCast(){
        return cast;
    }
    public ReviewList getReviews(){
        return reviews;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public void incrementTicketSold(){
        this.ticketSold++;
    }

    public void printMovieDetails() {
        System.out.println("moblima.Movie ID:" + movieId);
        System.out.println("Title: " + title);
        System.out.println("Status: " + status);
        System.out.println("Synopsis: " + synopsis);
        System.out.println("Director: " + director);
        System.out.println("Cast: " + cast);
        System.out.println("Rating: " + rating);
        System.out.println("Reviews:");
        reviews.listReviews();
    }

    public void addReview(Review review){
        reviews.add(review);
        rating = reviews.showAverageRating();
    }

    public int getRating() {
        return rating;
    }

    public int getTicketSold() {
        return ticketSold;
    }

}
