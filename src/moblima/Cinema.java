package moblima;

import java.util.ArrayList;

public class Cinema {

    //private static int currentCode=100;
    private String classLevel; // enum
    private String location;
    private String cinemaCode;

    public Cinema(String classLevel,
                  String location,
                  String cinemaCode) {
        this.classLevel = classLevel;
        this.location = location;
        this.cinemaCode = cinemaCode;
    }

    public String getClassLevel() {
        return this.classLevel;
    }

    public String getLocation() {
        return this.location;
    }

    public String getCinemaCode() {
        return this.cinemaCode;
    }

    
    
}
