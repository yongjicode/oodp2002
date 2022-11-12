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

    public final String getLoginId(){
        return loginId;
    }

    public final String getPassword(){
        return password;
    }

    public final Privilege getPrivilege(){
        return privilege;
    }
    
    public final String getName() {
    	return name;
    }
    
    public final String getEmail() {
    	return email;
    }
    public final String getPhoneNo(){
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
    public static int convertPrivilegeToInt(Privilege privilege){
        if (privilege == Privilege.User) return 0;
        else if (privilege == Privilege.CineplexAdmin) return 1;
        else return 0;
    }
    public abstract Privilege login(String loginId, String password);
}