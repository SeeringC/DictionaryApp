package AlertDisplay;

import javafx.scene.control.Alert;

public class ChangeWordDefinitionSuccessAlert implements CustomAlert {

    @Override
    public void displayAlert(String wordToDisplay) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information dialog");
        alert.setContentText("Từ " + wordToDisplay + " đã được sửa thành công!");
        alert.setHeaderText("Thông báo");
        alert.showAndWait();
    }
}
