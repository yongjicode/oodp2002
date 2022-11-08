package account;
import moblima.cineplex.Cineplex;

import java.util.Scanner;


public class CineplexAdminAccount extends Account{
    private Cineplex cineplex;
    public CineplexAdminAccount(String loginId, String password, Privilege privilege, Cineplex cineplex, String emailAddress, String phoneNo, String name){
        super(loginId, password,privilege,emailAddress,phoneNo, name);
        this.cineplex = cineplex;
    }

    public Cineplex getCineplex(){
        return this.cineplex;
    }

    public Privilege login(String loginId, String password){
        Scanner scanner = new Scanner(System.in);
        if(this.getLoginId().equals(loginId) && this.getPassword().equals(password)){
            System.out.println("Verification link has been sent to your phone. Please click to login.");
            String idle = scanner.nextLine();
            return this.getPrivilege();
        }
        return null;
    }

}