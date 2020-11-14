package sample;

import javafx.scene.image.Image;

public enum Tiles {
    red("test_piece.png"),
    green("test_piece.png"),
    blue("test_piece.png"),
    yellow("test_piece.png"),
    pink("test_piece.png");

    private Image icon;

    Tiles(String imgFile) {
        icon = new Image(imgFile);
    }

    public Image getIcon() {
        return icon;
    }

}
