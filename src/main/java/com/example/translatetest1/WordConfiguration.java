package com.example.translatetest1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class WordConfiguration {
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
        MyDictionary.addWordtodic(addbox1.getText(), addbox2.getText());
    }
}

