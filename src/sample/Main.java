package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;

public class Main extends Application {
    Stage primaryStage;
    Scene mainMenu;

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;
        primaryStage.setTitle("Time Swap");

        primaryStage.setMaximized(true);

        Button button_endless = new Button("Endless Mode");
        Button button_campaign = new Button("Campaign mode");
        Button button_inventory = new Button("Inventory");
        Button button_test = new Button("Test");
        
        //System.out.println("HI ALEX");
        //System.out.println("sup bitches");




        GridPane mmgp = new GridPane();

        mmgp.add(button_endless, 0, 0, 1, 1);
        mmgp.add(button_campaign, 0, 1, 1, 1);
        mmgp.add(button_inventory, 0, 2, 1, 1);

        mmgp.add(button_test, 0, 4, 1, 1);

        Scene scene = new Scene(mmgp, 200, 100);


        GlobalSettingsData gsd = new GlobalSettingsData();



        Items items = new Items();
        InventoryScreen is = new InventoryScreen(items, primaryStage, scene, gsd);
        ShopScreen ss = new ShopScreen( items, gsd, primaryStage, scene);
        Scene shopScene = ss.generateScene();


        primaryStage.setScene(scene);
        primaryStage.show();

        CampaignMap map = new CampaignMap(items, gsd, primaryStage, scene);

        button_campaign.setOnAction(value -> {
            try {
                primaryStage.setScene(map.setTheScene());
            } catch (Exception e) {
                e.printStackTrace();
            }
            primaryStage.show();
        });


        button_inventory.setOnAction(value -> {
            try {
                primaryStage.setScene(is.generateScene());
            } catch (Exception e) {
                e.printStackTrace();
            }
            primaryStage.show();
        });

        button_endless.setOnAction(value -> {
            try {
                primaryStage.setScene(new GameScreen(this, is.getValueOfInvSlots(), 1, 10000,20).getScene());
            } catch (Exception e) {
                e.printStackTrace();
            }
            primaryStage.show();
        });
    }

    public void endGame(int points, int goal, int movesLeft) {
        primaryStage.setScene(new LevelEnd(points, goal, movesLeft, this).getScene());
    }

    public void loadMainMenu() {
        primaryStage.setScene(mainMenu);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
