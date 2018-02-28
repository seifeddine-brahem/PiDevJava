/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Categorie;
import Entities.Evenement;
import Entities.Utilisateur;
import Services.CategorieService;
import Services.EvenementService;
import Utils.GetConnectedUser;
import Utils.PostFile;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import esbe.User;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
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
public class AjoutEventController implements Initializable {

    @FXML
    private JFXTextField horaire_ouv;
    @FXML
    private JFXTextField horaire_ferm;
    @FXML
    private JFXTextField description;
    @FXML
    private JFXButton image;
    @FXML
    private JFXComboBox<String> categorie;
    private Rectangle rectangle;

    EvenementService es = new EvenementService();

    @FXML
    private DatePicker date_ouverture;
    @FXML
    private DatePicker date_fin;
    private JFXButton annuler;
    private ObservableList<Evenement> data;
    String imageFile;

    public File f;
    @FXML
    private ImageView pic;

    @FXML
    private JFXButton annuler1;
    @FXML
    private JFXButton ajouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        List<String> listCategorie = new ArrayList<>();
//        listCategorie = es.listerNomsCategorie();
//        //   categorie.getItems().addAll(listCategorie);
        CategorieService cs = new CategorieService();
        List<String> listCat = new ArrayList<>();
        listCat = cs.afficherCategorieEvt();
        categorie.getItems().addAll(listCat);
        
        

    }

    @FXML
    public void ajouterEvenement(ActionEvent event) throws IOException, Exception {
        Utilisateur u = GetConnectedUser.GetConnectedUser();
        EvenementService es = new EvenementService();
        String nomCategorie = categorie.getSelectionModel().getSelectedItem();
        System.out.println("heth houwa" + categorie.getSelectionModel().getSelectedItem());
        Evenement e = new Evenement(date_ouverture.getValue(), date_fin.getValue(), horaire_ouv.getText(), horaire_ferm.getText(), description.getText(), PostFile.upload(f.getAbsolutePath()), es.getCategorie(nomCategorie), u);
        
        if(date_fin.getValue().isBefore(date_ouverture.getValue()))
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Date fin est supérieur à la date début!");
            alert.setContentText("Erreur");
            alert.showAndWait();
        }
        else if(LocalDate.now().isAfter(date_ouverture.getValue()))
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Date début est inférieure à la date actuelle");
            alert.setContentText("Erreur");
            alert.showAndWait();
        }
        else
        {
        es.ajouterEvenement(e);
        
        }
        
        
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/ProfilPartenaire.fxml"));

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Presentation/EventPartenaire.fxml"));
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

    void annuler() throws IOException {
    }

    @FXML
    public void retour(ActionEvent event) throws IOException
    {
//                FXMLLoader loader = new FXMLLoader();
//                loader.setLocation(getClass().getResource("/Presentation/ProfilPartenaire.fxml"));
//                Parent tableView = loader.load();
//                EventPartController eventPart = new EventPartController();            
//                Scene tablScene = new Scene(tableView); 
//                Stage primary = new Stage();
//                primary.setScene(tablScene);
//                primary.show();

        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/ProfilPartenaire.fxml"));

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Presentation/EventPartenaire.fxml"));
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

    @FXML
    public void uploadPhoto() throws MalformedURLException, Exception 
    {

        FileChooser.ExtensionFilter imageFilter
                = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");

        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(imageFilter);
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null)
        {
            imageFile = selectedFile.toURI().toURL().toString();
            this.f = selectedFile;
            System.out.println(imageFile);
            System.out.println(selectedFile.getAbsolutePath());
            // PostFile.upload(selectedFile.getAbsolutePath());

            javafx.scene.image.Image image = new javafx.scene.image.Image(imageFile);
            pic.setImage(image);
            //p.setImage(selectedFile.getName());
        }
    }

    public File selectedFile(File f) {
        return null;
    }

//       void annuler()
//    {
//        Stage stage = (Stage) annuler.getScene().getWindow();
//        stage.close();
//
//    }
//    
//    
}
