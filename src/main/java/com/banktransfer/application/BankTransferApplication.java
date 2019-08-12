package com.banktransfer.application;

import com.banktransfer.model.Account;
import com.banktransfer.resource.Default;
import com.banktransfer.resource.TransferFundsRestService;

import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The actual application that runs whenever the server starts up.
 */
public class BankTransferApplication extends Application {

    private Set<Object> singletons = new HashSet<>();

    /**
     * Number of accounts in this sample application.
     */
    private static final int NUM_ACCOUNTS = 10;

    /**
     * Number of deposits for each deposit thread to make.
     */
    private static final int NUM_DEPOSITS = 10;

    /**
     * Number of threads to make deposits.
     */
    private static final int NUM_DEPOSIT_THREADS = 5;

    /**
     * Initial balance of new accounts.
     */
    private static final int INITIAL_BALANCE = 100;

    /**
     * Amount of each deposit.
     */
    private static final int AMOUNT_TO_DEPOSIT = 10;

    /**
     * A mapping from account number to account.
     */
    private static Map<Integer, Account> accounts;

    public BankTransferApplication() throws InterruptedException {
        singletons.add(new TransferFundsRestService());
        singletons.add(new Default());
        configureBankTransferApplication();
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }

    private void configureBankTransferApplication() throws InterruptedException {
        accounts = new HashMap();
        for (int i = 0; i < NUM_ACCOUNTS; i++)
            openAccount(i, INITIAL_BALANCE);

        // create threads that will test the banking system
        Thread[] threads = new Thread[NUM_DEPOSIT_THREADS];

        int index = 0;

        // create threads that repeatedly deposit money to all accounts
        for (int i = 0; i < NUM_DEPOSIT_THREADS; i++) {
            threads[index++] = createDepositThread(AMOUNT_TO_DEPOSIT);
        }

        // start the threads
        for (Thread thread : threads)
            thread.start();

        // wait until the threads stop
        for (Thread thread : threads)
            thread.join();
    }

    /**
     * Returns a new thread that repeatedly deposits to all of the accounts.<p>
     *
     * @param depositAmount amount of each deposit
     * @return the new thread that makes deposits
     */
    private Thread createDepositThread(final double depositAmount) {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                for (int acctNumber = 0; acctNumber < NUM_ACCOUNTS; acctNumber++) {
                    Account acct = lookupAccount(acctNumber);

                    for (int i = 0; i < NUM_DEPOSITS; i++) {
                        deposit(acct, depositAmount);
                    }
                }
            }
        });
        return thread;
    }

    /**
     * Adds a new account with the specified number and initial balance.<p>
     *
     * @param accountNumber  account number of account to open
     * @param initialBalance initial balance of the account to open
     * @return the new account
     */
    private synchronized Account openAccount(int accountNumber, double initialBalance) {
        Account account = new Account(12345);
        accounts.put(accountNumber, account);
        account.setBalance(initialBalance);
        return account;
    }

    /**
     * Finds the account with the specified account number.
     * Returns <code>null</code> if no account is found.<p>
     *
     * @param accountNumber the number of the account to find
     * @return the account with the specified account number or <code>null</code>
     * if none found
     */
    private synchronized Account lookupAccount(int accountNumber) {
        return (Account) accounts.get(accountNumber);
    }

    /**
     * Deposits the specified amount to the specified account.<p>
     *
     * @param acct   account to add or subtract money
     * @param amount amount to deposit (may be negative)
     */
    private synchronized void deposit(Account acct, double amount) {
        double balance = acct.getBalance();   // get the current balance
        balance += amount;                    // adjust the balance
        acct.setBalance(balance);           // set the new balance
    }

    public static Map getAccounts() {
        return accounts;
    }
}
