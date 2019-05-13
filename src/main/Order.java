package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class Order {
    
    private int orderID;
    private Store store;
    private HashMap<Item, Integer> items;
    private double totalPrice;
    private int avgTime;
    private double taxRate;

    //Default Constructor.

   public Order() {
        orderID = 0 ; 
        items = new HashMap<>();
        totalPrice = 0;
        avgTime = 0;
        taxRate = 0.14;
   }

    public Order(int orderID, Store store, double totalPrice, int avgTime) {
        this.orderID = orderID;
        this.store = store;
        this.items = new HashMap<>();
        this.totalPrice = totalPrice;
        this.avgTime = avgTime;
        this.taxRate = 0.14;
    }
    
    
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
    
   public void removeItem(Item item){
       items.remove(item);
       calculateTotal();
   }
   
    public void setStore(Store store) {
        this.store = store;
    }
    public void setAvgTime(int avgTime) {
        this.avgTime = avgTime;
    }
  
    //Getters.
    public Store getStore() {
        return store;
    }
     public int getOrderID() {
        return orderID;
    }
    public HashMap<Item, Integer> getItems(){
        return items;
    }
  
    public double getTaxes() {
        return totalPrice * this.taxRate;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    
    public void calculateTotal() {
        totalPrice = 0;
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            totalPrice += (entry.getKey().getPrice() * entry.getValue()) + (totalPrice * this.taxRate);
        }
    }
    
    public void addItem(Item item, int quantity){
        items.put(item, quantity);
        calculateTotal();
     }
    public int getAvgTime() {
        return avgTime;
    }

    //Function to Print Receipt.
    public int checkOut(){
        Random r = new Random();
        int receiptNo =  r.nextInt(100);
        return receiptNo;
    }
    public void insertOrder(Order order) {
        Random r = new Random();
        int orderNumber =  r.nextInt(100);
        Connection connect;
        try {
            connect = DriverManager.getConnection(Main.DB_URL, Main.DB_USERNAME, Main.DB_PASSWORD);
            Statement st = connect.createStatement();
            String sql = "INSERT INTO zORDER (Order_ID,Total_Coast) VALUES (" + orderNumber + ", " + totalPrice + ")";
            st.executeUpdate(sql);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
    }        
    
    @Override
    public String toString() {
        return "Order{ " + "OrderID = " + orderID + ", \nStore = " + store + ", \nItems = " + items + ", \nTotalPrice = " + totalPrice + ",\nAvgTime = " + avgTime + '}';
    }

    
}

