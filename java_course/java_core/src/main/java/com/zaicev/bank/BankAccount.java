package com.zaicev.bank;

import java.util.UUID;

public class BankAccount {

    private final UUID accountId;
    private int balance;

    public BankAccount(int balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Initial balance must be positive");
        }
        this.accountId = UUID.randomUUID();
        this.balance = balance;
    }

    public synchronized void deposit(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Deposit must be positive");
        }
        balance += value;
    }

    public synchronized void withdraw(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Withdraw must be positive");
        }

        if (balance < value) {
            throw new IllegalArgumentException("Insufficient funds on account");
        }

        balance -= value;
    }

    public synchronized int getBalance() {
        return balance;
    }

    public UUID getAccountId() {
        return accountId;
    }
}
