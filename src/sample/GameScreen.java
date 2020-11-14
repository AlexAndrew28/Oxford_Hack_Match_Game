package sample;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameScreen {

    Scene gameScene;

    public GameScreen() {
        TileArea tiles = new TileArea();
        Pane testPane2 = new Pane();
        testPane2.setStyle("-fx-border-color: black");

        HBox outerLayout = new HBox(10);
        outerLayout.setPadding(new Insets(10));
        outerLayout.getChildren().add(tiles);
        outerLayout.getChildren().add(testPane2);
        outerLayout.getChildren().add(new Rectangle(10, 10, 64, 64));
        gameScene = new Scene(outerLayout, 1000, 5000);

    }

    public Scene getScene() {
        return gameScene;
    }

    private class TileArea extends Pane {

        private int tileSize = 64;

        public TileArea() {
            SwapLevel levelData = new SwapLevel(1, 100);

            setPrefSize(tileSize*8, tileSize*8);
            setStyle("-fx-border-color: black");
            Canvas canvas = new Canvas(tileSize*8, tileSize*8);
            GraphicsContext gc = canvas.getGraphicsContext2D();

            Tiles[][] tileGrid = levelData.getTileGrid();

            gc.drawImage(new Image("file:test_piece.png"), 10, 10);

            for (int i = 0; i < tileGrid.length; i++) {
                for (int j = 0; j < tileGrid.length; j++) {
//                    gc.drawImage(tileGrid[i][j].getIcon(), i * tileSize, j * tileSize);
                }
            }
//            getChildren().add(new Rectangle(10, 10, 64, 64));
            getChildren().add(canvas);
        }

    }

}
