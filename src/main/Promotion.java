package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Promotion {
    private int item_ID;
    private Item item;
    private int quantity;
    private double discount_percentage;
    private double new_price;
    private String image_name;
    private int storeId;

    public Promotion(int ID, Item item, int quantity, double discount_percentage, double new_price, String image_name, int storeId) {
        this.item_ID = ID;
        this.item = item;
        this.quantity = quantity;
        this.discount_percentage = discount_percentage;
        this.new_price = new_price;
        this.image_name = image_name;
        this.storeId = storeId;
    }

    public void setItem_ID(int item_ID) {
        this.item_ID = item_ID;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getItem_ID() {
        return item_ID;
    }

    public int getStoreId() {
        return storeId;
    }    

    public Promotion() {
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }
    
    
    public int getID() {
        return item_ID;
    }

    public void setID(int ID) {
        this.item_ID = ID;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDiscount_percentage() {
        return discount_percentage;
    }

    public void setDiscount_percentage(double discount_percentage) {
        this.discount_percentage = discount_percentage;
    }

    public double getNew_price() {
        return new_price;
    }

    public void setNew_price(double new_price) {
        this.new_price = new_price;
    }
    
    public static ArrayList<Promotion> getAllPromotions(){
        ArrayList<Promotion> promotions = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(Main.DB_URL, Main.DB_USERNAME, Main.DB_PASSWORD);
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM Item_Promotion";
            
            ResultSet results =null;
            results =st.executeQuery(sql);
            Promotion promotion = new Promotion();
            while(results.next()){
                int id = results.getInt("Item_ID");
                double discount = results.getDouble("Discount");
                int quantity = results.getInt("Quantity");
                double newPrice = results.getDouble("New_Price");
                String image_name = results.getString("Image_Name");
                int storeId = results.getInt("store_id");
                
                promotion.setID(id);
                promotion.setDiscount_percentage(discount);
                promotion.setQuantity(quantity);
                promotion.setNew_price(newPrice);
                promotion.setImage_name(image_name);
                promotion.setStoreId(storeId);
                
                promotions.add(promotion);
            }
            
            results.close();
            st.close();
            conn.close(); 
            
            return promotions;
            
        } catch (SQLException ex) {
            System.err.println(ex);
            return null;
        }    
    }
    
    @Override
    public String toString() {
        return "Promotion{" + "ID=" + item_ID +  ", quantity=" + quantity + ", discount_percentage=" + discount_percentage + ", new_price=" + new_price + '}' ;
    }
}
