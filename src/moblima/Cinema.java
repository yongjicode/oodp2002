package moblima;

import java.util.ArrayList;

public class Cinema {

    private static int currentCode=100;
    private int cinemaCode;
    private String classLevel; // enum

    public Cinema(String classLevel) {
        this.cinemaCode = currentCode++;
        this.classLevel = classLevel;
    }

    public String getClassLevel(){return classLevel;}
    public int getCinemaCode(){
        return cinemaCode;
    }
}
