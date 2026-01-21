import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();
        User loggedInUser = null;

        while (true) {
            System.out.println("\n=== Banking Information System ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    sc.nextLine();
                    String name = sc.nextLine();
                    System.out.print("Enter Password: ");
                    String password = sc.next();
                    System.out.print("Enter Initial Deposit: ");
                    double deposit = sc.nextDouble();

                    int accNo = bank.register(name, password, deposit);
                    System.out.println("Registration Successful! Your Account Number: " + accNo);
                    break;

                case 2:
                    System.out.print("Enter Account Number: ");
                    int loginAcc = sc.nextInt();
                    System.out.print("Enter Password: ");
                    String loginPass = sc.next();

                    loggedInUser = bank.login(loginAcc, loginPass);
                    if (loggedInUser != null) {
                        System.out.println("\nLogin Successful! Welcome " + loggedInUser.getName());
                        userMenu(sc, bank, loggedInUser);
                    } else {
                        System.out.println("Invalid Account or Password!");
                    }
                    break;

                case 3:
                    System.out.println("Thank you for using the Banking System!");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice! Try again.");
            }
        }
    }

    public static void userMenu(Scanner sc, Bank bank, User user) {
        int accountNumber = user.getAccountNumber();
        while (true) {
            System.out.println("\n--- User Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Account Statement");
            System.out.println("6. Logout");
            System.out.print("Enter choice: ");
            int option = sc.nextInt();

            switch (option) {
                case 1:
                    Account acc = bank.getAccount(accountNumber);
                    System.out.println("Current Balance: " + acc.getBalance());
                    break;

                case 2:
                    System.out.print("Enter Amount to Deposit: ");
                    double depAmount = sc.nextDouble();
                    bank.deposit(accountNumber, depAmount);
                    System.out.println("Amount Deposited Successfully!");
                    break;

                case 3:
                    System.out.print("Enter Amount to Withdraw: ");
                    double wAmount = sc.nextDouble();
                    if (bank.withdraw(accountNumber, wAmount)) {
                        System.out.println("Withdrawal Successful!");
                    } else {
                        System.out.println("Insufficient Balance!");
                    }
                    break;

                case 4:
                    System.out.print("Enter Receiver Account Number: ");
                    int recvAcc = sc.nextInt();
                    System.out.print("Enter Amount to Transfer: ");
                    double tAmount = sc.nextDouble();
                    if (bank.transfer(accountNumber, recvAcc, tAmount)) {
                        System.out.println("Transfer Successful!");
                    } else {
                        System.out.println("Transfer Failed! Check balance or account number.");
                    }
                    break;

                case 5:
                    bank.printStatement(accountNumber);
                    break;

                case 6:
                    System.out.println("Logged Out Successfully.");
                    return;

                default:
                    System.out.println("Invalid Option! Try Again.");
            }
        }
    }
}
