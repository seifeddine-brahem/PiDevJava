/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Items;

import Entities.ProduitHerbo;
import Entities.ProduitSalleDeSport;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author elbrh
 */
public class ProduitParatemController  {
    @FXML
    private HBox Hbox;
    @FXML
    private ImageView img;
    @FXML
    private Label nom;
    @FXML
    private Label prix;
    @FXML
    private Label desc;

    public ProduitParatemController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProduitHerboItem.fxml"));
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
    public void initialize() {

    }
    
    
    
    
    public void setInfo(ProduitHerbo string)
    {
        nom.setText(string.getNom());
        desc.setText(string.getDescription());
        prix.setText(Float.toString(string.getPrix()));
        Image image = new Image("http://localhost/"+string.getImage());
        img.setImage(image);
    }

    public HBox getBox()
    {
        return Hbox;
    }


    
    
    
    
}
