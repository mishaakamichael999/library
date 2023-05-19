package project.library;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

            stage.setResizable(false);
//            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));

            Scene scene = new Scene(root);

            stage.setTitle("Library Management");
            stage.setScene(scene);

            stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}