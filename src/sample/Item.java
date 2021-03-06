package sample;

import javafx.scene.image.Image;

public class Item {
    private int itemID;
    private String desc;
    private String name;
    private Boolean owned;
    private int era;
    private boolean equipped = false;
    private int goldCost;
    private Image icon;
    private int powerCost;

    public Item(int itemID, String name, String desc, int era, Boolean owned, int goldCost, Image icon, int powerCost){
        this.itemID = itemID;
        this.desc = desc;
        this.name = name;
        this.era = era;
        this.owned = owned;
        this.goldCost = goldCost;
        this.icon = icon;
        this.powerCost = powerCost;
    }

    public void setOwned(Boolean isOwned){
        owned = isOwned;
    }

    public boolean checkOwned(){
        return owned;
    }

    public String getName(){
        return name;
    }

    public int getEra(){
        return era;
    }

    public void updateEquipped(boolean isEquipped){
        equipped = isEquipped;
    }

    public boolean getEquipped(){
        return equipped;
    }

    public String getDesc(){
        return desc;
    }

    public int getGoldCost(){
        return goldCost;
    }

    public Image getIcon(){
        return icon;
    }

    public int getItemID() { return itemID; }

    public int getPowerCost() { return powerCost; }
}
