/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Evenement;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 *
 * @author Maha
 */
public class EvenementItemController 
{
        @FXML
    private HBox Hbox;
    @FXML
    private ImageView img;
    @FXML
    private Label desc;
        @FXML
    private JFXButton btn;
    
      public EvenementItemController() 
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Presentation/EvenementItem.fxml"));
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
          
    public void setInfo(Evenement string)
    {
        desc.setText(string.getDescription());
        Image image = new Image("http://localhost/"+string.getImage());
        img.setImage(image);
    }

    public HBox getBox()
    {
        return Hbox;
    }
}
