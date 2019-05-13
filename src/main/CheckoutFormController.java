package main;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.javafx.BrowserView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.stage.Stage;

public class CheckoutFormController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private StoreItemsController itemsController = new StoreItemsController();
    @FXML
    private Label containerLabel;
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Label timeLabel;
    @FXML
    private Label totalPriceLabel;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String items = "";
        for(String item : itemsController.getFinalList())
            items+= item + "\n\n\n\n";
        containerLabel.setText(items);
        timeLabel.setText(""+itemsController.getOrder().getAvgTime());
        totalPriceLabel.setText(itemsController.getOrder().getTotalPrice() +"$");
    }    

    @FXML
    private void confirmBtn(ActionEvent event) {
     Order order = new Order();
     Item item = new Item();
     
//     item.updateItem(item, "quantity");
//     order.insertOrder(itemsController.getOrder());
     int checkout =  itemsController.getOrder().checkOut();
     Alert alert = new Alert(AlertType.NONE,"Your receipt number is: "+checkout,ButtonType.OK);
     alert.show();
     Stage stage = (Stage) confirmButton.getScene().getWindow();
     stage.close();
    
    }

    @FXML
    private void cancelBtn(ActionEvent event) {
    Stage stage = (Stage) cancelButton.getScene().getWindow();
    stage.close();
    }

    @FXML
    private void showOnMapBtn(ActionEvent event) {
     
        
        
        Store store = new Store();
        //store.setId(itemsController.getOrder());
         Browser browser = new Browser();
        BrowserView view = new BrowserView(browser);
        browser.loadURL(store.getLocation());
        
        
        
           Stage newStage = new Stage();  
           BorderPane mapPane = new BorderPane();
           mapPane.setCenter(view);
           mapPane.setMinSize(1279,772);
           Scene newScene = new Scene(mapPane, USE_PREF_SIZE, USE_PREF_SIZE);
           newStage.setScene(newScene);
            newStage.show();
                    
        
    }
    
}
