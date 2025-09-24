public class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(String accountHolder, String accountNumber, double initialBalance, double interestRate) {
        super(accountHolder, accountNumber, initialBalance);
        this.interestRate = interestRate;
    }

    public void applyAnnualInterest() {
        double interest = getBalance() * (interestRate / 100);
        deposit(interest);
        System.out.printf("Interest of %.2f applied. New Balance: %.2f\n", interest, getBalance());
    }

    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be greater than 0.");
            return;
        }
        if (amount > getBalance()) {
            throw new InsufficientFundsException("Insufficient balance.");
        }
        setBalance(getBalance() - amount);
        System.out.printf("Withdrawn: %.2f. New Balance: %.2f\n", amount, getBalance());
    }

    @Override
    public String getAccountType() {
        return "SavingsAccount";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | SAVINGS ACCOUNT | Interest: %.2f%%", interestRate);
    }
}
