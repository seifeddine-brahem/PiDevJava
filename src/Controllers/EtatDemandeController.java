/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Demande;
import Entities.Utilisateur;
import Services.DemandeService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
            nom.setText(demande.getNom());
            prenom.setText(demande.getPrenom());
            date_naiss.setText(demande.getDate_naissance().toString());
            //num_id(Integer.parseInt(cin.getText()));
            //cin.setText(demande.getCIN().toString());
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
    public void accepter()
    {
//         int i=0;
//       i=e.getUser().getId();
//       
 
        cs.accepter(e);
    }
    
   public void decliner()
   {


        cs.decliner(e);
   }
    
}
