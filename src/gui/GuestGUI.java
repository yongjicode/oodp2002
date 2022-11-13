package gui;
import command.customer.*;
import moblima.cineplex.*;
import account.Account;
import java.util.Scanner;
import system.SystemSettings;

/**
 * GUI which is shown to guest
 */
public class GuestGUI implements Menu, Login, GetCommand {
    private Cineplex cineplex;
    private Account curAcc;

	/**
	 * Create a GuestGUI with the given Cineplex and Account
	 * @param cineplex which is the Cineplex guest is currently viewing
	 * @param curAcc which is the Account of guest (null)
	 */
    public GuestGUI(Cineplex cineplex, Account curAcc){
        this.cineplex = cineplex;
        this.curAcc = curAcc;
    }

	/**
	 * Prints list of possible actions that can be performed by Guest
	 */
    public void display(){
        System.out.println();
        //System.out.println("-----------------------------------------");
        System.out.println("=========================================");
        System.out.println("               Guest Menu                ");
        System.out.println("=========================================");
        //System.out.println("-----------------------------------------");
        System.out.println();
        System.out.println("Viewing as: Guest");
        System.out.println();
        System.out.println("Cineplex Branch: " + cineplex.getBranchName());
        System.out.println();
        System.out.println("1. Search Movie");
        System.out.println("2. List Movies");
        System.out.println("3. List Shows");
        System.out.println("4. View Seat Availability");
        System.out.println("5. Book Tickets");
        System.out.println("6. View Booking History");
        System.out.println("7. Top 5 Ranking");
        System.out.println("8. Change Location");
        System.out.println("9. Login");
        System.out.println("10. Exit");
        System.out.println("=========================================");
        //System.out.println("-----------------------------------------");
        System.out.println();
    }

	/**
	 * Gets input from Guest and executes the required instruction
	 * @return 0 to exit the program entirely, 1 to continue program
	 */

    public int execute(){
        Scanner scanner = new Scanner(System.in);
       
        //Error handling for invalid input 
        while(true)	{
        	System.out.print("Please enter the option number: ");
	        if(scanner.hasNextInt() == false) {
				
				System.out.println("Invalid input format for option number. Please try again.");
				scanner.nextLine();
				System.out.println();
				continue;
			}
		
	        int userCh = scanner.nextInt();
	        scanner.nextLine();
	        System.out.println();
	        System.out.println("=========================================");
	        
	        if (userCh == 10) {
	            return 0;
	        }
	        switch (userCh) {
	            case 1:
	                new UserSearchMovieCommand().execute();
	                break;
	            case 2:
	                new UserListMoviesCommand().execute();
	                break;
	            case 3:
	            	System.out.println();
	        		System.out.println("For cineplex location: " + cineplex.getBranchName());
	                new ListShowsCommand(cineplex).execute();
	                break;
	            case 4:
	                new ShowSeatAvailabilityCommand(cineplex).execute();
	                break;
	            case 5:
	                new GuestBookTicketCommand(cineplex).execute();
	                break;
	            case 6:
	                new GuestViewBookingHistoryCommand().execute();
	                break;
	
	            
	            case 7:
	                if(SystemSettings.getTop5MovieTicketsBool() && SystemSettings.getTop5MovieRatingsBool()){
	                    showTop5OptionsMenu();
	                    while(true) {
		                    System.out.print("Please enter option number: ");
		                    if(scanner.hasNextInt() == false) {
		        				
		        				System.out.println("Invalid input format for option number. Please try again.");
		        				scanner.nextLine();
		        				System.out.println();
		        				continue;
		        			}
		                    break;
	                    }
	                    userCh = scanner.nextInt();
	                    scanner.nextLine();
	                    if (userCh != 1  && userCh!=2){
	                    	System.out.println("Option number out of range. Please try again.");
	                        break;
	                    }
	                    if (userCh == 1){
	                        new RankTicketSalesCommand().execute();
	                    }
	                    else{
	                        new RankReviewRatingsCommand().execute();
	                    }
	                }
	                else if (SystemSettings.getTop5MovieRatingsBool()){
	                    new RankReviewRatingsCommand().execute();
	                }
	                else if (SystemSettings.getTop5MovieTicketsBool()){
	                    new RankTicketSalesCommand().execute();
	                }
	                else{
	                    System.out.println("Data unavailable.");
	                }
	                break;
	
	            case 8:
	                ChangeLocationCommand CLC = new ChangeLocationCommand(cineplex);
	                CLC.execute();
	                cineplex = CLC.getCineplex();
	                break;
	            case 9:
	                while(true){
	                    System.out.println();
	                    System.out.print("Please enter your Login ID: ");
	                    String userLogin = scanner.nextLine();
	                    System.out.print("Please enter your Password: ");
	                    String password = scanner.nextLine();
	                    curAcc = login(userLogin.toLowerCase(),password);
	                    if (curAcc==null){
	                        System.out.println("Invalid Details. Please try again.");
	                        continue;
	                    }
	                    else break;
	                }
	                System.out.println();
	                System.out.println("Logged in successfully");
	                break;
	
	            default:
	                System.out.println();
	                System.out.println("Option number out of range. Please try again.");
	                break;
	        }
	        return 1;
    	}
    };

	/**
	 * Authenticates username and password
	 * @param username which is username entered by Guest
	 * @param password which is password entered by Guest
	 * @return Account if login successful, null if not successful
	 */
    public Account login(String username, String password){
        return SystemSettings.login(username,password);
    }
	/**
	 * Returns Account in GuestGUI
	 * @return Account
	 */
    public Account getAccount(){
        return curAcc;
    }
	/**
	 * Returns Cineplex in GuestGUI
	 * @return Cineplex
	 */
    public Cineplex getCineplex(){
        return this.cineplex;
    }
	/**
	 * Prints possible options when SystemSettings enable showing of the top 5 movies ranked based on ticket sales or reviews
	 */
    public void showTop5OptionsMenu(){
        System.out.println();
        System.out.println("============== Option Menu ==============");
        System.out.println("1. Show Top 5 Movies by Ticket Sales");
        System.out.println("2. Show Top 5 Movies by Reviews");
        System.out.println("=========================================");
        System.out.println();
    }


}
