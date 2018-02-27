/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Categorie;
import Entities.Utilisateur;
import Entities.fos_user;
import Services.CategorieService;
import Services.ConseilService;
import Utils.GetConnectedUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import esbe.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Maha
 */
public class AjoutCategorieeController implements Initializable {

    @FXML
    private AnchorPane ajout;
    @FXML
    private AnchorPane modif;
    @FXML
    private AnchorPane supprimer;
    @FXML
    private JFXButton AjouterButton;
    @FXML
    private JFXButton ModifierButton;
    @FXML
    private JFXButton supprimerConseil;
    @FXML
    private JFXComboBox<String> typeCategorie;
    @FXML
    private JFXTextField nomCategorie;
    @FXML
    private Button AjouterCategorie;
    @FXML
    private Button annuler;
    @FXML
    private AnchorPane hedha;
    @FXML
    private Color x21;
    @FXML
    private Font x11;
    @FXML
    private JFXComboBox<String> NomUtilisateur;
    @FXML
    private Button RechercherNomUti;
    @FXML
    private Color x4;
    @FXML
    private Font x3;

    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        String tableType[]= {"Evenement","Conseil"};
        typeCategorie.getItems().addAll(tableType);
    }    
    
    @FXML
    void AjouterCategorie(ActionEvent event) throws IOException
    {

        Utilisateur u = GetConnectedUser.GetConnectedUser();
        CategorieService categorieService = new CategorieService();
        Categorie c = new Categorie(nomCategorie.getText(),typeCategorie.getSelectionModel().getSelectedItem(),u);     
        categorieService.ajouterCategorie(c);
        
        
//       

         Parent root = FXMLLoader.load(getClass().getResource("/Presentation/ProfilPartenaire.fxml"));

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Presentation/CategoriePartenaire.fxml"));
         AnchorPane name = (AnchorPane) loader.load();
           
        ProfilPartenaireController profilController = new ProfilPartenaireController();
//        Stage stage = (Stage) profilController.getMainMain().getScene().getWindow();

      
        profilController.getMainMain().getChildren().add(name);
        profilController.getMainMain().autosize();
        User u1 = new User();
        //u.getStageUser();
        Scene scene = new Scene(root);
        u1.getStageUser().setScene(scene);
        u1.getStageUser().show();

          final Node source = (Node) event.getSource();
          final Stage stage = (Stage) source.getScene().getWindow();
          stage.close();
          


    }


    @FXML
    void annuler(ActionEvent event) throws IOException 
    {
          Parent root = FXMLLoader.load(getClass().getResource("/Presentation/ProfilPartenaire.fxml"));

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Presentation/CategoriePartenaire.fxml"));
         AnchorPane name = (AnchorPane) loader.load();
           
        ProfilPartenaireController profilController = new ProfilPartenaireController();
//        Stage stage = (Stage) profilController.getMainMain().getScene().getWindow();

      
        profilController.getMainMain().getChildren().add(name);
        profilController.getMainMain().autosize();
        User u = new User();
        //u.getStageUser();
        Scene scene = new Scene(root);
        u.getStageUser().setScene(scene);
        u.getStageUser().show();

          final Node source = (Node) event.getSource();
          final Stage stage = (Stage) source.getScene().getWindow();
          stage.close();

    }
}
