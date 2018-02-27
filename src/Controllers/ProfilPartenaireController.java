/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
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
public class ProfilPartenaireController implements Initializable {

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
      private  AnchorPane main;
    
     public  static AnchorPane mainMain;
    @FXML
    private JFXButton conseil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        mainMain=main;
    }    

    @FXML
    private void event(ActionEvent event) throws IOException 
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Presentation/EventPartenaire.fxml"));
        Stage stage = (Stage)main.getScene().getWindow();
        AnchorPane name = (AnchorPane)loader.load();
        main.getChildren().add(name);
        main.autosize();
        mainMain=main;
    }

    public static AnchorPane getMainMain() {
        return mainMain;
    }

    @FXML
    private void categorie(ActionEvent event) throws IOException 
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Presentation/CategoriePartenaire.fxml"));
        Stage stage = (Stage)main.getScene().getWindow();
        AnchorPane name = (AnchorPane)loader.load();
        main.getChildren().add(name);
        main.autosize();
        mainMain=main;
    }

    @FXML
    private void conseil(ActionEvent event) throws IOException 
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Presentation/ConseilPartenaire.fxml"));
        Stage stage = (Stage)main.getScene().getWindow();
        AnchorPane name = (AnchorPane)loader.load();
        main.getChildren().add(name);
        main.autosize();
        mainMain=main;
    }

    

 
    

  
    
}
