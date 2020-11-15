package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class LevelEnd {

    Scene scene;

    public LevelEnd(int points, int goal, int movesLeft) {


        VBox layout = new VBox();
        Text title = new Text();
        title.setFont(Font.font(50));
        if (points >= goal) { // success
            title.setText("Success!");
        } else { // fail
            title.setText("Failure");
        }
        layout.getChildren().add(title);
        GridPane results = new GridPane();
        results.setPrefSize(600, 300);
        results.setAlignment(Pos.CENTER);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(50);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(50);
        results.setVgap(20);
        results.getColumnConstraints().addAll(column1, column2);
        Text pointResult = new Text("Total Points Gotten:");
        Text pointNum = new Text(points + "/" + goal);
        Text moveText = new Text("Moves Left Over:");
        Text moveNum = new Text(movesLeft + "");
        Text goldText = new Text("Gold Earned:");
        Text goldNum = new Text(movesLeft * goal + "");

        pointResult.setFont(Font.font(20));
        pointNum.setFont(Font.font(20));
        moveText.setFont(Font.font(20));
        moveNum.setFont(Font.font(20));
        goldText.setFont(Font.font(20));
        goldNum.setFont(Font.font(20));

        results.add(pointResult, 0, 0);
        results.add(pointNum, 1, 0);
        results.add(moveText, 0, 1);
        results.add(moveNum, 1, 1);
        results.add(goldText, 0, 2);
        results.add(goldNum, 1, 2);

        layout.getChildren().add(results);

        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(50);

        Button retry = new Button("Retry");
        Button toMap = new Button("Continue");
        retry.setFont(Font.font(25));
        toMap.setFont(Font.font(25));
        buttons.getChildren().add(retry);
        buttons.getChildren().add(toMap);

        layout.getChildren().add(buttons);

        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(50));

        scene = new Scene(layout, 800, 800);

        scene.setFill(Color.gray(0.7));

    }

    public Scene getScene() {
        return scene;
    }

}
