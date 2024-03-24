import javax.swing.JOptionPane;

// Bank Account class
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
}

// ATM class
public class ATMWithUI {
    private BankAccount account;

    public ATMWithUI(BankAccount account) {
        this.account = account;
    }

    public void displayOptions() {
        String options = "Welcome to the ATM.\n"
                + "1. Withdraw\n"
                + "2. Deposit\n"
                + "3. Check Balance\n"
                + "4. Exit";

        JOptionPane.showMessageDialog(null, options);
    }

    public void withdraw(double amount) {
        if (account.withdraw(amount)) {
            JOptionPane.showMessageDialog(null, "Withdrawal successful. Remaining balance: " + account.getBalance());
        } else {
            JOptionPane.showMessageDialog(null, "Insufficient balance.");
        }
    }

    public void deposit(double amount) {
        account.deposit(amount);
        JOptionPane.showMessageDialog(null, "Deposit successful. New balance: " + account.getBalance());
    }

    public void checkBalance() {
        JOptionPane.showMessageDialog(null, "Current balance: " + account.getBalance());
    }

    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(1000); // Initial balance set to 1000
        ATMWithUI atm = new ATMWithUI(bankAccount);

        boolean exit = false;

        while (!exit) {
            atm.displayOptions();
            String choice = JOptionPane.showInputDialog("Enter your choice:");

            switch (choice) {
                case "1":
                    String withdrawInput = JOptionPane.showInputDialog("Enter amount to withdraw:");
                    double withdrawAmount = Double.parseDouble(withdrawInput);
                    atm.withdraw(withdrawAmount);
                    break;
                case "2":
                    String depositInput = JOptionPane.showInputDialog("Enter amount to deposit:");
                    double depositAmount = Double.parseDouble(depositInput);
                    atm.deposit(depositAmount);
                    break;
                case "3":
                    atm.checkBalance();
                    break;
                case "4":
                    exit = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
            }
        }

        JOptionPane.showMessageDialog(null, "Thank you for using the ATM.");
    }
}
