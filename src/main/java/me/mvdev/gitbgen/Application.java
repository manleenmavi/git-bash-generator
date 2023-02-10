package me.mvdev.gitbgen;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        Font.loadFont(Application.class.getResourceAsStream("Dongle-Regular.ttf"), 12);
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("application-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 719, 320);
        scene.getStylesheets().add(Application.class.getResource("application-view.css").toExternalForm());
        stage.setTitle("Generate Git Bash Command");
        stage.getIcons().add(new javafx.scene.image.Image(Application.class.getResourceAsStream("git-bash-gen-icon-2.png")));
        stage.setScene(scene);
        stage.setMaxWidth(719);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}