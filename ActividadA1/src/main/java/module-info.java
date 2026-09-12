module BiciTaller {
    requires javafx.controls;
    requires javafx.fxml;

    opens app to javafx.fxml;
    opens controller to javafx.fxml;

    exports app;
}