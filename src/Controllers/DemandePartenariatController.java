/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Demande;
import Entities.Utilisateur;
import Services.DemandeService;
import Utils.GetConnectedUser;
import Utils.PostFile;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Maha
 */
public class DemandePartenariatController extends Application implements Initializable {

    @FXML
    private CheckBox check;
    @FXML
    private Button inscrire;
    @FXML
    private Button Annuler;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField num_identifiant;
    @FXML
    private DatePicker date_naissance;
    @FXML
    private JFXButton photo_etab;
    @FXML
    private JFXButton patente;
    
    
    
      String imageFile;
    
    public File f;
    @FXML
    private JFXTextField cin;
    @FXML
    private JFXButton image_cin_recto;
    @FXML
    private JFXButton image_cin_verso;
    @FXML
    private JFXButton photo_diplome;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
       Utilisateur u = GetConnectedUser.GetConnectedUser();
       nom.setText(u.getNom());
       prenom.setText(u.getPrenom());
       
       
    }    


    @Override
    public void start(Stage stage) throws Exception
    {
        
    }
    
    
    
    @FXML
     void ajouterDemande(ActionEvent event) throws IOException, Exception
    {
        Utilisateur u = GetConnectedUser.GetConnectedUser();
        DemandeService es= new DemandeService();
        if(check.isSelected())
        {
        Demande e = new Demande(nom.getText(), prenom.getText(),Integer.parseInt(cin.getText()), date_naissance.getValue(), PostFile.upload(f.getAbsolutePath()), PostFile.upload(f.getAbsolutePath()), PostFile.upload(f.getAbsolutePath()), PostFile.upload(f.getAbsolutePath()),Integer.parseInt(num_identifiant.getText()),u,"en attente");
        es.ajouterDemande(e);
        }
        else 
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERREUR");
            alert.setHeaderText(null);
            alert.setContentText("ERREUR");
            alert.showAndWait();
            
        }
    }
    

    
    @FXML
        public void uploadPhoto() throws MalformedURLException, Exception{
                
        FileChooser.ExtensionFilter imageFilter
        = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");

        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(imageFilter);
        File selectedFile = fc.showOpenDialog(null);
        if(selectedFile != null){
            
            imageFile = selectedFile.toURI().toURL().toString();
            this.f =selectedFile;
            System.out.println(imageFile);
            System.out.println(selectedFile.getAbsolutePath());
           // PostFile.upload(selectedFile.getAbsolutePath());
            
            javafx.scene.image.Image image = new javafx.scene.image.Image(imageFile);
            //pic.setImage(image); 
            //p.setImage(selectedFile.getName());
        }
    }
        
        public File selectedFile(File f){
         return null ;
        }
    
    


}
