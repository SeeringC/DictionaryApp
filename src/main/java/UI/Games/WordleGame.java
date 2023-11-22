package UI.Games;

import Manager.UIManager;
import UI.UILayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class WordleGame implements UILayer {

    @FXML
    private Pane currentPane;
    @FXML
    private TextField textField1;
    @FXML
    private TextField textField2;
    @Override
    public void onInit() {
        moveToNextTextField();
    }

    @Override
    public void onCLose() {

    }

    @FXML
    public void moveToNextTextField() {
        textField1.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() >= 1) {
                textField2.requestFocus();
            }
        });
    }

    @FXML
    public void switchToGame(ActionEvent event) throws IOException {
        UIManager.getIns(UIManager.class).openScene(currentPane, "Game.fxml");
    }
}
