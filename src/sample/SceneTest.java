package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Random;

public class SceneTest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Test");




        primaryStage.setScene(new GameScreen().getScene());
        primaryStage.show();
        primaryStage.setFullScreen(true);
    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}
