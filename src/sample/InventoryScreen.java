package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;

import java.util.concurrent.atomic.AtomicInteger;

public class InventoryScreen {
    private int era;
    private Items items;

    public InventoryScreen(int era, Items items){
        this.era = era;
        this.items = items;
    }

    public Scene generateScene(){
        Item[] ownedItems = items.getOwnedItems(era);
        GridPane mmgp = new GridPane();
        mmgp.setHgap(10);
        mmgp.setVgap(10);
        AtomicInteger currentInvSlot = new AtomicInteger(0);

        Button[] invSlots = new Button[8];
        Item[] valueOfInvSlots = new Item[8];

        Label description = new Label("");
        description.setMinWidth(300);
        description.setMaxWidth(300);
        mmgp.add(description, 6, 0, 1, 5);


        for(int i = 0; i < 8; i++){
            invSlots[i] = new Button("Open Slot");
            invSlots[i].setMinWidth(100);
            invSlots[i].setMaxWidth(100);
            int finalI = i;
            invSlots[i].setOnAction(value ->  {
                currentInvSlot.set(finalI);
                invSlots[finalI].setText("Open Slot");
                System.out.println(valueOfInvSlots[0]);
                valueOfInvSlots[finalI].updateEquipped(false);
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

        return new Scene(mmgp);
    }

}
