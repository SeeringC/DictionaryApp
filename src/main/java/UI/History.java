package UI;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;

public class History implements UILayer {

    @FXML
    private MediaView backgroundTest;
    @FXML
    private Pane currentPane;
    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;
    @Override
    public void onInit() {
        loadTestBackground();
    }

    @Override
    public void onClose() {

    }
    private void loadTestBackground() {
//        String path = new File("src/main/resources/com/example/translatetest1/Wallpapers/NightCityCyberpunk2077Wallpaper.mp4").getAbsolutePath();
//        Media media = new Media(new File(path).toURI().toString());
//        MediaPlayer mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.setAutoPlay(true);
//        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
//        backgroundTest.setMediaPlayer(mediaPlayer);
        file = new File("src/main/resources/com/example/translatetest1/Wallpapers/Night City - Cyberpunk 2077 Wallpaper (1).mp4");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        backgroundTest.setMediaPlayer(mediaPlayer);
        System.out.println("video played");
    }
}
