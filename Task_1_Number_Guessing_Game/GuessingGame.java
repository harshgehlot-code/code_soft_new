import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessingGame extends JFrame {
    private JLabel label;
    private JTextField guessField;
    private JButton submitButton;
    private int secretNumber;
    private int attempts;
    private int maxAttempts;
    private int score;
    private int rounds;

    public GuessingGame() {
        super("Guessing Game");
        setLayout(new FlowLayout());

        label = new JLabel("Enter your guess (1-100):");
        add(label);

        guessField = new JTextField(10);
        add(guessField);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitButtonListener());
        add(submitButton);

        maxAttempts = 6;
        score = 0;
        rounds = 0;

        newRound();

        setSize(300, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void newRound() {
        rounds++;
        attempts = 0;
        secretNumber = new Random().nextInt(100) + 1;
        label.setText("Enter your guess (1-100):");
    }

    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int guess = Integer.parseInt(guessField.getText());
            attempts++;

            if (guess == secretNumber) {
                label.setText(" Congratulations! You guessed the correct number!");
                score++;
                guessField.setText("");
                playAgain();
            } else if (guess > secretNumber) {
                label.setText("Your guess is too high!");
            } else {
                label.setText("Your guess is too low!");
            }

            if (attempts == maxAttempts) {
                label.setText("Sorry, you didn't guess the number. It was " + secretNumber + ".");
                playAgain();
            }
        }
    }

    private void playAgain() {
        int response = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Play Again?", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            newRound();
        } else {
            JOptionPane.showMessageDialog(this, "Your final score is " + score + " out of " + rounds + " rounds.");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new GuessingGame();
    }
}