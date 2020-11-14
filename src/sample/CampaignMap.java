package sample;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class CampaignMap {



        public Scene setTheScene(){


            /*Image imageDecline = new Image(getClass().getResourceAsStream("not.png"));
            Button button5 = new Button();
            button5.setGraphic(new ImageView(imageDecline));

             */

            GridPane mmgp = new GridPane();
            Button button_portal = new Button("Portal Level"); // new button
            Button button_non = new Button("Non-Portal Level");
            Button button_shop = new Button("Enter the Shop!");
            Button button_endless = new Button("Endless Mode");


            mmgp.add(button_portal, 0, 0, 1, 1); //[ i:  ][ i1:  ][ i2:  ][ i3:  ]
            mmgp.add(button_non, 0, 0, 1, 1);
            mmgp.add(button_shop, 0, 0, 1, 1);
            mmgp.add(button_endless, 0, 0, 1, 1);

            Scene map = new Scene(mmgp, 200, 100);


            return map;
        }


}
