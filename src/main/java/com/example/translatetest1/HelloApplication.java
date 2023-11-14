package com.example.translatetest1;

import Manager.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HelloApplication  {

    SceneManager sceneM = SceneManager.getIns(SceneManager.class);
    @FXML
    private AnchorPane HomePane;
    @FXML
    public void switchToTranslator(ActionEvent event) throws IOException {
        sceneM.openScene(HomePane,"Translator.fxml");
    }
    public void switchToAddWord(ActionEvent event) throws IOException {
        sceneM.openScene(HomePane,"WordConfiguration.fxml");
    }
    public void switchToLookUpWord(ActionEvent event) throws IOException {
        sceneM.openScene(HomePane,"LookUpWord.fxml");
    }
}
