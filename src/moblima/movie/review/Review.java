package moblima.movie.review;

public class Review {

    private String ticketId;
    private int rating;
    private String reviewDescription;

    public int getRating() {
        return rating;
    }


    public Review(int rating, String reviewDescription){
        this.rating = rating;
        this.reviewDescription = reviewDescription;
    }
    public void printReview() {
    	
        System.out.println("Review Description: " + reviewDescription);
        System.out.println("Review Rating: " + rating);
        System.out.println();
    }

    public String getReviewDescription() {
        return reviewDescription;
    }

    public String getTicketId() {
        return ticketId;
    }
}