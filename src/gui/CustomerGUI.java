package gui;
import account.*;
import moblima.cineplex.*;
import command.customer.*;
import system.SystemSettings;

import java.util.Scanner;

public class CustomerGUI implements Menu, Logout,GetCommand {
    private Account curAcc;
    private Cineplex cineplex;
    public CustomerGUI(Cineplex cineplex, Account curAcc){
        this.curAcc = curAcc;
        this.cineplex = cineplex;
    }
    public void display(){
        System.out.println();
        System.out.println("============== User Menu ================");
        System.out.println();
        System.out.println("Logged in as User: " + curAcc.getLoginId());
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
        System.out.println("10. Logout");
        System.out.println("11. Exit");
        System.out.println("=========================================");
        System.out.println();
    }

    public int execute(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the option number: ");
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
                new ListShowsCommand(cineplex).execute();
                break;
            case 4:
                new ShowSeatAvailabilityCommand(cineplex).execute();
                break;
            case 5:
                new BookTicketCommand(cineplex, curAcc).execute();
                break;
            case 6:
                new ViewBookingHistoryCommand(curAcc.getName()).execute();
                break;

            case 7:
                new ReviewMovieCommand().execute();
                break;
            case 8:
                if(SystemSettings.getTop5MovieTicketsBool() && SystemSettings.getTop5MovieRatingsBool()){
                    showTop5OptionsMenu();
                    System.out.print("Please enter option number: ");
                    userCh = scanner.nextInt();
                    scanner.nextLine();
                    if (userCh != 1  && userCh!=2){
                        System.out.println("Invalid Entry");
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
                    System.out.println("Data unavailable");
                }
                break;

            case 9:
                ChangeLocationCommand CLC = new ChangeLocationCommand(cineplex);
                CLC.execute();
                cineplex = CLC.getCineplex();
                break;
            case 10:
                logout();
                System.out.println();
                System.out.println("Logged out successfully");
                break;

            default:
                System.out.println();
                System.out.println("Invalid option. Please try again.");
                break;
        }
        return 1;
    }

    public void showTop5OptionsMenu(){
        System.out.println();
        System.out.println("============== Option Menu ==============");
        System.out.println("1. Show Top 5 Movies by Ticket Sales");
        System.out.println("2. Show Top 5 Movies by Reviews");
        System.out.println("=========================================");
        System.out.println();
    }

    public void logout(){
        this.curAcc = null;
    }

    public Account getAccount(){
        return this.curAcc;
    }

    public Cineplex getCineplex(){
        return this.cineplex;
    }

}
