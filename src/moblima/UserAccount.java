package moblima;

public class UserAccount extends Account{
    public UserAccount(String loginId, String password, int privilege){
        super(loginId, password,privilege);
    }
    public int login(String loginId, String password){
        if(this.getLoginId().equals(loginId) && this.getPassword().equals(password)){
            return this.getPrivilege();
        }
        return -1;
    }
}