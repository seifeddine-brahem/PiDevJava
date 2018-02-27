/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.AjoutEventController;
import DataStorage.MyDB;
import Entities.Evenement;
import Entities.Utilisateur;
import Services.EvenementService;
import Utils.GetConnectedUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Maha
 */
public class EventPartController extends Application implements Initializable {

    @FXML
    private Color x2;
    @FXML
    private Font x1;
    @FXML
    private Color x21;
    @FXML
    private Font x11;
    @FXML
    private AnchorPane aujourdhui;
    @FXML
    private Color x4;
    @FXML
    private Font x3;
    @FXML
    private AnchorPane demain;
    @FXML
    private AnchorPane semaine;
    @FXML
    private AnchorPane event;
    @FXML
    private AnchorPane ajout;
    @FXML
    private AnchorPane modif;
    @FXML
    private AnchorPane supprimer;
    
    EvenementService es= new EvenementService();
    private ObservableList<Evenement> data;
    private ObservableList<Evenement> dataa;
     
   public  static String s ="";
    
    Connection conn;
    @FXML
    private TableView<Evenement> tableEvent;
    @FXML
    private JFXListView<String> listCat;
    @FXML
    private TableColumn<Evenement, LocalDate> date_ouverture;
    @FXML
    private TableColumn<Evenement, LocalDate> date_fermeture;
    @FXML
    private TableColumn<Evenement, String> horaire1;
    @FXML
    private TableColumn<Evenement, String> horaire2;
    @FXML
    private TableColumn<Evenement, String> description;
    @FXML
    private TableColumn<Evenement, String> image;
    @FXML
    private TableColumn<Evenement, String> categorie;
    @FXML
    private JFXButton buttonAjout;
    @FXML
    private JFXButton todayButton;
    @FXML
    private DatePicker date;
    @FXML
    private Button raffraichir;

    @FXML
    private DatePicker date_ouverture1;
    @FXML
    private JFXTextField horaire_ouv;
    @FXML
    private JFXTextField description1;
    @FXML
    private JFXTextField horaire_ferm;
    @FXML
    private JFXTextField image2;
    @FXML
    private JFXComboBox<String> categorie1;
    @FXML
    private DatePicker date_fin1;
    
        Evenement evenement = new Evenement();
    @FXML
    private AnchorPane hedha;
    @FXML
    private Button modifier;

    
    public EventPartController()
    {
        this.conn=MyDB.getinstance().getConnexion();
    }
    
    final ObservableList options = FXCollections.observableArrayList();
    //final ObservableList listCat= new FXCollections.ObservableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {   
        Utilisateur u = GetConnectedUser.GetConnectedUser();
        List<Evenement> LE = es.afficherEvenementsPartenaire(u.getId());
        data = FXCollections.observableArrayList();
        Evenement event;
        
        
        

        LE.stream().forEach((j) -> {
            data.add(j);
        });
        System.out.println(data.size());
        tableEvent.setItems(data);
        date_ouverture.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        date_fermeture.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        horaire1.setCellValueFactory(new PropertyValueFactory<>("horaire_com"));
        horaire2.setCellValueFactory(new PropertyValueFactory<>("horaire_fin"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
  
        
        List<String> listCategorie= new ArrayList<>();
        listCategorie = es.listerNomsCategorie();
        listCat.getItems().addAll(listCategorie);
        
        date_ouverture1.setVisible(false);
        date_fin1.setVisible(false);
        horaire_ouv.setVisible(false);
        horaire_ferm.setVisible(false);
        description1.setVisible(false);
        image2.setVisible(false);
        categorie1.setVisible(false);
        modifier.setVisible(false);
 
        setCellValueFromTableToText();
    } 
 
    
    
    private void setCellValueFromTableToText() {
        List<String> listCategorie = new ArrayList<>();
                listCategorie = es.listerNomsCategorie();
                categorie1.getItems().addAll(listCategorie);
        tableEvent.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) 
            {
                
                
                date_ouverture1.setVisible(true);
                date_fin1.setVisible(true);
                horaire_ouv.setVisible(true);
                horaire_ferm.setVisible(true);
                description1.setVisible(true);
                image2.setVisible(true);
                categorie1.setVisible(true);
                modifier.setVisible(true);
                
                evenement = tableEvent.getItems().get(tableEvent.getSelectionModel().getSelectedIndex());
                categorie1.setValue(evenement.getNom_categorie());
                date_ouverture1.setValue(evenement.getDate_debut());
                date_fin1.setValue(evenement.getDate_fin());
                horaire_ouv.setText(evenement.getHoraire_com());
                horaire_ferm.setText(evenement.getHoraire_fin());
                description1.setText(evenement.getDescription());
                image2.setText(evenement.getImage());
                categorie1.setValue(evenement.getNom_categorie());    
                
            }
        });

    }
    
    
    @FXML
    void modifierEvent(ActionEvent event) 
    {
               
        evenement.setDate_debut(date_ouverture1.getValue());
        evenement.setDate_fin(date_fin1.getValue());
        evenement.setHoraire_com(horaire_ouv.getText());
        evenement.setHoraire_fin(horaire_ferm.getText());
        evenement.setDescription(description1.getText());
        evenement.setImage(image2.getText());
        evenement.getCategorie().setId_categorie((es.getCategorie(categorie1.getSelectionModel().getSelectedItem())).getId_categorie());

        
        es.modifierEvenement(evenement);
        
        date_ouverture1.setVisible(false);
        date_fin1.setVisible(false);
        horaire_ouv.setVisible(false);
        horaire_ferm.setVisible(false);
        description1.setVisible(false);
        image2.setVisible(false);
        categorie1.setVisible(false);
        modifier.setVisible(false);
 
        tableEvent.setItems(data);
        afficher();
    }
   
   
    @FXML
    public void afficher()
    {

        
        Utilisateur u = GetConnectedUser.GetConnectedUser();
        List<Evenement> LE = es.afficherEvenementsPartenaire(u.getId());
        data = FXCollections.observableArrayList();
        Evenement event;
        
        
        

        LE.stream().forEach((j) -> {
            data.add(j);
        });
        System.out.println(data.size());
        tableEvent.setItems(data);
        date_ouverture.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        date_fermeture.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        horaire1.setCellValueFactory(new PropertyValueFactory<>("horaire_com"));
        horaire2.setCellValueFactory(new PropertyValueFactory<>("horaire_fin"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
  
    }
    
        @FXML
    void Ajout(ActionEvent event) throws IOException
    {
        Stage Stage = new Stage();
        Parent root =FXMLLoader.load(getClass().getResource("/Presentation/ajouterEvenement.fxml"));     
        Scene scene = new Scene(root);
        
        
        Stage.setScene(scene);
        Stage.show(); 
        annuler();

    }
  
        void annuler() 
    {
         Stage stage = (Stage) buttonAjout.getScene().getWindow();
         stage.close();
    }

        @FXML
    void supprimerEvenement(ActionEvent event)
    {
        Evenement e1 = tableEvent.getSelectionModel().getSelectedItem();
        EvenementService es = new EvenementService();
        es.supprimmerEvenement(e1.getDate_debut());
        afficher();
        
        
    }
    
    @FXML
    void modif() throws IOException
    {
       
        Evenement e= tableEvent.getSelectionModel().getSelectedItem();
        Stage Stage = new Stage();
        Parent root =FXMLLoader.load(getClass().getResource("/Presentation/ModifierEvent.fxml"));     
        Scene scene = new Scene(root);
        Stage.setScene(scene);
        Stage.show(); 
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
        @FXML
        void chercherParDate(ActionEvent event)
    {

         LocalDate s= date.getValue();
       
       List<Evenement> LE = es.chercherEvenementParDate(s);
        data = FXCollections.observableArrayList();
        Evenement e;
        

       LE.stream().forEach((j) -> {
            data.add(j);
        });
        tableEvent.setItems(data);
        date_ouverture.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        date_fermeture.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        horaire1.setCellValueFactory(new PropertyValueFactory<>("horaire_com"));
        horaire2.setCellValueFactory(new PropertyValueFactory<>("horaire_fin"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
       
    }
    
//        @FXML
//    void chercherDateJour() 
//    {
//        EvenementService es= new EvenementService();
//        data=es.chercherEvenementAujourdhui();
//        tableEvent.setItems(null);
//
//        date_ouverture.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
//        date_fermeture.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
//        horaire1.setCellValueFactory(new PropertyValueFactory<>("horaire_com"));
//        horaire2.setCellValueFactory(new PropertyValueFactory<>("horaire_fin"));
//        description.setCellValueFactory(new PropertyValueFactory<>("description"));
//        image.setCellValueFactory(new PropertyValueFactory<>("image"));
//        categorie.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
//                        tableEvent.setItems(data);        
//
//    }
//    
        @FXML
    void chercherEvenementDemain(ActionEvent event)
    {
        
        List<Evenement> LE = es.chercherEvenementDemain();
        data = FXCollections.observableArrayList();
      //  Evenement event;
        
        
        

        LE.stream().forEach((j) -> {
            data.add(j);
        });
        System.out.println(data.size());
        tableEvent.setItems(data);
        date_ouverture.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        date_fermeture.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        horaire1.setCellValueFactory(new PropertyValueFactory<>("horaire_com"));
        horaire2.setCellValueFactory(new PropertyValueFactory<>("horaire_fin"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
  
                   
 
        
    }
    
        @FXML
    void chercherSemaine(ActionEvent event)
    {
       
        List<Evenement> LE = es.chercherEvenementSemaine();
        data = FXCollections.observableArrayList();
        //Evenement event;
        
        
        

        LE.stream().forEach((j) -> {
            data.add(j);
        });
        System.out.println(data.size());
        tableEvent.setItems(data);
        date_ouverture.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        date_fermeture.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        horaire1.setCellValueFactory(new PropertyValueFactory<>("horaire_com"));
        horaire2.setCellValueFactory(new PropertyValueFactory<>("horaire_fin"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
  
                   
        

    }
    
    @FXML
    void chercherDateJour() 
    {
       List<Evenement> LE = es.chercherEvenementAujourdhui();
        data = FXCollections.observableArrayList();
        Evenement e;
        

        LE.stream().forEach((j) -> {
            data.add(j);
        });
        tableEvent.setItems(data);
        date_ouverture.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        date_fermeture.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        horaire1.setCellValueFactory(new PropertyValueFactory<>("horaire_com"));
        horaire2.setCellValueFactory(new PropertyValueFactory<>("horaire_fin"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
                   
    }

  

//    @FXML
//    private void affichagerCat(ActionEvent event) {
//   
//    }

//    @FXML
//    private void chercheMAha(ContextMenuEvent event) {
//    }
//    

    @FXML
    private void affichagerCat(MouseEvent event) {
            String  cat= listCat.getSelectionModel().getSelectedItem();
        List<Evenement> LE = es.chercherEvenementParCategorie(cat);
        data = FXCollections.observableArrayList();
        Evenement e;
        

        LE.stream().forEach((j) -> {
            data.add(j);
        });
        tableEvent.setItems(data);
        date_ouverture.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        date_fermeture.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        horaire1.setCellValueFactory(new PropertyValueFactory<>("horaire_com"));
        horaire2.setCellValueFactory(new PropertyValueFactory<>("horaire_fin"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
    }
    
    
    
    
    
    
}
