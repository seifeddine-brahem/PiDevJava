/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Commentaire;
import Entities.CommentaireConseill;
import Entities.Conseil;
import Entities.Evenement;
import Entities.Utilisateur;
import Services.CommentaireConseilService;
import Services.CommentaireService;
import Utils.GetConnectedUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import esbe.CommentaireConseil;
import esbe.User;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.w3c.dom.Comment;

/**
 * FXML Controller class
 *
 * @author Maha
 */
public class AjouterCommentaireConseilController implements Initializable {

    @FXML
    private Button ajouter;
    @FXML
    private Button Annuler;
    @FXML
    private JFXTextField commentaire;
    @FXML
    private TableView<CommentaireConseill> tableCommentaire;
    @FXML
    private Label desc;
    @FXML
    private ImageView img;

    
        
    
    CommentaireConseilService cs = new CommentaireConseilService();
    private ObservableList<CommentaireConseill> data;
    @FXML
    private TableColumn<CommentaireConseill, String> contenu;
    @FXML
    private TableColumn<CommentaireConseill, String> proprietaire;
    /**
     * Initializes the controller class.
     */
    @Override 
    public void initialize(URL url, ResourceBundle rb) 
    {

    }

    Conseil e = new Conseil();
    CommentaireConseill com = new CommentaireConseill();
    
    public void setConseil(Conseil conseil)
    {
        this.e = conseil ;
        if(conseil != null){

            desc.setText(conseil.getDescription());
            Image image = new Image("http://localhost/"+conseil.getImage());
            img.setImage(image);
                    System.out.println(e);
         List<CommentaireConseill> LC = cs.chercherCommentaire(e);
        data = FXCollections.observableArrayList();
        Commentaire c;

        LC.stream().forEach((j) -> {
            data.add(j);
        });
            tableCommentaire.setItems(data);
            contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
            proprietaire.setCellValueFactory(celldata -> new ReadOnlyStringWrapper(celldata.getValue().getUser().getUsername()));
        }
    }

    @FXML
    public void ajouterCommentaire(ActionEvent event) throws IOException 
    {
        Utilisateur u = GetConnectedUser.GetConnectedUser();
        CommentaireConseilService cs = new CommentaireConseilService();
        CommentaireConseill c = new CommentaireConseill(u,commentaire.getText(),e);       
        cs.ajouterCommentaireConseil(c);
 
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/Profile.fxml"));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Presentation/ConseilUtilisateur.fxml"));
        AnchorPane name = (AnchorPane) loader.load();          
        ProfileController profilController = new ProfileController();
        profilController.getMainMain().getChildren().add(name);
        profilController.getMainMain().autosize();
        User u1 = new User();
        Scene scene = new Scene(root);
        u1.getStageUser().setScene(scene);
        u1.getStageUser().show();
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }    

    @FXML
    public void retour(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/Profile.fxml"));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Presentation/ConseilUtilisateur.fxml"));
        AnchorPane name = (AnchorPane) loader.load();          
        ProfileController profilController = new ProfileController();
        profilController.getMainMain().getChildren().add(name);
        profilController.getMainMain().autosize();
        User u = new User();
        Scene scene = new Scene(root);
        u.getStageUser().setScene(scene);
        u.getStageUser().show();
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
}
