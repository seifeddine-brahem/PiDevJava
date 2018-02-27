/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Conseil;
import Entities.Evenement;
import java.awt.Image;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 *
 * @author Maha
 */
public class ConseilItemController 
{
        @FXML
    private HBox Hbox;
    @FXML
    private ImageView img;
    @FXML
    private Label contenu;
    
      public ConseilItemController() 
     {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Presentation/ConseilItem.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    public void initialize() 
    {

        
    }
    
        
    public void setInfo(Conseil string)
    {
        contenu.setText(string.getDescription());
        javafx.scene.image.Image image = new javafx.scene.image.Image("http://localhost/"+string.getImage());
        img.setImage(image);
    }

    public HBox getBox()
    {
        return Hbox;
    }
}