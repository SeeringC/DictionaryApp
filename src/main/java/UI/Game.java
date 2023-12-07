package UI;

import Manager.UIManager;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Game implements UILayer {

    @FXML
    private Pane currentPane;
    @FXML
    private ImageView wordleGameImage;
    @FXML
    private Pane wordleGameDescription;
    @FXML
    private ImageView multipleChoiceGameImage;
    @FXML
    private Pane multipleChoiceGameDescription;
    @Override
    public void onInit() {
        loadUILogic();
    }

    @Override
    public void onClose() {

    }

    @FXML
    private void switchScene(Event event, String fxmlFile) throws IOException {
        UIManager.getIns(UIManager.class).openScene(currentPane, fxmlFile);
    }
    @FXML
    private void switchToWordleGame(MouseEvent event) throws IOException {
        switchScene(event, "Games/WordleGame.fxml");
    }

    @FXML
    private void switchToMCQsGame(MouseEvent event) throws IOException {
        switchScene(event, "Games/MCQsGame.fxml");
    }

    @FXML
    private void backToMainMenu(KeyEvent event) throws IOException {
        System.out.println("key press");
        if (event.getCode() == KeyCode.ESCAPE) {
            UIManager.getIns(UIManager.class).openScene(currentPane, "MainMenu.fxml");
        }
    }

    private void loadUILogic() {
        currentPane.setFocusTraversable(true);

        wordleGameImage.setOnMouseEntered(e -> wordleGameDescription.setVisible(true));
        wordleGameImage.setOnMouseExited(e -> wordleGameDescription.setVisible(false));

        multipleChoiceGameImage.setOnMouseEntered(e -> multipleChoiceGameDescription.setVisible(true));
        multipleChoiceGameImage.setOnMouseExited(e -> multipleChoiceGameDescription.setVisible(false));


        wordleGameDescription.setVisible(false);
        multipleChoiceGameDescription.setVisible(false);
    }
}
