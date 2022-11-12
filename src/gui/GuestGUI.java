package gui;
import command.customer.*;
import moblima.cineplex.*;
import account.Account;
import java.util.Scanner;
import system.SystemSettings;

public class GuestGUI implements Menu, Login, GetCommand {
    private Cineplex cineplex;
    private Account curAcc;

    public GuestGUI(Cineplex cineplex, Account curAcc){
        this.cineplex = cineplex;
        this.curAcc = curAcc;
    }

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
        System.out.println("7. Review Movie");
        System.out.println("8. Top 5 Ranking");
        System.out.println("9. Change Location");
        System.out.println("10. Login");
        System.out.println("11. Exit");
        System.out.println("=========================================");
        //System.out.println("-----------------------------------------");
        System.out.println();
    }

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
	        
	        if (userCh == 11) {
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
	                new ReviewMovieCommand().execute();
	                break;
	            case 8:
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
	
	            case 9:
	                ChangeLocationCommand CLC = new ChangeLocationCommand(cineplex);
	                CLC.execute();
	                cineplex = CLC.getCineplex();
	                break;
	            case 10:
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

    public Account login(String username, String password){
        return SystemSettings.login(username,password);
    }

    public Account getAccount(){
        return curAcc;
    }
    public Cineplex getCineplex(){
        return this.cineplex;
    }

    public void showTop5OptionsMenu(){
        System.out.println();
        System.out.println("============== Option Menu ==============");
        System.out.println("1. Show Top 5 Movies by Ticket Sales");
        System.out.println("2. Show Top 5 Movies by Reviews");
        System.out.println("=========================================");
        System.out.println();
    }


}
