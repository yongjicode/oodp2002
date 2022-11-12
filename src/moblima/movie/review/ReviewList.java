package moblima.movie.review;

import moblima.booking.ticket.MovieTicket;

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
        	//System.out.println("-----------------------------------------");
            System.out.println("             No reviews yet!             ");
            
        }
        for(Review review: reviews){
        	
            review.printReview();
        }
    }

    public int showAverageRating(){
       if (reviews.size() == 0) return 0;
       return totalRating/reviews.size();
    }
    public String convertRatingsToString(){
        if (reviews.size() == 0) return null;
        String output = "";
        int count=0;
        for (Review review: reviews){
            if (count++ ==0) output += Integer.toString(review.getRating());
            else{
                output += ";";
                output += Integer.toString(review.getRating());
            }
        }
        return output;
    }

    public String convertDescriptionToString(){
        if (reviews.size() == 0) return null;
        String output = "";
        int count = 0;
        for (Review review: reviews){
            if (count++ ==0) output+= review.getReviewDescription();
            else{
                output += ";";
                output += review.getReviewDescription();
            }
        }
        return output;
    }




}