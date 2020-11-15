package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;

public class Main extends Application {

    Stage primaryStage;
    Scene mainMenu;
    GlobalSettingsData gsd;
    InventoryScreen is;
    CampaignMap map;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Time Swap");

        primaryStage.setMaximized(true);

        Text title = new Text("Time Swap");
        title.setFont(Font.font(60));
        Button button_endless = new Button("Endless Mode");
        Button button_campaign = new Button("Campaign mode");
        Button button_inventory = new Button("Inventory");
        Button button_shop = new Button("Shop");
//        Button button_test = new Button("Test");

        Font btnFont = Font.font(30);
        button_endless.setFont(btnFont);
        button_campaign.setFont(btnFont);
        button_inventory.setFont(btnFont);
        button_shop.setFont(btnFont);
//        button_test.setFont(btnFont);

        button_endless.setMinWidth(300);
        button_campaign.setMinWidth(300);
        button_inventory.setMinWidth(300);
        button_shop.setMinWidth(300);
//        button_test.setMinWidth(300);

        button_shop.setOnAction(value ->  {
            System.out.println("button - shop");
        });

        VBox menuBox = new VBox();
        menuBox.setStyle("-fx-background-color: #B4B4B4;");
        menuBox.setSpacing(20);

        menuBox.getChildren().add(title);
        menuBox.getChildren().add(button_campaign);
//        menuBox.getChildren().add(button_endless);
//        menuBox.getChildren().add(button_inventory);
//        menuBox.getChildren().add(button_shop);
//        menuBox.getChildren().add(button_test);

        menuBox.setAlignment(Pos.CENTER);

        mainMenu = new Scene(menuBox, 600, 600);

        gsd = new GlobalSettingsData();

        Items items = new Items();
        is = new InventoryScreen(items, primaryStage, this, gsd);
        ShopScreen ss = new ShopScreen(items, gsd, primaryStage, this);
        Scene shopScene = ss.generateScene();

        primaryStage.setScene(mainMenu);
        primaryStage.show();

        map = new CampaignMap(items, gsd, primaryStage, mainMenu, this);

        button_campaign.setOnAction(value -> {
            try {
                primaryStage.setScene(map.setTheScene());
            } catch (Exception e) {
                e.printStackTrace();
            }
            primaryStage.show();
        });

        button_shop.setOnAction(value -> {
            try {
                primaryStage.setScene(shopScene);
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
                primaryStage.setScene(new GameScreen(this, is.getValueOfInvSlots(), 1, 10000, 20, true).getScene());
            } catch (Exception e) {
                e.printStackTrace();
            }
            primaryStage.show();
        });
    }

    public void endGame(int points, int goal, int movesLeft, boolean endless) {
        primaryStage.setScene(new LevelEnd(points, goal, movesLeft, this, endless).getScene());
    }

    public void loadMainMenu() {
        primaryStage.setScene(mainMenu);
    }

    public void loadCampaignMap() {
        try {
            primaryStage.setScene(map.setTheScene());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
