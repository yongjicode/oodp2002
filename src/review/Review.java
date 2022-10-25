package review;

public class Review {

    private String ticketId;
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
