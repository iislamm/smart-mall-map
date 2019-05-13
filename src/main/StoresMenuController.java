package main;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

public class StoresMenuController implements Initializable {

    @FXML
    private BorderPane storePane;
    @FXML
    private Label catLabel;
    public  ListView<StoresListViewForm.StoreList> listView = new ListView<StoresListViewForm.StoreList>();
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }

    @FXML
    private void fastFoodBtn(ActionEvent event) {
         Store s = new Store();
        List<StoresListViewForm.StoreList> list = new ArrayList<>();
        for (Store b : s.getStoresCategory("Fast food")){
            list.add(new StoresListViewForm.StoreList(b, storePane));
        
        ObservableList<StoresListViewForm.StoreList> myObservableList = FXCollections.observableList(list);
        listView.setItems(myObservableList);
        storePane.setCenter(listView); 
        }
        
    }

    @FXML
    private void restaurantsBtn(ActionEvent event) {
             Store s = new Store();
        List<StoresListViewForm.StoreList> list = new ArrayList<>();
        for (Store b : s.getStoresCategory("Restaurant")){
            list.add(new StoresListViewForm.StoreList(b, storePane));
           
        ObservableList<StoresListViewForm.StoreList> myObservableList = FXCollections.observableList(list);
        listView.setItems(myObservableList);
        storePane.setCenter(listView); 
        }
    }

    @FXML
    private void coffeShopBtn(ActionEvent event) {
        
        Store s = new Store();
      
        List<StoresListViewForm.StoreList> list = new ArrayList<>();
        
        for (Store b : s.getStoresCategory("Coffee Shop")){
            list.add(new StoresListViewForm.StoreList(b, storePane));      
        ObservableList<StoresListViewForm.StoreList> myObservableList = FXCollections.observableList(list);
        listView.setItems(myObservableList);
        storePane.setCenter(listView); 
        }
      
    }

    @FXML
    private void storesBtn(ActionEvent event) {
        Store s = new Store();
        List<StoresListViewForm.StoreList> list = new ArrayList<>();
        for (Store b : s.getStoresCategory("other")){
            list.add(new StoresListViewForm.StoreList(b, storePane));
         
        ObservableList<StoresListViewForm.StoreList> myObservableList = FXCollections.observableList(list);
        listView.setItems(myObservableList);
        storePane.setCenter(listView); 
    }
            
    }
     
}

