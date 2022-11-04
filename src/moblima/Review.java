package moblima;

public class Review {

    private int rating;
    private String reviewDescription;

    public Review(int rating, String reviewDescription) {
        this.rating = rating;
        this.reviewDescription = reviewDescription;
    }

    public int getRating() {
        return rating;
    }

    public void printReview() {
        System.out.println("Description: " + reviewDescription);
        System.out.println("Rating: " + rating);
    }
}
