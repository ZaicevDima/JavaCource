package com.zaicev.bank;

import java.util.concurrent.atomic.AtomicInteger;

public class BankAccount {

    private final AtomicInteger balance;

    public BankAccount(int balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Initial balance must be positive");
        }
        this.balance = new AtomicInteger(balance);
    }

    public void deposit(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Deposit must be positive");
        }
        balance.addAndGet(value);
    }

    public boolean withdraw(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Withdraw must be positive");
        }
        while (true) {
            int currentValue = balance.get();
            if (currentValue < value) {
                return false;
            }

            if (balance.compareAndSet(currentValue, currentValue - value)) {
                return true;
            }
        }
    }

    public int getBalance() {
        return balance.get();
    }
}
