package com.banktransfer.service.impl;

import com.banktransfer.application.BankTransferApplication;
import com.banktransfer.model.Account;
import com.banktransfer.service.TransferFunds;

import java.util.Map;

/**
 * This class is an actual implementation of TransferFunds for Savings Account
 */
public class SavingTransferFundsImpl implements TransferFunds {

    @Override
    public synchronized void transferFunds(int source, int destination, int amount) throws Exception {
        final Map<Integer, Account> accountMap = BankTransferApplication.getAccounts();
        final Account sourceAccount = lookupAccount(source, accountMap);
        if (sourceAccount == null)
            throw new Exception("Invalid Source Account Information");

        final Account destinationAccount = lookupAccount(destination, accountMap);
        if (destinationAccount == null)
            throw new Exception("Invalid Destination Account Information");

        double sourceAccountBalance = sourceAccount.getBalance();
        if (sourceAccountBalance < amount)
            throw new Exception("The existing balance is less than the amount " +
                    "requested to be transferred.");
        sourceAccountBalance = sourceAccountBalance - amount;
        sourceAccount.setBalance(sourceAccountBalance);

        double destinationAccountBalance = destinationAccount.getBalance();
        destinationAccountBalance = destinationAccountBalance + amount;
        destinationAccount.setBalance(destinationAccountBalance);
    }

    private synchronized Account lookupAccount(int accountNumber, Map<Integer, Account> accountMap) {
        Object key = accountNumber;
        return accountMap.get(key);
    }
}
