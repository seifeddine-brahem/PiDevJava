/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.ProfilPartenaireController.mainMain;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Maha
 */
public class ProfileController implements Initializable {

    @FXML
    private AnchorPane holderAnchor;
    @FXML
    private Label lblName;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblPhone;
    @FXML
    private Label lblLocation;
    @FXML
    private Label fabEdit;
    @FXML
    private AnchorPane fabPane;
    @FXML
    private AnchorPane main;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        mainMain=main;
    }    
    
    
        
    public void conseil(ActionEvent event) throws IOException
    {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Presentation/ConseilUtilisateur.fxml"));
        Stage stage = (Stage)main.getScene().getWindow();
        AnchorPane name = (AnchorPane)loader.load();
        main.getChildren().add(name);
        main.autosize();
    }
    
        public void demande(ActionEvent event) throws IOException
    {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Presentation/AjouterEtablissement.fxml"));
        Stage stage = (Stage)main.getScene().getWindow();
        AnchorPane name = (AnchorPane)loader.load();
        main.getChildren().add(name);
        main.autosize();
    }

        
        public void event(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Presentation/EventUtilisateur.fxml"));
        Stage stage = (Stage)main.getScene().getWindow();
        AnchorPane name = (AnchorPane)loader.load();
        main.getChildren().add(name);
        main.autosize();        
    }
        
    public static AnchorPane getMainMain()
    {
        return mainMain;
    }
    
    
    
}
