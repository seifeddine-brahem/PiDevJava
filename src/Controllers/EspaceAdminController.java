/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Maha
 */
public class EspaceAdminController implements Initializable {

    @FXML
    private AnchorPane plan;
    @FXML
    private JFXButton Chat;
    @FXML
    private JFXButton Notification;
    @FXML
    private JFXButton Calendrier;
    @FXML
    private JFXButton Event;
    @FXML
    private JFXButton Conseil;
    @FXML
    private JFXButton Rendezvous;
    @FXML
    private JFXButton Offre;
    @FXML
    private JFXButton Profile;
    @FXML
    private AnchorPane chat;
    @FXML
    private AnchorPane notification;
    @FXML
    private AnchorPane calendrier;
    @FXML
    private AnchorPane evenement;
    @FXML
    private AnchorPane conseil;
    @FXML
    private AnchorPane rendezvous;
    @FXML
    private AnchorPane offre;
    @FXML
    private AnchorPane editer;
    @FXML
    private JFXTextField recherche;
    @FXML
    private JFXButton rechercher;
    @FXML
    private AnchorPane rech;
    @FXML
    private AnchorPane id_anchor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void chat(ActionEvent event) {
    }

    @FXML
    private void gerer_offre(ActionEvent event) {
    }
    
}
