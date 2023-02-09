package me.mvdev.gitbgen;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("application-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 720, 450);
        scene.getStylesheets().add(Application.class.getResource("application-view.css").toExternalForm());
        stage.setTitle("Generate Git Bash Command");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}