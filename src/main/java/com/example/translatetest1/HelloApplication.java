package com.example.translatetest1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Translator.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        System.out.println("java version: " + System.getProperty("java.version"));
        System.out.println("javafx version : " + System.getProperty("javafx.version"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}