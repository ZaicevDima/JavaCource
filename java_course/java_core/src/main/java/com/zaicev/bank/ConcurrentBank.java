package com.zaicev.bank;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentBank {

    private final Set<BankAccount> bankAccountList = ConcurrentHashMap.newKeySet();

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

        BankAccount first = (account1.getAccountId().compareTo(account2.getAccountId())) < 0 ? account1 : account2;
        BankAccount second = first == account1 ? account2 : account1;

        synchronized (first) {
            synchronized (second) {
                account1.withdraw(countingMoney);
                account2.deposit(countingMoney);
            }
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
