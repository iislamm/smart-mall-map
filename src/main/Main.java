package main;


import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {
    
    public static final String DB_URL = ""; // database connection URL
    public static final String DB_USERNAME = ""; // database username
    public static final String DB_PASSWORD = ""; // database password
    
      
    @Override
    public void start(Stage primaryStage) throws IOException {
     
        Parent root = FXMLLoader.load(getClass().getResource("MainForm.fxml")); 
//        newRoot
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setResizable(true);
        
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}



