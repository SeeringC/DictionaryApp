package UI;

import Manager.SceneManager;
import com.example.translatetest1.MyDictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
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
        SceneManager.getIns(SceneManager.class).openScene(event,"Translator.fxml");
    }

    @FXML
    public void switchToAddWord(ActionEvent event) throws IOException {
        SceneManager.getIns(SceneManager.class).openScene(event,"AddWord.fxml");
    }

    @FXML
    public void switchToGame(ActionEvent event) throws IOException {
        SceneManager.getIns(SceneManager.class).openScene(event,"Game.fxml");
    }

    @FXML
    public void switchFileTranslator(ActionEvent event) throws IOException {
        SceneManager.getIns(SceneManager.class).openScene(event,"FileTranslator.fxml");
    }

    @FXML
    public void switchImageTranslator(ActionEvent event) throws IOException {
        SceneManager.getIns(SceneManager.class).openScene(event,"ImageTranslator.fxml");
    }

    @FXML
    public void switchHistory(ActionEvent event) throws IOException {
        SceneManager.getIns(SceneManager.class).openScene(event,"History.fxml");
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
