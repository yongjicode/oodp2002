package moblima;

import java.time.LocalDateTime;

public class Movie{

    private static int currentId=1;

    public int getMovieId() {
        return movieId;
    }

    private int movieId;
    private String title;
    private String synopsis;
    private String director;
    private String cast; //need change to list of casts
    private ReviewList reviews;
    private MovieStatus status;
    private LocalDateTime expiryDate;

    private int ticketSold;

    private int rating;

    public Movie(String title,
                 MovieStatus status,
                 String synopsis,
                 String director,
                 String cast,
                 LocalDateTime expiryDate) {
        this.movieId = currentId++;
        this.title = title;
        this.status = status;
        this.synopsis = synopsis;
        this.director = director;
        this.cast = cast;
        this.expiryDate = expiryDate;
        this.reviews = new ReviewList();
        this.ticketSold = 0;
        this.rating = 0;
    }

    public String getTitle() {
        return title;
    }

    public void setStatus(MovieStatus status) {
        this.status = status;
    }

    public void incrementTicketSold(){
        this.ticketSold++;
    }

    public void printMovieDetails() {
    	System.out.println();
        System.out.println("moblima.Movie ID: " + movieId);
        System.out.println("Title: " + title);
        System.out.println("Status: " + status);
        System.out.println("Synopsis: " + synopsis);
        System.out.println("Director: " + director);
        System.out.println("Cast: " + cast);
        System.out.println("Rating: " + rating);
        System.out.print("Reviews: ");
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

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public String getCast() {
        return cast;
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
}
