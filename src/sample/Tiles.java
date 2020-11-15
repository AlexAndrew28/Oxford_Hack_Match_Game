package sample;

import javafx.scene.image.Image;

public enum Tiles {

    multiGreen("multi_green.png", 0, null),
    multiOrange("multi_orange.png", 0, null),
    multiRed("multi_red.png", 0, null),
    multiCyan("multi_blue.png", 0, null),
    orange("basic_orange.png", GameStats.stdWeight, multiOrange),
    cyan("basic_cyan.png", GameStats.stdWeight, multiCyan),
    green("basic_green.png", GameStats.stdWeight, multiGreen),
    red("basic_red.png", GameStats.stdWeight, multiRed),
    power("golden_power.png", GameStats.medWeight, null),
    accelerate("accelerate.png", GameStats.medWeight, null),
    decelerate("decelerate.png", GameStats.lowWeight, null),
    blank("basic_blank.png", 0, null),
    shadow("shadow.png", 0, null),
    placeHolder("placeholder.png", 0, null),
    extraMove("plus_move.png", GameStats.medWeight, null);


    private Image icon;
    private int weight;
    private Tiles multi;

    Tiles(String imgFile, int weight, Tiles multi) {
        icon = new Image("file:" + System.getProperty("user.dir") + "\\src\\images\\" + imgFile);
        this.weight = weight;
        this.multi = multi;
    }

    public Image getIcon() {
        return icon;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Tiles getMulti() {
        return multi;
    }

    public static Tiles basic(Tiles tile) {
        switch (tile) {
            case multiGreen:
                return green;
            case multiRed:
                return red;
            case multiCyan:
                return cyan;
            case multiOrange:
                return orange;
            default:
                return tile;
        }
    }

}
