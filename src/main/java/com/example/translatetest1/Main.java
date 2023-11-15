package com.example.translatetest1;

import Manager.DataBaseManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    @Override
    public void init() throws Exception {
        DataBaseManager.getIns(DataBaseManager.class).init();
    }

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HelloApplication.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        System.out.println("java version: " + System.getProperty("java.version"));
        System.out.println("javafx version : " + System.getProperty("javafx.version"));
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String [] args) {
        launch();
    }
}