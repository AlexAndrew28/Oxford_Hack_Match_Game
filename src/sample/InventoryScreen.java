package sample;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class InventoryScreen {
    private int era;
    private Items items;
    private Item[] valueOfInvSlots = new Item[8];
    private Stage primaryStage;
    private Main main;
    private GlobalSettingsData gsd;

    public InventoryScreen(Items items, Stage primaryStage, Main main, GlobalSettingsData gsd){
        this.items = items;
        this.primaryStage = primaryStage;
        this.main = main;
        this.gsd = gsd;
    }

    public Scene generateScene() throws FileNotFoundException {
        era = gsd.getEra();

        primaryStage.setMaximized(true);

        Item[] ownedItems = items.getOwnedItems(era);
        GridPane mmgp = new GridPane();

        mmgp.setVgap(10);
        mmgp.setHgap(10);

        AtomicInteger currentInvSlot = new AtomicInteger(0);
        mmgp.setMinWidth(Screen.getPrimary().getBounds().getWidth());
        mmgp.setMinHeight(Screen.getPrimary().getBounds().getHeight());
        Button[] invSlots = new Button[8];

        Rectangle backgroundRec = new Rectangle();
        backgroundRec.setX(0);
        backgroundRec.setY(0);
        backgroundRec.setWidth(800);
        backgroundRec.setHeight(Screen.getPrimary().getBounds().getHeight()*2);
        backgroundRec.setFill(Color.SANDYBROWN);

        backgroundRec.setArcWidth(20);
        backgroundRec.setArcHeight(20);

        Rectangle finalBackground = new Rectangle();
        finalBackground.setX(0);
        finalBackground.setY(0);
        finalBackground.setWidth(Screen.getPrimary().getBounds().getWidth());
        finalBackground.setHeight(Screen.getPrimary().getBounds().getHeight());
        finalBackground.setFill(Color.SADDLEBROWN);

        mmgp.add(new Group(finalBackground), 0, 0, 20,15);

        mmgp.add(new Group(backgroundRec),1,0,10,20);

        Region bigLeftSpacer = new Region();
        bigLeftSpacer.setMinWidth((Screen.getPrimary().getBounds().getWidth()/2) - 400);
        mmgp.add(bigLeftSpacer, 0, 0, 1, 1);
        /*
        Region spacer = new Region();
        spacer.setMinWidth(20);
        mmgp.add(spacer, 5,0,1,10);

         */

        Image banner = new Image(new FileInputStream(System.getProperty("user.dir") + "\\src\\images\\inventoryBanner.png"));

        mmgp.add(new ImageView(banner), 1, 0, 10, 1);

        Label description = new Label("");
        description.setMinWidth(400);
        description.setMaxWidth(400);
        description.setMinHeight(50);
        description.setWrapText(true);
        description.setFont(Font.font("Verdana",20));
        description.setAlignment(Pos.CENTER);
        mmgp.add(description, 1, 4, 4, 1);

        Image icon = new Image(new FileInputStream(System.getProperty("user.dir") + "\\src\\images\\blank.png"));

        ImageView iconHolder = new ImageView(icon);

        mmgp.add(iconHolder,7,2,1,1);

        Button backButton = new Button("Return to main menu");

        backButton.setOnAction(value ->  {
            main.loadCampaignMap();
        });

        mmgp.add(backButton, 7, 1, 1, 1);



        for(int i = 0; i < 8; i++){
            invSlots[i] = new Button("Open Slot");
            invSlots[i].setMinWidth(100);
            invSlots[i].setMaxWidth(100);
            int finalI = i;
            invSlots[i].setOnAction(value ->  {
                currentInvSlot.set(finalI);
                invSlots[finalI].setText("Open Slot");
                if (valueOfInvSlots[finalI] != null){
                    valueOfInvSlots[finalI].updateEquipped(false);
                }
                valueOfInvSlots[finalI] = null;
            });
            int column = i % 4 + 1;
            int row = (i/4) + 1;
            mmgp.add(invSlots[i], column, row, 1, 1);
        }



        for(int i = 0; i < ownedItems.length; i++){
            Button newButton = new Button(ownedItems[i].getName());
            newButton.setWrapText(true);
            newButton.setMaxWidth(100);
            newButton.setMinWidth(100);
            int finalI = i;
            newButton.hoverProperty().addListener((event)->
                    description.setText(ownedItems[finalI].getDesc())
            );
            AtomicReference<Image> image = new AtomicReference<>();
            newButton.hoverProperty().addListener((event) ->
                    image.set(ownedItems[finalI].getIcon())
            );
            newButton.hoverProperty().addListener((event) ->
                    iconHolder.setImage(image.get())
            );

            newButton.setOnAction(value ->  {
                System.out.println("button - " + ownedItems[finalI].getName());
                if(!ownedItems[finalI].getEquipped() && currentInvSlot.get() < 8){
                    invSlots[currentInvSlot.get()].setText(ownedItems[finalI].getName());
                    ownedItems[finalI].updateEquipped(true);
                    valueOfInvSlots[currentInvSlot.get()] = ownedItems[finalI];
                    currentInvSlot.set(currentInvSlot.get() + 1);
                }
            });
            int column = i % 4 + 1;
            int row = (i/4) + 5;
            mmgp.add(newButton, column, row, 1, 1);
        }

        Item[] slots = getValueOfInvSlots();

        for(int i = 0; i < slots.length; i++){
            if(slots[i] != null){
                invSlots[i].setText(slots[i].getName());
            }
        }

        return new Scene(mmgp);
    }

    public Item[] getValueOfInvSlots() {
        return valueOfInvSlots;
    }
}
