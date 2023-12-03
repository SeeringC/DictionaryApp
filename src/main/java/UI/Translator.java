package UI;


import Manager.UIManager;
import Manager.SoundManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    private Label first ;
    @FXML
    private Label second;
    @FXML
    private TextField inputWord;
    @FXML
    private TextArea outputWord;

    @Override
    public void onInit() {

    }

    @Override
    public void onClose() {

    }

    @FXML
    public void switchToHelloApplication(ActionEvent event) throws IOException {
        UIManager.getIns(UIManager.class).openScene(currentPane, "HelloApplication.fxml");
    }

    @FXML
    private void switchLanguage(ActionEvent event) {
        swapLabels(first, second);
        swapTextFields(inputWord, outputWord);
    }

    private void swapLabels(Label first, Label second) {
        String temp = first.getText();
        first.setText(second.getText());
        second.setText(temp);
    }

    private void swapTextFields(TextField inputWord, TextArea outputWord) {
        String temp = inputWord.getText();
        inputWord.setText(outputWord.getText());
        outputWord.setText(temp);
    }

    @FXML
    private void translateWord(KeyEvent event) throws IOException {
        String fromLanguage = first.getText();
        String toLanguage = second.getText();
        String word = inputWord.getText();
        String translatedWord = translate(fromLanguage, toLanguage, word);
        outputWord.setText(translatedWord);
    }

    private String getLanguageCode(Label label) {
        return label.getText().toLowerCase().substring(0, 2);
    }

    @FXML
    private void speakWord(ActionEvent event) {
        SoundManager.getIns(SoundManager.class).speakWord(inputWord.getText());
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
}


