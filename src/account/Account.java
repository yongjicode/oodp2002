package account;

public abstract class Account{
	private String email;
	private String name;
	private String phoneNo;
    private String loginId;
    private String password;
    private Privilege privilege;
    //0 for user, 1 for cineplexAdmin, 2 for companyAdmin

    public Account(String loginId, String password, Privilege privilege, String emailAddress, String phoneNo, String name){

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

    public Privilege getPrivilege(){
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
    public static Privilege convertIntToPrivilege(int i){
        switch(i){
            case 0:
                return Privilege.User;
            case 1:
                return Privilege.CineplexAdmin;
            default:
                return Privilege.CompanyAdmin;
        }
    }
    public abstract Privilege login(String loginId, String password);
}