package Manager;

import Singleton.Singleton;
import UI.UILayer;
import com.example.translatetest1.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class UIManager extends Singleton<UIManager> {

    public <T extends UILayer> void openScene(Pane currentPane, String url) throws IOException {
//        URL location = getClass().getResource(url);
//        FXMLLoader fxmlLoader = new FXMLLoader(location);
//        fxmlLoader.load();
//        ModuleLayer.Controller c = (ModuleLayer.Controller) fxmlLoader.getController();
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(Main.class.getResource(url)));
        Pane nextPane = loader.load();
        T controller = loader.getController();
        controller.onInit();
        currentPane.getChildren().removeAll();
        currentPane.getChildren().setAll(nextPane);

    }
}