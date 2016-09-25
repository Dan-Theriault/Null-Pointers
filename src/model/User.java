package model;

/**
 * Created by dtheriault3 on 9/25/16.
 */
public class User {
    private static Database db = new Database();
    private boolean authorizedUser;
    private String username;

    public User(String username, String password) {
        authorizedUser = db.logIn(username, password);
        this.username = username;
    }

    public boolean isAuthorizedUser() {
        return authorizedUser;
    }

    public String getUsername() {
        return username;
    }
}
