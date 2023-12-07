package UI;

import Manager.UIManager;
import com.example.translatetest1.MyDictionary;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.SQLException;

public class AddWord implements UILayer {
    @FXML
    private Pane currentPane;
    @FXML
    private TextField wordTarget;
    @FXML
    private JFXTextArea wordDefinition;

    @Override
    public void onInit() {

    }

    @Override
    public void onClose() {

    }

    public void saveWord(ActionEvent event) throws SQLException {
        MyDictionary.getIns(MyDictionary.class).addWordToDic(wordTarget.getText(), wordDefinition.getText());
    }

    @FXML
    private void backToMainMenu(KeyEvent event) throws IOException {
        System.out.println("key press");
        if (event.getCode() == KeyCode.ESCAPE) {
            UIManager.getIns(UIManager.class).openScene(currentPane, "MainMenu.fxml");
        }
    }

}

