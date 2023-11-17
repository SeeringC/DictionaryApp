package UI;

import Manager.SceneManager;
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

public class HelloApplication implements Initializable {

    MyDictionary myDic = MyDictionary.getIns(MyDictionary.class);

    private ArrayList<String> suggestions = null;
    @FXML
    private TextField searchWord;
    @FXML
    private Pane currentPane;
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
        SceneManager.getIns(SceneManager.class).openScene(currentPane, "Translator.fxml");
    }

    @FXML
    public void switchToAddWord(ActionEvent event) throws IOException {
        SceneManager.getIns(SceneManager.class).openScene(currentPane, "AddWord.fxml");
    }

    @FXML
    public void switchToGame(ActionEvent event) throws IOException {
        SceneManager.getIns(SceneManager.class).openScene(currentPane, "Game.fxml");
    }

    @FXML
    public void switchToFileTranslator(ActionEvent event) throws IOException {
        SceneManager.getIns(SceneManager.class).openScene(currentPane, "FileTranslator.fxml");
    }

    @FXML
    public void switchToBookmarks(ActionEvent event) throws IOException {
        SceneManager.getIns(SceneManager.class).openScene(currentPane, "Bookmarks.fxml");
    }

    @FXML
    public void switchToHistory(ActionEvent event) throws IOException {
        SceneManager.getIns(SceneManager.class).openScene(currentPane, "History.fxml");
    }

    public ArrayList<String> lookUpWord() {
        ArrayList<String> tu = new ArrayList<>();
        Enumeration<String> keys = myDic.dic.keys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            tu.add(key);
        }
        return tu;
    }

}
