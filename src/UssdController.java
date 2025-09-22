import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.math.BigDecimal;

// Person details
class Person {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private BigDecimal balance;

    public Person(String firstName, String lastName, String phoneNumber, BigDecimal balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}

interface UssdMenu {
    String getMenu();
}

// Main Menu
class MainMenu implements UssdMenu {
    private final List<String> options;

    public MainMenu() {
        options = Arrays.asList(
                "1. Send Money",
                "2. Withdraw Money",
                "3. Apply for Loan",
                "4. Make Purchase",
                "5. Check Balance",
                "6. Exit"
        );
    }

    @Override
    public String getMenu() {
        StringBuilder sb = new StringBuilder("CON Welcome to MyBank\n");
        for (String option : options) {
            sb.append(option).append("\n");
        }
        return sb.toString();
    }
}

// Balance Menu
class BalanceMenu implements UssdMenu {
    private final Person person;

    public BalanceMenu(Person person) {
        this.person = person;
    }

    @Override
    public String getMenu() {
        return "END " + person.getFirstName() + ", your balance is: KES " + person.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}

public class UssdController {
    private final List<Person> people = new ArrayList<>();

    public static void main(String[] args) {
        new UssdController().run();
    }

    public void run() {
        // Initialize people
        people.add(new Person("Cynthia", "Kanguhizi", "254700000000", new BigDecimal("10000.00")));
        people.add(new Person("John", "Doe", "2547111111111", new BigDecimal("150.00")));
        people.add(new Person("Laura", "Joy", "2547222222222", new BigDecimal("20.00")));
        people.add(new Person("Paul", "Ochieng", "254733333333", new BigDecimal("670.00")));
        people.add(new Person("Valentine", "Khasoa", "254744444444", new BigDecimal("499.00")));
        people.add(new Person("Wendy", "Wangeci", "254755555555", new BigDecimal("3201.00")));
        people.add(new Person("Vivian", "Mwende", "254766666666", new BigDecimal("59.00")));
        people.add(new Person("Newton", "Kioko", "254777777777", new BigDecimal("1.00")));
        people.add(new Person("Kelvin", "Webi", "254788888888", new BigDecimal("844.00")));
        people.add(new Person("Edwin", "Macharia", "254799999999", new BigDecimal("296.00")));

        Scanner scanner = new Scanner(System.in);

        // Select Profile
        System.out.println("Select your profile:");
        for (int i = 0; i < people.size(); i++) {
            System.out.println((i + 1) + ". " + people.get(i).getFirstName() + " " + people.get(i).getLastName() + " (" + people.get(i).getPhoneNumber() + ")");
        }
        System.out.println("Enter your choice:");
        int choice = Integer.parseInt(scanner.nextLine()) - 1;
        Person currentUser = people.get(choice);
        System.out.println("You are logged in as: " + currentUser.getFirstName() + " " + currentUser.getLastName() + " " + currentUser.getPhoneNumber() + "\n");

        // Loop
        boolean running = true;
        while (running) {
            MainMenu menu = new MainMenu();
            System.out.println(menu.getMenu());
            System.out.println("Choose option:");
            String option = scanner.nextLine();

            switch (option) {
                case "1": // Send Money
                    System.out.println("Enter recipient number:");
                    String recipientNumber = scanner.nextLine();
                    Person recipient = null;

                    for (Person p : people) {
                        if (p.getPhoneNumber().equals(recipientNumber)) {
                            recipient = p;
                            break;
                        }
                    }

                    if (recipient == null) {
                        System.out.println("Invalid recipient number");
                        break;
                    }

                    System.out.println("Enter amount to send:");
                    BigDecimal sendAmount = new BigDecimal(scanner.nextLine());

                    if (sendAmount.compareTo(BigDecimal.ZERO) < 0) {
                        System.out.println("Invalid amount!");
                    } else if (sendAmount.compareTo(currentUser.getBalance()) > 0) {
                        System.out.println("Insufficient funds!");
                    } else {
                        currentUser.setBalance(currentUser.getBalance().subtract(sendAmount));
                        recipient.setBalance(recipient.getBalance().add(sendAmount));
                        System.out.println("Sent " + sendAmount.setScale(2, BigDecimal.ROUND_HALF_UP) + " to " + recipient.getFirstName() + " " + recipient.getLastName() + ". Your balance is: " + currentUser.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP));
                    }
                    break;

                case "2": // Withdraw Money
                    System.out.println("Enter amount to withdraw:");
                    BigDecimal withdrawAmount = new BigDecimal(scanner.nextLine());

                    if (withdrawAmount.compareTo(BigDecimal.ZERO) < 0) {
                        System.out.println("Invalid amount!");
                    } else if (withdrawAmount.compareTo(currentUser.getBalance()) > 0) {
                        System.out.println("Insufficient balance!");
                    } else {
                        currentUser.setBalance(currentUser.getBalance().subtract(withdrawAmount));
                        System.out.println("You withdrew " + withdrawAmount.setScale(2, BigDecimal.ROUND_HALF_UP) + ". New balance: " + currentUser.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP));
                    }
                    break;

                case "3": // Apply for Loan
                    System.out.println("Enter loan amount:");
                    BigDecimal loanAmount = new BigDecimal(scanner.nextLine());

                    currentUser.setBalance(currentUser.getBalance().add(loanAmount));
                    BigDecimal payable = loanAmount.multiply(new BigDecimal("1.10"));
                    System.out.println("Loan approved! Amount " + loanAmount.setScale(2, BigDecimal.ROUND_HALF_UP) + " added. Your balance is: " + currentUser.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP));
                    System.out.println("You must repay: " + payable.setScale(2, BigDecimal.ROUND_HALF_UP));
                    break;

                case "4": // Make Purchase
                    System.out.println("Enter purchase amount:");
                    BigDecimal purchaseAmount = new BigDecimal(scanner.nextLine());

                    if (purchaseAmount.compareTo(BigDecimal.ZERO) < 0) {
                        System.out.println("Invalid amount!");
                    } else if (purchaseAmount.compareTo(currentUser.getBalance()) > 0) {
                        System.out.println("Not enough balance!");
                    } else {
                        currentUser.setBalance(currentUser.getBalance().subtract(purchaseAmount));
                        System.out.println("Purchase successful! Remaining balance: " + currentUser.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP));
                    }
                    break;

                case "5": // Check Balance
                    System.out.println("Your balance is: " + currentUser.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP));
                    break;

                case "6": // Exit
                    System.out.println("Goodbye " + currentUser.getFirstName() + " " + currentUser.getLastName() + "!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}
