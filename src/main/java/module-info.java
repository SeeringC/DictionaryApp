module com.example.translatetest1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.logging;
    requires jsapi;
    requires org.kordamp.ikonli.fontawesome5;
    requires java.sql;
    requires java.net.http;
    requires com.jfoenix;
    requires java.desktop;

    opens com.example.translatetest1 to javafx.fxml;
    exports com.example.translatetest1;
    exports Manager;
    opens Manager to javafx.fxml;
    exports Singleton;
    opens Singleton to javafx.fxml;
    exports UI;
    opens UI to javafx.fxml;
}