package com.banktransfer.model;

import java.util.List;

/**
 * Model class for customer at the bank. This class is not utilized in the current
 * implementation, but can be used for other activities.
 */
public class Customer {

    private String firstName;
    private String middleName;
    private String lastName;
    private String address;

    private List<String> listOfAccount;
    private boolean isPlatinumCustomer;

    public Customer(final String firstName, final String middleName, final String lastName,
                    final String address, final List<String> listOfAccount, final boolean isPlatinumCustomer) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.address = address;
        this.listOfAccount = listOfAccount;
        this.isPlatinumCustomer = isPlatinumCustomer;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(final String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public List<String> getListOfAccount() {
        return listOfAccount;
    }

    public void setListOfAccount(final List<String> listOfAccount) {
        this.listOfAccount = listOfAccount;
    }

    public boolean isPlatinumCustomer() {
        return isPlatinumCustomer;
    }

    public void setPlatinumCustomer(final boolean platinumCustomer) {
        isPlatinumCustomer = platinumCustomer;
    }
}
