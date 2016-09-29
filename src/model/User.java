package model;

/** Class representing a User.
 *
 * Created by dtheriault3 on 9/25/16.
 */
public class User {
    private String title;
    private String name;
    private String username;
    private AccountType accountType;
    private String emailAddress;
    private String homeAddress;
    private String password;

    public User(String name, String username, AccountType accountType, String password) {
        this.name = name;
        this.username = username;
        this.accountType = accountType;
        this.password = password;
    }

    public User(String title, String name, String username, AccountType accountType,
                String emailAddress, String homeAddress, String password) {
        this(name, username, accountType, password);
        this.title = title;
        this.emailAddress = emailAddress;
        this.homeAddress = homeAddress;
    }


    @Override
    public String toString() {
        return "User{" + title + " " + name + " " + username + " " + accountType + " " + emailAddress
                + " " + homeAddress + " " + password + "}";
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
