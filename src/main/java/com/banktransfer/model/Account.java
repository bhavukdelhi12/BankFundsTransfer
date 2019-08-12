package com.banktransfer.model;

/**
 * Model class for Account at a bank.
 */
public class Account {

    private double balance;
    private String accountId;
    private String bankName;

    public Account(final int uniqueBankId) {
        if (uniqueBankId == 12345) {
            bankName = "USA Bank";
        } else {
            bankName = "Other bank";
        }
    }
    public double getBalance() {
        return balance;
    }

    public void setBalance(final double balance) {
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(final String accountId) {
        this.accountId = accountId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(final String bankName) {
        this.bankName = bankName;
    }
}
