import ratings.Review;

import java.util.ArrayList;

public class Movie {
    private String title;
    private String status; //should be enum
    private String synopsis;
    private String director;
    private String[] cast;
    private ArrayList<Review> reviews = new ArrayList<>();


    public void addReview(Review review){
        reviews.add(review);
    }

    public void listReviews(){
        for(Review review: reviews){
            System.out.println(review.printReview());
        }
    }
}
