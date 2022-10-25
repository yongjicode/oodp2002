public class Seating {

    private int[][] seats;

    public Seating() {
        this.seats = new int[10][10];
    }

    public void printSeats() {
        for(int row = 0; row<10; row++){
            System.out.print("Row" + (row+1) + ": ");
            for(int col = 0; col<10; col++){
                System.out.print(seats[row][col]);
            }
            System.out.print("\n");
        }
    }
}
