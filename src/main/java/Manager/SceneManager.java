package Manager;

import Singleton.Singleton;
import com.example.translatetest1.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class SceneManager extends Singleton<SceneManager> {



    public void openScene(Pane currentPane, String url) throws IOException {
        Pane nextAnchorPane = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(url)));
        currentPane.getChildren().removeAll();
        currentPane.getChildren().setAll(nextAnchorPane);
    }
}