package com.example.translatetest1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class AddWord {
    @FXML
    private AnchorPane AddPane;
    @FXML
    private Button bt1;
    @FXML
    private TextField addbox1;
    @FXML
    private TextField addbox2;
    @FXML
    public void switchToHelloApplication(ActionEvent event) throws IOException {
        new SwitchScene(AddPane,"HelloApplication.fxml");
    }

    public void Success(ActionEvent event) throws SQLException {
        DataBaseManager.addWordtoSQL(addbox1.getText(), addbox2.getText());
        if (!MyDictionary.isWordInDic(addbox1.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information dialog");
            alert.setContentText("Từ " + addbox1.getText() + " đã được thêm thành công!");
            alert.setHeaderText("Thông báo");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information dialog");
            alert.setContentText("Từ " + addbox1.getText() + " đã tồn tại trong từ điển!");
            alert.setHeaderText("Thông báo");
            alert.showAndWait();
        }
    }
}

