package Manager;

import Singleton.Singleton;
import UI.UILayer;
import com.example.translatetest1.DictionaryApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class UIManager extends Singleton<UIManager> {

    public <T extends UILayer> void openScene(Pane currentPane, String url) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(DictionaryApplication.class.getResource(url)));
        Pane nextPane = loader.load();
        T controller = loader.getController();
        controller.onInit();
        currentPane.getChildren().removeAll();
        currentPane.getChildren().setAll(nextPane);

    }
}