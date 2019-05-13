package main;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StoreItemsController implements Initializable {

    @FXML
    private BorderPane itemsListView;
    @FXML
    private Label cartLabel;
    @FXML
    private Label storeNameLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Button proceedBtn;
    @FXML
    private Label totalPriceLabel;
    @FXML
    private ListView<ItemsForm.Listitems> storeItemsListView;

  
    @FXML
    private ListView<String> myListView;
    
    private static Order order = new Order();
    private static ArrayList<String> ItemsName = new ArrayList<>();
    private static Map <String,Integer> items;
    private static List<String> finalList= new ArrayList();

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Item i = new Item();
        items = new HashMap();
        StoresListViewForm.StoreList ay = new StoresListViewForm.StoreList();
        List<ItemsForm.Listitems> list = new ArrayList<>();
        for (Item item : i.displayItems(ay.choosedStore)) {
            list.add(new ItemsForm.Listitems(item));
        }
        ObservableList<ItemsForm.Listitems> myObservableList = FXCollections.observableList(list);
        storeItemsListView.setItems(myObservableList);
        
        storeNameLabel.setText(ay.choosedStore.getName()+"-"+ "First Floor");

    }

    public void itemsChoosed(Item item,int number) {
        order.addItem(item, number);
        ItemsName.add(item.getName());
        items.put(item.getName(),number);            
    }
        public void itemsDecremenet(Item item,int number) {
       order.addItem(item, number);
       ItemsName.remove(ItemsName.indexOf(item.getName()));
        items.put(item.getName(),number); 
   }

    @FXML
    private void orderSubmissionBtn(ActionEvent event) {
       int k = 0;
      finalList.removeAll(finalList);
        for(String i : ItemsName){
            if(!finalList.contains(i)){
                finalList.add(i);
            }
        }
        for(String x: finalList){
           finalList.set(k, x + " x"+items.get(x));
           k++;
        }
        ObservableList ay7agga = FXCollections.observableList(finalList);
        myListView.setItems(ay7agga);
        priceLabel.setText(order.getTotalPrice() + "$");
    }

    @FXML
    private void proceedCheckOutBtn(ActionEvent event) {
        Stage newStage = new Stage();
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("checkoutForm.fxml"));
                        Scene newScene = new Scene(root, 700, 500);
                        newStage.initStyle(StageStyle.UNDECORATED);
                        newStage.setScene(newScene);
                        newStage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(StoresListViewForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 
    }

    public List<String> getFinalList() {
        return finalList;
    }

    public  Order getOrder() {
        return order;
    }
    
    

}
