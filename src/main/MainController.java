package main;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.dom.By;
import com.teamdev.jxbrowser.chromium.dom.DOMDocument;
import com.teamdev.jxbrowser.chromium.dom.DOMElement;
import com.teamdev.jxbrowser.chromium.dom.events.DOMEvent;
import com.teamdev.jxbrowser.chromium.dom.events.DOMEventListener;
import com.teamdev.jxbrowser.chromium.dom.events.DOMEventType;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.LoadAdapter;
import com.teamdev.jxbrowser.chromium.javafx.BrowserView;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MainController implements Initializable {
    
    private Browser browser;
    
    @FXML
    private BorderPane containerPane;
    
    
    private Pane loginPane;
    private ScrollPane PromotionsPane;
    private Pane nearestExitPane;
    private Pane orderPane;
    
    BorderPane mapView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        mapView = new BorderPane();
        browser = new Browser();
        BrowserView view = new BrowserView(browser);
        mapView.setCenter(view);
        
        try {
            loginPane = FXMLLoader.load(getClass().getResource("LoginForm.fxml"));
            PromotionsPane = FXMLLoader.load(getClass().getResource("PromotionsForm.fxml"));
            nearestExitPane = FXMLLoader.load(getClass().getResource("NearestExitForm.fxml"));
            
            
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         browser.addLoadListener(new LoadAdapter() {
            @Override
            public void onFinishLoadingFrame(FinishLoadingEvent event) {
                initListener();
            }
        });
         
        browser.loadURL("https://maps.mapwize.io/#/v/mall_map?k=cXmoADz5kGxPT4wN&u=default_universe&l=en&z=16&embed=true&menu=false");
        
        containerPane.setCenter(mapView);
    }
    
    public void adminButtonHandler(ActionEvent event) {
        containerPane.setCenter(loginPane);
    }
    
    public void mapButtonHandler(ActionEvent event) {
        containerPane.setCenter(mapView);
    }
    
    public void PromotionsButtonHandler(ActionEvent event) {
        containerPane.setCenter(PromotionsPane);
    }
    
    public void nearestExitButtonHandler(ActionEvent event) {
        containerPane.setCenter(nearestExitPane);
    }
    
    public void orderButtomHandler(ActionEvent event) throws IOException {
        orderPane = FXMLLoader.load(getClass().getResource("StoresMenu.fxml"));
        containerPane.setCenter(orderPane);
    }
    
    private void initListener() {
        DOMDocument document = browser.getDocument();
        DOMElement element = document.getDocumentElement();
        element.addEventListener(DOMEventType.OnClick, new DOMEventListener() {
            public void handleEvent(DOMEvent event) {
                String storeName;
                try {
                    storeName = document.findElements(By.className("ng-tns-c4-1")).get(1).findElement(By.className("title")).getInnerText();
                } catch(Exception e) {
                    storeName = null;
                }
                // user clicked document element
                System.out.println(storeName);
                System.out.println(new Date());
                Store clickedStore = Store.search(storeName);
                SearchRecord newSearchRecord = new SearchRecord(clickedStore.getName(), new Date());
                newSearchRecord.send();
            }
        }, false);
    }
    
}
