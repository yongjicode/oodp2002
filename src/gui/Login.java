package gui;
import account.Account;

public interface Login {
    public Account login(String username, String password);
    public Account getAccount();
}
