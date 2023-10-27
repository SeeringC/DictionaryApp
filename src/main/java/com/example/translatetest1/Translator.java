package com.example.translatetest1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
public class Translator {

    @FXML
    private TextField inputWord;
    @FXML
    private TextArea outputWord;

    @FXML
    private void translateWord(ActionEvent event) throws IOException {
        String word = inputWord.getText();
        String translatedWord = translate("en", "fr", word);
        System.out.println("new function: " + translatedWord);

        outputWord.setText(translatedWord);

    }

    private static String translate(String langFrom, String langTo, String text) throws IOException {
        // INSERT YOU URL HERE
        String urlStr = "https://script.google.com/macros/s/AKfycbyXrwuAli6fGFhO_SkvG749ydnrNkcLSCpC9EfMUtOc-6KdpPB_RDOeLGCIFWZXk3uT/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}
