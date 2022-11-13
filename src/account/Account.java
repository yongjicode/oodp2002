package account;

/**
 * Abstract class for Account
 */
public abstract class Account{
	private String email;
	private String name;
	private String phoneNo;
    private String loginId;
    private String password;
    private Privilege privilege;
    //0 for user, 1 for cineplexAdmin, 2 for companyAdmin

    /**
     * Creates an Account Object with given parameters
     * @param loginId which is the login ID of user
     * @param password which is the password of user
     * @param privilege which is the privilege of user
     * @param emailAddress which is the email address of user
     * @param phoneNo which is the phone number of user
     * @param name which is the name of user
     */
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

    /**
     * Converts Int to Privilege
     * @param i which is an integer
     * @return Privilege corresponding to integer
     */
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

    /**
     * Converts Privilege to Int
     * @param privilege of Account
     * @return integer corresponding to Privilege
     */
    public static int convertPrivilegeToInt(Privilege privilege){
        if (privilege == Privilege.User) return 0;
        else if (privilege == Privilege.CineplexAdmin) return 1;
        else return 0;
    }
    public abstract Privilege login(String loginId, String password);
}