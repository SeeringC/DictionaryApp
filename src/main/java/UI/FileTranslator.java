package UI;

import Manager.UIManager;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class FileTranslator implements UILayer {
    @FXML
    private Pane currentPane;
    @FXML
    private TextArea translatedContent;
    @FXML
    private TextArea contentToTranslate;
    @FXML
    private javafx.scene.control.Label langFrom;
    @FXML
    private javafx.scene.control.Label langTo;
    @FXML
    private JFXButton switchLanguageBtn;
    private FileChooser fileChooser = new FileChooser();
    private String browsedFileContent = "";



    @Override
    public void onInit() {
        limitFileTypes();
    }

    @Override
    public void onClose() {

    }


    private void limitFileTypes() {
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Word Documents", "*.docx")
        );
    }

    @FXML
    private void browseFile(ActionEvent event) {
        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    browsedFileContent = browsedFileContent.concat(scanner.nextLine() + "\n");
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        contentToTranslate.setText(browsedFileContent);
    }

    @FXML
    private void switchLanguage(ActionEvent event) {
        swapLabels(langFrom, langTo);
    }

    private void swapLabels(javafx.scene.control.Label fromLabel, javafx.scene.control.Label toLabel) {
        String temp = fromLabel.getText();
        fromLabel.setText(toLabel.getText());
        toLabel.setText(temp);
    }

    @FXML
    private void translateFileContent(ActionEvent event) throws IOException {
        String fromLanguage = getLanguageCode(langFrom);
        String toLanguage = getLanguageCode(langTo);

        String translatedFileContent = translate(fromLanguage, toLanguage, browsedFileContent);
        translatedContent.setText(translatedFileContent);
        System.out.println("setted");
    }

    private String getLanguageCode(Label label) {
        return label.getText().toLowerCase().substring(0, 2);
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

    public void exportFile() {
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            saveTextToFile(file);
        }
    }

    private void saveTextToFile(File file) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(translatedContent.getText());
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void backToMainMenu(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ESCAPE) {
            UIManager.getIns(UIManager.class).openScene(currentPane, "MainMenu.fxml");
        }
    }
}
