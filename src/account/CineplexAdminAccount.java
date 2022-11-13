package account;
import moblima.cineplex.Cineplex;

import java.util.Scanner;

/**
 * Account for Cineplex Admin
 */
public class CineplexAdminAccount extends Account{
    private Cineplex cineplex;

    /**
     * Creates a CineplexAdminAccount with the given parameters
     * @param loginId which is the login ID of CineplexAdminAccount
     * @param password which is the password of CineplexAdminAccount
     * @param privilege which is the Privilege of CineplexAdminAccount
     * @param cineplex which is the Cineplex that the account is tagged to
     * @param emailAddress which is the email address of CineplexAdminAccount
     * @param phoneNo which is the phone number of CineplexAdminAccount
     * @param name which is the name of CineplexAdminAccount
     */
    public CineplexAdminAccount(String loginId, String password, Privilege privilege, Cineplex cineplex, String emailAddress, String phoneNo, String name){
        super(loginId, password,privilege,emailAddress,phoneNo, name);
        this.cineplex = cineplex;
    }

    /**
     * Returns Cineplex tied to CineplexAdminAccount
     * @return Cineplex
     */
    public Cineplex getCineplex(){
        return this.cineplex;
    }

    /**
     * Authenticates login of CineplexAdmin. Extra layer of security (2FA) is included.
     * @param loginId which is entered by user
     * @param password which is entered by user
     * @return Account if login successful, null if login failed
     */
    public Privilege login(String loginId, String password){
        Scanner scanner = new Scanner(System.in);
        if(this.getLoginId().equals(loginId) && this.getPassword().equals(password)){
            System.out.println("Verification link has been sent to your phone. Please press 'enter' to login.");
            String idle = scanner.nextLine();
            return this.getPrivilege();
        }
        return null;
    }

}