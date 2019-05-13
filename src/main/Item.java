package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Item {

    private int id;
    private String name;
    private double price;
    private int storeId;
    private int quantity;
    private String description;
    private String category;

    public Item(int id, String name, double price, int storeId, int quan, String description, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.storeId = storeId;
        this.quantity = quan;
        this.description = description;
        this.category = category;
        insertItem(this);
    }

    public Item() {
    }

    public int getID() {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    
    
    
    void insertItem(Item item) {
       
        try {
            Connection conn = DriverManager.getConnection(Main.DB_URL, Main.DB_USERNAME, Main.DB_PASSWORD);
            Statement st = conn.createStatement();
            String sql = "INSERT INTO ITEM (ID,NAME,PRICE,STOREID,QUANTITY,DESCRIPTION,CATEGORY) VALUES (" + item.id + ",'" + item.name + "'," + item.price + "," + item.storeId + "," + item.quantity + ", '" + item.description + "', '" + item.category + "')";
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void updateItem(Item item, String updateType) {
       
        String sql;
        updateType = updateType.toLowerCase();
        try {
Connection conn = DriverManager.getConnection("jdbc:sqlserver://smart-mall-map.database.windows.net:1433;database=smart-mall-map;user=main@smart-mall-map;password=SE1Group9;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;",
                        "main@smart-mall-map", "SE1Group9");
Statement st = conn.createStatement();
            if (updateType.equals("id")) {
                sql = "UPDATE ITEM SET ID =" + item.id + "WHERE NAME = " + item.name + "";
            } else if (updateType.equals("name")) {
                sql = "UPDATE ITEM SET NAME =" + item.name + "WHERE ID = " + item.id + "";
            } else if (updateType.equals("price")) {
                sql = "UPDATE ITEM SET PRICE =" + item.price + "WHERE ID = " + item.id + "";
            } else if (updateType.equals("storeid")) {
                sql = "UPDATE ITEM SET STOREID =" + item.storeId + "WHERE ID = " + item.id + "";
            } else if (updateType.equals("quantity")) {
                sql = "UPDATE ITEM SET QUANTITY =" + item.quantity + "WHERE ID = " + item.id + "";
            } else if (updateType.equals("description")) {
                sql = "UPDATE ITEM SET DESCRIPTION =" + item.quantity + "WHERE ID = " + item.id + "";
            } else if (updateType.equals("category")) {
                sql = "UPDATE ITEM SET DESCRIPTION =" + item.category + "WHERE ID = " + item.id + "";
            } else {
                return;
            }

            st.executeUpdate(sql);
            st.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void deleteItem(int id) {

       
        String sql;
        try {
Connection conn = DriverManager.getConnection("jdbc:sqlserver://smart-mall-map.database.windows.net:1433;database=smart-mall-map;user=main@smart-mall-map;password=SE1Group9;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;",
                        "main@smart-mall-map", "SE1Group9");
Statement st = conn.createStatement();
            sql = "DELETE FROM ITEM WHERE ID=" + id + "";
            st.executeUpdate(sql);
            st.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return  "\nItem Name: "+ name +"\n\n"+"Price: "+ price + "\n\n"+ "Description: "+description + "\n\n"+"Category: "+category ;
    }

    public ArrayList<Item> displayAllItems() {

       
        ArrayList<Item> items = new ArrayList<>();
        try {
Connection conn = DriverManager.getConnection("jdbc:sqlserver://smart-mall-map.database.windows.net:1433;database=smart-mall-map;user=main@smart-mall-map;password=SE1Group9;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;",    "main@smart-mall-map", "SE1Group9");
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM ITEM";
            ResultSet result = null;
            result = st.executeQuery(sql);
            while (result.next()) {

                Item item = new Item();
                // message += result.getString("NAME") + " \t" + result.getDouble("PRICE") + "\t " + result.getString("DESCRIPTION") + " \n";
                item.id = result.getInt("ID");
                item.name = result.getString("NAME");
                item.storeId = result.getInt("STOREID");
                item.quantity = result.getInt("QUANTITY");
                item.price = result.getDouble("PRICE");
                item.description = result.getString("DESCRIPTION");
                item.category = result.getString("CATEGORY");

                items.add(item);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return items;
    }

         public ArrayList<Item> displayItems(Store store){
    ArrayList<Item> items = new ArrayList<>();
        try {
Connection conn = DriverManager.getConnection("jdbc:sqlserver://smart-mall-map.database.windows.net:1433;database=smart-mall-map;user=main@smart-mall-map;password=SE1Group9;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;",    "main@smart-mall-map", "SE1Group9");
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM ITEM WHERE ITEM.STOREID = '"+store.getId()+"'";
            ResultSet result = null;
            result = st.executeQuery(sql);
            while (result.next()) {

                Item item = new Item();
                // message += result.getString("NAME") + " \t" + result.getDouble("PRICE") + "\t " + result.getString("DESCRIPTION") + " \n";
                item.setId(result.getInt("ID"));
                item.setName(result.getString("NAME"));
                item.setStoreId(result.getInt("STOREID"));
                item.setQuantity(result.getInt("QUANTITY"));
                item.setPrice(result.getDouble("PRICE"));
                item.setDescription(result.getString("DESCRIPTION"));
                item.setCategory(result.getString("CATEGORY"));

                items.add(item);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return items;
    }
}
