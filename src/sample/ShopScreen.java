package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ShopScreen {
    private int era;
    private Items items;
    private GlobalSettingsData gsd;
    private Stage primaryStage;
    private Main main;

    public ShopScreen(Items items, GlobalSettingsData gsd, Stage primaryStage, Main main){
        this.items = items;
        this.gsd = gsd;
        this.primaryStage = primaryStage;
        this.main = main;
    }


    public Scene generateScene() throws FileNotFoundException {
        era = gsd.getEra();
        primaryStage.setMaximized(true);
        GridPane mmgp = new GridPane();
        mmgp.setMinWidth(Screen.getPrimary().getBounds().getWidth());
        ScrollBar sc = new ScrollBar();
        sc.setOrientation(Orientation.VERTICAL);
        sc.setMax(360);
        sc.setPrefHeight(Screen.getPrimary().getBounds().getHeight()-100);

        Button backButton = new Button("Return to main menu");
        backButton.setOnAction(value ->  {
            main.loadCampaignMap();
        });

        //mmgp.add(backButton, 1, 0, 1,1);

        Rectangle backgroundRec = new Rectangle();
        backgroundRec.setX(0);
        backgroundRec.setY(0);
        backgroundRec.setWidth(800);
        backgroundRec.setHeight(Screen.getPrimary().getBounds().getHeight()*2);
        backgroundRec.setFill(Color.DARKGRAY);

        backgroundRec.setArcWidth(20);
        backgroundRec.setArcHeight(20);

        Rectangle finalBackground = new Rectangle();
        finalBackground.setX(0);
        finalBackground.setY(0);
        finalBackground.setWidth(Screen.getPrimary().getBounds().getWidth());
        finalBackground.setHeight(Screen.getPrimary().getBounds().getHeight()*5);
        finalBackground.setFill(Color.DIMGREY);

        mmgp.add(new Group(finalBackground), 0, 0, 10, 50);
        mmgp.add(new Group(backgroundRec),1,2,10,30);

        Image topBorder = new Image(new FileInputStream(System.getProperty("user.dir") + "\\src\\images\\topBorder.png"));
        mmgp.add(new ImageView(topBorder),1,0,10,1);

        Label desc = new Label("");
        desc.setFont(Font.font("Verdana",20));
        desc.setMaxWidth(250);
        desc.setWrapText(true);
        mmgp.add(desc,7,3,1,3);

        Item[] itemsInShop = items.getUnownedItems(era);

        System.out.println(itemsInShop.length);

        Label currentGold = new Label(""+gsd.getGold());
        currentGold.setFont(Font.font("Verdana",20));
        currentGold.setMinWidth(770);
        currentGold.setAlignment(Pos.CENTER_RIGHT);
        mmgp.add(currentGold, 1, 0, 10,1);
        Image shopBanner = new Image(new FileInputStream(System.getProperty("user.dir") + "\\src\\images\\shopBanner.png"));
        mmgp.add(new ImageView(shopBanner),1,1,10,1);

        Region bigLeftSpacer = new Region();
        bigLeftSpacer.setMinWidth((Screen.getPrimary().getBounds().getWidth()/2) - 400);
        mmgp.add(bigLeftSpacer, 0, 0, 1, 20);

        //Region smallLeftSpacer = new Region();
        //smallLeftSpacer.setMinWidth(10);
        mmgp.add(sc,1,2,1,20);
        mmgp.add(backButton, 1, 0, 1,1);
        sc.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                mmgp.setLayoutY(-new_val.doubleValue());
            }
        });



        int currentItemEra = 1;
        int modifier = 0;
        int column = 2;
        int row = 3;
        for(int i = 0; i < itemsInShop.length; i++){
            if (itemsInShop[i].getEra() > currentItemEra){
                currentItemEra = itemsInShop[i].getEra();
                modifier = modifier + 1;
                column = 2;
                row = row + 2;
                //Label spacer = new Label("");
                //mmgp.add(spacer, column, row, 1, 1);
                //row = row + 1;
            }
            Label itemName = new Label(itemsInShop[i].getName());
            itemName.setFont(Font.font("Verdana",15));
            itemName.setWrapText(true);
            itemName.setMaxWidth(100);
            Image itemIcon = itemsInShop[i].getIcon();
            Button shopItem = new Button(""+itemsInShop[i].getGoldCost());
            shopItem.setFont(Font.font("Verdana",15));
            shopItem.setMinWidth(100);
            shopItem.setMaxWidth(100);
            int finalI = i;
            int finalColumn = column;
            int finalRow = row;
            shopItem.setOnAction(value ->  {
                System.out.println(itemsInShop[finalI].getName());
                int currentPlayerGold = gsd.getGold();
                if(currentPlayerGold >= itemsInShop[finalI].getGoldCost()){
                    try {
                        Image soldOutIcon = new Image(new FileInputStream(System.getProperty("user.dir") + "\\src\\images\\soldOut.png"));
                        mmgp.add(new ImageView(soldOutIcon), finalColumn, finalRow +1,1,1);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    shopItem.setDisable(true);
                    gsd.updateGold(currentPlayerGold-itemsInShop[finalI].getGoldCost());
                    itemsInShop[finalI].setOwned(true);
                    currentGold.setText(""+gsd.getGold());
                }

            });
            shopItem.hoverProperty().addListener((event)->
                    desc.setText(itemsInShop[finalI].getDesc())
            );

            mmgp.add(itemName,column,row,1,1);
            mmgp.add(new ImageView(itemIcon),column,row+1,1,1);
            mmgp.add(shopItem, column, row+2, 1, 1);


            column = column + 1;
            if(column > 5){
                column = 2;
                row = row + 3;
            }
        }


        return new Scene(mmgp);
    }

}
