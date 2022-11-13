package account;

public class CustomerAccount extends Account{
    /**
     * Creates CustomerAccount with given parameters
     * @param loginId which is the login ID of Customer
     * @param password which is the password of Customer
     * @param privilege which is the Privilege of Customer
     * @param emailAddress which is the email address of Customer
     * @param phoneNo which is the phone number of Customer
     * @param name which is the name of Customer
     */


    public CustomerAccount(String loginId, String password, Privilege privilege, String emailAddress, String phoneNo, String name){
        super(loginId, password,privilege,emailAddress,phoneNo,name);
    }
    /**
     * Authenticates login of CustomerAccount
     * @param loginId which is entered by user
     * @param password which is entered by user
     * @return Account if login successful, null if login failed
     */
    public Privilege login(String loginId, String password){
        if(this.getLoginId().equals(loginId) && this.getPassword().equals(password)){
            return this.getPrivilege();
        }
        return null;
    }
}