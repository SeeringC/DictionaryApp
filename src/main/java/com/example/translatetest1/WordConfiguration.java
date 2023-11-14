package com.example.translatetest1;

import Manager.SceneManager;
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
    MyDictionary myDic = MyDictionary.getIns(MyDictionary.class);
    SceneManager sceneM = SceneManager.getIns(SceneManager.class);
    @FXML
    public void switchToHelloApplication(ActionEvent event) throws IOException {
        sceneM.openScene(AddPane,"HelloApplication.fxml");
    }

    public void Success(ActionEvent event) throws SQLException {
        myDic.addWordToDic(addbox1.getText(), addbox2.getText());
    }
}

