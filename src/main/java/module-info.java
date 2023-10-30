module com.example.translatetest1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.logging;
    requires jsapi;
    requires  org.kordamp.ikonli.fontawesome5;
    requires java.sql;

    opens com.example.translatetest1 to javafx.fxml;
    exports com.example.translatetest1;
}