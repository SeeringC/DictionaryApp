package UI;

import Manager.SceneManager;
import com.example.translatetest1.MyDictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.SQLException;

public class AddWord {
    @FXML
    private Pane currentPane;
    @FXML
    private Button bt1;
    @FXML
    private TextField addbox1;
    @FXML
    private TextField addbox2;
    @FXML
    public void switchToHelloApplication(ActionEvent event) throws IOException {
        SceneManager.getIns(SceneManager.class).openScene(currentPane, "HelloApplication.fxml");
    }

    public void Success(ActionEvent event) throws SQLException {
        MyDictionary.getIns(MyDictionary.class).addWordToDic(addbox1.getText(), addbox2.getText());
    }
}

