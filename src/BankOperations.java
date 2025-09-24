public interface BankOperations {
    void deposit(double amount);
    void withdraw(double amount) throws InsufficientFundsException;
    double getBalance();
    String getAccountNumber();
    String getAccountHolder();
}
