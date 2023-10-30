module com.example.translatetest1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires jlayer;
    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.logging;
    requires jsapi;

    opens com.example.translatetest1 to javafx.fxml;
    exports com.example.translatetest1;
}