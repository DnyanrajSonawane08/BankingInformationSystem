import java.util.ArrayList;

public class Bank {
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Account> accounts = new ArrayList<>();
    private ArrayList<Transaction> transactions = new ArrayList<>();
    private int nextAccountNumber = 1001;

    public int register(String name, String password, double initialDeposit) {
        int accountNumber = nextAccountNumber++;
        users.add(new User(name, password, accountNumber));
        accounts.add(new Account(accountNumber, initialDeposit));
        transactions.add(new Transaction(accountNumber, "OPEN ACCOUNT", initialDeposit));
        return accountNumber;
    }

    public User login(int accountNumber, String password) {
        for (User u : users) {
            if (u.getAccountNumber() == accountNumber && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    public Account getAccount(int accountNumber) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accountNumber) return acc;
        }
        return null;
    }

    public void deposit(int accountNumber, double amount) {
        Account acc = getAccount(accountNumber);
        if (acc != null) {
            acc.deposit(amount);
            transactions.add(new Transaction(accountNumber, "DEPOSIT", amount));
        }
    }

    public boolean withdraw(int accountNumber, double amount) {
        Account acc = getAccount(accountNumber);
        if (acc != null && acc.withdraw(amount)) {
            transactions.add(new Transaction(accountNumber, "WITHDRAW", amount));
            return true;
        }
        return false;
    }

    public boolean transfer(int fromAcc, int toAcc, double amount) {
        Account acc1 = getAccount(fromAcc);
        Account acc2 = getAccount(toAcc);

        if (acc1 != null && acc2 != null && acc1.withdraw(amount)) {
            acc2.deposit(amount);
            transactions.add(new Transaction(fromAcc, "TRANSFER OUT", amount));
            transactions.add(new Transaction(toAcc, "TRANSFER IN", amount));
            return true;
        }
        return false;
    }

    public void printStatement(int accountNumber) {
        System.out.println("\n--- Account Statement for: " + accountNumber + " ---");
        for (Transaction t : transactions) {
            if (t.getAccountNumber() == accountNumber) {
                System.out.println(t);
            }
        }
    }
}
