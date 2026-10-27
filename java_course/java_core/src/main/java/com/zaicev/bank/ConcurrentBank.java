package com.zaicev.bank;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentBank {

    private final Set<BankAccount> bankAccountList = ConcurrentHashMap.newKeySet();
    private final ReentrantLock transferLock = new ReentrantLock();

    public BankAccount createAccount(int countingMoney) {
        BankAccount account = new BankAccount(countingMoney);
        bankAccountList.add(account);

        return account;
    }


    public void transfer(BankAccount account1, BankAccount account2, int countingMoney) {
        Objects.requireNonNull(account1, "Account1 mustn't be null");
        Objects.requireNonNull(account2, "Account2 mustn't be null");

        if (account1 == account2) {
            return;
        }

        if (countingMoney <= 0) {
            throw new IllegalArgumentException("Counting money must be positive");
        }

        if (!bankAccountList.contains(account1) || !bankAccountList.contains(account2)) {
            throw new IllegalArgumentException("Both accounts must belong to this bank");
        }

        transferLock.lock();

        try {
            account1.withdraw(countingMoney);
            account2.deposit(countingMoney);
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
