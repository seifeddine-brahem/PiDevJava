/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Commentaire;
import Entities.Evenement;
import Entities.Utilisateur;
import Services.CommentaireService;
import Services.EvenementService;
import Utils.GetConnectedUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Maha
 */
public class AjouterCommentaireEventController implements Initializable {

    @FXML
    private AnchorPane Desc;

    @FXML
    private Button ajouter;

    @FXML
    private Button Annuler;

    @FXML
    private JFXTextField commentaire;

    @FXML
    private TableView<Commentaire> tableCommentaire;

    @FXML
    private Label datedeb;

    @FXML
    private Label dateFin;

    @FXML
    private Label HoraireDeb;

    @FXML
    private Label HoraireFin;

    @FXML
    private ImageView img;

    @FXML
    private Label desc;

    @FXML
    private JFXButton btnparticipe;

    @FXML
    private JFXButton btnInteresse;

    EvenementService es = new EvenementService();

    CommentaireService cs = new CommentaireService();
    private ObservableList<Commentaire> data;
    Commentaire com = new Commentaire();
    @FXML
    private TableColumn<Commentaire, String> contenu;
    @FXML
    private TableColumn<Commentaire, String> proprietaire;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        System.out.println(e);
//        List<Commentaire> LC = cs.chercherCommentaire(e);
//        data = FXCollections.observableArrayList();
//        Commentaire c;
//
//        LC.stream().forEach((j) -> {
//            data.add(j);
//        });
//        tableCommentaire.setItems(data);
//        contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
//        proprietaire.setCellValueFactory(new PropertyValueFactory<>("id_user"));

        //proprietaire.setCellValueFactory(new PropertyValueFactory<>("nom_user"));
    }

    Evenement e = new Evenement();
    

    public void setEvent(Evenement event) 
    {
        this.e = event;
        if (event != null) 
        {
            datedeb.setText(event.getDate_debut().toString());
            dateFin.setText(event.getDate_fin().toString());
            HoraireDeb.setText(event.getHoraire_com());
            HoraireFin.setText(event.getHoraire_fin());
            desc.setText(event.getDescription());
            
            
            Image image = new Image("http://localhost/" + event.getImage());
            img.setImage(image);
            System.out.println(e);
            List<Commentaire> LC = cs.chercherCommentaire(e);
            data = FXCollections.observableArrayList();
            Commentaire c;

            LC.stream().forEach((j) -> {
                data.add(j);
            });
            tableCommentaire.setItems(data);
            contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
            proprietaire.setCellValueFactory(celldata -> new ReadOnlyStringWrapper(celldata.getValue().getUser().getUsername()));
        }

        Utilisateur u = null;
        if (es.checkisPartcipant(u, e)) 
        {
            btnparticipe.setText("deja Participant");
            btnparticipe.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    es.AnnulerParticipant(u, e);
                }
            });
            btnInteresse.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    es.updateInteresse(u, e);
                }
            });

        }
        else if(es.checkisInteresse(u, e)){
            btnInteresse.setText("deja interessé");
            btnInteresse.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    es.AnnulerInteresse(u, e);
                }
            });
            btnparticipe.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    es.updateParticipe(u, e);
                }
            });
    }
        else{
            //btnInteresse.setText("deja interessé");
            btnInteresse.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    es.setInteresse(u, e);
                }
            });
            btnparticipe.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    es.setParticipe(u, e);
                }
            });
            
        }
    }

    @FXML
    private void ajouterCommentaire(ActionEvent event) throws IOException 
    {
        Utilisateur u = GetConnectedUser.GetConnectedUser();
        CommentaireService commentaireService = new CommentaireService();
        Commentaire c = new Commentaire(u,commentaire.getText(),e);
        commentaireService.ajouterCommentaireEvent(c);
        
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("/Presentation/AjouterCommentaireEvent.fxml"));
//        Parent tableView = loader.load();
//        Scene tablScene = new Scene(tableView); 
//        Stage primary = new Stage();
//        primary.setScene(tablScene);
//        primary.show();
//        annuler();

    }


}
