/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.ProfilPartenaireController.mainMain;
import static Utils.GetConnectedUser.main;
import DataStorage.MyDB;
import Entities.Utilisateur;
import Services.AdminService;
import static Utils.GetConnectedUser.main;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Maha
 */
public class AdministrationPanelController implements Initializable {

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
    public void stat(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Presentation/Dashboard.fxml"));
        Stage stage = (Stage)main.getScene().getWindow();
        AnchorPane name = (AnchorPane)loader.load();
        main.getChildren().add(name);
        main.autosize();
    }
    
    public void showUsersDialog() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Presentation/ListeUsers.fxml"));
        Stage stage = (Stage)main.getScene().getWindow();
        AnchorPane name = (AnchorPane)loader.load();
        main.getChildren().add(name);
        main.autosize();
    }
    
    
    
    public void demande(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Presentation/ListerDemandesAdmin.fxml"));
        Stage stage = (Stage)main.getScene().getWindow();
        AnchorPane name = (AnchorPane)loader.load();
        main.getChildren().add(name);
        main.autosize();
    }
    
    
    public void event(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Presentation/ListerEventsAdmin.fxml"));
        Stage stage = (Stage)main.getScene().getWindow();
        AnchorPane name = (AnchorPane)loader.load();
        main.getChildren().add(name);
        main.autosize();        
    }
    
    public void conseil(ActionEvent event) throws IOException
    {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Presentation/ListConseilAdmin.fxml"));
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
