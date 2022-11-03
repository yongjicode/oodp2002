package moblima;

import java.util.ArrayList;

public class Cinema {

    private static int currentCode=100;
    private int cinemaCode;
    private String classLevel; // enum

    public Cinema(String classLevel,
                  String location) {
        this.cinemaCode = currentCode++;
        this.classLevel = classLevel;
    }
}
