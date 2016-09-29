package model;

/**
 * Created by nickhutchinson on 9/28/16.
 */
public enum AccountType {
    USER("USR"),
    WORKER("WKR"),
    MANAGER("MNGR"),
    ADMIN("ADMN");
    private String accountTypeValue;

    AccountType (String value) {
        this.accountTypeValue = value;
    }

    public String getAccountTypeValue() {
        return accountTypeValue;
    }

}
