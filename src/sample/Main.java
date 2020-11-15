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

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Time Swap");

        primaryStage.setMaximized(true);

        Button button_endless = new Button("Endless Mode");
        Button button_campaign = new Button("Campaign mode");
        Button button_inventory = new Button("Inventory");
        Button button_shop = new Button("Shop");
        Button button_test = new Button("Test");
        
        //System.out.println("HI ALEX");
        //System.out.println("sup bitches");

        button_shop.setOnAction(value ->  {
            System.out.println("button - shop");
        });



        GridPane mmgp = new GridPane();

        mmgp.add(button_endless, 0, 0, 1, 1);
        mmgp.add(button_campaign, 0, 1, 1, 1);
        mmgp.add(button_inventory, 0, 2, 1, 1);
        mmgp.add(button_shop, 0, 3, 1, 1);

        mmgp.add(button_test, 0, 4, 1, 1);

        Scene scene = new Scene(mmgp, 200, 100);
        GlobalSettingsData gsd = new GlobalSettingsData();



        Items items = new Items();
        InventoryScreen is = new InventoryScreen(1,items);
        ShopScreen ss = new ShopScreen(5, items, gsd);
        Scene scene2 = ss.generateScene();


        primaryStage.setScene(scene2);
        primaryStage.show();

        CampaignMap map = new CampaignMap();

        button_campaign.setOnAction(value -> {
            try {
                primaryStage.setScene(map.setTheScene());
            } catch (Exception e) {
                e.printStackTrace();
            }
            primaryStage.show();
        });
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
