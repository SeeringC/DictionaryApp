package UI;

import Manager.UIManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Game implements UILayer {

    @FXML
    private Pane currentPane;
    @Override
    public void onInit() {

    }

    @Override
    public void onCLose() {

    }

    @FXML
    private void switchToMainMenu(ActionEvent event) throws IOException {
        UIManager.getIns(UIManager.class).openScene(currentPane, "HelloApplication.fxml");
    }

    @FXML
    private void switchToWordleGame(ActionEvent event) throws IOException {
        UIManager.getIns(UIManager.class).openScene(currentPane, "Games/WordleGame.fxml");
    }
}
