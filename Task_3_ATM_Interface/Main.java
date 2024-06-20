import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Class to represent the user's bank account
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
}

// Class to represent the ATM machine
class ATM extends JFrame {
    private BankAccount account;
    private JTextField amountField;
    private JLabel balanceLabel;

    public ATM(BankAccount account) {
        super("ATM");
        this.account = account;
        setLayout(new FlowLayout());

        JLabel welcomeLabel = new JLabel("Welcome to the ATM!");
        add(welcomeLabel);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(new WithdrawButtonListener());
        add(withdrawButton);

        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(new DepositButtonListener());
        add(depositButton);

        JButton checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.addActionListener(new CheckBalanceButtonListener());
        add(checkBalanceButton);

        amountField = new JTextField(10);
        add(amountField);

        balanceLabel = new JLabel("Balance: $" + String.format("%.2f", account.getBalance()));
        add(balanceLabel);

        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private class WithdrawButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            double amount = Double.parseDouble(amountField.getText());
            if (account.withdraw(amount)) {
                balanceLabel.setText("Balance: $" + String.format("%.2f", account.getBalance()));
                JOptionPane.showMessageDialog(ATM.this, "Withdrawal successful!");
            } else {
                JOptionPane.showMessageDialog(ATM.this, "Insufficient balance!");
            }
        }
    }

    private class DepositButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            double amount = Double.parseDouble(amountField.getText());
            account.deposit(amount);
            balanceLabel.setText("Balance: $" + String.format("%.2f", account.getBalance()));
            JOptionPane.showMessageDialog(ATM.this, "Deposit successful!");
        }
    }

    private class CheckBalanceButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            balanceLabel.setText("Balance: $" + String.format("%.2f", account.getBalance()));
        }
    }
}

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000.0);
        ATM atm = new ATM(account);
    }
}

//create a java programe with the use of swing awt.