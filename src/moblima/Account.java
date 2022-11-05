package moblima;

public abstract class Account {
    private String loginId;
    private String password;
    private int privilege;
    //0 for user, 1 for cineplexAdmin, 2 for companyAdmin

    public Account(String loginId, String password, int privilege){

        this.loginId = loginId;
        this.password = password;
        this.privilege = privilege;
    }

    public String getLoginId(){
        return loginId;
    }

    protected String getPassword(){
        return password;
    }

    public int getPrivilege(){
        return privilege;
    }
    public abstract int login(String loginId, String password);
}