package review;

import java.util.ArrayList;

public class ReviewList {

    private ArrayList<Review> reviews = new ArrayList<>();
    public void add(Review review) {
        reviews.add(review);
    }

    public void listReviews(){
        for(Review review: reviews){
            review.printReview();
        }
    }
}
