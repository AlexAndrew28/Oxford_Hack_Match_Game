package sample;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


public class GameScreen {

    Scene gameScene;

    public GameScreen() {
        SwapLevel levelData = new SwapLevel(1, 100);
        TileArea tiles = new TileArea(levelData);
        Pane testPane2 = new Pane();
        testPane2.setStyle("-fx-border-color: black");

        HBox outerLayout = new HBox(10);
        outerLayout.setAlignment(Pos.CENTER);
        outerLayout.setPadding(new Insets(10));
        outerLayout.getChildren().add(tiles);
        outerLayout.getChildren().add(new InfoArea(levelData));
        gameScene = new Scene(outerLayout, 1600, 800);
        gameScene.setFill(Color.gray(0.7));

    }

    public Scene getScene() {
        return gameScene;
    }

    private class InfoArea extends VBox {

        public InfoArea(SwapLevel levelData) {
//            setStyle("-fx-border-color: black");
            setAlignment(Pos.CENTER);

            Canvas stats = new Canvas(400, 64*9-200);
            GraphicsContext gc = stats.getGraphicsContext2D();
            gc.setFont(Font.font(20));

            int rowHeight = 30;
            int buffer = 10;
            gc.setFill(Color.rgb(210, 210, 255));
            gc.fillRoundRect(0,0, stats.getWidth(), stats.getHeight() + 100, 20, 20);
            gc.setFill(Color.gray(0));
            gc.fillText("Level:", buffer, rowHeight);
            gc.fillText(Integer.toString(levelData.getLevel()), stats.getWidth()/2, rowHeight);
            gc.fillText("Acceleration", buffer, rowHeight*2);
            gc.fillText(Integer.toString(levelData.getAcceleration()), stats.getWidth()/2, rowHeight*2);
            gc.fillText("Power:", buffer, rowHeight*3);
            gc.fillText(Integer.toString(levelData.getPower()), stats.getWidth()/2, rowHeight*3);

            gc.setFill(Color.rgb(51,51,255));
            gc.setLineWidth(2);
            for (int i = 0; i < 3; i++) {
                gc.strokeLine(buffer, rowHeight*(i+1)+5, stats.getWidth() - buffer, rowHeight*(i+1)+5);
            }

            gc.setTextAlign(TextAlignment.CENTER);
            gc.fillText("Points", stats.getWidth()/2, 140);
            int barHeight = 64;
            int fillAmount = (int) (stats.getWidth() - (buffer+2)*2) * levelData.getPoints() / levelData.getGoal();

            gc.setFill(Color.rgb(0,0,0));
            gc.fillRoundRect(buffer, 150, stats.getWidth() - buffer*2, barHeight, barHeight/2, barHeight/2);
            gc.setFill(Color.rgb(210,210,255));
            gc.fillRoundRect(buffer+2, 150+2, stats.getWidth() - (buffer+2)*2, barHeight-4, (barHeight-4)/2, (barHeight-4)/2);
            gc.setFill(Color.rgb(51,51,255));
            gc.fillRoundRect(buffer+2, 150+2, fillAmount, barHeight-4, (barHeight-4)/2, (barHeight-4)/2);

            gc.setTextBaseline(VPos.CENTER);
            gc.setFill(Color.rgb(0,0,0));
            gc.fillText(Integer.toString(levelData.getPoints()) + "/" + Integer.toString(levelData.getGoal()), stats.getWidth()/2, 150 + barHeight/2);

            getChildren().add(stats);
            Canvas items = new Canvas(400, 200);
            int itemGap = 2;
            gc = items.getGraphicsContext2D();
            gc.setFill(Color.rgb(210, 210, 255));
            gc.fillRoundRect(0,-100, items.getWidth(), items.getHeight() + 100, 20, 20);

            gc.setFill(Color.rgb(69, 69, 111));
            int step = (400-buffer*2)/4;
            for (int i = 0; i < 8; i++) {
                gc.fillOval(buffer + (i%4)*step + itemGap, buffer + (i/4)*step, step - 2*itemGap, step/4);
            }
            getChildren().add(items);
        }

    }

    private class TileArea extends VBox {

        private int tileSize = 64;
        private int buffer =  2;
        private SwapLevel levelData;
        private Tiles dragTile;
        private int dragx;
        private int dragy;
        private int ogdragx;
        private int ogdragy;
        private int offsetx;
        private int offsety;

        public TileArea(SwapLevel levelData) {
            this.levelData = levelData;
            dragTile = null;
            setAlignment(Pos.CENTER);
            setPrefSize(tileSize*8, tileSize*8);
//            setStyle("-fx-border-color: black");
            Canvas canvas = new Canvas(tileSize*8, tileSize*9);

            draw(canvas);

            getChildren().add(canvas);
            canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    if (event.getX() > 0 && event.getX() < tileSize*8 && event.getY() > tileSize && event.getY() < 9*tileSize) {
                        Tiles[][] tileGrid = levelData.getTileGrid();
                        dragTile = tileGrid[(int) event.getX()/tileSize][(int) event.getY()/tileSize - 1];
                        tileGrid[(int) event.getX()/tileSize][(int) event.getY()/tileSize - 1] = Tiles.placeHolder;
                        dragx = (int) event.getX();
                        dragy = (int) event.getY();
                        ogdragx = (int) event.getX()/tileSize;
                        ogdragy = (int) event.getY()/tileSize - 1;
                        offsetx = dragx - ogdragx * tileSize;
                        offsety = dragy - (ogdragy + 1) * tileSize;
                        draw(canvas);
                    }
                    System.out.println("YOU PRESSED?");
                }
            });
            canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    if (dragTile != null) {
                        dragx = (int) event.getX();
                        dragy = (int) event.getY();
                        draw(canvas);
                    }
                    System.out.println("YOU MOVED?");
                }
            });
            canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    if (dragTile != null) {
                        Tiles[][] tileGrid = levelData.getTileGrid();
                        int newx = (int) event.getX()/tileSize;
                        int newy = (int) event.getY()/tileSize - 1;
                        if (newx == ogdragx && newy == ogdragy) {
                            tileGrid[ogdragx][ogdragy] = dragTile;
                        } else if (newx >= 0 && newx < 8 && newy == ogdragy) {
                            if (newx > ogdragx) {
                                for (int i = ogdragx; i < newx; i++) {
                                    tileGrid[i][newy] = tileGrid[i+1][newy];
                                }
                            } else if (newx < ogdragx) {
                                for (int i = ogdragx; i > newx; i--) {
                                    tileGrid[i][newy] = tileGrid[i-1][newy];
                                }
                            }
                            tileGrid[newx][newy] = dragTile;
                        } else if (newy >= 0 && newy < 8 && newx == ogdragx) {
                            if (newy > ogdragy) {
                                for (int i = ogdragy; i < newy; i++) {
                                    tileGrid[newx][i] = tileGrid[newx][i+1];
                                }
                            } else if (newy < ogdragy) {
                                for (int i = ogdragy; i > newy; i--) {
                                    tileGrid[newx][i] = tileGrid[newx][i-1];
                                }
                            }
                            tileGrid[newx][newy] = dragTile;
                        } else {
                            tileGrid[ogdragx][ogdragy] = dragTile;
                        }
                        dragTile = null;
                        draw(canvas);
                    }
                    System.out.println("YOU RELEASED?");
                }
            });
        }



        private void draw(Canvas canvas) {
            GraphicsContext gc = canvas.getGraphicsContext2D();

            gc.clearRect(0,0,canvas.getWidth(), canvas.getHeight());
            Tiles[][] tileGrid = levelData.getTileGrid();
            Tiles[] preview = levelData.getPreviewRow();
            gc.setFill(Color.rgb(168, 168, 191));
            gc.fillRoundRect(0,0,tileSize*8, tileSize, tileSize/2, tileSize/2);
            gc.setFill(Color.rgb(224, 224, 255));
            gc.fillRoundRect(0,tileSize + buffer,tileSize*8, tileSize*8, tileSize/2, tileSize/2);
            for (int i = 0; i < tileGrid.length; i++) {
                gc.drawImage(preview[i].getIcon(), i * tileSize + buffer, buffer, tileSize - buffer*2, tileSize - buffer*2);
                for (int j = 0; j < tileGrid.length; j++) {
                    gc.drawImage(tileGrid[i][j].getIcon(), i * tileSize + buffer, (j+1) * tileSize + buffer*2, tileSize - buffer*2, tileSize - buffer*2);
                }
            }
            // draw tile being dragged if any
            if (dragTile != null) {
                gc.drawImage(dragTile.getIcon(), dragx - offsetx, dragy - offsety, tileSize - buffer*2, tileSize - buffer*2);
            }
        }

    }

}
