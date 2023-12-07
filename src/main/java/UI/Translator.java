package UI;


import Manager.SoundManager;
import Manager.UIManager;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Translator implements UILayer {

    @FXML
    private Pane currentPane;
    @FXML
    private Label langFrom;
    @FXML
    private Label langTo;
    @FXML
    private JFXTextArea inputWord;
    @FXML
    private JFXTextArea outputWord;

    @Override
    public void onInit() {

    }

    @Override
    public void onClose() {

    }

    @FXML
    private void switchLanguage(ActionEvent event) {
        swapLabels(langFrom, langTo);
        swapTextAreas(inputWord, outputWord);
    }

    private void swapLabels(Label first, Label second) {
        String temp = first.getText();
        first.setText(second.getText());
        second.setText(temp);
    }

    private void swapTextAreas(JFXTextArea inputWord, JFXTextArea outputWord) {
        String temp = inputWord.getText();
        inputWord.setText(outputWord.getText());
        outputWord.setText(temp);
    }

    @FXML
    private void translateWord(ActionEvent event) throws IOException {
        String fromLanguage = getLanguageCode(langFrom);
        String toLanguage = getLanguageCode(langTo);
        String word = inputWord.getText();
        String translatedWord = translate(fromLanguage, toLanguage, word);
        outputWord.setText(translatedWord);
    }

    private String getLanguageCode(Label label) {
        return label.getText().toLowerCase().substring(0, 2);
    }

    @FXML
    private void speakInputWord(ActionEvent event) {
        SoundManager.getIns(SoundManager.class).speakWord(inputWord.getText());
    }

    @FXML
    private void speakOutputWord(ActionEvent event) {
        SoundManager.getIns(SoundManager.class).speakWord(outputWord.getText());
    }

    private String translate(String langFrom, String langTo, String text) throws IOException {
        String urlStr = buildUrl(langFrom, langTo, text);
        return sendGetRequest(urlStr);
    }
    private String buildUrl(String langFrom, String langTo, String text) throws IOException {
        return "https://script.google.com/macros/s/AKfycbyXrwuAli6fGFhO_SkvG749ydnrNkcLSCpC9EfMUtOc-6KdpPB_RDOeLGCIFWZXk3uT/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
    }

    private String sendGetRequest(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    @FXML
    private void backToMainMenu(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ESCAPE) {
            UIManager.getIns(UIManager.class).openScene(currentPane, "MainMenu.fxml");
        }
    }
}


