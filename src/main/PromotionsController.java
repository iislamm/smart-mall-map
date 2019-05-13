package main;

import java.io.IOException;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;


public class PromotionsController implements Initializable {
   
    @FXML
    private ScrollPane containerPane;
    
    private GridPane allItemsGrid;
    
    private GridPane itemPane;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        allItemsGrid = new GridPane();
        allItemsGrid.maxHeight(Double.POSITIVE_INFINITY);
        allItemsGrid.maxWidth(Double.POSITIVE_INFINITY);
        
        allItemsGrid.setVgap(20);
        allItemsGrid.setHgap(20);
        
        try {
            getPromotions();
        } catch (IOException ex) {
            Logger.getLogger(PromotionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getPromotions() throws IOException {
        int ID;
        double old_price  , new_price;
        String name , image_name , level , store_name;
        DecimalFormat df2 = new DecimalFormat("0.00");
        int storeId;
        
        try {
            Connection conn = DriverManager.getConnection(Main.DB_URL, 
                    Main.DB_USERNAME, Main.DB_PASSWORD);
            Statement st = conn.createStatement();
            
            String sql = "SELECT Item_Promotion.Item_ID\n" +
"	  ,Item.PRICE\n" +
"	  ,Item.NAME AS 'Item_Name'\n" +
"      ,Item_Promotion.Discount\n" +
"      ,Item_Promotion.Quantity\n" +
"      ,Item_Promotion.New_Price\n" +
"      ,Item_Promotion.Image_Name\n" +
"	  ,Store.ID as 'Store_Id'\n" +
"	  ,Store.NAME AS 'Store_Name'\n" +
"	  ,Store.Location AS 'level'\n" +
"  FROM Item_Promotion, Item, Store\n" +
"  WHERE Item_Promotion.Item_ID = Item.ID AND ITEM.STOREID = Store.ID;";
            
            ResultSet rs = null;
            
            rs = st.executeQuery(sql);
            
            int counter = 0;
            int row = 0;
            while(rs.next()) {
                itemPane = new GridPane();
                itemPane.setVgap(10);
                itemPane.setHgap(10);
                ID = rs.getInt("Item_ID");
                df2.setRoundingMode(RoundingMode.DOWN);
                old_price = rs.getFloat("PRICE");
                
                old_price = Double.parseDouble(df2.format(old_price));
                new_price = old_price - rs.getFloat("Discount")*old_price;
                new_price = Double.parseDouble(df2.format(new_price));
                name = rs.getString("Item_Name");
                if(name.length()>25)
                    name = name.substring(0, 25);
                storeId = rs.getInt("Store_Id");
                image_name = rs.getString("Image_Name");
                level = rs.getString("level");
                store_name = rs.getString("Store_Name");
                Image image = new Image(getClass().getResourceAsStream("../Project Images/"+image_name+".jpg"));
                ImageView imageView = new ImageView();
                
                imageView.setFitWidth(420);
                imageView.setFitHeight(250);
//                imageView.maxHeight(250);
//                imageView.maxWidth(Double.MAX_VALUE);
                imageView.setImage(image);


                itemPane.add(imageView, 0, 0);

                GridPane descriptionPane = new GridPane();
                Label oldPriceLabel = new Label("Old Price:");
                Label oldPriceValue = new Label(Double.toString(old_price));
                
                Label nameLabel = new Label(name);
                oldPriceValue.setOpacity(0.5);
                nameLabel.setPadding(new Insets(0,0,0,100));
                //descriptionPane.setAlignment(Pos.CENTER);
                descriptionPane.add(nameLabel, 1, 0);
                double x = oldPriceValue.getLayoutX();
                Separator s = new Separator();
                s.setLayoutX(x);
                oldPriceValue.setPadding(new Insets(0,0,0,20));
                s.setPadding(new Insets(0,0,0,20));
                //descriptionPane.setAlignment(Pos.BASELINE_LEFT);
                //descriptionPane.add(oldPriceLabel, 1, 1);
                descriptionPane.add(oldPriceValue, 0, 1);
                descriptionPane.add(s, 0, 1);

                Label newPriceLabel = new Label("New Price:");
                Label newPriceValue = new Label(Double.toString(new_price));
                newPriceValue.setPadding(new Insets(0,0,0,20));
                Label storeNameLabel = new Label("Store Name: ");
                Label storeNameValue = new Label(store_name);
                storeNameValue.setPadding(new Insets(0,0,0,120));
                //descriptionPane.add(newPriceLabel, 1, 2);
                descriptionPane.add(storeNameValue, 1, 2);
                
                
                
                //descriptionPane.add(storeNameLabel, 3, 1);
                descriptionPane.add(newPriceValue, 2, 1);
                
                Label levelNameLabel = new Label("Level: ");
                Label levelNameValue = new Label(level);
                
                /*descriptionPane.add(levelNameLabel, 3, 1);
                descriptionPane.add(levelNameValue, 5, 1);*/
                
                itemPane.add(descriptionPane, 0, 1);
                
                if (counter % 3 == 0 && counter != 0) {
                    row++;
                }
                
                allItemsGrid.add(itemPane, counter % 3, row);
                counter++;

                containerPane.setContent(allItemsGrid);
            }
            
            st.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(PromotionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ArrayList<Promotion> promotions = Promotion.getAllPromotions();
    }
    
    
}