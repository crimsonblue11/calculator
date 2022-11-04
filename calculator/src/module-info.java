module calculator {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.desktop;

    opens main to javafx.graphics, javafx.fxml;
}