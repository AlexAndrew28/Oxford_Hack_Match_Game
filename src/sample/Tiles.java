package sample;

import javafx.scene.image.Image;

public enum Tiles {

    blue("basic_orange.png", 5),
    cyan("basic_cyan.png", 5),
    green("basic_green.png", 5),
    red("basic_red.png", 5),
    power("golden_power.png", 3),
    accelerate("accelerate.png", 3),
    decelerate("decelerate.png", 2),
    blank("basic_blank.png", 0),
    shadow("shadow.png", 0),
    placeHolder("placeholder.png", 0);

    private Image icon;
    private int weight;

    Tiles(String imgFile, int weight) {
        icon = new Image("file:" + System.getProperty("user.dir") + "\\src\\images\\" + imgFile);
        this.weight = weight;
    }

    public Image getIcon() {
        return icon;
    }

    public int getWeight() {
        return weight;
    }

}
