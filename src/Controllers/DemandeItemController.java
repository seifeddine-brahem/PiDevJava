/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Demande;
import Entities.Evenement;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Maha
 */
public class DemandeItemController implements Initializable {

    @FXML
    private HBox Hbox;
    @FXML
    private ImageView img;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;


    /**
     * Initializes the controller class.
     */
    
    
    
    public DemandeItemController()
    {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Presentation/DemandeItem.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            //throw new RuntimeException(e);
        }
    }
    
        public void setInfo(Demande string)
    {
        nom.setText(string.getNom());
        prenom.setText(string.getPrenom());
        Image image = new Image("http://localhost/"+string.getDiplome());
        img.setImage(image);
    }

    public HBox getBox()
    {
        return Hbox;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
       
    }    
    
}
