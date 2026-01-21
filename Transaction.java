import java.time.LocalDateTime;

public class Transaction {
    private int accountNumber;
    private String type;
    private double amount;
    private LocalDateTime date;

    public Transaction(int accountNumber, String type, double amount) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.date = LocalDateTime.now();
    }

    public String toString() {
        return date + " | " + type + " | Amount: " + amount;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}
