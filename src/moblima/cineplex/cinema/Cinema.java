package moblima.cineplex.cinema;

public class Cinema {

    private static int currentCode=1;
    private String cinemaCode;
    private CinemaClass classLevel; // enum

    public Cinema(CinemaClass classLevel) {
        this.classLevel = classLevel;
    }

    public void generateCinemaCode(String cineplexCode) {
        this.cinemaCode = cineplexCode.toUpperCase() + currentCode++;
    }

    public void printDetails(){
        System.out.println("Cinema Code: " + cinemaCode);
        System.out.println("Class Level: " + classLevel);
        System.out.println();
    }
    public CinemaClass getClassLevel(){return classLevel;}
    public String getCinemaCode(){
        return cinemaCode;
    }

    public static CinemaClass convertToCinemaClass(String classLevel){
        switch(classLevel.toLowerCase()){
            case "normal":
                return CinemaClass.NORMAL;
            case "gold":
                return CinemaClass.GOLD;
            case "platinum":
                return CinemaClass.PLATINUM;
        }
        return null;
    }

}
