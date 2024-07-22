package bankSystem;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private final Map<Integer, Account> accounts;
    private final ReentrantLock lock;

    public Bank() {
        this.accounts = new HashMap<Integer, Account>();
        this.lock = new ReentrantLock();
    }

    public void addAccount(int accountId, Account account){
        lock.lock();
        try {
            accounts.put(accountId, account);
        } finally {
            lock.unlock();
        }
    }

    public Account getAccount(int accountId){
        lock.lock();
        try {
            return accounts.get(accountId);
        } finally {
            lock.unlock();
        }
    }

}
