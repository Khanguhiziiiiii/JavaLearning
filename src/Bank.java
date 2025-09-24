import java.util.*;

public class Bank {
    private final Map<String, BankAccount> accountsMap= new HashMap<>();
    private final Set<String> accountNumbers= new HashSet<>();
    private final List<BankAccount> accountsList = new ArrayList<>();

    private int accountCounter=1000;

    public synchronized String generateAccountNumber(){
        accountCounter++;
        String accNo="AC"+accountCounter;
        while(accountNumbers.contains(accNo)){
            accountCounter++;
            accNo="AC"+accountCounter;
        }
        return accNo;
    }

    public boolean addAccount(BankAccount account){
        String accNo=account.getAccountNumber();
        if(accountNumbers.contains(accNo)){
            return false;
        }
        accountNumbers.add(accNo);
        accountsMap.put(accNo,account);
        accountsList.add(account);
        return true;
    }

    public BankAccount findAccount(String accountNumber){
        return accountsMap.get(accountNumber);
    }

    public List<BankAccount> getAllAccounts(){
        return new ArrayList<>(accountsList);
    }

    public  boolean removeAccount(String accountNumber){
        BankAccount acc=accountsMap.remove(accountNumber);
        if(acc==null){
            return false;
        }
        accountNumbers.remove(accountNumber);
        accountsList.remove(acc);
        return true;
    }

    public void clearAll(){
        accountNumbers.clear();
        accountsList.clear();
        accountsMap.clear();
    }
}
