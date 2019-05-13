package main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ItemsForm {

    public static class Listitems extends BorderPane {

        private  Item i ;
        private Label content = new Label();
        private Button addbtn = new Button();
        private Button delBtn = new Button();
        private TextField field = new TextField();
        public int counter = 0;
        private StoreItemsController itemsController = new StoreItemsController();

        Listitems(Item item) {
            super();
            content.setText(item.toString());
            field.setText(String.valueOf(counter));
            field.setMaxWidth(50);
            addbtn.setText("+");
            delBtn.setText("-");
            content.setWrapText(true);
            content.setMinHeight(210);
            content.setMaxWidth(700);
            content.setStyle("-fx-font-size:15; -fx-font-family: \"Segoe UI\"; -fx-text-fill:black;");
            addbtn.setStyle("-fx-background-radius:10; -fx-background-color: #9A2C72; -fx-text-fill: white;    -fx-font-weight: bold; -fx-font-size:15;  -fx-font-family: \"Segoe UI\";");
            delBtn.setStyle("-fx-background-radius:10; -fx-background-color: #9A2C72; -fx-text-fill: white;    -fx-font-weight: bold; -fx-font-size:15;");
            HBox hbox = new HBox(addbtn, field, delBtn);
            hbox.setSpacing(40);
            this.setLeft(content);
            this.setRight(hbox);
            addbtn.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) { 
                    i = item;
                        if (counter <= 100 && counter >= 0) {
                        field.setText(String.valueOf(++counter));
                    }
                    itemsController.itemsChoosed(i,counter);
                }
            });

            delBtn.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                    if (counter <= 100 && counter > 0) {
                        field.setText(String.valueOf(--counter));
                    }
                    itemsController.itemsDecremenet(i,counter);
                }
            });
//            this.setStyle("-fx-background-color:#E8DDCB;");
            this.setMinWidth(USE_PREF_SIZE);
        }

    }
}
