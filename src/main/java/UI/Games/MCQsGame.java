package UI.Games;

import Manager.UIManager;
import UI.Games.MCQsGameData.Question;
import UI.Games.MCQsGameData.QuestionBank;
import UI.UILayer;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class MCQsGame implements UILayer {

    @FXML
    private Pane currentPane;
    @FXML
    private JFXTextArea questionTextArea;
    @FXML
    private RadioButton answer1, answer2, answer3, answer4;
    @FXML
    private Label feedbackLabel;
    @FXML
    private JFXTextArea answerDisplay;
    private ToggleGroup answersGroup;
    private Question currentQuestion;
    private List<Question> questions;
    private Random random = new Random();

    @Override
    public void onInit() {
        initializeComponents();
        loadNextQuestion();
    }

    @Override
    public void onClose() {
        // Add any necessary cleanup code here
    }

    private void initializeComponents() {
        loadQuestionsData();
        setupAnswerToggleGroup();
    }

    private void loadQuestionsData() {
        questions = QuestionBank.getQuestions();
    }

    private void setupAnswerToggleGroup() {
        answersGroup = new ToggleGroup();
        answer1.setToggleGroup(answersGroup);
        answer2.setToggleGroup(answersGroup);
        answer3.setToggleGroup(answersGroup);
        answer4.setToggleGroup(answersGroup);
    }

    @FXML
    private void handleAnswerSelection() {
        RadioButton selectedAnswer = (RadioButton) answersGroup.getSelectedToggle();
        String answerText = selectedAnswer.getText();
        boolean isCorrect = answerText.equals(currentQuestion.getCorrectAnswer());
        displayFeedback(isCorrect, answerText);
        disableUserAnswerSelection();
    }

    @FXML
    private void loadNextQuestion() {
        resetUIForNextQuestion();
        currentQuestion = getRandomQuestion();
        displayQuestionAndAnswers(currentQuestion);
        enableUserAnswerSelection();
    }

    private Question getRandomQuestion() {
        int index = random.nextInt(questions.size());
        return questions.get(index);
    }

    private void displayQuestionAndAnswers(Question question) {
        questionTextArea.setText(question.getQuestion());
        List<String> answers = question.getAnswerList();
        answer1.setText(answers.get(0));
        answer2.setText(answers.get(1));
        answer3.setText(answers.get(2));
        answer4.setText(answers.get(3));
    }

    private void disableUserAnswerSelection() {
        answer1.setDisable(true);
        answer2.setDisable(true);
        answer3.setDisable(true);
        answer4.setDisable(true);
    }

    private void enableUserAnswerSelection() {
        answer1.setDisable(false);
        answer2.setDisable(false);
        answer3.setDisable(false);
        answer4.setDisable(false);
    }
    private void resetUIForNextQuestion() {
        answersGroup.selectToggle(null);
        feedbackLabel.setVisible(false);
        answerDisplay.setVisible(false);
    }

    private void displayFeedback(boolean isCorrect, String answer) {
        feedbackLabel.setText(isCorrect ? "CORRECT!" : "INCORRECT!");
        feedbackLabel.setTextFill(isCorrect ? Color.GREEN : Color.RED);
        feedbackLabel.setVisible(true);
        answerDisplay.setText("The answer is:\n " + answer);
        answerDisplay.setVisible(true);
    }

    @FXML
    private void backToGames (KeyEvent event) throws IOException {
        System.out.println("key press");
        if (event.getCode() == KeyCode.ESCAPE) {
            UIManager.getIns(UIManager.class).openScene(currentPane, "UI.Game.fxml");
        }
    }
}
