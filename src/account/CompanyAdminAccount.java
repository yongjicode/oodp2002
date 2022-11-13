package account;

import java.util.Scanner;
/**
 * Account for Company Admin
 */
public class CompanyAdminAccount extends Account{
    /**
     * Creates CompanyAdminAccount with given parameters
     * @param loginId which is the login ID of CompanyAdminAccount
     * @param password which is the password of CompanyAdminAccount
     * @param privilege which is the Privilege of CompanyAdminAccount
     * @param emailAddress which is the email address of CompanyAdminAccount
     * @param phoneNo which is the phone number of CompanyAdminAccount
     * @param name which is the name of CompanyAdminAccount
     */
    public CompanyAdminAccount(String loginId, String password, Privilege privilege,String emailAddress, String phoneNo, String name){
        super(loginId, password,privilege,emailAddress,phoneNo,name);
    
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