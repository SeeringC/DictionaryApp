package UI;

import Manager.DataFlowManager;
import Manager.UIManager;
import com.example.translatetest1.MyDictionary;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
public class MainMenu implements Initializable, UILayer {

    @FXML
    private TextField searchWord;
    @FXML
    private Pane currentPane;

    private ArrayList<String> suggestions;

    @Override
    public void onInit() {
        // Initialization logic if needed
    }

    @Override
    public void onClose() {
        // Cleanup logic if needed
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        suggestions = lookUpWord();
        bindAutoCompletionToSearchWord();
    }

    private void bindAutoCompletionToSearchWord() {
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
    private void switchScene(Event event, String fxmlFile) throws IOException {
        UIManager.getIns(UIManager.class).openScene(currentPane, fxmlFile);
    }

    @FXML
    public void switchToTranslator(ActionEvent event) throws IOException {
        switchScene(event, "Translator.fxml");
    }

    @FXML
    public void switchToAddWord(ActionEvent event) throws IOException {
        switchScene(event, "AddWord.fxml");
    }

    @FXML
    public void switchToGame(ActionEvent event) throws IOException {
        switchScene(event, "Game.fxml");
    }

    @FXML
    public void switchToFileTranslator(ActionEvent event) throws IOException {
        switchScene(event, "FileTranslator.fxml");
    }

    @FXML
    private void searchWord(ActionEvent event) throws IOException {
        DataFlowManager.getIns(DataFlowManager.class).setCurrentEnViWordByTargetAndDefinition(
                searchWord.getText(),
                MyDictionary.getIns(MyDictionary.class).lookUpWordInDic(searchWord.getText()));
        switchScene(event, "WordDisplay.fxml");
    }

    private ArrayList<String> lookUpWord() {
        ArrayList<String> words = new ArrayList<>();
        Enumeration<String> keys = MyDictionary.getIns(MyDictionary.class).enToViDic.keys();
        while (keys.hasMoreElements()) {
            words.add(keys.nextElement());
        }
        return words;
    }

    /////////////////////// UI LOGIC AREA ///////////////////////////////
    @FXML
    private AnchorPane playGamesDescription;
    @FXML
    private AnchorPane googleTranslateDescription;
    @FXML
    private AnchorPane addWordDescription;
    @FXML
    private AnchorPane fileTranslatorDescription;
    @FXML
    private Button playGamesButton;
    @FXML
    private Button googleTranslateButton;
    @FXML
    private Button addWordButton;
    @FXML
    private Button fileTranslatorButton;
    private void loadUILogic() {
        playGamesButton.setOnMouseEntered(e -> playGamesDescription.setVisible(true));
        playGamesButton.setOnMouseExited(e -> playGamesDescription.setVisible(false));

        googleTranslateButton.setOnMouseEntered(e -> googleTranslateDescription.setVisible(true));
        googleTranslateButton.setOnMouseExited(e -> googleTranslateDescription.setVisible(false));

        addWordButton.setOnMouseEntered(e -> addWordDescription.setVisible(true));
        addWordButton.setOnMouseExited(e -> addWordDescription.setVisible(false));

        fileTranslatorButton.setOnMouseEntered(e -> fileTranslatorDescription.setVisible(true));
        fileTranslatorButton.setOnMouseExited(e -> fileTranslatorDescription.setVisible(false));

        playGamesDescription.setVisible(false);
        googleTranslateDescription.setVisible(false);
        addWordDescription.setVisible(false);
        fileTranslatorDescription.setVisible(false);
    }

}
