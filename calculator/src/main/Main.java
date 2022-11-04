package main;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @FXML
    private TextField input1;
    @FXML
    private Label output;

    public static void main(String[] args) {
//        new CalcWindow().createGUI();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        int WIDTH = 400;
        int HEIGHT = 700;

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/calculator-main.fxml")));

        Scene scene = new Scene(root, WIDTH, HEIGHT);

        stage.setTitle("Calculator");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleInput() {
        output.setText("" + Logic.evaluate(input1.getText()));
    }

}