package com.example.translatetest1;


import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.Key;
import java.util.Locale;

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
            System.out.println("new function: " + translatedWord);

    }

    @FXML
    private void speakword(MouseEvent event) {
        try
        {
//setting properties as Kevin Dictionary
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us" + ".cmu_us_kal.KevinVoiceDirectory");
//registering speech engine
            Central.registerEngineCentral("com.sun.speech.freetts" + ".jsapi.FreeTTSEngineCentral");
//create a Synthesizer that generates voice
            Synthesizer synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
//allocates a synthesizer
            synthesizer.allocate();
//resume a Synthesizer
            synthesizer.resume();
//speak the specified text until the QUEUE become empty
            synthesizer.speakPlainText(inputWord.getText(), null);
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
//deallocating the Synthesizer
            synthesizer.deallocate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
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


