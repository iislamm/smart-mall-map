package main;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class StatisticsController implements Initializable {
    
    @FXML
    private BorderPane mainPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        TableView tableView = new TableView();
        
        TableColumn<String, SearchRecord> column1 = new TableColumn<>("Store Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("storeName"));
        
        TableColumn<String, SearchRecord> column2 = new TableColumn<>("Time");
        column2.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
        
        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        
        ArrayList<SearchRecord> records = SearchRecord.getAll();
        
        for (SearchRecord r : records) {
            tableView.getItems().add(r);
        }

        mainPane.setCenter(tableView);

        
    }
    
}
