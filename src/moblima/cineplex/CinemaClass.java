package moblima.cineplex;

public enum CinemaClass {
    NORMAL,
    GOLD,
    PLATINUM;

    public CinemaClass parseInt(int i){
        switch(i){
            case 0:
                return CinemaClass.NORMAL;
            case 1:
                return CinemaClass.GOLD;
            default:
                return CinemaClass.PLATINUM;
        }
    }
}
