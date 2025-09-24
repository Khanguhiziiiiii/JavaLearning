public class CheckingAccount extends BankAccount {
    private double overdraftLimit;

    public CheckingAccount(String accountHolder, String accountNumber, double initialBalance, double overdraftLimit) {
        super(accountHolder, accountNumber, initialBalance);
        this.overdraftLimit = overdraftLimit;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be greater than 0.");
            return;
        }

        double allowed = getBalance() + overdraftLimit;
        if (amount > allowed) {
            throw new InsufficientFundsException("Exceeds overdraft limit. Available (balance + overdraft) is: " + allowed);
        }

        setBalance(getBalance() - amount);
        System.out.printf("Withdrawn: %.2f. New Balance: %.2f.\n", amount, getBalance());
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public String getAccountType() {
        return "CheckingAccount";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | CHECKING ACCOUNT | Overdraft: %.2f", overdraftLimit);
    }
}
