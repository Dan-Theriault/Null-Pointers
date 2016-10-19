package model;

/**
 * Created by nickhutchinson on 9/28/16.
 */
public enum AccountType {
    USER("USER"),
    WORKER("WORKER"),
    MANAGER("MANAGER"),
    ADMIN("ADMIN");
    private String accountTypeValue;

    AccountType(String value) {
        this.accountTypeValue = value;
    }

    /**
     * getter for value of account type
     * @return String representation of account type
     */
    public String getAccountTypeValue() {
        return accountTypeValue;
    }

    /**
     * set the account type of a user to a new value
     * @param accountTypeValue value of a valid enum element
     */
    public void setAccountTypeValue(String accountTypeValue) {
        this.accountTypeValue = accountTypeValue;
    }
}
