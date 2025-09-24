import java.util.*;

public class Main {
    private static final String DATA_FILE = "accounts.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();

        List<BankAccount> loaded = Files.loadAccounts(DATA_FILE);
        for (BankAccount a : loaded) {
            bank.addAccount(a);
        }

        boolean running = true;
        BankAccount currentAccount = null;

        while (running) {
            if (currentAccount == null) {
                showMenu();
                String choice = sc.nextLine().trim();

                switch (choice) {
                    case "1":
                        currentAccount = createAccount(sc, bank);
                        break;
                    case "2":
                        currentAccount = chooseAccount(sc, bank);
                        break;
                    case "3":
                        saveAndExit(bank);
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            } else {
                showAccountMenu(currentAccount);
                String choice = sc.nextLine().trim();

                switch (choice) {
                    case "1":
                        depositToAccount(sc, currentAccount);
                        break;
                    case "2":
                        withdrawFromAccount(sc, currentAccount);
                        break;
                    case "3":
                        checkBalance(currentAccount);
                        break;
                    case "4":
                        applyInterest(currentAccount);
                        break;
                    case "5":
                        requestLoan(currentAccount);
                        break;
                    case "6":
                        currentAccount = null;
                        System.out.println("You have logged out of the account.");
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            }
        }

        sc.close();
    }

    private static void showMenu() {
        System.out.println("\n--- Welcome to My Bank System ---");
        System.out.println("1. Create New Account");
        System.out.println("2. Select from Existing Accounts");
        System.out.println("3. Exit");
        System.out.print("Enter choice: ");
    }

    private static void showAccountMenu(BankAccount acc) {
        System.out.println("\n--- Account Menu (" + acc.getAccountNumber() + ") ---");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Check Balance");
        System.out.println("4. Apply Interest");
        System.out.println("5. Request Loan");
        System.out.println("6. Logout");
        System.out.print("Enter choice: ");
    }

    private static BankAccount createAccount(Scanner sc, Bank bank) {
        System.out.print("Enter account holder name: ");
        String holderName = sc.nextLine().trim();

        System.out.println("Choose account type: \n1) Savings \n2) Checking");
        String t = sc.nextLine().trim();

        String accNo = bank.generateAccountNumber();
        double initial = 0.0;

        if (t.equals("1")) {
            double rate = 7.0;
            SavingsAccount s = new SavingsAccount(holderName, accNo, initial, rate);
            bank.addAccount(s);
            System.out.println("Savings account created successfully.");
            return s;
        } else {
            double overdraft = initial * 0.1;
            CheckingAccount c = new CheckingAccount(holderName, accNo, initial, overdraft);
            bank.addAccount(c);
            System.out.println("Checking account created successfully.");
            return c;
        }
    }

    private static BankAccount chooseAccount(Scanner sc, Bank bank) {
        List<BankAccount> all = bank.getAllAccounts();
        if (all.isEmpty()) {
            System.out.println("No accounts found.");
            return null;
        }

        System.out.println("Available Accounts:");
        for (int i = 0; i < all.size(); i++) {
            System.out.println((i + 1) + ". " + all.get(i));
        }

        System.out.print("Enter choice: ");
        int choice = Integer.parseInt(sc.nextLine().trim());
        if (choice < 1 || choice > all.size()) {
            System.out.println("Invalid choice.");
            return null;
        }

        return all.get(choice - 1);
    }

    private static void depositToAccount(Scanner sc, BankAccount acc) {
        System.out.print("Enter amount to deposit: ");
        double amount = Double.parseDouble(sc.nextLine().trim());
        acc.deposit(amount);

        if (acc instanceof CheckingAccount) {
            ((CheckingAccount) acc).setOverdraftLimit(acc.getBalance() * 0.1);
        }
    }

    private static void withdrawFromAccount(Scanner sc, BankAccount acc) {
        System.out.print("Enter amount to withdraw: ");
        double amount = Double.parseDouble(sc.nextLine().trim());

        try {
            acc.withdraw(amount);
        } catch (InsufficientFundsException e) {
            System.out.println("Insufficient funds.");
        }
    }

    private static void applyInterest(BankAccount acc) {
        if (acc instanceof SavingsAccount) {
            ((SavingsAccount) acc).applyAnnualInterest();
            System.out.println("Annual interest applied.");
        } else {
            System.out.println("This is not a Savings Account.");
        }
    }

    private static void requestLoan(BankAccount acc) {
        if (acc.getBalance() < 10000) {
            System.out.println("You can't request a loan.");
        } else {
            System.out.println("Loan requested successfully.");
        }
    }

    private static void checkBalance(BankAccount acc) {
        System.out.println("Your balance is: " + acc.getBalance());
    }

    private static void saveAndExit(Bank bank) {
        List<BankAccount> all = bank.getAllAccounts();
        Files.saveAccounts(DATA_FILE, all);
        System.out.println("Accounts saved to " + DATA_FILE);
    }
}
