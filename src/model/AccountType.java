package model;

/**
 * Created by nickhutchinson on 9/28/16.
 */
public enum AccountType {
    USER("User"),
    WORKER("Worker"),
    MANAGER("Manager"),
    ADMIN("Administrator");
    private String accountTypeValue;

    AccountType(String value) {
        this.accountTypeValue = value;
    }

    public String getAccountTypeValue() {
        return accountTypeValue;
    }

    public void setAccountTypeValue(String accountTypeValue) {
        this.accountTypeValue = accountTypeValue;
    }
}
