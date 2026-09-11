module BiciTaller {
    requires javafx.controls;
    requires javafx.fxml;

    opens viewController to javafx.fxml;
    exports app;
    exports viewController;
}