package main;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchRecord {
    private static int counter = 0;
    
    private String storeName;
    private Date dateTime;

    public SearchRecord(String storeName, Date dateTime) {
        this.storeName = storeName;
        this.dateTime = dateTime;
        counter++;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
    
    public void send() {
        try {
            Connection conn = DriverManager.getConnection(Main.DB_URL,
                        Main.DB_USERNAME, Main.DB_PASSWORD);
            Statement st = conn.createStatement();
            
            java.sql.Timestamp newDateTime = new java.sql.Timestamp(this.dateTime.getTime());
            
            String sql = "INSERT INTO dbo.Search_Record (ID, StoreName, DateTime) VALUES (" + counter + ", '" + this.storeName + "', '" + newDateTime + "')";
            
            st.executeUpdate(sql);
            st.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(SearchRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ArrayList<SearchRecord> getAll() {
        ArrayList<SearchRecord> results = new ArrayList<>();
        String storeName;
        Date dateTime;
        
        try {
            
            Connection conn = DriverManager.getConnection(Main.DB_URL,
                    Main.DB_USERNAME, Main.DB_PASSWORD);
            Statement st = conn.createStatement();
            
            String sql = "SELECT StoreName, DateTime FROM Search_Record ORDER BY DateTime DESC";
            
            ResultSet rs = null;
            rs = st.executeQuery(sql);
            
            while (rs.next()) {
                storeName = rs.getString("StoreName");
                dateTime = rs.getTimestamp("DateTime");
                results.add(new SearchRecord(storeName, dateTime));
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(SearchRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return results;
    }
}
