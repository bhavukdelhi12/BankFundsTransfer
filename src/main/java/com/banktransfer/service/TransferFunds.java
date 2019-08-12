package com.banktransfer.service;

/**
 * This interface contains functions that can be implemented by any different type
 * of account (Eg: Savings, Current, etc.) at a bank.
 */
public interface TransferFunds {
    void transferFunds(int source, int destination, int amount) throws Exception;
}
