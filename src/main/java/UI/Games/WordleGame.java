package UI.Games;

import Manager.UIManager;
import UI.UILayer;
import com.example.translatetest1.MyDictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.*;

public class WordleGame implements UILayer {

    private final int WORD_LENGTH = 5;
    private final int GUESS_TURN = 6;
//    private char[] answerWord = new char[WORD_LENGTH];
    private List<String> answerWord = new ArrayList<>();
    private List<String> tempAnswerWord = new ArrayList<>();
    private TextField[][] textFieldsArr = new TextField[6][5];
    private ArrayList<String> wordsList = new ArrayList<String>();

    @FXML
    private Pane currentPane;
    @FXML
    private GridPane wordleGridPane;

    @Override
    public void onInit() {
        loadGame();
        loadWords();
        assignRandomWordToAnswerWord();
    }

    @Override
    public void onCLose() {

    }

    @FXML
    public void switchToGame(ActionEvent event) throws IOException {
        UIManager.getIns(UIManager.class).openScene(currentPane, "Game.fxml");
    }

    public void loadGame() {
        for (int row = 0; row < GUESS_TURN; row++) {
            for (int col = 0; col < WORD_LENGTH; col++) {
                TextField textField = new TextField();
                // Limit input to a single character
                textField.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue.length() > 1) {
                        textField.setText(newValue.substring(0, 1));
                    }
                });
                textFieldsArr[row][col] = textField;  // Store the TextField in the array
                wordleGridPane.add(textField, col, row);
            }
        }
        moveToNextTextField();
    }


    public void moveToNextTextField() {
        for (int row = 0; row < GUESS_TURN; row++) {
            for (int col = 0; col < WORD_LENGTH; col++) {
                int finalCol = col;
                int finalRow = row;
                textFieldsArr[row][col].setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.BACK_SPACE && finalCol != 0 && textFieldsArr[finalRow][finalCol].getText().isEmpty()) {
                        textFieldsArr[finalRow][finalCol - 1].requestFocus();
                    }
                });
                textFieldsArr[row][col].textProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue.length() >= 1 && oldValue.length() < 1) {
                        if (finalCol != WORD_LENGTH - 1) {
                            textFieldsArr[finalRow][finalCol + 1].requestFocus();
                        } else if (finalRow != GUESS_TURN - 1) {
                            textFieldsArr[finalRow + 1][0].requestFocus();
                        }
                        if (finalCol == WORD_LENGTH - 1) {
                            showResult(finalRow);
                        }
                    }
                });
            }
        }
    }


    public void showResult(int row) {
        int correctCharGuessed = 0;
        for (int col = 0; col < WORD_LENGTH; col++) {
//            System.out.println("text here is: " + String.valueOf(textFieldsArr[row][col].getText().charAt(0)));
//            System.out.println("Temp answer word: " + tempAnswerWord.contains(textFieldsArr[row][col].getText()));
            System.out.println("the text here is: " + String.valueOf(textFieldsArr[row][col].getText()));
            System.out.println("the answer here is: " + String.valueOf(answerWord.get(col)));
            if (Objects.equals(textFieldsArr[row][col].getText(), answerWord.get(col))) {
                textFieldsArr[row][col].setStyle("-fx-background-color: green;");
                tempAnswerWord.remove(textFieldsArr[row][col].getText());
                correctCharGuessed++;
            } else if (answerWord.contains(textFieldsArr[row][col].getText())) {
                textFieldsArr[row][col].setStyle("-fx-background-color: yellow;");
                tempAnswerWord.remove((textFieldsArr[row][col].getText()));
            } else {
                textFieldsArr[row][col].setStyle("-fx-background-color: grey;");
                tempAnswerWord.remove((textFieldsArr[row][col].getText()));
            }
//            System.out.println("Temp answer word: " + tempAnswerWord.get(col));
        }

        if (correctCharGuessed == WORD_LENGTH) {
            String answerString = "";
            for (int i = 0; i < answerWord.size(); i++) {
                answerString += answerWord.get(i);
            }
            UIManager.getIns(UIManager.class).displayWordleGameAlert("Win", answerString.toString());
        }
        if (row == GUESS_TURN - 1) {
            String answerString = "";
            for (int i = 0; i < answerWord.size(); i++) {
                answerString += answerWord.get(i);
            }
            UIManager.getIns(UIManager.class).displayWordleGameAlert("Lose", answerString.toString());
        }
        tempAnswerWord.addAll(answerWord);
    }

    private void loadWords() {
        Enumeration<String> keys = MyDictionary.getIns(MyDictionary.class).enToViDic.keys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            if(key.length() == WORD_LENGTH) {
                wordsList.add(key);
            }
        }
    }

    private void assignRandomWordToAnswerWord() {
        Random randomWord = new Random();
        int index = randomWord.nextInt(wordsList.size());

        String randomedWord = wordsList.get(index);
        System.out.println("The word is: " + randomedWord);

        for (int i = 0; i < WORD_LENGTH; i++) {
            answerWord.add(String.valueOf(randomedWord.charAt(i)));
        }
        tempAnswerWord.addAll(answerWord);

    }

}
