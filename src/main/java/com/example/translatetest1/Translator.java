package com.example.translatetest1;


import Manager.SoundManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Translator {
    @FXML
    private Label first ;
    @FXML
    private Label second;
    @FXML
    private TextField inputWord;
    @FXML
    private TextArea outputWord;


    @FXML
    private void switchlanguage(ActionEvent event) {
        String s = first.getText();
        first.setText(second.getText());
        second.setText(s);
        String ss = inputWord.getText();
        inputWord.setText(outputWord.getText());
        outputWord.setText(ss);
    }

    @FXML
    private void translateWord(KeyEvent event) throws IOException {
        String aa = first.getText().toLowerCase();
        String bb = second.getText().toLowerCase();
        aa = aa.substring(0, 2);
        bb = bb.substring(0, 2);
            String word = inputWord.getText();
            String translatedWord = translate(aa, bb, word);
            outputWord.setText(translatedWord);

    }

    @FXML
    private void speakWord(ActionEvent event) {
        SoundManager.getIns(SoundManager.class).setWordToSpeak(inputWord.getText());
        SoundManager.getIns(SoundManager.class).speakWord();
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

        //slow here
        long startTime = System.nanoTime();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        long endTime = System.nanoTime();
        System.out.println("Time = : "
                + (endTime - startTime) / 1000000 + " ms");
        //

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }
}


