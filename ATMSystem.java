import java.util.ArrayList;
import java.util.Scanner;


class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private int pin;
    private double balance;
    private ArrayList<String> transactionHistory;

    public BankAccount(String accountNumber, String accountHolder, int pin, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public boolean validatePin(int inputPin) {
        return this.pin == inputPin;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Must be greater than 0.");
            return;
        }
        balance += amount;
        transactionHistory.add("Deposited: " + amount);
        System.out.println("Successfully deposited $" + amount);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Must be greater than 0.");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient balance! Current balance: $" + balance);
            return;
        }
        balance -= amount;
        transactionHistory.add("Withdrew: " + amount);
        System.out.println("Successfully withdrew $" + amount);
    }

    public void checkBalance() {
        System.out.println("Current balance: $" + balance);
    }

    public void showTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            System.out.println("Transaction History:");
            for (String record : transactionHistory) {
                System.out.println(record);
            }
        }
    }

    public String getAccountHolder() {
        return accountHolder;
    }
}


class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the ATM, " + account.getAccountHolder());

       
        int attempts = 0;
        boolean authenticated = false;
        while (attempts < 3) {
            System.out.print("Enter your PIN: ");
            int inputPin = scanner.nextInt();
            if (account.validatePin(inputPin)) {
                authenticated = true;
                break;
            } else {
                attempts++;
                System.out.println("Incorrect PIN! Attempts left: " + (3 - attempts));
            }
        }

        if (!authenticated) {
            System.out.println("Too many wrong attempts. Account locked!");
            return;
        }

        int choice;
        do {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Transaction History");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> account.checkBalance();
                case 2 -> {
                    System.out.print("Enter amount to deposit: $");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                }
                case 3 -> {
                    System.out.print("Enter amount to withdraw: $");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                }
                case 4 -> account.showTransactionHistory();
                case 5 -> System.out.println("Thank you for using the ATM");
                default -> System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 5);
    }
}

public class ATMSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get user details
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();

        System.out.print("Set your PIN (numbers only): ");
        int pin = scanner.nextInt();

        System.out.print("Enter your starting balance: $");
        double balance = scanner.nextDouble();

        BankAccount userAccount = new BankAccount(accountNumber, name, pin, balance);

        ATM atm = new ATM(userAccount);
        atm.start();
    }
}