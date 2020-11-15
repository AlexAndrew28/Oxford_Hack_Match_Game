package sample;


import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class CampaignMap {



    public Scene setTheScene() throws Exception {


        //image file path:
        //      C:/Users/Productive/Documents/Coding/Principles of Programming/Oxford_Hack_Match_Game/src/res/portal.png
        //      C:\Users\Productive\Documents\Coding\Principles of Programming\Oxford_Hack_Match_Game\src\res\portal.png







        GridPane grid = new GridPane();



        /*//grid.setStyle("-fx-background-image: url('src/res/backdrop.png')");

        grid.setStyle("-fx-background-image: url('\\src\\res\\background.png')");

        grid.setStyle("-fx-background-repeat: stretch");
        grid.setStyle("-fx-background-size: 1920 1080");
        grid.setStyle("-fx-background-position: center center");*/

        BackgroundImage myBI= new BackgroundImage(new Image("file:\\src\\res\\background.png",1920,1080,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        grid.setBackground(new Background(myBI));

        //portal level buttons
        Button buttonPortal_start = new Button("Begin!"); // starting portal (in dinosaur age)
        Button buttonPortal_fantasy = new Button("Level 2-0"); // 2nd portal (from dinosaurs to fantasy)
        Button buttonPortal_future = new Button("Level 3-0"); // 3rd portal (from fantasy to future)
        Button buttonPortal_end = new Button("Finale"); // final portal (from future to game complete)

        //non-portal level buttons
        Button buttonLevel_1_1 = new Button("1-1");
        Button buttonLevel_1_2 = new Button("1-2");
        Button buttonLevel_1_3 = new Button("1-3");

        Button buttonLevel_2_1 = new Button("2-1");
        Button buttonLevel_2_2 = new Button("2-2");
        Button buttonLevel_2_3 = new Button("2-3");

        Button buttonLevel_3_1 = new Button("3-1");
        Button buttonLevel_3_2 = new Button("3-2");
        Button buttonLevel_3_3 = new Button("3-3");

        Button button_non = new Button("Non-Portal Level");
        Button button_shop = new Button("Enter the Shop!");
        Button button_endless = new Button("Endless Mode");

        //portal button 1
        FileInputStream input = new FileInputStream("src/res/small_portal.png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        buttonPortal_start.setGraphic(imageView);
        buttonPortal_start.setStyle("-fx-background-color: #8B008B");

        buttonPortal_fantasy.setGraphic(imageView);
        buttonPortal_fantasy.setStyle("-fx-background-color: #8B008B");

        buttonPortal_future.setGraphic(imageView);
        buttonPortal_future.setStyle("-fx-background-color: #8B008B");

        buttonPortal_end.setGraphic(imageView);
        buttonPortal_end.setStyle("-fx-background-color: #f4f4f4");


        //non portal buttons:
        buttonLevel_1_1.setShape(new Circle(100));
        buttonLevel_1_1.setStyle("-fx-background-color: ffffff");
        //buttonLevel_1_1.setStyle("-fx-border-color: #000000");

        //
        buttonLevel_1_2.setShape(new Circle(10));
        buttonLevel_1_2.setStyle("-fx-background-color: #ffffff");
        //
        buttonLevel_1_3.setShape(new Circle(10));
        buttonLevel_1_3.setStyle("-fx-background-color: #ffffff");

        //level 2
        buttonLevel_2_1.setShape(new Circle(10));
        buttonLevel_2_1.setStyle("-fx-background-color: #ffffff");
        //
        buttonLevel_2_2.setShape(new Circle(10));
        buttonLevel_2_2.setStyle("-fx-background-color: #ffffff");
        //
        buttonLevel_2_3.setShape(new Circle(10));
        buttonLevel_2_3.setStyle("-fx-background-color: #ffffff");

        //level 3
        buttonLevel_3_1.setShape(new Circle(10));
        buttonLevel_3_1.setStyle("-fx-background-color: #ffffff");
        //
        buttonLevel_3_2.setShape(new Circle(10));
        buttonLevel_3_2.setStyle("-fx-background-color: #ffffff");
        //
        buttonLevel_3_3.setShape(new Circle(10));
        buttonLevel_3_3.setStyle("-fx-background-color: #ffffff");


        ColumnConstraints column0 = new ColumnConstraints();
        column0.setPercentWidth(20);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(20);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(20);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(20);
        grid.getColumnConstraints().addAll(column0, column1, column2, column3);

        RowConstraints row0 = new RowConstraints();
        row0.setPercentHeight(20);
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(20);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(20);
        RowConstraints row3 = new RowConstraints();
        row3.setPercentHeight(20);
        RowConstraints row4 = new RowConstraints();
        row4.setPercentHeight(0);
        grid.getRowConstraints().addAll(row0, row1, row2, row3); // each get 1/3 of width

        //set positions of portal buttons
        grid.add(buttonPortal_start, 1, 0, 1, 1);//[ i:  offset x][ i1:  offset y][ i2:  size x][ i3:  size y]
        grid.add(buttonPortal_fantasy, 2, 0, 1, 1);
        grid.add(buttonPortal_future, 3, 0, 1, 1);
        grid.add(buttonPortal_end, 3, 4, 1, 1);

        //set position of level buttons
        grid.add(buttonLevel_1_1, 1, 1, 1, 1);
        grid.add(buttonLevel_1_2, 1, 2, 1, 1);
        grid.add(buttonLevel_1_3, 1, 3, 1, 1);

        grid.add(buttonLevel_2_1, 2, 1, 1, 1);
        grid.add(buttonLevel_2_2, 2, 2, 1, 1);
        grid.add(buttonLevel_2_3, 2, 3, 1, 1);

        grid.add(buttonLevel_3_1, 3, 1, 1, 1);
        grid.add(buttonLevel_3_2, 3, 2, 1, 1);
        grid.add(buttonLevel_3_3, 3, 3, 1, 1);

        //set positions of other buttons
        grid.add(button_non, 0, 1, 1, 1);
        grid.add(button_shop, 0, 2, 1, 1);
        grid.add(button_endless, 0, 3, 1, 1);

        Scene map = new Scene(grid, 1920, 1080);

        //actions

        buttonLevel_1_1.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) ->  {
            if (show) {
                buttonLevel_1_1.setStyle("-fx-background-color: #c4c4c4");
                //buttonLevel_1_1.setStyle("-fx-border-color: #000000");
            } else {
                buttonLevel_1_1.setStyle("-fx-background-color: #ffffff");
                //buttonLevel_1_1.setStyle("-fx-border-color: #000000");
            }
        });
        buttonLevel_1_2.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) ->  {
            if (show) {
                buttonLevel_1_2.setStyle("-fx-background-color: #c4c4c4");
            } else {
                buttonLevel_1_2.setStyle("-fx-background-color: #ffffff");
            }
        });
        buttonLevel_1_3.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) ->  {
            if (show) {
                buttonLevel_1_3.setStyle("-fx-background-color: #c4c4c4");
            } else {
                buttonLevel_1_3.setStyle("-fx-background-color: #ffffff");
            }
        });

        buttonLevel_2_1.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) ->  {
            if (show) {
                buttonLevel_2_1.setStyle("-fx-background-color: #c4c4c4");
            } else {
                buttonLevel_2_1.setStyle("-fx-background-color: #ffffff");
            }
        });
        buttonLevel_2_2.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) ->  {
            if (show) {
                buttonLevel_2_2.setStyle("-fx-background-color: #c4c4c4");
            } else {
                buttonLevel_2_2.setStyle("-fx-background-color: #ffffff");
            }
        });
        buttonLevel_2_3.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) ->  {
            if (show) {
                buttonLevel_2_3.setStyle("-fx-background-color: #c4c4c4");
            } else {
                buttonLevel_2_3.setStyle("-fx-background-color: #ffffff");
            }
        });

        buttonLevel_3_1.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) ->  {
            if (show) {
                buttonLevel_3_1.setStyle("-fx-background-color: #c4c4c4");
            } else {
                buttonLevel_3_1.setStyle("-fx-background-color: #ffffff");
            }
        });
        buttonLevel_3_2.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) ->  {
            if (show) {
                buttonLevel_3_2.setStyle("-fx-background-color: #c4c4c4");
            } else {
                buttonLevel_3_2.setStyle("-fx-background-color: #ffffff");
            }
        });
        buttonLevel_3_3.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) ->  {
            if (show) {
                buttonLevel_3_3.setStyle("-fx-background-color: #c4c4c4");
            } else {
                buttonLevel_3_3.setStyle("-fx-background-color: #ffffff");
            }
        });

        return map;
    }

    public void test(Stage primaryStage) throws Exception {
        primaryStage.setTitle("ImageView Experiment 1");

        FileInputStream input = new FileInputStream("src/res/portal.png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);

        HBox hbox = new HBox(imageView);

        Scene scene = new Scene(hbox, 200, 100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
