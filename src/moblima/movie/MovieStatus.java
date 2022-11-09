package moblima.movie;

public enum MovieStatus {

    COMING_SOON("COMING SOON"),
    PREVIEW("PREVIEW"),
    NOW_SHOWING("NOW SHOWING"),
    END_OF_SHOWING("END OF SHOWING");

    private final String toString;
    private MovieStatus(String toString) {
        this.toString = toString;
    }

}
