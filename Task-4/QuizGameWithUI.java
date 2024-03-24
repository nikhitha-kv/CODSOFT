import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    private String question;
    private List<String> options;
    private int correctAnswer;

    public Question(String question, List<String> options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}

public class QuizGameWithUI {
    private List<Question> questions;
    private int currentQuestionIndex;
    private int score;
    private Timer timer;
    private JLabel timerLabel;
    private JFrame frame;

    public QuizGameWithUI(List<Question> questions) {
        this.questions = questions;
        this.currentQuestionIndex = 0;
        this.score = 0;
        this.timer = new Timer();
        this.timerLabel = new JLabel();
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Quiz Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        JLabel questionLabel = new JLabel();
        questionPanel.add(questionLabel);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < 4; i++) {
            JButton optionButton = new JButton();
            optionButton.addActionListener(e -> {
                int answer = Integer.parseInt(optionButton.getText().substring(0, 1));
                submitAnswer(answer);
            });
            optionsPanel.add(optionButton);
        }

        JPanel timerPanel = new JPanel();
        timerPanel.add(new JLabel("Time left: "));
        timerPanel.add(timerLabel);

        frame.add(questionPanel, BorderLayout.NORTH);
        frame.add(optionsPanel, BorderLayout.CENTER);
        frame.add(timerPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public void startQuiz() {
        displayQuestion();
    }

    private void displayQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            timerLabel.setText("10");
            frame.revalidate();
            frame.repaint();

            TimerTask task = new TimerTask() {
                int timeLeft = 10;

                @Override
                public void run() {
                    timeLeft--;
                    timerLabel.setText(Integer.toString(timeLeft));
                    if (timeLeft <= 0) {
                        timer.cancel();
                        SwingUtilities.invokeLater(() -> displayQuestion());
                    }
                }
            };

            timer = new Timer();
            timer.scheduleAtFixedRate(task, 1000, 1000);

            JLabel questionLabel = (JLabel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(0);
            questionLabel.setText("Question " + (currentQuestionIndex + 1) + ": " + currentQuestion.getQuestion());
            List<String> options = currentQuestion.getOptions();
            for (int i = 0; i < options.size(); i++) {
                JButton optionButton = (JButton) ((JPanel) frame.getContentPane().getComponent(1)).getComponent(i);
                optionButton.setText((i + 1) + ". " + options.get(i));
            }
        } else {
            endQuiz();
        }
    }

    public void submitAnswer(int answer) {
        timer.cancel();
        Question currentQuestion = questions.get(currentQuestionIndex);
        if (answer == currentQuestion.getCorrectAnswer()) {
            score++;
            JOptionPane.showMessageDialog(null, "Correct!");
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect!");
        }
        currentQuestionIndex++;
        displayQuestion();
    }

    private void endQuiz() {
        JOptionPane.showMessageDialog(null, "Quiz finished!\nYour score: " + score + "/" + questions.size());
        frame.dispose();
    }

    public static void main(String[] args) {
        List<Question> questions = new ArrayList<>();
       
        questions.add(new Question("What is the syntax to declare a variable in Java?",
                List.of("var x;", "String x;", "x;", "int x;"), 4));
        questions.add(new Question("Which of the following is not a primitive data type in Java?",
                List.of("int", "boolean", "float", "String"), 4));
        questions.add(new Question("What is the output of '2' + 2?",
                List.of("4", "2", "22", "Compilation error"), 3));

        QuizGameWithUI quizGame = new QuizGameWithUI(questions);
        quizGame.startQuiz();
    }
}
