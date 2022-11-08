package account;

public class UserAccount extends Account{
    public UserAccount(String loginId, String password, int privilege,String emailAddress, String phoneNo, String name){
        super(loginId, password,privilege,emailAddress,phoneNo,name);
    }
    public int login(String loginId, String password){
        if(this.getLoginId().equals(loginId) && this.getPassword().equals(password)){
            return this.getPrivilege();
        }
        return -1;
    }
}