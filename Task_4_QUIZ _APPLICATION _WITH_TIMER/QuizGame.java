
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class QuizGame extends JFrame {
    private JLabel questionLabel;
    private JRadioButton[] options;
    private ButtonGroup optionGroup;
    private JButton submitButton;
    private int currentQuestionIndex;
    private int score;
    private int timeLeft;
    private Timer timer;
    private JPanel optionPanel;

    private String[] questions = {
        "Who is the number 1 batsman in cricket?",
        "Who is the number 1 bowler in cricket?",
        "Who is the number 1 fielder in cricket?"
    };

    private String[][] optionsArray = {
        {"Sachin Tendulkar", "Virat Kohli", "Rohit Sharma", "AB de Villiers"},
        {"Muttiah Muralitharan", "Shane Warne", "Anil Kumble", "Glenn McGrath"},
        {"Jonty Rhodes", "Herschelle Gibbs", "Ricky Ponting", "AB de Villiers"}
    };

    private int[] correctAnswers = {1, 0, 0};

    public QuizGame() {
        super("Quiz Game");
        setLayout(new BorderLayout());

        questionLabel = new JLabel("Question 1:");
        add(questionLabel, BorderLayout.NORTH);

        optionPanel = new JPanel();
        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
        add(optionPanel, BorderLayout.CENTER);

        optionGroup = new ButtonGroup();
        options = new JRadioButton[4];
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            optionGroup.add(options[i]);
            optionPanel.add(options[i]);
        }

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitButtonListener());
        add(submitButton, BorderLayout.SOUTH);

        currentQuestionIndex = 0;
        score = 0;
        timeLeft = 30;
        timer = new Timer();

        startNextQuestion();
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void startNextQuestion() {
        questionLabel.setText(questions[currentQuestionIndex]);
        for (int i = 0; i < 4; i++) {
            options[i].setText(optionsArray[currentQuestionIndex][i]);
            options[i].setSelected(false);
        }

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeLeft--;
                if (timeLeft <= 0) {
                    timer.cancel();
                    showNextQuestion();
                }
            }
        }, 1000, 1000);
    }

    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            timer.cancel();
            int selectedOption = getSelectedOption();
            if (selectedOption == correctAnswers[currentQuestionIndex]) {
                score++;
            }
            showNextQuestion();
        }
    }

    private int getSelectedOption() {
        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected()) {
                return i;
            }
        }
        return -1;
    }

    private void showNextQuestion() {
        currentQuestionIndex++;
        if (currentQuestionIndex < questions.length) {
            timeLeft = 30;
            startNextQuestion();
        } else {
            showResults();
        }
    }

    private void showResults() {
        questionLabel.setText("Results:");
        String resultText = "Score: " + score + " out of " + questions.length + "\n";
        for (int i = 0; i < questions.length; i++) {
            resultText += "Question " + (i + 1) + ": " + (correctAnswers[i] == getSelectedOption()? "Correct" : "Incorrect") + "\n";
        }
        JOptionPane.showMessageDialog(this, resultText);
        System.exit(0);
    }

    public static void main(String[] args) {
        new QuizGame();
    }
}