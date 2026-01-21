public class User {
    private String name;
    private String password;
    private int accountNumber;

    public User(String name, String password, int accountNumber) {
        this.name = name;
        this.password = password;
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}
