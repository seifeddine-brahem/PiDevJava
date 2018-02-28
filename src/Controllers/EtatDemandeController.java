/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Demande;
import Entities.Utilisateur;
import Services.DemandeService;
import esbe.AdminTest;
import esbe.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Maha
 */
public class EtatDemandeController implements Initializable
{

    @FXML
    private Button Annuler;
    @FXML
    private ImageView diplome;
    @FXML
    private ImageView patente;
    @FXML
    private ImageView cin_verso;
    @FXML
    private ImageView cin_recto;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label date_naiss;
    @FXML
    private Label num_id;
    @FXML
    private Label cin;
    @FXML
    private ImageView photo_etab;

      Demande e = new Demande();
      DemandeService cs = new DemandeService();
      
    @FXML
    private Button a;
    Utilisateur u;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    

    public void setDemande(Demande demande) 
    {
        this.e = demande;
        if (demande != null)
        { 
            int id=demande.getUser().getId();
            nom.setText(demande.getNom());
            prenom.setText(demande.getPrenom());
            date_naiss.setText(demande.getDate_naissance().toString());
            num_id.setText(String.valueOf(demande.getNum_identifiant()));
            cin.setText(String.valueOf(demande.getCIN()));
            Image image = new Image("http://localhost/" + demande.getCIN_image_recto());
            Image image1 = new Image("http://localhost/" + demande.getCIN_image_verso());
            Image image2= new Image("http://localhost/" + demande.getDiplome());
            Image image3 = new Image("http://localhost/" + demande.getPatente());
            Image image4 = new Image("http://localhost/" + demande.getPhoto_etab());
            
            cin_recto.setImage(image);
            cin_verso.setImage(image1);
            diplome.setImage(image2);
            patente.setImage(image3);
            photo_etab.setImage(image4);
    
    }
    }
        
    @FXML
    public void accepter(ActionEvent event) throws IOException
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alerte demande");
        alert.setHeaderText("Confirmation");
        alert.setContentText("Êtes-vous sûr(e) de vouloir accepter?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
        cs.accepter(e);
        }
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/Admin.fxml"));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Presentation/ListerDemandesAdmin.fxml"));
        AnchorPane name = (AnchorPane) loader.load();          
        AdministrationPanelController profilController = new AdministrationPanelController();
        profilController.getMainMain().getChildren().add(name);
        profilController.getMainMain().autosize();
        AdminTest u = new AdminTest();
        Scene scene = new Scene(root);
        u.getStageAdmin().setScene(scene);
        u.getStageAdmin().show();
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
   public void decliner(ActionEvent event) throws IOException
   {
       
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alerte demande");
        alert.setHeaderText("Confirmation");
        alert.setContentText("Êtes-vous sûr(e) de vouloir decliner?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
         cs.decliner(e);
        }
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/Admin.fxml"));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Presentation/ListerDemandesAdmin.fxml"));
        AnchorPane name = (AnchorPane) loader.load();          
        AdministrationPanelController profilController = new AdministrationPanelController();
        profilController.getMainMain().getChildren().add(name);
        profilController.getMainMain().autosize();
        AdminTest u = new AdminTest();
        Scene scene = new Scene(root);
        u.getStageAdmin().setScene(scene);
        u.getStageAdmin().show();
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
   }
    
}
