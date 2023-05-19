package project.library;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws IOException {
            stage.setResizable(false);
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

            Scene scene = new Scene(root);
            scene.getRoot().requestFocus();
            stage.setTitle("Library Management");
            stage.setScene(scene);

            stage.show();

    }


    public static void main(String[] args) {
        launch();
    }

}