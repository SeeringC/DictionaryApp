package UI;

import Manager.AppDataManager;
import Manager.UIManager;
import com.example.translatetest1.MyDictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class HelloApplication implements Initializable, UILayer {

    private ArrayList<String> suggestions = null;
    @FXML
    private TextField searchWord;
    @FXML
    private Pane currentPane;
    @Override
    public void onInit() {

    }

    @Override
    public void onClose() {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // copy all the word target from the dictionary to suggestions
        suggestions = lookUpWord();
        // suggest the words target in the lexicographical order
        TextFields.bindAutoCompletion(searchWord, param -> {
            String userInput = param.getUserText();
            if (userInput.isEmpty()) {
                return suggestions;
            } else {
                return suggestions.stream()
                        .filter(s -> s.toLowerCase().startsWith(userInput.toLowerCase()))
                        .sorted()
                        .collect(Collectors.toList());
            }
        });
    }
    @FXML
    public void switchToTranslator(ActionEvent event) throws IOException {
        UIManager.getIns(UIManager.class).openScene(currentPane, "Translator.fxml");
    }

    @FXML
    public void switchToAddWord(ActionEvent event) throws IOException {
        UIManager.getIns(UIManager.class).openScene(currentPane, "AddWord.fxml");
    }

    @FXML
    public void switchToGame(ActionEvent event) throws IOException {
        UIManager.getIns(UIManager.class).openScene(currentPane, "Game.fxml");
    }

    @FXML
    public void switchToFileTranslator(ActionEvent event) throws IOException {
        UIManager.getIns(UIManager.class).openScene(currentPane, "FileTranslator.fxml");
    }

    @FXML
    public void switchToBookmarks(ActionEvent event) throws IOException {
        UIManager.getIns(UIManager.class).openScene(currentPane, "Bookmarks.fxml");
    }

    @FXML
    public void switchToHistory(ActionEvent event) throws IOException {
        UIManager.getIns(UIManager.class).openScene(currentPane, "History.fxml");
    }

    @FXML
    private void searchWord(ActionEvent event) throws IOException {
        AppDataManager.getIns(AppDataManager.class).setCurrentEnViWordByTargetAndDefinition(
                searchWord.getText(),
                MyDictionary.getIns(MyDictionary.class).lookUpWordInDic(searchWord.getText()));
        UIManager.getIns(UIManager.class).openScene(currentPane, "WordDisplay.fxml");
    }
//    @FXML
//    public void showNewButton(ActionEvent event) throws IOException {
//        //css test area
//        Button btn = new Button("Get your copy now_");
//        btn.getStyleClass().add("btn");
//        Label btnContent = new Label("Get your copy now_");
//        btnContent.getStyleClass().add("btn__content");
//
//        Label btnGlitch = new Label();
//        btnGlitch.getStyleClass().add("btn__glitch");
//
//        Label btnLabel = new Label("r25");
//        btnLabel.getStyleClass().add("btn__label");
//        VBox vbox = new VBox(btnContent, btnGlitch, btnLabel);
//        btn.setGraphic(vbox);
//    }

    public ArrayList<String> lookUpWord() {
        ArrayList<String> tu = new ArrayList<>();
        Enumeration<String> keys = MyDictionary.getIns(MyDictionary.class).enToViDic.keys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            tu.add(key);
        }
        return tu;
    }

}
