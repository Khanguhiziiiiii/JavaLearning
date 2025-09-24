import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static sun.text.normalizer.Utility.escape;

public class Files {
    private static final String DELIMITER = "|";

    public static void saveAccounts(String filePath, List<BankAccount> accounts) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (BankAccount acc : accounts) {
                StringBuilder sb = new StringBuilder();
                sb.append(acc.getAccountType()).append(DELIMITER);
                sb.append(acc.getAccountNumber()).append(DELIMITER);
                sb.append(escape(acc.getAccountHolder())).append(DELIMITER);
                sb.append(String.format(Locale.US, "%.2f", acc.getBalance())).append(DELIMITER);

                if (acc instanceof SavingsAccount) {
                    sb.append(String.format(Locale.US, "%.4f", ((SavingsAccount) acc).getInterestRate()));
                } else if (acc instanceof CheckingAccount) {
                    sb.append(String.format(Locale.US, "%.4f", ((CheckingAccount) acc).getOverdraftLimit()));
                } else {
                    sb.append("0");
                }
                bw.write(sb.toString());
                bw.newLine();
            }
            System.out.println("Saved " + accounts.size() + " account(s) to " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving accounts: " + e.getMessage());
        }
    }

    public static List<BankAccount> loadAccounts(String filePath) {
        List<BankAccount> accounts = new ArrayList<>();
        File f= new File(filePath);
        if (!f.exists()) {
            System.out.println("Account file does not exist");
            return accounts;
        }try(BufferedReader br= new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = br.readLine())!=null){
                String[] parts = line.split("\\|",-1);
                if(parts.length<5)continue;
                String type = parts[0];
                String accNo = parts[1];
                String holder = parts[2];
                double balance = Double.parseDouble(parts[3]);
                double extra = Double.parseDouble(parts[4]);

                if(type.equalsIgnoreCase("SavingsAccount")){
                    accounts.add(new SavingsAccount(holder, accNo, balance, extra));
                }else if(type.equalsIgnoreCase("CheckingAccount")){
                    accounts.add(new CheckingAccount(holder, accNo, balance, extra));
                }else{
                    accounts.add(new CheckingAccount(holder, accNo, balance, 0.00));
                }
            }
            System.out.println("Loaded " + accounts.size() + " account(s) from " + filePath);
        }catch (IOException e) {
            System.err.println("Error loading accounts: " + e.getMessage());
        }
        return accounts;
    }

    private static String escape(String s) {
        return s.replace(DELIMITER, "");
    }
    private static String unescape(String s) {
        return s;
    }
}
