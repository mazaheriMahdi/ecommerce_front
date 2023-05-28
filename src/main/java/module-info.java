module com.example.store_front {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.net.http;
    requires jackson.databind;
    requires jackson.core;
    requires com.google.gson;
    requires fontawesomefx;


    opens com.example.store_front to javafx.fxml;
    exports com.example.store_front;
    exports com.example.store_front.Components;
    opens com.example.store_front.Components to javafx.fxml;
    opens com.example.store_front.Models to com.google.gson;
}