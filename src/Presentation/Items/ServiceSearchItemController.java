/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Items;

import Entities.Service;
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
public class ServiceSearchItemController  {
    @FXML
    private HBox Hbox;

    @FXML
    private Label nom;

    @FXML
    private Label prix;

    @FXML
    private Label NomEtab;
    
    @FXML
    private Label adress;

    @FXML
    private Label desc;
    public ServiceSearchItemController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ServiceSearchItem.fxml"));
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
        // TODO
    
    
    
    
    public void setInfo(Service string)
    {
        nom.setText(string.getNom());
        desc.setText(string.getDescription());
        prix.setText(Float.toString(string.getTarif()));
        NomEtab.setText(string.getEtab().getNom());
        adress.setText(string.getEtab().getAdresse());

    }

    public HBox getBox()
    {
        return Hbox;
    }


    
    
    
    
}
