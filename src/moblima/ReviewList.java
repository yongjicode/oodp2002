package moblima;

import moblima.Review;

import java.util.ArrayList;

public class ReviewList {
    private int totalRating=0;
    private ArrayList<Review> reviews = new ArrayList<>();

    public void add(Review review) {
        reviews.add(review);
        totalRating = totalRating + review.getRating();
    }

    public void listReviews(){
        if(reviews.size()==0){
            System.out.println("No reviews yet!");
        }
        for(Review review: reviews){
            review.printReview();
        }
    }

    public int showAverageRating(){
       return totalRating/reviews.size();
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }
}
