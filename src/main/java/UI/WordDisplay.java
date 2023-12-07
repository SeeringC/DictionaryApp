package UI;

import Manager.DataFlowManager;
import Manager.SoundManager;
import Manager.UIManager;
import com.example.translatetest1.MyDictionary;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class WordDisplay implements UILayer {
    @FXML
    private Pane currentPane;
    @FXML
    private Label wordTarget;
    @FXML
    private JFXTextArea wordDefinition;

    @Override
    public void onInit() {
        displaySearchedWord();
    }

    @Override
    public void onClose() {

    }

    @FXML
    private void editWordDefinition(ActionEvent event) throws IOException {
        wordDefinition.setEditable(true);
    }

    @FXML
    private void saveWordToDic(ActionEvent event) throws IOException {
        MyDictionary.getIns(MyDictionary.class).changeWordDefinition(wordTarget.getText(), wordDefinition.getText());
        wordDefinition.setEditable(false);
    }

    @FXML
    private void deleteWordFromDic(ActionEvent event) throws IOException {
        MyDictionary.getIns(MyDictionary.class).deleteWordInDic(wordTarget.getText());
        UIManager.getIns(UIManager.class).openScene(currentPane, "MainMenu.fxml");
    }

    public void displaySearchedWord() {
        wordTarget.setText(DataFlowManager.getIns(DataFlowManager.class).getCurrentEnViWordTarget());
        wordDefinition.setText(formatString(DataFlowManager.getIns(DataFlowManager.class).getCurrentEnViWordDefinition()));
    }

    @FXML
    private void speakInputWord(ActionEvent event) {
        SoundManager.getIns(SoundManager.class).speakWord(wordTarget.getText());
    }

    private String formatString(String s) {
        return s.replaceAll("<br />", "\n")
                .replaceAll("</Q></I>", "")
                .replaceAll("<I><Q>", "");
    }

    @FXML
    private void backToMainMenu(KeyEvent event) throws IOException {
        System.out.println("key press");
        if (event.getCode() == KeyCode.ESCAPE) {
            UIManager.getIns(UIManager.class).openScene(currentPane, "MainMenu.fxml");
        }
    }
}
