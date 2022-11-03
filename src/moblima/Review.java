package moblima;

public class Review {

    // removed ticketId
    private String reviewDescription;

    public int getRating() {
        return rating;
    }

    private int rating;

    public void printReview() {
        System.out.println("Description: " + reviewDescription);
        System.out.println("Rating: " + rating);
    }
}
