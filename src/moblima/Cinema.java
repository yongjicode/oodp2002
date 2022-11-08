package moblima;

public class Cinema {

    private static int currentCode=1;
    private int cinemaCode;
    private CinemaClass classLevel; // enum

    public Cinema(CinemaClass classLevel) {
        this.cinemaCode = currentCode++;
        this.classLevel = classLevel;
    }

    public void printDetails(){
        System.out.println("Cinema Code: " + cinemaCode);
        System.out.println("Class Level: " + classLevel);
        System.out.println();
    }
    public CinemaClass getClassLevel(){return classLevel;}
    public int getCinemaCode(){
        return cinemaCode;
    }

}
