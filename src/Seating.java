public class Seating {

    private int[][] seats;

    public Seating() {
        this.seats = new int[10][10];
    } //instantiate all to 0

    public void printSeats() {
        //need make it beautiful
        for(int row = 0; row<10; row++){
            System.out.print("Row" + (row+1) + ": ");
            for(int col = 0; col<10; col++){
                System.out.print(seats[row][col]);
            }
            System.out.print("\n");
        }
    }

    public void bookSeat(String seatId){
        char alphabet = seatId.charAt(0);
        int row = alphabet - 'A';
        int col = Integer.parseInt(seatId.substring(1)) - 1;
        seats[row][col] = 1;
    }
}
