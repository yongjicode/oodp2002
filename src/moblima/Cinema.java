package moblima;

import java.util.ArrayList;

public class Cinema {

    private static int currentCode=100;
    private String cinemaCode;
    private String classLevel; // enum

    public Cinema(String classLevel,
                  String location) {
        this.cinemaCode = currentCode++;
        this.classLevel = classLevel;
    }

    public String getCinemaCode() {
        return this.cinemaCode;
    }

    public String getClassLevel() {
        return this.classLevel;
    }
    
}
