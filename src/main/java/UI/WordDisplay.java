package UI;

import Manager.UIManager;
import com.example.translatetest1.Cache;
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
    public void onCLose() {

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
        wordTarget.setText(Cache.getCurrentEnViWordTarget());
        wordDefinition.setText(handleString(Cache.getCurrentEnViWordDefinition()));
    }

    public String handleString(String s) {
        String xuongdong = "<br />";
        String delete_first = "<I><Q>";
        String delete_behind = "</Q></I>";
        String endl = "" + '\n';
        s = s.replaceAll(xuongdong, endl);
        s = s.replaceAll(delete_behind, "");
        s = s.replaceAll(delete_first, "");
        return s;
    }

}
