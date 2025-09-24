public abstract class BankAccount implements BankOperations {
    private String accountHolder;
    private String accountNumber;
    private String accountType;
    private double balance;

    //Constructor
    public BankAccount(String accountHolder, String accountNumber, double initialBalance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = Math.max(0, initialBalance);
    }

    //Getters
    public String getAccountHolder() {
        return accountHolder;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountType() {return accountType;}

    public double getBalance() {
        return balance;
    }

    //Setter
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public void deposit(double amount) {
        if (amount < 0) {
            System.out.println("Invalid amount");
        }
        balance += amount;
        System.out.println("You have deposited " + amount + ". Your new balance is " + balance);
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount < 0) {
            System.out.println("Invalid amount");
        }else if (amount > balance) {
            System.out.println("Invalid amount");
        }else {
            balance -= amount;
            System.out.println("You have withdrawn " + amount + ". Your new balance is " + balance);
        }
    }

}
