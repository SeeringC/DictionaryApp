package Manager;

import Singleton.Singleton;
import UI.UILayer;
import com.example.translatetest1.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
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

    public void displayAlert(String type, String word) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        switch (type) {
            case "AddWordSuccess" :
                alert.setTitle("Information dialog");
                alert.setContentText("Từ " + word + " đã được thêm thành công!");
                alert.setHeaderText("Thông báo");
                alert.showAndWait();
                break;

            case "AddWordFailed" :
                alert.setTitle("Information dialog");
                alert.setContentText("Từ " + word + " đã tồn tại trong từ điển!");
                alert.setHeaderText("Thông báo");
                alert.showAndWait();
                break;

            case "DeleteWordSuccess" :
                alert.setTitle("Information dialog");
                alert.setContentText("Từ " + word + " đã được xóa thành công!");
                alert.setHeaderText("Thông báo");
                alert.showAndWait();
                break;

            case "SearchWordFailed" :
                alert.setTitle("Information dialog");
                alert.setContentText("Từ " + word + " không tồn tại trong từ điển!");
                alert.setHeaderText("Thông báo");
                alert.showAndWait();
                break;
        }

    }
}