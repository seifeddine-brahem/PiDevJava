package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class SplashController implements Initializable {

    @FXML
    private Label lb_title;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ResourceBundle bundle = ResourceBundle.getBundle("dictionary");
        lb_title.setText(bundle.getString("Administration Panel"));
    }
}
