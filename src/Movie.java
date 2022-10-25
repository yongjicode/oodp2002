import review.ReviewList;

public class Movie {

    private static int currentId=1;

    private int movieId;
    private String title;
    private String status; //should be enum
    private String synopsis;
    private String director;
    private String cast; //need change to list of casts
    private ReviewList reviews;

    public String getTitle() {
        return title;
    }

    public void printMovieDetails() {
        System.out.println("Title: " + title);
        System.out.println("Status: " + status);
        System.out.println("Synopsis: " + synopsis);
        System.out.println("Director: " + director);
        System.out.println("Cast: " + cast);
        System.out.println("Reviews:");
        reviews.listReviews();

    }
}
