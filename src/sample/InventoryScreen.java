package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicInteger;

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

    public Scene generateScene(){



        era = 1;
        primaryStage.setMaximized(true);
        Item[] ownedItems = items.getOwnedItems(era);
        GridPane mmgp = new GridPane();
        mmgp.setHgap(10);
        mmgp.setVgap(10);
        AtomicInteger currentInvSlot = new AtomicInteger(0);

        Button[] invSlots = new Button[8];


        Label description = new Label("");
        description.setMinWidth(300);
        description.setMaxWidth(300);
        mmgp.add(description, 6, 0, 1, 5);

        Button backButton = new Button("Return to main menu");

        backButton.setOnAction(value ->  {
            main.loadCampaignMap();
        });

        mmgp.add(backButton, 7, 0, 1, 1);



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
            int column = i % 4;
            int row = (i/4);
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
            newButton.setOnAction(value ->  {
                System.out.println("button - " + ownedItems[finalI].getName());
                if(!ownedItems[finalI].getEquipped() && currentInvSlot.get() < 8){
                    invSlots[currentInvSlot.get()].setText(ownedItems[finalI].getName());
                    ownedItems[finalI].updateEquipped(true);
                    valueOfInvSlots[currentInvSlot.get()] = ownedItems[finalI];
                    currentInvSlot.set(currentInvSlot.get() + 1);
                }
            });
            int column = i % 4;
            int row = (i/4) + 3;
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
