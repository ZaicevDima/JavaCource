package com.zaicev.bank;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentBank {

    private final List<BankAccount> bankAccountList = new CopyOnWriteArrayList<>();
    private final ReentrantLock transferLock = new ReentrantLock();

    public BankAccount createAccount(int countingMoney) {
        BankAccount account = new BankAccount(countingMoney);
        bankAccountList.add(account);

        return account;
    }


    public boolean transfer(BankAccount account1, BankAccount account2, int countingMoney) {
        Objects.requireNonNull(account1, "Account1 mustn't be null");
        Objects.requireNonNull(account2, "Account2 mustn't be null");
        if (countingMoney <= 0) {
            throw new IllegalArgumentException("Counting money must be positive");
        }
        if (!bankAccountList.contains(account1) || !bankAccountList.contains(account2)) {
            throw new IllegalArgumentException("Both accounts must belong to this bank");
        }

        transferLock.lock();
        try {
            if (!account1.withdraw(countingMoney)) return false;
            account2.deposit(countingMoney);
            return true;
        } finally {
            transferLock.unlock();
        }
    }

    public int getTotalBalance() {
        int sum = 0;
        for (BankAccount a : bankAccountList) {
            sum += a.getBalance();
        }
        return sum;
    }
}
