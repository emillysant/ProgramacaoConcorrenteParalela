package bankSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private double balance;
    private final List<Transaction> transactionHistory;
    private final ReentrantLock lock;

    public Account(double initialBalance) {
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        this.lock = new ReentrantLock();
    }

    public void deposit(double amount) {
        lock.lock();
        try {
            balance = balance+amount;
            transactionHistory.add(new Transaction( "Deposit", amount));
        } finally {
            lock.unlock();
        }
    }

    public List<Transaction> getTransactions() {
        lock.lock();
        try {
            return transactionHistory;
        } finally {
            lock.unlock();
        }
    }
    public boolean withdraw(double amount) {
        lock.lock();
        try {
            if (balance >= amount) {
                balance -= amount;
                transactionHistory.add(new Transaction("Withdrawal", amount));
                return true;
            } else  {
                System.out.println("Insufficient funds");
                return false;
            }
        }finally {
            lock.unlock();
        }
    }

    public double getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                ", transactionHistory=" + transactionHistory +
                '}';
    }
}
