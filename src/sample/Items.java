package sample;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class Items {

    private ArrayList<Item> allItems = new ArrayList<Item>();


    public Items() throws FileNotFoundException {

        Image image1 = new Image(new FileInputStream(System.getProperty("user.dir") + "\\src\\images\\stoneNecklace.png"));
        Item item1 = new Item(0, "Stone Necklace", "Decreases the number of deceleration tokens by 50%", 1, true, 250, image1,7);
        allItems.add(item1);

        Image image2 = new Image(new FileInputStream(System.getProperty("user.dir") + "\\src\\images\\basicSpear.png"));
        Item item2 = new Item(1, "Basic Spear", "Consume all of the mechanical tokens on the board", 1, true, 200, image2,5);
        allItems.add(item2);

        Image image3 = new Image(new FileInputStream(System.getProperty("user.dir") + "\\src\\images\\torch.png"));
        Item item3 = new Item(2, "Torch", "Converts up to 3 acceleration tokens to time tokens", 1, true, 150, image3,5);
        allItems.add(item3);

        Image image4 = new Image(new FileInputStream(System.getProperty("user.dir") + "\\src\\images\\leatherCloak.png"));
        Item item4 = new Item(3, "Leather Cloak", "Increase the number of protection tokens by 50%", 1, true, 150, image4,5);
        allItems.add(item4);

        Image image6 = new Image(new FileInputStream(System.getProperty("user.dir") + "\\src\\images\\basicTools.png"));
        Item item6 = new Item(5, "Basic Tools", "Removes a chosen column", 1, false, 100, image6,3);
        allItems.add(item6);

        Image image7 = new Image(new FileInputStream(System.getProperty("user.dir") + "\\src\\images\\mammothTusk.png"));
        Item item7 = new Item(6, "Mammoth Tusk", "Converts up to 5 protection tokens into mechanisation tokens", 1, false, 90, image7,2);
        allItems.add(item7);

        Image image8 = new Image(new FileInputStream(System.getProperty("user.dir") + "\\src\\images\\simpleRaft.png"));
        Item item8 = new Item(7, "Simple Raft", "+1 power for every time token that you match", 1, false, 350, image8,7);
        allItems.add(item8);

        Image image9 = new Image(new FileInputStream(System.getProperty("user.dir") + "\\src\\images\\basicBarkShield.png"));
        Item item9 = new Item(8, "Basic Bark Shield", "The next deceleration match is ignored", 1, false, 200, image9,3);
        allItems.add(item9);

        Image image10 = new Image(new FileInputStream(System.getProperty("user.dir") + "\\src\\images\\wizardsStaff.png"));
        Item item10 = new Item(9, "Wizards Staff", "Increases the number of power tokens by 50%", 2, false, 400, image10,5);
        allItems.add(item10);

        Image image11 = new Image(new FileInputStream(System.getProperty("user.dir") + "\\src\\images\\knightlyArmour.png"));
        Item item11 = new Item(10, "Knightly Armour", "+1 time for every 5 protection tokens on the board", 2, false, 450, image11,6);
        allItems.add(item11);

        Image image12 = new Image(new FileInputStream(System.getProperty("user.dir") + "\\src\\images\\excalibur.png"));
        Item item12 = new Item(11, "Excalibur", "Consume all of the protection tokens on the board", 2, false, 500, image12,5);
        allItems.add(item12);

        Image image13 = new Image(new FileInputStream(System.getProperty("user.dir") + "\\src\\images\\simpleTools.png"));
        Item item13 = new Item(12, "Simple Tools", "Remove a chosen row", 2, false, 150, image13,3);
        allItems.add(item13);

        Image image14 = new Image(new FileInputStream(System.getProperty("user.dir") + "\\src\\images\\seeingStone.png"));
        Item item14 = new Item(13, "Seeing Stone", "Increase the number of acceleration tokens by 50%", 2, false, 500, image14,7);
        allItems.add(item14);

        Image image15 = new Image(new FileInputStream(System.getProperty("user.dir") + "\\src\\images\\lininClothes.png"));
        Item item15 = new Item(14, "Linen Clothes", "Increase the number of ventilation tokens by 50%", 2, false, 300, image15,5);
        allItems.add(item15);

        Image image16 = new Image(new FileInputStream(System.getProperty("user.dir") + "\\src\\images\\sting.png"));
        Item item16 = new Item(15, "Sting", "Consume all ventilation tokens on the board", 2, false, 350, image16,5);
        allItems.add(item16);

        Image image17 = new Image(new FileInputStream(System.getProperty("user.dir") + "\\src\\images\\fireballSpell.png"));
        Item item17 = new Item(16, "Fireball Spell", "Consume all deceleration tokens on the board but for each one consumed gain +1 time", 2, false, 500, image17,3);
        allItems.add(item17);

        Image image18 = new Image(new FileInputStream(System.getProperty("user.dir") + "\\src\\images\\fancyCrown.png"));
        Item item18 = new Item(17, "Fancy Crown", "+1 time for every 5 of a kind match", 2, false, 500, image18,6);
        allItems.add(item18);

        Image image19 = new Image(new FileInputStream(System.getProperty("user.dir") + "\\src\\images\\fancyMetalShield.png"));
        Item item19 = new Item(18, "Fancy Metal Shield", "The next deceleration match is counted as an acceleration", 2, false, 300, image19,4);
        allItems.add(item19);

        Image image20 = new Image(new FileInputStream(System.getProperty("user.dir") + "\\src\\images\\laserSword.png"));
        Item item20 = new Item(19, "Laser Sword", "Consume all of the radiation tokens on the board", 3, false, 400, image20,5);
        allItems.add(item20);

        Image image21 = new Image(new FileInputStream(System.getProperty("user.dir") + "\\src\\images\\taco.png"));
        Item item21 = new Item(20, "A Taco", "Looks really good", 3, false, 800, image21,1);
        allItems.add(item21);

        Image image22 = new Image(new FileInputStream(System.getProperty("user.dir") + "\\src\\images\\sunglasses.png"));
        Item item22 = new Item(21, "Cool Sunglasses", "Makes you look cool while you win", 3, false, 200, image22,1);
        allItems.add(item22);
    }


    public Item[] getOwnedItems(int currentEra){
        ArrayList<Item> eraItems = new ArrayList<Item>();
        for(int i = 0; i < allItems.size(); i++){
            if(allItems.get(i).getEra() <= currentEra && allItems.get(i).checkOwned()){
                eraItems.add(allItems.get(i));
            }
        }
        return eraItems.toArray(new Item[0]);
    }

    public Item[] getUnownedItems(int currentEra){
        ArrayList<Item> eraItems = new ArrayList<Item>();
        for(int i = 0; i < allItems.size(); i++){
            if(allItems.get(i).getEra() <= currentEra && !allItems.get(i).checkOwned()){
                eraItems.add(allItems.get(i));
            }
        }
        return eraItems.toArray(new Item[0]);
    }
}
