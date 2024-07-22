import bankSystem.Account;
import bankSystem.Bank;
import bankSystem.CustomerThread;
import bankSystem.Transaction;
import processFileLog.ParallelLogProcessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println("****************************************************************************************************************");
        System.out.println("Programação Concorrente e Paralela");
        System.out.println("****************************************************************************************************************");

        Bank bank = new Bank();

        for(int i = 0; i <5;i++) {
            Account account = new Account(500.0);
            bank.addAccount(i+1, account);
        }

        List<CustomerThread> threads = new ArrayList<CustomerThread>();
        for(int i = 0; i < 5; i++) {
            CustomerThread thread = new CustomerThread(bank, i+1);
            threads.add(thread);
            thread.start();
        }

        for (CustomerThread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        for (int i = 1; i <= 5; i++) {
            Account account = bank.getAccount(i);
            System.out.println("\nAccount " + i + " balance: " + account.getBalance());
            System.out.println("Transaction history for account " + i + ":");
            for (Transaction transaction : account.getTransactions()) {
                System.out.println(transaction);
            }
        }

        System.out.println("****************************************************************************************************************");
        System.out.println("Processamento de Arquivos de Log");
        System.out.println("****************************************************************************************************************");

        List<String> logFiles = Arrays.asList(
                "logs/log1.txt",
                "logs/log2.txt",
                "logs/log3.txt"
        );
        String wordToCount = "info";

        ParallelLogProcessor processor = new ParallelLogProcessor(4);
        int totalOccurrences = processor.processLogs(logFiles, wordToCount);

        System.out.println("Total occurrences of '" + wordToCount + "': " + totalOccurrences);
    }
}