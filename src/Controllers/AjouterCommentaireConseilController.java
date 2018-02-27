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
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    @FXML
    private JFXButton btnClear;
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
    private void ajouterCommentaire(ActionEvent event) throws IOException 
    {
        Utilisateur u = GetConnectedUser.GetConnectedUser();
        CommentaireConseilService cs = new CommentaireConseilService();
        CommentaireConseill c = new CommentaireConseill(u,commentaire.getText(),e);       
        cs.ajouterCommentaireConseil(c);
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("/Presentation/AjouterCommentaireEvent.fxml"));
//        Parent tableView = loader.load();
//        Scene tablScene = new Scene(tableView); 
//        Stage primary = new Stage();
//        primary.setScene(tablScene);
//        primary.show();
//        annuler()
        
    }    
    
}
