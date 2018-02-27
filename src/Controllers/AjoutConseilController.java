/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Conseil;
import Entities.Utilisateur;
import Services.ConseilService;
import Utils.GetConnectedUser;
import Utils.PostFile;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import esbe.User;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Maha
 */
public class AjoutConseilController implements Initializable
{


    @FXML
    private JFXComboBox<String> categorie;

    @FXML
    private JFXTextField description;
    
    ConseilService cs= new ConseilService();
    private ObservableList<Conseil> data;
    private Button CancelButton;
    
    Conseil2Controller conseilController= new Conseil2Controller();
    @FXML
    private JFXButton image;
    
    
    
     String imageFile;
         public File f;
    @FXML
    private ImageView pic;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        List<String> listCategorie= new ArrayList<>();
        listCategorie = cs.listerNomsCategorie();
        categorie.getItems().addAll(listCategorie);
    }    

    @FXML
    private void ajouterConseil(ActionEvent event) throws IOException, Exception 
    {
        Utilisateur u = GetConnectedUser.GetConnectedUser();
        ConseilService conseilService = new ConseilService();
        String nomCategorie = categorie.getSelectionModel().getSelectedItem();
        Conseil c = new Conseil(description.getText(),PostFile.upload(f.getAbsolutePath()),u,conseilService.getCategorie(nomCategorie));
        
        conseilService.ajouterConseil(c);
        
                  Parent root = FXMLLoader.load(getClass().getResource("/Presentation/ProfilPartenaire.fxml"));

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Presentation/ConseilPartenaire.fxml"));
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
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("/Presentation/ConseilPart.fxml"));
//        Parent tableView = loader.load();
//        Scene tablScene = new Scene(tableView); 
//        Stage primary = new Stage();
//        primary.setScene(tablScene);
//        primary.show();
//        annuler();
        
    }
    
      void annuler() 
    {
        Stage stage = (Stage) CancelButton.getScene().getWindow();
        stage.close();
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
            pic.setImage(image); 
            //p.setImage(selectedFile.getName());
        }
    }
        
        public File selectedFile(File f){
         return null ;
        }

    @FXML
    private void retour(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/ProfilPartenaire.fxml"));

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Presentation/ConseilPartenaire.fxml"));
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
