package main;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.javafx.BrowserView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author ahmed hossam
 */
public class NearestExitController implements Initializable {

    @FXML
    private BorderPane mapBorderPane;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Browser browser = new Browser();
        BrowserView view = new BrowserView(browser);
        browser.loadURL("https://maps.mapwize.io/#/f/p/mall_map/device_1/t/p/mall_map/entrance?k=cXmoADz5kGxPT4wN&z=17.949");
        mapBorderPane.setCenter(view);
    }
    
}
