package sample;

import javafx.scene.image.Image;

public enum Tiles {

    multiGreen("multi_green.png", 0, null),
    multiOrange("multi_orange.png", 0, null),
    multiRed("multi_red.png", 0, null),
    multiCyan("multi_blue.png", 0, null),
    orange("basic_orange.png", 5, multiOrange),
    cyan("basic_cyan.png", 5, multiCyan),
    green("basic_green.png", 5, multiGreen),
    red("basic_red.png", 5, multiRed),
    power("golden_power.png", 3, null),
    accelerate("accelerate.png", 3, null),
    decelerate("decelerate.png", 2, null),
    blank("basic_blank.png", 0, null),
    shadow("shadow.png", 0, null),
    placeHolder("placeholder.png", 0, null),
    extraMove("plus_move.png", 3, null);


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
