package account;

import java.util.Scanner;

public class CompanyAdminAccount extends Account{
    public CompanyAdminAccount(String loginId, String password, Privilege privilege,String emailAddress, String phoneNo, String name){
        super(loginId, password,privilege,emailAddress,phoneNo,name);
    
    }

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