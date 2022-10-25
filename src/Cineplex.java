import java.util.ArrayList;

public class Cineplex {
    private String name;
    private static ArrayList<Cinema> cinemas = new ArrayList<>();

    public static void addCinema(Cinema cinema){
        cinemas.add(cinema);
    }
}
