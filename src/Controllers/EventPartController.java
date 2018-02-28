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
    private AnchorPane aujourdhui;
    @FXML
    private AnchorPane demain;
    @FXML
    private AnchorPane semaine;
    
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
    //private TableColumn<Evenement, String> categorie;
    @FXML
    private JFXButton buttonAjout;
    @FXML
    private JFXButton todayButton;
    @FXML
    private DatePicker date;

    @FXML
    private DatePicker date_ouverture1;
    @FXML
    private JFXTextField horaire_ouv;
    @FXML
    private JFXTextField description1;
    @FXML
    private JFXTextField horaire_ferm;

    
    @FXML
    private DatePicker date_fin1;
    
        Evenement evenement = new Evenement();
    @FXML
    private Button modifier;
    @FXML
    private JFXButton btnSupprimer;

    
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
        
        archiver();
        

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
        //categorie.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
  
        
        List<String> listCategorie= new ArrayList<>();
        listCategorie = es.listerNomsCategorie();
        listCat.getItems().addAll(listCategorie);
        
        date_ouverture1.setVisible(false);
        date_fin1.setVisible(false);
        horaire_ouv.setVisible(false);
        horaire_ferm.setVisible(false);
        description1.setVisible(false);

        modifier.setVisible(false);
 
        setCellValueFromTableToText();
        
        btnSupprimer.setVisible(false);
        
        
    } 
 
    
    
    public void setCellValueFromTableToText() {
        List<String> listCategorie = new ArrayList<>();
                listCategorie = es.listerNomsCategorie();
        tableEvent.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) 
            {
                
                
                date_ouverture1.setVisible(true);
                date_fin1.setVisible(true);
                horaire_ouv.setVisible(true);
                horaire_ferm.setVisible(true);
                description1.setVisible(true);

                modifier.setVisible(true);
                btnSupprimer.setVisible(true);

                
                evenement = tableEvent.getItems().get(tableEvent.getSelectionModel().getSelectedIndex());
                date_ouverture1.setValue(evenement.getDate_debut());
                date_fin1.setValue(evenement.getDate_fin());
                horaire_ouv.setText(evenement.getHoraire_com());
                horaire_ferm.setText(evenement.getHoraire_fin());
                description1.setText(evenement.getDescription());
                
            }
        });

    }
    
    
    @FXML
    public void modifierEvent(ActionEvent event) 
    {
               
        evenement.setDate_debut(date_ouverture1.getValue());
        evenement.setDate_fin(date_fin1.getValue());
        evenement.setHoraire_com(horaire_ouv.getText());
        evenement.setHoraire_fin(horaire_ferm.getText());
        evenement.setDescription(description1.getText());
        //evenement.getCategorie().setId_categorie((es.getCategorie(categorie1.getSelectionModel().getSelectedItem())).getId_categorie());

        
        es.modifierEvenement(evenement);
        
        date_ouverture1.setVisible(false);
        date_fin1.setVisible(false);
        horaire_ouv.setVisible(false);
        horaire_ferm.setVisible(false);
        description1.setVisible(false);

        modifier.setVisible(false);
 
        tableEvent.setItems(data);
        afficher();
    }
   
   
    @FXML
    public void afficher()
    {

        List<Evenement> LE = es.afficherEvenements();
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
        //categorie.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
  
    }
    
        @FXML
   public  void Ajout(ActionEvent event) throws IOException
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
    public void supprimerEvenement(ActionEvent event)
    {
        Evenement e1 = tableEvent.getSelectionModel().getSelectedItem();
        EvenementService es = new EvenementService();
        es.supprimerEvenement(e1);
        afficher();
        
        
    }
    
    public void modif() throws IOException
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
        //categorie.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
       
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
    public void chercherEvenementDemain(ActionEvent event)
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
//        categorie.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
  
                   
 
        
    }
    
        @FXML
   public void chercherSemaine(ActionEvent event)
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
//        categorie.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
  
                   
        

    }
    
    @FXML
   public void chercherDateJour() 
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
//        categorie.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
                   
    }
 

    @FXML
    public void affichagerCat(MouseEvent event) 
    {
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
//        categorie.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
    }
    
    
    public void archiver()
    {
        es.modifierEvenementArchive();
    }
    
    
    
    
}
