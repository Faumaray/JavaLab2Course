module Ray {
    requires javafx.controls;
    requires javafx.fxml;

    opens Ray to javafx.fxml;
    exports Ray;
}