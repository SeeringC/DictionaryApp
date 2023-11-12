package com.example.translatetest1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HelloApplication  {
    @FXML
    private AnchorPane HomePane;
    @FXML
    public void switchToTranslator(ActionEvent event) throws IOException {
        new SwitchScene(HomePane,"Translator.fxml");
    }
    public void switchToAddWord(ActionEvent event) throws IOException {
        new SwitchScene(HomePane,"AddWord.fxml");
    }
    public void switchToLookUpWord(ActionEvent event) throws IOException {
        new SwitchScene(HomePane,"LookUpWord.fxml");
    }
}
