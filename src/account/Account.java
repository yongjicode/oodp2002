package account;

public abstract class Account{
	private String email;
	private String name;
	private String phoneNo;
    private String loginId;
    private String password;
    private int privilege;
    //0 for user, 1 for cineplexAdmin, 2 for companyAdmin

    public Account(String loginId, String password, int privilege, String emailAddress, String phoneNo, String name){

        this.loginId = loginId;
        this.password = password;
        this.privilege = privilege;
        this.name = name;
        this.phoneNo = phoneNo;
        this.email = emailAddress;
    }

    public String getLoginId(){
        return loginId;
    }

    public String getPassword(){
        return password;
    }

    public int getPrivilege(){
        return privilege;
    }
    
    public String getName() {
    	return name;
    }
    
    public String getEmail() {
    	return email;
    }
    public String getPhoneNo(){
    	return phoneNo;
    }
    public abstract int login(String loginId, String password);
}