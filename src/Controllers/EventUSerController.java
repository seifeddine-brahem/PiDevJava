/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DataStorage.MyDB;
import Entities.Evenement;
import Presentation.LsitViewEventCell;
import Services.EvenementService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Maha
 */
public class EventUSerController implements Initializable {

    @FXML
    private Color x2;
    @FXML
    private Font x1;
    @FXML
    private AnchorPane event;
    @FXML
    private TableView<Evenement> tableEvent;
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
    private AnchorPane hedha;
    @FXML
    private JFXListView<Evenement> ListView;
    @FXML
    private Color x21;
    @FXML
    private Font x11;
    @FXML
    private JFXListView<String> listCat;
    @FXML
    private DatePicker date;
    @FXML
    private AnchorPane aujourdhui;
    @FXML
    private AnchorPane demain;
    @FXML
    private AnchorPane semaine;
    @FXML
    private JFXButton todayButton;
     EvenementService es= new EvenementService();
    private ObservableList<Evenement> data;
    private ObservableList<Evenement> dataa;
     
   public  static String s ="";
    
    Connection conn;
    
     public EventUSerController()
    {
        this.conn=MyDB.getinstance().getConnexion();
    }
    
    final ObservableList options = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        List<Evenement> LE = es.afficherEvenements();
        data = FXCollections.observableArrayList();
        Evenement event;
        LE.stream().forEach((j) -> {
            data.add(j);
        });
        System.out.println(data.size());
        ListView.setItems(data);
        ListView.setCellFactory(new Callback<ListView<Evenement>, ListCell<Evenement>>() {
            @Override
                public ListCell<Evenement> call(ListView<Evenement> param) {
                return new LsitViewEventCell();
            }
        } );
        
        ListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
            try {
                showEventDetails(newValue);
            } catch (IOException ex) {
                //Logger.getLogger(EventUSerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
  
        
        List<String> listCategorie= new ArrayList<>();
        listCategorie = es.listerNomsCategorie();
        listCat.getItems().addAll(listCategorie);
        
        tableEvent.setVisible(false);
        

    }    
    public void showEventDetails(Evenement event) throws IOException{
        
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(getClass().getResource("../Presentation/AjouterCommentaireEvent.fxml"));
            // Create the dialog Stage.
            Stage dialogStage = new Stage();            
            AnchorPane page = (AnchorPane) loader2.load();
          //  dialogStage.setTitle("Edit Service");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Set the person into the controller.
            AjouterCommentaireEventController controller = loader2.getController();
            //controller.setDialogStage(dialogStage);
            controller.setEvent(event);
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
    }
  
    
        @FXML
       public void chercherParDate(ActionEvent event)
    {
        tableEvent.setVisible(true);

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
 
        @FXML
    public void chercherEvenementDemain(ActionEvent event)
    {
        tableEvent.setVisible(true);
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
   public void chercherSemaine(ActionEvent event)
    {
       tableEvent.setVisible(true);
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
  public  void chercherDateJour() 
    {
        tableEvent.setVisible(true);
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


    @FXML
    public void affichagerCat(MouseEvent event) {
        tableEvent.setVisible(true);
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
        categorie.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
  
    }
    
}
