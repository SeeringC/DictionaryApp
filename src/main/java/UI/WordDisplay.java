package UI;

import Manager.AppDataManager;
import Manager.UIManager;
import com.example.translatetest1.MyDictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class WordDisplay implements UILayer {
    @FXML
    private Pane currentPane;
    @FXML
    private Label wordTarget;
    @FXML
    private TextField wordDefinition;

    @Override
    public void onInit() {
        displaySearchedWord();
    }

    @Override
    public void onClose() {

    }

    @FXML
    private void switchToMainMenu(ActionEvent event) throws IOException {
        UIManager.getIns(UIManager.class).openScene(currentPane, "HelloApplication.fxml");
        wordDefinition.setEditable(false);
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
    }

    @FXML
    private void addSetWordToBookmarks(ActionEvent event) throws IOException {
        //do it later.
    }

    public void displaySearchedWord() {
        wordTarget.setText(AppDataManager.getIns(AppDataManager.class).getCurrentEnViWordTarget());
        wordDefinition.setText(formatString(AppDataManager.getIns(AppDataManager.class).getCurrentEnViWordDefinition()));
    }

    private String formatString(String s) {
        return s.replaceAll("<br />", "\n")
                .replaceAll("</Q></I>", "")
                .replaceAll("<I><Q>", "");
    }
}
