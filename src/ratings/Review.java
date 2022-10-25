package ratings;

public class Review {

    private String ticketId;
    private String reviewDescription;
    private int rating;

    public String printReview() {
        System.out.println("Description: " + reviewDescription);
        System.out.println("Rating: " + rating);
    }
}
