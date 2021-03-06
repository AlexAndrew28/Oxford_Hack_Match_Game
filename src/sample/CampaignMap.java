package sample;

import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CampaignMap {
    private Main main;
    private Items items;
    private GlobalSettingsData gsd;
    private Stage primaryStage;
    private Scene scene;

    public CampaignMap(Items items, GlobalSettingsData gsd, Stage primaryStage, Scene scene, Main main){
        this.main = main;
        this.items = items;
        this.gsd = gsd;
        this.primaryStage = primaryStage;
        this.scene = scene;
    }


    public Scene setTheScene() throws Exception {
        GridPane grid = new GridPane();

        Image back = new Image(new FileInputStream("src\\res\\lands.png"));
        grid.add(new ImageView(back),0,0,20,20);

        Font btnFont = Font.font("default", FontWeight.BOLD, 30);

        //portal level buttons
        Button buttonPortal_start = new Button("Begin!"); // starting portal (in dinosaur age)
        Button buttonPortal_fantasy = new Button("Level 2-0"); // 2nd portal (from dinosaurs to fantasy)
        Button buttonPortal_future = new Button("Level 3-0"); // 3rd portal (from fantasy to future)
        Button buttonPortal_end = new Button(""); // final portal (from future to game complete)

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


        Button button_shop = new Button("Enter the Shop!");
        Button button_inventory = new Button("View Inventory");
        button_shop.setFont(btnFont);
        button_inventory.setFont(btnFont);

        //portal button 1
        FileInputStream inputA = new FileInputStream("src/res/round_portalA.png");
        Image imageA = new Image(inputA);
        ImageView imageViewA = new ImageView(imageA);
        buttonPortal_start.setGraphic(imageViewA);

        FileInputStream inputB = new FileInputStream("src/res/round_portalB.png");
        Image imageB = new Image(inputB);
        ImageView imageViewB = new ImageView(imageB);
        buttonPortal_fantasy.setGraphic(imageViewB);

        FileInputStream inputC = new FileInputStream("src/res/round_portalC.png");
        Image imageC = new Image(inputC);
        ImageView imageViewC = new ImageView(imageC);
        buttonPortal_future.setGraphic(imageViewC);

        FileInputStream inputD = new FileInputStream("src/res/round_portalD.png");
        Image imageD = new Image(inputD);
        ImageView imageViewD = new ImageView(imageD);
        buttonPortal_end.setGraphic(imageViewD);


        buttonPortal_start.setStyle("-fx-background-color: transparent");

        buttonPortal_fantasy.setStyle("-fx-background-color: transparent");

        buttonPortal_future.setStyle("-fx-background-color: transparent");

        buttonPortal_end.setStyle("-fx-background-color: transparent");


        //non portal buttons:
        buttonLevel_1_1.setShape(new Circle(100));
        buttonLevel_1_1.setStyle("-fx-background-color: ffffff");
        buttonLevel_1_1.setFont(btnFont);
        //buttonLevel_1_1.setStyle("-fx-border-color: #000000");

        //
        buttonLevel_1_2.setShape(new Circle(10));
        buttonLevel_1_2.setStyle("-fx-background-color: #ffffff");
        buttonLevel_1_2.setFont(btnFont);
        //
        buttonLevel_1_3.setShape(new Circle(10));
        buttonLevel_1_3.setStyle("-fx-background-color: #ffffff");
        buttonLevel_1_3.setFont(btnFont);

        //level 2
        buttonLevel_2_1.setShape(new Circle(10));
        buttonLevel_2_1.setStyle("-fx-background-color: #ffffff");
        buttonLevel_2_1.setFont(btnFont);
        //
        buttonLevel_2_2.setShape(new Circle(10));
        buttonLevel_2_2.setStyle("-fx-background-color: #ffffff");
        buttonLevel_2_2.setFont(btnFont);
        //
        buttonLevel_2_3.setShape(new Circle(10));
        buttonLevel_2_3.setStyle("-fx-background-color: #ffffff");
        buttonLevel_2_3.setFont(btnFont);

        //level 3
        buttonLevel_3_1.setShape(new Circle(10));
        buttonLevel_3_1.setStyle("-fx-background-color: #ffffff");
        buttonLevel_3_1.setFont(btnFont);
        //
        buttonLevel_3_2.setShape(new Circle(10));
        buttonLevel_3_2.setStyle("-fx-background-color: #ffffff");
        buttonLevel_3_2.setFont(btnFont);
        //
        buttonLevel_3_3.setShape(new Circle(10));
        buttonLevel_3_3.setStyle("-fx-background-color: #ffffff");
        buttonLevel_3_3.setFont(btnFont);


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

//        grid.add(button_shop, 0, 2, 1, 1);
//        grid.add(button_inventory, 0, 3, 1, 1);
        grid.add(button_shop, 1, 4, 1, 1);
        grid.add(button_inventory, 2, 4, 1, 1);


        Scene map = new Scene(grid, 1920, 1080);

        FileInputStream input2 = new FileInputStream("src/res/round_portal_on.png");
        Image image2 = new Image(input2);
        ImageView imageView2 = new ImageView(image2);

        //button hover stuff

        buttonPortal_start.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) ->  {
            if (show) {
                buttonPortal_start.setGraphic(imageView2);
                //buttonLevel_1_1.setStyle("-fx-border-color: #000000");
            } else {
                buttonPortal_start.setGraphic(imageViewA);
                //buttonLevel_1_1.setStyle("-fx-border-color: #000000");
            }
        });

        buttonPortal_fantasy.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) ->  {
            if (show) {
                buttonPortal_fantasy.setGraphic(imageView2);
                //buttonLevel_1_1.setStyle("-fx-border-color: #000000");
            } else {
                buttonPortal_fantasy.setGraphic(imageViewB);
                //buttonLevel_1_1.setStyle("-fx-border-color: #000000");
            }
        });

        buttonPortal_future.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) ->  {
            if (show) {
                buttonPortal_future.setGraphic(imageView2);
                //buttonLevel_1_1.setStyle("-fx-border-color: #000000");
            } else {
                buttonPortal_future.setGraphic(imageViewC);
                //buttonLevel_1_1.setStyle("-fx-border-color: #000000");
            }
        });

        buttonPortal_end.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) ->  {
            if (show) {
                buttonPortal_end.setGraphic(imageView2);
                //buttonLevel_1_1.setStyle("-fx-border-color: #000000");
            } else {
                buttonPortal_end.setGraphic(imageViewD);
                //buttonLevel_1_1.setStyle("-fx-border-color: #000000");
            }
        });



        //non portal level actions
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

        button_shop.setOnAction(value -> {
            ShopScreen ss = new ShopScreen(items, gsd, primaryStage, main);
            Scene scene2 = null;
            try {
                scene2 = ss.generateScene();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            primaryStage.setScene(scene2);
            primaryStage.setMaximized(true);
            primaryStage.isFullScreen();
            primaryStage.show();
        });

        button_inventory.setOnAction(value -> {
            Scene scene2 = null;
            try {
                scene2 = main.is.generateScene();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            primaryStage.setScene(scene2);
            primaryStage.setMaximized(true);
            primaryStage.isFullScreen();
            primaryStage.show();
        });


        //basic Levels

        buttonLevel_1_1.setOnAction(value -> {
            primaryStage.setScene(new GameScreen(main, main.is.getValueOfInvSlots(), 1, 1000, 10, false).getScene());
            primaryStage.setMaximized(true);
            primaryStage.isFullScreen();
            primaryStage.show();
        });

        buttonLevel_1_2.setOnAction(value -> {
            primaryStage.setScene(new GameScreen(main, main.is.getValueOfInvSlots(), 2, 5000, 20, false).getScene());
            primaryStage.setMaximized(true);
            primaryStage.isFullScreen();
            primaryStage.show();
        });

        buttonLevel_1_3.setOnAction(value -> {
            primaryStage.setScene(new GameScreen(main, main.is.getValueOfInvSlots(), 3, 7500, 20, false).getScene());
            primaryStage.setMaximized(true);
            primaryStage.isFullScreen();
            primaryStage.show();
        });

        buttonLevel_2_1.setOnAction(value -> {
            primaryStage.setScene(new GameScreen(main, main.is.getValueOfInvSlots(), 4, 10000, 20, false).getScene());
            primaryStage.setMaximized(true);
            primaryStage.isFullScreen();
            primaryStage.show();
        });

        buttonLevel_2_2.setOnAction(value -> {
            primaryStage.setScene(new GameScreen(main, main.is.getValueOfInvSlots(), 5, 12500, 20, false).getScene());
            primaryStage.setMaximized(true);
            primaryStage.isFullScreen();
            primaryStage.show();
        });

        buttonLevel_2_3.setOnAction(value -> {
            primaryStage.setScene(new GameScreen(main, main.is.getValueOfInvSlots(), 6, 15000, 20, false).getScene());
            primaryStage.setMaximized(true);
            primaryStage.isFullScreen();
            primaryStage.show();
        });

        buttonLevel_3_1.setOnAction(value -> {
            primaryStage.setScene(new GameScreen(main, main.is.getValueOfInvSlots(), 7, 17500, 20, false).getScene());
            primaryStage.setMaximized(true);
            primaryStage.isFullScreen();
            primaryStage.show();
        });

        buttonLevel_3_2.setOnAction(value -> {
            primaryStage.setScene(new GameScreen(main, main.is.getValueOfInvSlots(), 8, 20000, 20, false).getScene());
            primaryStage.setMaximized(true);
            primaryStage.isFullScreen();
            primaryStage.show();
        });

        buttonLevel_3_3.setOnAction(value -> {
            primaryStage.setScene(new GameScreen(main, main.is.getValueOfInvSlots(), 9, 25000, 20, false).getScene());
            primaryStage.setMaximized(true);
            primaryStage.isFullScreen();
            primaryStage.show();
        });

        buttonPortal_start.setOnAction(value -> { // tutorial scene
            primaryStage.setScene(new GameScreen(main, main.is.getValueOfInvSlots(), 1, 10000, 5, true).getScene());
            primaryStage.setMaximized(true);
            primaryStage.isFullScreen();
            primaryStage.show();

        });

        buttonPortal_fantasy.setOnAction(value ->{ // cutscene?
            primaryStage.setScene(new GameScreen(main, main.is.getValueOfInvSlots(), 1, 10000, 10, true).getScene());
            primaryStage.setMaximized(true);
            primaryStage.isFullScreen();
            primaryStage.show();

        });

        buttonPortal_future.setOnAction(value ->{ // also cutscene?
            primaryStage.setScene(new GameScreen(main, main.is.getValueOfInvSlots(), 1, 10000, 15, true).getScene());
            primaryStage.setMaximized(true);
            primaryStage.isFullScreen();
            primaryStage.show();

        });

        buttonPortal_end.setOnAction(value ->{ // endless mode
            primaryStage.setScene(new GameScreen(main, main.is.getValueOfInvSlots(), 1, 10000, 20, true).getScene());
            primaryStage.setMaximized(true);
            primaryStage.isFullScreen();
            primaryStage.show();
        });






        return map;
    }
}
