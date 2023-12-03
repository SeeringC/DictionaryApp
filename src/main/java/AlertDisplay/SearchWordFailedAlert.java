package AlertDisplay;

import javafx.scene.control.Alert;

public class SearchWordFailedAlert implements CustomAlert{
    @Override
    public void displayAlert(String wordToDisplay) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information dialog");
        alert.setContentText("Từ " + wordToDisplay + " không tồn tại trong từ điển!");
        alert.setHeaderText("Thông báo");
        alert.showAndWait();
    }
}
