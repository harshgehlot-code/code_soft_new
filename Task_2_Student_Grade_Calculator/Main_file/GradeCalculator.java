import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradeCalculator extends JFrame {
    private JLabel subject1Label;
    private JLabel subject2Label;
    private JLabel subject3Label;
    private JLabel subject4Label;
    private JLabel subject5Label;
    private JTextField subject1Field;
    private JTextField subject2Field;
    private JTextField subject3Field;
    private JTextField subject4Field;
    private JTextField subject5Field;
    private JButton calculateButton;
    private JLabel resultLabel;

    public GradeCalculator() {
        super("Grade Calculator");
        setLayout(new GridLayout(7, 2));

        subject1Label = new JLabel("Subject 1 (out of 100):");
        add(subject1Label);
        subject1Field = new JTextField(10);
        add(subject1Field);

        subject2Label = new JLabel("Subject 2 (out of 100):");
        add(subject2Label);
        subject2Field = new JTextField(10);
        add(subject2Field);

        subject3Label = new JLabel("Subject 3 (out of 100):");
        add(subject3Label);
        subject3Field = new JTextField(10);
        add(subject3Field);

        subject4Label = new JLabel("Subject 4 (out of 100):");
        add(subject4Label);
        subject4Field = new JTextField(10);
        add(subject4Field);

        subject5Label = new JLabel("Subject 5 (out of 100):");
        add(subject5Label);
        subject5Field = new JTextField(10);
        add(subject5Field);

        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new CalculateButtonListener());
        add(calculateButton);

        resultLabel = new JLabel("");
        add(resultLabel);

        setSize(300, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int subject1 = Integer.parseInt(subject1Field.getText());
            int subject2 = Integer.parseInt(subject2Field.getText());
            int subject3 = Integer.parseInt(subject3Field.getText());
            int subject4 = Integer.parseInt(subject4Field.getText());
            int subject5 = Integer.parseInt(subject5Field.getText());

            int totalMarks = subject1 + subject2 + subject3 + subject4 + subject5;
            double averagePercentage = (double) totalMarks / 5;

            String grade;
            if (averagePercentage >= 90) {
                grade = "A";
            } else if (averagePercentage >= 80) {
                grade = "B";
            } else if (averagePercentage >= 70) {
                grade = "C";
            } else if (averagePercentage >= 60) {
                grade = "D";
            } else {
                grade = "F";
            }

            resultLabel.setText("Total Marks: " + totalMarks + "\nAverage Percentage: "+ String.format("\n%.2f", averagePercentage) + "%\nGrade: " + grade);
        }
    }

    public static void main(String[] args) {
        new GradeCalculator();
    }
}