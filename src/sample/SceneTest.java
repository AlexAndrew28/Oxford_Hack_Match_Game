package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class SceneTest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Test");
        primaryStage.setScene(new GameScreen().getScene());
        primaryStage.show();
//        primaryStage.setFullScreen(true);
        System.out.println();
    }

//    public static final double W = 200; // canvas dimensions.
//    public static final double H = 200;
//
//    public static final double D = 20;  // diameter.

//    @Override public void start(Stage stage) {
//        DoubleProperty x  = new SimpleDoubleProperty();
//        DoubleProperty y  = new SimpleDoubleProperty();
//
//        Timeline timeline = new Timeline(
//                new KeyFrame(Duration.seconds(0),
//                        new KeyValue(x, 0),
//                        new KeyValue(y, 0)
//                ),
//                new KeyFrame(Duration.seconds(3),
//                        new KeyValue(x, W - D),
//                        new KeyValue(y, H - D)
//                )
//        );
//        timeline.setAutoReverse(true);
//        timeline.setCycleCount(Timeline.INDEFINITE);
//
//        final Canvas canvas = new Canvas(W, H);
//        AnimationTimer timer = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                GraphicsContext gc = canvas.getGraphicsContext2D();
//                gc.setFill(Color.CORNSILK);
//                gc.fillRect(0, 0, W, H);
//                gc.setFill(Color.FORESTGREEN);
//                gc.fillOval(
//                        x.doubleValue(),
//                        y.doubleValue(),
//                        D,
//                        D
//                );
//            }
//        };
//
//        stage.setScene(
//                new Scene(
//                        new Group(
//                                canvas
//                        )
//                )
//        );
//        stage.show();
//
//        timer.start();
//        timeline.play();
//    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}
