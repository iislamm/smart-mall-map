package main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class LoginController implements Initializable {
    
    @FXML
    private Label titleLabel;

    @FXML
    private BorderPane containerPane;
    
    @FXML
    private TextField emailTextField;
    
    @FXML
    private PasswordField passwordTextField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void handleLogin(ActionEvent event) throws IOException {
        titleLabel.setText("Logging in...");
        
        
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        
        try {
            Connection conn = DriverManager.getConnection(Main.DB_URL, Main.DB_USERNAME, Main.DB_PASSWORD);
            Statement st = conn.createStatement();
            
            String sql = "SELECT * FROM Employee WHERE Email = '" + email + "' AND Password = '" + password + "'";
            
            ResultSet rs = null;
            rs = st.executeQuery(sql);
            
            if (rs.next()) {
                titleLabel.setText("Successfully logged in");
                Pane statisticsScene = FXMLLoader.load(getClass().getResource("statisticsForm.fxml"));
                System.out.println(statisticsScene);
                System.out.println(containerPane);
                containerPane.setCenter(statisticsScene);
            } else {
                titleLabel.setText("Invalid login");
            }
            
            st.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
