package bankSystem;

import java.util.Random;

public class CustomerThread extends Thread {
    private final Bank bank;
    private int accountId;
    private Random random;

    public CustomerThread(Bank bank, int accountId) {
        this.bank = bank;
        this.accountId = accountId;
        this.random = new Random();
    }

    @Override
    public void run() {
        super.run();
        Account account = bank.getAccount(accountId);
        if (account == null) {
            System.out.println("Account not found: " + accountId);
            return;
        }

        for (int i = 0; i <5; i++) {
            double amount = random.nextDouble() * 100;
            Boolean randomBoolean = random.nextBoolean();
            if (randomBoolean) {
                account.withdraw(amount);
                System.out.println("Customer " + Thread.currentThread().getId() + " withdrew " + amount + " from account " + accountId);
            } else {
                account.deposit(amount);
                System.out.println("Customer " + Thread.currentThread().getId() + " deposited " + amount + " into account " + accountId);
            }
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
