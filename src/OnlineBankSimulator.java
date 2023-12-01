import java.util.ArrayList;
import java.util.Scanner;

class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;
    private ArrayList<String> transactionHistory;

    public BankAccount(String accountNumber, String accountHolder) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposit: +" + amount);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrawal: -" + amount);
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    public void transfer(BankAccount receiver, double amount) {
        if (amount <= balance) {
            balance -= amount;
            receiver.deposit(amount);
            transactionHistory.add("Transfer to " + receiver.getAccountHolder() + ": -" + amount);
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    public void viewTransactionHistory() {
        System.out.println("Transaction History for Account " + accountNumber + ":");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}

public class OnlineBankSimulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Online Bank Simulator!");

        // Create accounts
        BankAccount account1 = new BankAccount("123456", "John Doe");
        BankAccount account2 = new BankAccount("789012", "Jane Smith");

        // Perform transactions
        account1.deposit(1000);
        account1.withdraw(200);
        account1.transfer(account2, 300);

        account2.deposit(500);
        account2.withdraw(50);

        // Display account information
        displayAccountInfo(account1);
        displayAccountInfo(account2);

        // View transaction history
        account1.viewTransactionHistory();
        account2.viewTransactionHistory();

        scanner.close();
    }

    private static void displayAccountInfo(BankAccount account) {
        System.out.println("Account Holder: " + account.getAccountHolder());
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Balance: $" + account.getBalance());
        System.out.println();
    }
}