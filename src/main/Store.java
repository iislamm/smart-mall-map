package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class Store {
    private int id ;
    private String name;
    private String category;
    private double rate;
    private int priceRange;
    private String location;

    public Store() {
    }
    
    

    public Store(int id, String name, String category, double rate, int priceRange,String loc) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.rate = rate;
        this.priceRange = priceRange;
        this.location = loc;
        
        HashMap<String, String> map = new HashMap<String, String>();
        map.keySet();
    }

    @Override
    public String toString() {
        return  "\nName: " + name + "\n\nCategory: " + category + "\n\nRate: " + rate + "/5.0\n\nPriceRange:" + priceRange + "/3";
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(int priceRange) {
        this.priceRange = priceRange;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public static Store search(String storeName){
        try {
            
            Connection conn = DriverManager.getConnection(Main.DB_URL,
                        Main.DB_USERNAME, Main.DB_PASSWORD);
            Statement st = conn.createStatement();
            
            String sql = "SELECT * FROM Store WHERE name = '" + storeName + "'";
            ResultSet result = null;
            result = st.executeQuery(sql);
            
            result.next();
            
            int id = result.getInt("ID");
            String name = result.getString("Name");
            String category = result.getString("Category");
            double rate = result.getFloat("Rate");
            double priceRange = result.getInt("Price_Rate");
            String location = result.getString("Location");
            Store resultStore = new Store(id, name, category, rate, id, category);
            
            st.close();
            conn.close();
            
            return resultStore;
            
        } catch (SQLException ex) {
            System.err.println(ex);
            return null;
        }
        catch (Exception ex) {
            System.err.println(ex);
            return null;
        }
    }
       
    public static ArrayList<Store> getStoresCategory(String category){
    ArrayList <Store> requestedStores = new ArrayList<>();
      try {
Connection conn = DriverManager.getConnection("jdbc:sqlserver://smart-mall-map.database.windows.net:1433;database=smart-mall-map;user=main@smart-mall-map;password=SE1Group9;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;",    "main@smart-mall-map", "SE1Group9");
            Statement st = conn.createStatement();
            String sql= "";
            if(category.equals("Coffee Shop") || category.equals("Restaurant")||category.equals("Fast food"))
            sql = "SELECT * FROM Store WHERE Category = '"+category+"' ";
            else
               sql = "SELECT * FROM Store WHERE Category != 'Coffee Shop' and Category !='Restaurant' and Category !='Fast food' and Category != 'Furniture' and Category !='Bank'"; 
            ResultSet result = null;
            result = st.executeQuery(sql);
            while (result.next()) {
                Store s  = new Store(result.getInt("ID"),result.getString("Name"),result.getString("Category"),result.getDouble("Rate"),result.getInt("Price_Rate"),result.getString("Location"));
                requestedStores.add(s);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    return requestedStores;
    }   
       
    
    
    
    
    
    
}
