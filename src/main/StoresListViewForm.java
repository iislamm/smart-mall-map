package main;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;




public class StoresListViewForm {
  
     public static class StoreList extends BorderPane {
        public static Store choosedStore = null ;
        private Item i = new Item();
        private Label content = new Label();
        public  Button viewShopBtn = new Button();
        private Image imag;
        private ImageView view;

        public StoreList() {
        }
       
        
        StoreList(Store item, BorderPane storePane) {
            super();
            imag =  new Image(getClass().getResourceAsStream("..\\Project Images\\"+item.getId()+".png"));
            view = new ImageView(imag);
            content.setText(item.toString());
            viewShopBtn.setText("View " + item.getName());
            content.setStyle("-fx-font-size:15; -fx-font-family: \"Segoe UI\"; -fx-text-fill:black;");
            content.setWrapText(true);
            content.setMinHeight(210);
            content.setMaxWidth(700);
            viewShopBtn.setStyle("-fx-background-radius:5; -fx-background-color: #9A2C72; -fx-text-fill: white;    -fx-font-weight: bold; -fx-font-size:15;");
            view.setFitWidth(100);
            view.setFitHeight(100);
            this.setLeft(view);
            this.getLeft().setTranslateY(50);
            this.setCenter(content);
            this.setRight(viewShopBtn);
            this.getRight().setTranslateY(60); 
//            this.setStyle("-fx-background-color:#E8DDCB;");
            this.setMinWidth(USE_PREF_SIZE);
            
            viewShopBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                 Stage newStage = new Stage();
                    try {
                        choosedStore = item;
                        Pane root = FXMLLoader.load(getClass().getResource("StoreItems.fxml"));
                        storePane.setCenter(root);
                        storePane.setLeft(null);
                    } catch (IOException ex) {
                        Logger.getLogger(StoresListViewForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }       
       
    }

}
